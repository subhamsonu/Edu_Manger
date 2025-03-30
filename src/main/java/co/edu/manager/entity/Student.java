package co.edu.manager.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Table(name = "student")
@Entity
public class Student {

	@Id
	@GeneratedValue()
	private Long id;

	private String name;
	private String dateOfBirth;
	private String gender;
	@Column(unique = true, nullable = false)
	private String studentCode;

	private String email;
	private String mobileNumber;
	private String parentsName;

	@ManyToOne
	@JoinColumn(name = "role_id", nullable = false)
	private Role role;

	@PrePersist
	private void generateStudentCode() {
		this.studentCode = "EDU" + String.format("%03d", this.id);
	}
}
