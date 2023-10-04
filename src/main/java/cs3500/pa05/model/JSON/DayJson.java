package cs3500.pa05.model.JSON;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * represents a day in Json format
 *
 * @param dayOfWeek day of the week
 * @param num position of day in journal
 */
public record DayJson(@JsonProperty("dayOfWeek") String dayOfWeek,
                      @JsonProperty("numberRep") int num) {
}
