package co.edu.manager.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import co.edu.manager.entity.Admin;
import co.edu.manager.entity.Student;
import co.edu.manager.repository.AdminRepository;
import co.edu.manager.repository.StudentRepository;

@Service
public class UserDetail implements  UserDetailsService {
	
	private final AdminRepository adminRepo;
	private final StudentRepository studentRepo;
	
	

	public UserDetail(AdminRepository adminRepo, StudentRepository studentRepo) {
		super();
		this.adminRepo = adminRepo;
		this.studentRepo = studentRepo;
	}



	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		if (username == null || username.isEmpty()) {
            throw new UsernameNotFoundException("Username is empty");
        }
		
		Admin admin =  adminRepo.findByEmail(username);
		
		if(admin!=null) {
			return User.withUsername(admin.getEmail()).password(admin.getPassword()).roles("ADMIN").build();
		}
		else {
			Student student = studentRepo.findByStudentCode(username);
			return User.withUsername(student.getStudentCode()).password(student.getDateOfBirth()).roles("STUDENT").build();
		}
	}
		
	

}
