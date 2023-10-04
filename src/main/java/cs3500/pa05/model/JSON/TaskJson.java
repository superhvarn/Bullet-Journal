package cs3500.pa05.model.JSON;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a task in Json format
 *
 * @param name of task
 * @param desc description of task
 * @param day of task
 * @param completed status of task
 */
public record TaskJson(@JsonProperty("name") String name, @JsonProperty("descr") String desc,
                       @JsonProperty("day") DayJson day,
                       @JsonProperty("completed") boolean completed) {
}
