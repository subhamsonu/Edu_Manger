package co.edu.manager.utility;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import co.edu.manager.dto.AdminDto;
import co.edu.manager.entity.Admin;

public class UtilityService {

	
	PasswordEncoder passwordEncoder;
	
	public static Admin adminDtotoEntity(AdminDto dto){
		return new Admin().builder().name(dto.getName()).email(dto.getEmail().toLowerCase()).password(new BCryptPasswordEncoder().encode(dto.getPassword())).build();
	}
	
}
