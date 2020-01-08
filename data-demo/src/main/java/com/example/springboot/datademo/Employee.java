package com.example.springboot.datademo;

import javax.persistence.*;

@Entity
@Table(name="TBL_EMPLOYEES")
public class Employee {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
     private long id;
     
     @Column(name="firstname")
     private String firstname;
     
     @Column(name="email", nullable=false, length=200)
     private String email;

	public Employee(Long id,String firstname,String email) {
		this.email = email;
		this.id = id;
		this.firstname = firstname;
	}

	    protected Employee() {

    }



	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstname=" + firstname + ", email=" + email + "]";
	}
     
}
