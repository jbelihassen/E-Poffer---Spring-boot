package com.offre.codingdojo.full_project_manager.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.offre.codingdojo.full_project_manager.models.ProjectModel;
import com.offre.codingdojo.full_project_manager.models.UserModel;
@Repository
public interface ProjectRepository extends CrudRepository<ProjectModel, Long> {
	List<ProjectModel> findAll();
	List<ProjectModel> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String key1,String key2);


}
