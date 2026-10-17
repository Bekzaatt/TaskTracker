package kz.bekzataitymov.projects.tasktracker.repository;

import kz.bekzataitymov.projects.tasktracker.enums.Status;
import kz.bekzataitymov.projects.tasktracker.model.Task;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
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

public interface TaskRepository extends JpaRepository<Task, String> {

    List<Task> findAllByStatus(Status status);

    @Override
    <S extends Task> S save(S entity);
}
