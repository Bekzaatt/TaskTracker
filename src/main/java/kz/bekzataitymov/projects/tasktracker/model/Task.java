package kz.bekzataitymov.projects.tasktracker.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import kz.bekzataitymov.projects.tasktracker.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@JsonPropertyOrder({"id", "description", "status", "createdAt", "updatedAt"})
public class Task {
    private String id;
    private String description;
    private Status status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Task(){}
}
