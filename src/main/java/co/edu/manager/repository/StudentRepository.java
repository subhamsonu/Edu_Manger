package co.edu.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.manager.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
	
	public Student findByStudentCode(String studentCode);

}
