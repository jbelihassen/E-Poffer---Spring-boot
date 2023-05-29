package com.offre.codingdojo.full_project_manager.controllers;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.offre.codingdojo.full_project_manager.models.UserModel;
import com.offre.codingdojo.full_project_manager.services.ProjectService;
import com.offre.codingdojo.full_project_manager.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.ValidationException;

@RestController
@CrossOrigin(origins ="http://localhost:4200")
public class HomeController {
	// Add once service is implemented:
	@Autowired
	private UserService userService;
	@Autowired
	private ProjectService projectService;
	//Api Registration
	@PostMapping("/api/register")
	public ResponseEntity<?> register(@RequestBody UserModel newUser) {
	    String tempEmail = newUser.getEmail();
	    String tempPassword = newUser.getPassword();

	    if (tempEmail == null || "".equals(tempEmail)) {
	        return ResponseEntity.badRequest().body("Email is required");
	    }else {
	    	UserModel user = userService.fetchUserBYEmail(tempEmail);
			if (user != null) {
				return ResponseEntity.badRequest().body("Email already exist!");
			}

	    }

	    if (tempPassword == null || "".equals(tempPassword)) {
	        return ResponseEntity.badRequest().body("Password is required");
	    }else {
	        String hashedPassword = BCrypt.hashpw(tempPassword, BCrypt.gensalt());
	        newUser.setPassword(hashedPassword);
	    }

	    try {
	        UserModel user = userService.createUser(newUser);
	        return ResponseEntity.ok(user);
	    } catch (ValidationException ex) {
	        return ResponseEntity.badRequest().body(ex.getMessage());
}
	}
	//Api Login
	@PostMapping("/api/login")
	public UserModel loginUser(@RequestBody UserModel newLogin) throws Exception {
	    String tempEmail = newLogin.getEmail();
	    String tempPass = newLogin.getPassword();
	    UserModel userObj = null;
	    if (tempEmail != null && tempPass != null) {
	        userObj = userService.fetchUserBYEmail(tempEmail);
	        if (userObj != null) {	        	
	        	if(!BCrypt.checkpw(newLogin.getPassword(), userObj.getPassword())) {
	        		throw new Exception("Invalid Credentials!");
	    		}
	        }
	    }
	    if (userObj == null) {
	        throw new Exception("Invalid Credentials!");
	    }
	    return userObj;
	}
	
	
	//find user
	@GetMapping("/api/users/{userId}")
	public UserModel findUser(@PathVariable Long userId) {
	    return userService.findUser(userId);
	}
	
	
//Api Delete user
	@DeleteMapping("/api/users/{userId}")
	public void deleteUser(@PathVariable Long userId) {
	    userService.deleteUser(userId);
	}

// Api Logout
	@PostMapping("/api/logout")
	public ResponseEntity<Object> logout(HttpSession session) {
		session.removeAttribute("userID");
		return new ResponseEntity<>(HttpStatus.OK);
	}
//Api Get All users
	@GetMapping("/api/users")
	public List<UserModel> getAllUsers() {
		return userService.allUsers();
	}
	
	


}
