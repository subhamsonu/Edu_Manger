package co.edu.manager.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

public class Student {
	
	@Id
	@GeneratedValue()
	 private Long id;

	    private String name;
	    private String dateOfBirth;
	    private String gender;
	    private String studentCode;  

	    private String email;
	    private String mobileNumber;
	    private String parentsName;

}
