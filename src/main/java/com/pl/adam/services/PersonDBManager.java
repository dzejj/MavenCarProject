package com.pl.adam.services;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.pl.adam.projectfiles.Person;

public class PersonDBManager {

	private Connection conn;
	
	private Statement stmt;
	
	private PreparedStatement addPersonStmt;
	private PreparedStatement getPersonsStmt;
	
	
	public PersonDBManager()
	{
		try {
			Properties props=new Properties();
			
			try {
				props.load(ClassLoader.getSystemResourceAsStream("com/pl/adam/jdbc.properties"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			conn= DriverManager.getConnection(props.getProperty("url"));
					
			stmt=conn.createStatement();
			boolean personTableExists=false;
			
			ResultSet rs= conn.getMetaData().getTables(null, null, null, null);
			
			while(rs.next())
			{
				if("Person".equalsIgnoreCase(rs.getString("TABLE_NAME")))
					{
						personTableExists=true;
						break;
					}
			}
			
			
			if(!personTableExists)
			{
				stmt.executeUpdate("" + //stm.executeQuery("...")
					"CREATE TABLE person(" +
					"id bigint GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY," +
					"name varchar(20)," +
					"" +
					")");
			}
			
			addPersonStmt=conn.prepareStatement("" +
					"INSERT INTO person (name) VALUES (?)" +
					"");
			
			getPersonsStmt=conn.prepareStatement("" +
					"SELECT * FROM person" +
					"");
			
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	public void addPerson(Person p)
	{
		try {
			addPersonStmt.setString(1, p.getName());
			
			addPersonStmt.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	
	}
	
	public List<Person> getAllPersons()
	{
		List<Person> persons=new ArrayList<Person>();
		
		try {
			ResultSet rs= getPersonsStmt.executeQuery();
			
			while(rs.next())
			{
				persons.add(new Person(rs.getString("name")));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return persons;
	}
	
	
}
