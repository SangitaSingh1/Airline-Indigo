package com.nt.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.nt.dao.LocationDao;
import com.nt.model.Location;
import com.nt.util.DBUtil;

public class LocationDaoImpl implements LocationDao {

	@Override
	public boolean addLocation(Location location) {

		String query = "INSERT INTO location (name, code, active) VALUES (?, ?, ?)";

		try (Connection connection = DBUtil.getConnection();
				PreparedStatement pt = connection.prepareStatement(query)) {

			pt.setString(1, location.getName());
			pt.setString(2, location.getCode());
			pt.setBoolean(3, true);

			int rowsAffected = pt.executeUpdate();

			return rowsAffected > 0;
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return false;

	}

	@Override
	public boolean updateLocation(Location location) {

		String query = "UPDATE location SET name=?, code=?, active=? WHERE id=?";

		try (Connection connection = DBUtil.getConnection();
				PreparedStatement pt = connection.prepareStatement(query);) {

			pt.setString(1, location.getName());
			pt.setString(2, location.getCode());
			pt.setBoolean(3, true);

			pt.setLong(4, location.getId());
			int rowsupdated = pt.executeUpdate();

			return rowsupdated > 0;
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Location getById(Long id) {
		String query = "SELECT name,code,active FROM location WHERE id=?";
		Location location = null;
		try (Connection connection = DBUtil.getConnection();
				PreparedStatement pt = connection.prepareStatement(query)) {
			pt.setLong(1, id);
			ResultSet rs = pt.executeQuery();
			while (rs.next()) {
				String name = rs.getString("name");
				String code = rs.getString("code");
				Boolean active = rs.getBoolean("active");

				location = new Location(id, name, code, active);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return location;
	}

	@Override
	public List<Location> getAllLocations() {
		String query = "SELECT * FROM location";
		List<Location> locations = new ArrayList<Location>();
		try (Connection connection = DBUtil.getConnection();
				PreparedStatement pt = connection.prepareStatement(query)) {
			ResultSet rs = pt.executeQuery();
			while (rs.next()) {
				Long id = rs.getLong("id");
				String name = rs.getString("name");
				String code = rs.getString("code");
				Boolean active = rs.getBoolean("active");
				locations.add(new Location(id, name, code, active));
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return locations;
	}

	@Override
	public boolean deleteLocation(Long id) {

		String query = "DELETE FROM location WHERE id=?";
		try (Connection con = DBUtil.getConnection(); PreparedStatement pt = con.prepareStatement(query)) {
			pt.setLong(1, id);
			int rowsdeleted = pt.executeUpdate();

			return rowsdeleted > 0;
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean isLocationExists(String name) {
	    try (Connection connection = DBUtil.getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(*) FROM location WHERE name = ?")) {

	        preparedStatement.setString(1, name);
	        

	        try (ResultSet resultSet = preparedStatement.executeQuery()) {
	            if (resultSet.next()) {
	                int count = resultSet.getInt(1);
	                return count > 0;
	            }
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return false;
	}

}
