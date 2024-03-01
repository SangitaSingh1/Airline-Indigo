package com.nt.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable{
	

   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private Long id;
   private String firstName;
   private String lastName;
   private String email;
   private String password;
   private Long roleId;
   private Boolean active;
public User(String firstName, String lastName, String email, String password) {
	super();
	this.firstName = firstName;
	this.lastName = lastName;
	this.email = email;
	this.password = password;
}
   
   
   
}
