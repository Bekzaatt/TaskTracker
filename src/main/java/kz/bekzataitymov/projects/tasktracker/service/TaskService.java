package kz.bekzataitymov.projects.tasktracker.service;

import kz.bekzataitymov.projects.tasktracker.enums.Status;
import kz.bekzataitymov.projects.tasktracker.model.Task;
import kz.bekzataitymov.projects.tasktracker.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public Task getById(String id) {
        return taskRepository.findById(id).orElse(null);
    }

    public List<Task> getAll() {
        return taskRepository.findAll();
    }

    public List<Task> getAllByStatus(Status status) {
        return taskRepository.findAllByStatus(status);
    }

    public String addTask(String description) {
        LocalDateTime now = LocalDateTime.now();

        Task task = new Task(
                UUID.randomUUID().toString(),
                description,
                Status.TODO,
                now,
                now
        );

        taskRepository.save(task);
        return "Task added: " + task.getId();
    }

    public String updateTask(String id, String description) {
        Task task = getById(id);

        if (task == null) {
            return "Task not found";
        }

        task.setDescription(description);
        task.setUpdatedAt(LocalDateTime.now());

        taskRepository.save(task);
        return "Task updated: " + id;
    }

    public String deleteTask(String id) {
        Task task = getById(id);

        if (task == null) {
            return "Task not found";
        }

        taskRepository.delete(task);
        return "Task deleted: " + id;
    }

    public String markTaskAsInProgress(String id, Status status) {
        return updateStatus(id, status);
    }

    public String markTaskAsDone(String id, Status status) {
        return updateStatus(id, status);
    }

    private String updateStatus(String id, Status status) {
        Task task = getById(id);

        if (task == null) {
            return "Task not found";
        }

        task.setStatus(status);
        task.setUpdatedAt(LocalDateTime.now());

        taskRepository.save(task);
        return "Task status updated: " + id;
    }
}