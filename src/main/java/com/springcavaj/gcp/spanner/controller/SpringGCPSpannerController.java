/**
 * 
 */
package com.springcavaj.gcp.spanner.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springcavaj.gcp.spanner.model.User;
import com.springcavaj.gcp.spanner.service.SpringGCPSpannerService;


/**
 * @author c86am
 *
 */

@RestController
public class SpringGCPSpannerController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SpringGCPSpannerController.class);
	
	private final SpringGCPSpannerService gcpSpannerService;
	
	@Autowired
	public SpringGCPSpannerController(SpringGCPSpannerService gcpSpannerService) {
		this.gcpSpannerService = gcpSpannerService;
	}
	
	
	@GetMapping("/allUsers")
    public List<User> getAllUserData() {
		LOGGER.info("getAllUserData() -> All Data are fetched");
		return gcpSpannerService.getAllUsers();
    }
	
	@PostMapping("/saveUser")
    public User saveUserData(@RequestBody User user) {
        LOGGER.info("saveUserData() -> New Record of User saved");
        return gcpSpannerService.saveUser(user);
    }
	
	@GetMapping("/getUser/{id}")
    public User getUserDataById(@PathVariable(value = "id") String id) {
		Long userId = Long.parseLong(id);
    	User user = gcpSpannerService.getUserById(userId);
    	LOGGER.info("getUserDataById() -> Fetch the User Detail : {}", user);
        return user;
    }
	
	@PutMapping("/updateUser/{id}")
    public User updateUserData(@PathVariable(value = "id") String id, @RequestBody User user) {
        LOGGER.info("SpringGCPSqlStorageController -> updateUser() -> Update the existing User Record");
        Long userId = Long.parseLong(id);
        return gcpSpannerService.updateUser(user, userId);
    }
	
	@DeleteMapping("/deleteUser/{id}")
    public void deleteUser(@PathVariable(value = "id") String id) {
		Long userId = Long.parseLong(id);
		gcpSpannerService.deleteUser(userId);
    	LOGGER.info("deleteUser() -> User deleted for userId : {}", userId);
    }
}
