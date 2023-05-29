package com.offre.codingdojo.full_project_manager.repositories;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.offre.codingdojo.full_project_manager.models.UserModel;
@Repository
public interface UserRepository extends CrudRepository<UserModel, Long> {
	List<UserModel> findAll();

	UserModel findByEmail(String email);
	UserModel findByEmailAndPassword(String email, String password);
}
