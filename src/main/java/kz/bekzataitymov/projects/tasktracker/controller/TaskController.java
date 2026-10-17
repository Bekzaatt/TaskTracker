package kz.bekzataitymov.projects.tasktracker.controller;

import kz.bekzataitymov.projects.tasktracker.enums.Status;
import kz.bekzataitymov.projects.tasktracker.model.Task;
import kz.bekzataitymov.projects.tasktracker.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    public List<Task> getAll() {
        return taskService.getAll();
    }

    @GetMapping("/{id}")
    public Task getById(@PathVariable String id) {
        return taskService.getById(id);
    }

    @GetMapping("/status/{status}")
    public List<Task> getAllByStatus(@PathVariable Status status) {
        return taskService.getAllByStatus(status);
    }

    @PostMapping
    public String addTask(@RequestBody Task task) {
        return taskService.addTask(task.getDescription());
    }

    @PutMapping("/{id}")
    public String updateTask(
            @PathVariable String id,
            @RequestBody Task task) {
        return taskService.updateTask(id, task.getDescription());
    }

    @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable String id) {
        return taskService.deleteTask(id);
    }
}