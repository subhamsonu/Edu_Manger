package co.edu.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.manager.entity.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

	
	public Admin findByEmail(String email);
}
