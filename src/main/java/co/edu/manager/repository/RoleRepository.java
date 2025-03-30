package co.edu.manager.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.manager.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	
	public Optional<Role> findByRoleName(String name);
}
