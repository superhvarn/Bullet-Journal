package cs3500.pa05.model.JSON;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa05.model.Theme;
import java.util.List;

/**
 * represents our journal with Json
 *
 * @param tasks in our journal
 * @param events in our journal
 * @param theme of our journal
 */
public record BulletJSON(@JsonProperty("tasks") List<TaskJson> tasks,
                         @JsonProperty("events") List<EventJson> events,
                         @JsonProperty("theme") Theme theme) {
}
