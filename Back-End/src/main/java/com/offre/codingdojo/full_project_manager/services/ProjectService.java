package com.offre.codingdojo.full_project_manager.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.offre.codingdojo.full_project_manager.models.ProjectModel;
import com.offre.codingdojo.full_project_manager.models.UserModel;
import com.offre.codingdojo.full_project_manager.repositories.ProjectRepository;

@Service
public class ProjectService {
	@Autowired
	private ProjectRepository projectRepository;
	 // returns all the projects
    public List<ProjectModel> allProjects(String searchKey) {
    	if(searchKey.equals("")) {
        return projectRepository.findAll();
    }else {
    	return projectRepository.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(searchKey, searchKey);}
    }
    // creates a project
    public ProjectModel createProject(ProjectModel p) {
        return projectRepository.save(p);
    }
    // retrieves a project
    public ProjectModel findProject(Long id) {
        Optional<ProjectModel> optionalProject = projectRepository.findById(id);
        if(optionalProject.isPresent()) {
            return optionalProject.get();
        } else {
            return null;
        }
    }
    // Delete Project 
	public void deleteProject(Long id) {
		projectRepository.deleteById(id);
	}
	// Edit Project
	public ProjectModel updateProject(ProjectModel project) {
        return projectRepository.save(project);
		
	}
	//

	

}
