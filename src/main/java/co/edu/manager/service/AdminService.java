package co.edu.manager.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import co.edu.manager.dto.AdminDto;
import co.edu.manager.entity.Admin;
import co.edu.manager.entity.Role;
import co.edu.manager.repository.AdminRepository;
import co.edu.manager.repository.RoleRepository;
import co.edu.manager.utility.UtilityService;
import jakarta.transaction.Transactional;

@Service
public class AdminService {

	private AdminRepository adminRepo;
	private RoleRepository roleRepo;
	
	
	public AdminService(AdminRepository adminRepo, RoleRepository roleRepo) {
		super();
		this.adminRepo = adminRepo;
		this.roleRepo = roleRepo;
	}


	@Transactional
	public void addAdminUser(AdminDto adminDto) {
		
		Admin admin = UtilityService.adminDtotoEntity(adminDto);
		  Optional<Role> role = roleRepo.findByRoleName("ADMIN");
	        role.ifPresent(admin::setRole);
	        
	        adminRepo.save(admin);

	}
}
