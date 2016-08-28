package com.simpledatabasedrivenbatch.person.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.simpledatabasedrivenbatch.person.Person;
import com.simpledatabasedrivenbatch.person.PersonDao;

@Repository
public class PersonDaoImpl implements PersonDao {

	private final String INSERT = "INSERT INTO PERSON (NAME, STATUS, HIGHT) VALUES (?,?,?)";

	private final String UPDATE = "UPDATE PERSON SET NAME=?, STATUS=?, HIGHT=? WHERE ID = ?";

	private final String FIND_BY_ID = "SELECT ID, NAME, STATUS, HIGHT FROM PERSON WHERE ID = ?";

	private final DataSource dataSource;

	@Autowired
	public PersonDaoImpl(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}

	@Override
	public Person create(Person person) {
		try {

			PreparedStatement ps = getPreparedStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			mapPersonToStatementForCreation(person, ps);
			executeSQL(ps);
			Long id = extractGeneratedID(ps.getGeneratedKeys());
			person.setId(id);
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return person;
	}

	@Override
	public Person findByID(Long personID) {
		Person foundPerson = new Person();
		try {
			PreparedStatement ps = getPreparedStatement(FIND_BY_ID, Statement.NO_GENERATED_KEYS);
			mapPersonIdToStatement(personID, ps);
			ResultSet rs = ps.executeQuery();
			foundPerson = mapResultSetToPerson(rs);
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return foundPerson;
	}

	@Override
	public Person update(Person person) {
		try {
			PreparedStatement ps = getPreparedStatement(UPDATE, Statement.NO_GENERATED_KEYS);
			mapPersonToStatementForUpdate(person, ps);
			executeSQL(ps);
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return person;
	}

	private void mapPersonToStatementForCreation(Person person, PreparedStatement ps) throws SQLException {
		ps.setString(1, person.getName());
		ps.setString(2, person.getStatus().toString());
		ps.setDouble(3, person.getHight());
	}
	
	private void mapPersonToStatementForUpdate(Person person, PreparedStatement ps) throws SQLException {
		mapPersonToStatementForCreation(person, ps);
		ps.setLong(4, person.getId());
	}

	private void mapPersonIdToStatement(Long personId, PreparedStatement ps) throws SQLException {
		ps.setLong(1, personId);
	}

	private Person mapResultSetToPerson(ResultSet rs) throws SQLException {
		Person person = null;
		if (rs != null && rs.next()) {
			person = new Person();
			person.setId(rs.getLong(1));
			person.setName(rs.getString(2));
			person.setStatus(rs.getString(3));
			person.setHight(rs.getDouble(4));
		}
		rs.close();
		return person;
	}

	private Long extractGeneratedID(ResultSet generatedKeysResult) throws SQLException {
		Long id = null;
		if (generatedKeysResult != null && generatedKeysResult.next()) {
			id = generatedKeysResult.getLong(1);
		}
		return id;
	}

	private void executeSQL(PreparedStatement ps) throws SQLException {
		ps.executeUpdate();
		ps.getConnection().commit();
	}

	private Connection prepareConnection() throws SQLException {
		Connection con = dataSource.getConnection();
		con.setAutoCommit(false);
		return con;
	}

	private PreparedStatement getPreparedStatement(String SQL, int returnGeneratedKeys) throws SQLException {
		Connection con = prepareConnection();
		PreparedStatement ps = con.prepareStatement(SQL, returnGeneratedKeys);
		return ps;
	}

}
