package com.nt.dao;

import java.util.List;

import com.nt.model.Location;

public interface LocationDao {
    
	
	boolean addLocation(Location location);
	public boolean updateLocation(Location location);
	public Location getById(Long id);
	public List<Location> getAllLocations();
	public boolean deleteLocation(Long id);
	public boolean isLocationExists(String name);
}
