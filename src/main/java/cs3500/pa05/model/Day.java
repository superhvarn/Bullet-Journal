package cs3500.pa05.model;

/**
 * Represents days in a week in java journal
 */
public enum Day {

  /**
   * Sunday (first day in journal)
   */
  SUNDAY("SUN", 0),

  /**
   * Monday (second day in journal)
   */
  MONDAY("MON", 1),

  /**
   * Tuesday (third day in journal)
   */
  TUESDAY("TUE", 2),

  /**
   * Wednesday (fourth day in journal)
   */
  WEDNESDAY("WED", 3),

  /**
   * Thursday (fifth day in journal)
   */
  THURSDAY("THU", 4),

  /**
   * Friday (sixth day in journal)
   */
  FRIDAY("FRI", 5),

  /**
   * Saturday (seventh day in journal)
   */
  SATURDAY("SAT", 6),

  /**
   * If there is no day assigned
   */
  EMPTY("", -1);

  private String abbrev;

  private int placement;

  Day(String abbrev, int placement) {

    this.abbrev = abbrev;
    this.placement = placement;
  }

  /**
   * to check if the given abbreviation of a day is equal to the enum using string
   *
   * @param str the given abbreviation
   *
   * @return the day given or exception is thrown
   */
  public static Day fromString(String str) {
    System.out.println(str);
    for (Day day : Day.values()) {
      if (day.abbrev.equals(str)) {
        System.out.println(day);
        return day;
      }
    }
    throw new IllegalArgumentException("Nuh uh: " + str);
  }

  /**
   * to check if the given abbreviation of a day is equal to the enum using integer (idx placement)
   *
   * @param i the given placement
   *
   * @return the day given or exception is thrown
   */
  public static Day fromPlacement(int i) {
    for (Day day : Day.values()) {
      if (day.placement == i) {
        System.out.println(day);
        return day;
      }
    }
    throw new IllegalArgumentException("Nuh uh: " + i);
  }

  /**
   * getter for the placement of a day in the journal
   *
   * @return position of day
   */
  public int getPlacement() {
    return this.placement;
  }

  /**
   * strings this abbreviation
   *
   * @return this abbreviation
   */
  public String toString() {
    return this.abbrev;
  }
}
