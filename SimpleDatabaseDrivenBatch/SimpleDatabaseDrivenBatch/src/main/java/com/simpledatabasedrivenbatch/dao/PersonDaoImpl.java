package com.simpledatabasedrivenbatch.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.simpledatabasedrivenbatch.domain.Person;

@Repository
public class PersonDaoImpl implements PersonDao {
	
	private final String INSERT = "INSERT INTO PERSON (NAME, STATUS, HIGHT) VALUES (?,?,?)";
	
	private final DataSource dataSource;
	
	@Autowired
	public PersonDaoImpl(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}

	@Override
	public void savePerson(Person person) {
		try {
			Connection con = prepareConnection();
			PreparedStatement ps = con.prepareStatement(INSERT);
			mapPersonToStatement(person, ps);
			ps.executeUpdate();
			
			//System.out.println("Person Generated Id:"+id);
			
			con.commit();
			ResultSet result = ps.getGeneratedKeys();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void mapPersonToStatement(Person person, PreparedStatement ps) throws SQLException {
		ps.setString(1, person.getName());
		ps.setString(2, person.getStatus().toString());
		ps.setDouble(3, person.getHight());
	}

	private Connection prepareConnection() throws SQLException {
		Connection con = dataSource.getConnection();
		con.setAutoCommit(false);
		return con;
	}

}
