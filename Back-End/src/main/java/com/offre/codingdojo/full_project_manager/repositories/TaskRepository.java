package com.offre.codingdojo.full_project_manager.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.offre.codingdojo.full_project_manager.models.ReviewModel;
@Repository
public interface TaskRepository extends CrudRepository<ReviewModel, Long> {
	List<ReviewModel> findAll();


}
