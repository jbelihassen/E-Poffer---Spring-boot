package com.offre.codingdojo.full_project_manager.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.offre.codingdojo.full_project_manager.models.UserModel;
import com.offre.codingdojo.full_project_manager.repositories.UserRepository;



@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	
	 // returns all  users
    public List<UserModel> allUsers() {
        return userRepository.findAll();
    }
     
    // creates a user
    public UserModel createUser(UserModel user) {
         return userRepository.save(user);
    }
    public  UserModel fetchUserBYEmail (String email) {
    	return userRepository.findByEmail(email);
    }
    
    public UserModel fetchUserBYEmailAndPass(String email , String password) {
    	return userRepository.findByEmailAndPassword(email, password);
    	
    }
    
    // find on user by id
    public UserModel findUser(Long id) {
        Optional<UserModel> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            return null;
        }
    }
    // Delete User
	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}


	
	
	
	}




