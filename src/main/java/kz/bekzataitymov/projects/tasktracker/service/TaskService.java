package kz.bekzataitymov.projects.tasktracker.service;

import kz.bekzataitymov.projects.tasktracker.enums.Status;
import kz.bekzataitymov.projects.tasktracker.model.Task;
import kz.bekzataitymov.projects.tasktracker.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    private Task getById(String id) {
        return taskRepository.getById(id);
    }

    public List<Task> getAll() {
        return taskRepository.getAll() == null ? new ArrayList<>() : taskRepository.getAll();
    }

    public List<Task> getAllByStatus(Status status) {
        return taskRepository.getAllByStatus(status);
    }

    public String addTask(String description) {
        Task task = new Task(UUID.randomUUID().toString(), description, Status.TODO, LocalDateTime.now(), LocalDateTime.now());
        List<Task> tasks = this.getAll();
        tasks.add(task);
        return taskRepository.addTask(tasks);
    }

    public String updateTask(String id, String description) {
        List<Task> tasks = this.getAll();
        for (int i = 0; i < tasks.size(); i++){
            if(tasks.get(i).getId().equals(id)){
                Task updated = tasks.get(i);
                updated.setDescription(description);
                updated.setUpdatedAt(LocalDateTime.now());

                tasks.set(i, updated);
            }
        }
        return taskRepository.updateTask(id, tasks);
    }

    public String deleteTask(String id) {
        Task existedTask = this.getById(id);
        if (existedTask == null) {
            return null;
        }
        return taskRepository.deleteTask(existedTask);
    }

    public String markTaskAsInProgress(String id, Status inProgress){
        return taskRepository.updateTask(id, this.updateProcess(id, inProgress));
    }

    public String markTaskAsDone(String id, Status done){
        return taskRepository.updateTask(id, this.updateProcess(id, done));
    }

    private List<Task> updateProcess(String id, Status status){
        List<Task> tasks = this.getAll();
        for(int i = 0; i < tasks.size(); i++){
            if(tasks.get(i).getId().equals(id)){
                Task task = tasks.get(i);
                task.setStatus(status);
                task.setUpdatedAt(LocalDateTime.now());
                tasks.set(i, task);
            }
        }
        return tasks;
    }
}
