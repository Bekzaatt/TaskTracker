package kz.bekzataitymov.projects.tasktracker.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import kz.bekzataitymov.projects.tasktracker.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"id", "description", "status", "createdAt", "updatedAt"})
@Entity
public class Task {
    @Id
    private String id;
    @Column(nullable = false)
    private String description;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
