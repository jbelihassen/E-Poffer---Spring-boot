package com.offre.codingdojo.full_project_manager.controllers;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.offre.codingdojo.full_project_manager.models.ImageModel;
import com.offre.codingdojo.full_project_manager.models.ProjectModel;
import com.offre.codingdojo.full_project_manager.models.ReviewModel;
import com.offre.codingdojo.full_project_manager.models.UserModel;
import com.offre.codingdojo.full_project_manager.services.ProjectService;
import com.offre.codingdojo.full_project_manager.services.TaskService;
import com.offre.codingdojo.full_project_manager.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins ="http://localhost:4200")
@RequestMapping("/api")
public class ProjectController {
	@Autowired
	private ProjectService projectService;
	@Autowired
	private UserService userService;
	@Autowired
	private TaskService taskService;

	// ===Api to Add new project
	@PostMapping(value={"/projects/new"}, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	public ProjectModel addNewProject(@RequestPart("project") ProjectModel project,
									@RequestPart("imageFile") MultipartFile[] file) {
		try {
			Set<ImageModel> images = uploadImage(file);
			project.setProjectImages(images);
			return projectService.createProject(project);
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return null;
			
		}

	}
	
	public Set<ImageModel> uploadImage(MultipartFile[] multipartFiles) throws IOException{
		Set<ImageModel> imageModels = new HashSet<>();
		for(MultipartFile file : multipartFiles) {
			ImageModel imageModel = new ImageModel(
					file.getOriginalFilename(),
					file.getContentType(),
					file.getBytes());
			imageModels.add(imageModel);
		}
		return imageModels;
	}
	// Api to show all projects
	@GetMapping("/projects")
	public List<ProjectModel> getAllProjects(@RequestParam(defaultValue="") String searchKey){
		return projectService.allProjects(searchKey);
	}

	//Delete Project
	@DeleteMapping("delete/{id}")
    public void destroy(@PathVariable("id") Long id) {
        projectService.deleteProject(id);
    }
	// API to show project
    @GetMapping("project/{id}")
    public ProjectModel showProject(@PathVariable("id") Long id) {
        ProjectModel project = projectService.findProject(id);
        return project;
    }
  //API to get the joinedProjects
    @GetMapping("/user/JoinedProjects/{id}")
    public List<ProjectModel> showUserJoinedProjects(@PathVariable("id") Long id) {
        UserModel user = userService.findUser(id);
        return user.getUserProjects();
    }
    //API to get the UserProjects
    @GetMapping("/user/projects/{id}")
    public List<ProjectModel> showUserProjects(@PathVariable("id") Long id) {
        UserModel user = userService.findUser(id);
        return user.getProjects();
    }
 // API to join project
    @PostMapping("/projects/join/{projectId}")
    public ProjectModel joinProject(@PathVariable("projectId") Long projectId, @RequestBody Long userId) {

        // find user from the DB using id
        UserModel loggedUser = userService.findUser(userId);

        // grab the selected project from the DB
        ProjectModel thisProject = projectService.findProject(projectId);

        // make many to many connection
        thisProject.getJoinedUsers().add(loggedUser);

        // save the project to DB
        projectService.updateProject(thisProject);

        // return the updated project model
        return thisProject;
    }
    //Api Remove Join
  //REMOVE
    @PostMapping("/projects/leave/{projectId}")
    public ProjectModel removeFromList(@PathVariable("projectId")Long projectId
    		, @RequestBody Long userId) {

        // find user from the DB using id
        UserModel loggedUser=userService.findUser(userId);

        // grab the selected project from the DB
        ProjectModel thisProject = projectService.findProject(projectId);

        // make many to many connection
        thisProject.getJoinedUsers().remove(loggedUser);

        // don't forget to save to DB
        projectService.updateProject(thisProject);


        return thisProject;
    }
    
// API to update Project
    @PutMapping("/project/{id}")
    public ResponseEntity<ProjectModel> updateProjectModel(@PathVariable("id") Long id, @Valid @RequestBody ProjectModel project,
            BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().build();
        } else {
            ProjectModel existingProjectModel = projectService.findProject(id);
            if (existingProjectModel == null) {
                return ResponseEntity.notFound().build();
            } else {
                project.setPosted_user(existingProjectModel.getPosted_user());
                project.setProjectImages(existingProjectModel.getProjectImages());
                projectService.updateProject(project);
                return ResponseEntity.ok(project);
            }
        }
     }
    //Api to add task
  
    @PostMapping("/reviews")
    public ReviewModel addTask(@RequestBody ReviewModel task) {
        return taskService.createTask(task);
    }
    //Api to get project reviews
    @GetMapping("/reviews/{projectId}")
    public List<ReviewModel> showProjectReviews(@PathVariable("projectId") Long id){
        ProjectModel project = projectService.findProject(id);
        return project.getReviews();
    }

}
