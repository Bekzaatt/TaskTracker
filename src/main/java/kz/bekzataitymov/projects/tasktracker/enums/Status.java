package kz.bekzataitymov.projects.tasktracker.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Status {
    TODO, IN_PROGRESS, DONE;

    public static Status fromString(String taskStatus){
        if(taskStatus.equalsIgnoreCase("in-progress")){
            return IN_PROGRESS;
        }

        return Status.valueOf(taskStatus);

    }
}
