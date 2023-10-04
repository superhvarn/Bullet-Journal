package cs3500.pa05.model.JSON;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents an event in Json format
 *
 * @param name of event
 * @param desc description of event
 * @param day of event
 * @param startTime of event
 * @param duration of event
 */
public record EventJson(
    @JsonProperty("name") String name,
    @JsonProperty("description") String desc,
    @JsonProperty("day") DayJson day,
    @JsonProperty("startTime") double startTime,
    @JsonProperty("duration") double duration) {
}
