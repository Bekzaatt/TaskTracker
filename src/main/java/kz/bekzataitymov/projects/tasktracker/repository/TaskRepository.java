package kz.bekzataitymov.projects.tasktracker.repository;

import kz.bekzataitymov.projects.tasktracker.enums.Status;
import kz.bekzataitymov.projects.tasktracker.model.Task;
import lombok.extern.slf4j.Slf4j;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.SerializationFeature;
import tools.jackson.databind.json.JsonMapper;
import tools.jackson.databind.node.ObjectNode;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
public class TaskRepository {
    private final ObjectMapper objectMapper = JsonMapper.builder()
            .enable(SerializationFeature.INDENT_OUTPUT)
            .build();

    private static final String PATH = Paths.get(System.getProperty("user.dir"), "task.json").toString();

    public TaskRepository(){
    }

    public Task getById(String id) {
        JsonNode jsonNode = objectMapper.readTree(new File(PATH));
        List<JsonNode> jsonNodes = objectMapper.convertValue(jsonNode, new TypeReference<>() {});

        for (JsonNode currentTaskNode : jsonNodes) {
            if (currentTaskNode.get("id").asText().equals(id)) {
                log.info("Equals");
                return this.objectMapper.treeToValue(currentTaskNode, Task.class);
            }
        }
        return null;
    }

    public List<Task> getAllByStatus(Status status) {
        JsonNode jsonNode = objectMapper.readTree(new File(PATH));
        List<Task> tasks = new ArrayList<>();
        for (JsonNode currentTaskNode : jsonNode) {
            if (currentTaskNode.get("status").asText().equalsIgnoreCase(status.name())) {
                tasks.add(this.objectMapper.convertValue(currentTaskNode, new TypeReference<>() {}));
            }
        }
        return tasks;
    }

    public String addTask(List<Task> tasks) {
        this.objectMapper.writeValue(new File(PATH), tasks);
        return "Task added successfully, id: " + tasks.getLast().getId();
    }

    public String updateTask(String id, List<Task> tasks) {
        objectMapper.writeValue(new File(PATH), tasks);
        return "Task updated successfully, id: " + id;
    }

    public String deleteTask(Task task) {
        ObjectNode objectNode = this.objectMapper.valueToTree(PATH);
        objectNode.remove(task.getId());
        objectNode.remove(task.getDescription());
        objectNode.remove(task.getStatus().name());
        objectNode.remove(task.getUpdatedAt().toString());
        objectNode.remove(task.getCreatedAt().toString());

        return "Task deleted successfully id: " + task.getId();
    }

    public List<Task> getAll() {
        JsonNode jsonNode = objectMapper.readTree(new File(PATH));
        return objectMapper.convertValue(jsonNode, new TypeReference<>() {});
    }
}
