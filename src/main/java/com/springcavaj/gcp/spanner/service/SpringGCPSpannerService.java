/**
 * 
 */
package com.springcavaj.gcp.spanner.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.cloud.spanner.DatabaseClient;
import com.google.cloud.spanner.Mutation;
import com.google.cloud.spanner.ResultSet;
import com.google.cloud.spanner.Statement;
import com.google.cloud.spanner.Struct;
import com.springcavaj.gcp.spanner.model.User;

/**
 * @author c86am
 *
 */
@Service
public class SpringGCPSpannerService {
	private Logger LOGGER = LoggerFactory.getLogger(SpringGCPSpannerService.class);
	
	private final DatabaseClient databaseClient;
	
	@Autowired
	public SpringGCPSpannerService(DatabaseClient databaseClient) {
		this.databaseClient = databaseClient;
	}
	
	public User saveUser(User user) {
		LOGGER.info("saveUser() -> User Details : {}", user);
		String SQL = "INSERT INTO user (user_id, name, mobile_no) VALUES ('" + user.getUserId() + "', '" + user.getName() + "', '" + user.getMobileNo() + "')";
		LOGGER.info("Insert SQL Query : {}", SQL);
		Mutation mutation = Mutation.newInsertBuilder("user").set("user_id").to(user.getUserId()).set("name").to(user.getName()).set("mobile_no").to(user.getMobileNo()).build();
		Iterable<Mutation> iterable = Collections.singleton(mutation);
		databaseClient.writeAtLeastOnce(iterable);
		return user;
	}
	
	public List<User> getAllUsers() {
		LOGGER.info("getAllUsers() -> Method invoked");
		List<User> users = new ArrayList<>();
		String SQL = "SELECT user_id, name, mobile_no FROM user";
		LOGGER.info("Select all SQL Query : {}", SQL);
		Statement statement = Statement.of(SQL);
		ResultSet resultSet = databaseClient.singleUse().executeQuery(statement);
		while(resultSet.next()) {
			Struct struct = resultSet.getCurrentRowAsStruct();
			Long userId = struct.getLong("user_id");
			String name = struct.getString("name");
			String mobileNo = struct.getString("mobile_no");
			User user = new User();
			user.setUserId(userId);
			user.setName(name);
			user.setMobileNo(mobileNo);
			users.add(user);
			
		}
		return users;
	}
	
	public User getUserById(Long userId) {
		LOGGER.info("getUserById() -> Get user information for ID : {}", userId);
		String SQL = "SELECT user_id, name, mobile_no FROM user WHERE user_id = " + userId;
		LOGGER.info("Select SQL Query : {}", SQL);
		Statement statement = Statement.of(SQL);
		User user = new User();
		ResultSet resultSet = databaseClient.singleUse().executeQuery(statement);
		while(resultSet.next()) {
			Struct struct = resultSet.getCurrentRowAsStruct();
			Long retrieveUserId = struct.getLong("user_id");
			String name = struct.getString("name");
			String mobileNo = struct.getString("mobile_no");
			user.setUserId(retrieveUserId);
			user.setName(name);
			user.setMobileNo(mobileNo);
		}
		return user;
	}
	
	public User updateUser(User user, Long userId) {
		LOGGER.info("updateUser() -> Get user information for Id : {}", userId);
		String SQL = "UPDATE user SET name = '" + user.getName() + "', mobile_no = '" + user.getMobileNo() + "' WHERE user_id = " + userId;
		LOGGER.info("Update SQL Query : {}", SQL);
		Statement statement = Statement.of(SQL);
		Long lowerBounbRow = databaseClient.executePartitionedUpdate(statement);
		LOGGER.info("Lower Bound Row : {}", lowerBounbRow);
		return user;
	}
	
	public void deleteUser(Long userId) {
		LOGGER.info("deleteUser() -> Get user information for Id : {}", userId);
		String SQL = "DELETE FROM user WHERE user_id = @valueOfuserId";
		LOGGER.info("Delete SQL Query : {}", SQL);
		Statement statement = Statement.newBuilder(SQL)
			    .bind("valueOfuserId").to(userId) // Bind the value to the parameter
			    .build();
		databaseClient.executePartitionedUpdate(statement);
	}
}
