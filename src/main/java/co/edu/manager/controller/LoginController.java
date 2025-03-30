package co.edu.manager.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.manager.dto.AdminDto;
import co.edu.manager.dto.AdminLoginRequestDto;
import co.edu.manager.dto.AuthResponse;
import co.edu.manager.dto.StudentLoginrequestDto;
import co.edu.manager.security.JwtUtil;
import co.edu.manager.service.AdminService;
import co.edu.manager.service.UserDetail;

@RestController
@RequestMapping("/edu-api/v1/log")
public class LoginController {

	private final AuthenticationManager authManager;
	private final JwtUtil jwtUtil;
	private final UserDetail userDetailsService;
	private final PasswordEncoder passwordEncoder;
	private AdminService adminService;

	
	public LoginController(AuthenticationManager authManager, JwtUtil jwtUtil, UserDetail userDetailsService,
			PasswordEncoder passwordEncoder, AdminService adminService) {
		super();
		this.authManager = authManager;
		this.jwtUtil = jwtUtil;
		this.userDetailsService = userDetailsService;
		this.passwordEncoder = passwordEncoder;
		this.adminService = adminService;
	}

	@PostMapping("/admin/login")
	private ResponseEntity<?> adminLogin(@RequestBody AdminLoginRequestDto request) {
		authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword()));
		UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUserName());
		String token = jwtUtil.generateToken(userDetails);
		return new ResponseEntity(new AuthResponse(token), HttpStatus.OK);
	}
	
	@PostMapping("/student/login")
	private ResponseEntity<?> studentLogin(@RequestBody  StudentLoginrequestDto request) {
		
		UserDetails userDetails = userDetailsService.loadUserByUsername(request.getStudentCode());
		if(passwordEncoder.matches(request.getDateOfBirth() ,userDetails.getPassword())) {
			String token = jwtUtil.generateToken(userDetails);
			return new ResponseEntity(new AuthResponse(token), HttpStatus.OK);
		}
		else {
			return new ResponseEntity("Invalid Credentials", HttpStatus.UNAUTHORIZED);
		}
	}
	
	
	@PostMapping("/add/admin")
	private ResponseEntity<?> addAdminUser(@RequestBody  AdminDto admin) {
		
	adminService.addAdminUser(admin);
	
	return new ResponseEntity("Admin Created Successfully",HttpStatus.CREATED);
	}

}
