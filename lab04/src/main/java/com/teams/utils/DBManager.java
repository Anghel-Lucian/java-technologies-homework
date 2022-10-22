package com.teams.utils;

import java.sql.*;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;

import com.teams.entities.City;
import com.teams.entities.Team;

@Stateless
public class DBManager {
	final static String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	final static String DB_URL = "jdbc:mysql://127.0.0.1:3306/league?useSSL=false";
	final static String DB_USER = "root";
	final static String DB_PASS = "password";
	
	@Resource(mappedName = "jdbc/league")
	private DataSource ds;
	
	public Connection createConnection() throws ClassNotFoundException, SQLException {
		Class.forName(JDBC_DRIVER);
		
		Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
//		Connection con = this.ds.getConnection(DB_USER, DB_PASS);
		
		return con;
	}
	
	public void addCity(City city) throws SQLException, ClassNotFoundException {
		String query = "INSERT INTO league.city VALUES ('" + city.getId() + "', '" + city.getName() + "');";
		Connection con = this.createConnection();
		Statement stmt = con.createStatement();
		stmt.executeUpdate(query);
		
		stmt.close();
		con.close();
	}
	
	public City getCity(String id) throws SQLException, ClassNotFoundException {
		Connection con = this.createConnection();
		String query = "SELECT city_name FROM league.city WHERE city_id='" + id + "';";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		String cityName = null;
		
		while(rs.next()) {
			cityName = rs.getString("city_name");
		}
		
		return new City(id, cityName);
	}
	
	public void addTeam(Team team) throws SQLException, ClassNotFoundException {
		String query = "INSERT INTO league.team VALUES ('" + team.getId() + "', '" + team.getName() + "', '" + team.getFoundingDate() + "', '" + team.getCity() + "', '" + team.getCityName() + "');";
		Connection con = this.createConnection();
		Statement stmt = con.createStatement();
		stmt.executeUpdate(query);
		
		stmt.close();
		con.close();
	}
	
	public ArrayList<City> getCities() throws SQLException, ClassNotFoundException {
		Connection con = this.createConnection();
		ArrayList<City> cities = new ArrayList<City>();
		String query = "SELECT * FROM league.city;";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		
		while(rs.next()) {
			String id = rs.getString("city_id");
			String name= rs.getString("city_name"); 
			
			City city = new City(id, name);
			
			cities.add(city);
		}
		
		stmt.close();
		con.close();
		
		return cities;
	}
	
	public ArrayList<Team> getTeams() throws SQLException, ClassNotFoundException {
		Connection con = this.createConnection();
		ArrayList<Team> teams = new ArrayList<Team>();
		String query = "SELECT * FROM league.team;";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		
		while(rs.next()) {
			String id = rs.getString("team_id");
			String name = rs.getString("team_name");
			String foundingDate = rs.getString("team_founding_date");
			String city = rs.getString("team_city");
			String cityName = rs.getString("team_city_name");
			
			Team team = new Team(id, name, foundingDate, city, cityName);
			
			teams.add(team);
		}
		
		stmt.close();
		con.close();
		
		return teams;
	}
	
	public boolean cityExists(String name) throws ClassNotFoundException, SQLException {
		Connection con = this.createConnection();
		String query = "SELECT city_name FROM league.city WHERE city_name='" + name + "';";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		
		if(!rs.next()) {
			stmt.close();
			con.close();
			return false;
		};
		
		stmt.close();
		con.close();
		return true;
	}
	
	public boolean teamExists(String name, String foundingDate, String city) throws ClassNotFoundException, SQLException {
		Connection con = this.createConnection();
		String query = "SELECT team_name FROM league.team WHERE team_name='" + name + "' AND team_founding_date='" + foundingDate + "' AND team_city='" + city + "';";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		
		if(!rs.next()) {
			stmt.close();
			con.close();
			return false;
		};
		
		stmt.close();
		con.close();
		return true;
	}
	
	public void updateCity(City city) throws ClassNotFoundException, SQLException {
		System.out.print("UPDATING CITY");
		System.out.print(city.getId());
		System.out.print(city.getName());
		Connection con = this.createConnection();
		String query = "UPDATE league.city SET city_name='" + city.getName() + "';";
		Statement stmt = con.createStatement();
		stmt.executeUpdate(query);
		
		stmt.close();
		con.close();
	}
}
