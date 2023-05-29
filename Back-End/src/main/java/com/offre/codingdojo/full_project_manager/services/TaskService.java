package com.offre.codingdojo.full_project_manager.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.offre.codingdojo.full_project_manager.models.ReviewModel;
import com.offre.codingdojo.full_project_manager.repositories.TaskRepository;

@Service
public class TaskService {
	@Autowired
	private TaskRepository taskRepository;
	 // returns all the tasks
    public List<ReviewModel> allTasks() {
        return taskRepository.findAll();
    }
    // creates a task
    public ReviewModel createTask(ReviewModel t) {
        return taskRepository.save(t);
    }
    // retrieves a task
    public ReviewModel findTask(Long id) {
        Optional<ReviewModel> optionalTask = taskRepository.findById(id);
        if(optionalTask.isPresent()) {
            return optionalTask.get();
        } else {
            return null;
        }
    }
    // Delete Task 
	public void deleteTask(Long id) {
		taskRepository.deleteById(id);
	}
	// Edit Task
	public ReviewModel updateTask(ReviewModel task) {
        return taskRepository.save(task);
		
	}
	//
	

}
