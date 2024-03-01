package com.nt.model;



import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {
   private Long id;
   private String departure;
   private String arrival;
   private Date date;
   private int seats;
   private Boolean active;
public Ticket(String departure, String arrival, Date date, int seats, Boolean active) {
	super();
	this.departure = departure;
	this.arrival = arrival;
	this.date = date;
	this.seats = seats;
	this.active = active;
}
   
   
}
