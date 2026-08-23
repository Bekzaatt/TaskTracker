package kz.bekzataitymov.projects.tasktracker.controller;

import kz.bekzataitymov.projects.tasktracker.enums.Status;
import kz.bekzataitymov.projects.tasktracker.model.Task;
import kz.bekzataitymov.projects.tasktracker.repository.TaskRepository;
import kz.bekzataitymov.projects.tasktracker.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Scanner;

@Slf4j
public class TaskController {

    public static void main(String[] args) {
        TaskRepository taskRepository = new TaskRepository();
        TaskService taskService = new TaskService(taskRepository);

        if (args.length == 0) {
            log.warn("Write command line");
            return;
        }

        switch (args[0]) {
            case "add" -> System.out.println(taskService.addTask(args[1]));
            case "update" -> System.out.println(taskService.updateTask(args[1], args[2]));
            case "delete" -> System.out.println(taskService.deleteTask(args[1]));
            case "mark-in-progress" -> System.out.println(taskService.markTaskAsInProgress(args[1], Status.IN_PROGRESS));
            case "mark-done" -> System.out.println(taskService.markTaskAsDone(args[1], Status.DONE));
            case "list" -> {
                List<Task> tasks = args.length == 1 ? taskService.getAll() : taskService.getAllByStatus(Status.fromString(args[1].toUpperCase()));
                for (Task task : tasks) {
                    System.out.println(task);
                }
            }
        }
    }
}
