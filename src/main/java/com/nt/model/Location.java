package com.nt.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Location {
	private Long id;
	private String name;
	private String code;
	private Boolean active;
	public Location(String name, String code, Boolean active) {
		super();
		this.name = name;
		this.code = code;
		this.active = active;
	}
	

}
