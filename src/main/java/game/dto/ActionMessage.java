package game.dto;

public class ActionMessage {

  public static String fireball(String actor, String target, int dmg) {
    return String.format(
      "%s casts a fireball 🔥 at %s dealing %d damage.",
      actor,
      target,
      dmg
    );
  }

  public static String flameburst(String actor, String target, int dmg) {
    return String.format(
      "%s casts a flameburst 🔥 at %s dealing %d damage.",
      actor,
      target,
      dmg
    );
  }

  public static String burn(String target, int dmg) {
    return String.format("%s is burning for %d damage.", target, dmg);
  }
}
