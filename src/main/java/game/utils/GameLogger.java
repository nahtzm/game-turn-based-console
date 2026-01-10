package game.utils;

import static game.utils.AnsiColor.*;

public final class GameLogger {

  private static boolean enabled = true;

  private GameLogger() {}

  public static void enable() {
    enabled = true;
  }

  public static void disable() {
    enabled = false;
  }

  private static void log(String tag, String message, String color) {
    if (!enabled) return;
    System.out.println(color("[" + tag + "] ", color) + message);
  }

  public static void info(String message) {
    log("INFO", message, GRAY);
  }

  public static void combat(String message) {
    log("COMBAT", message, BLUE);
  }

  public static void damage(String message) {
    log("DAMAGE", message, RED);
  }

  public static void effect(String message) {
    log("EFFECT", message, PURPLE);
  }

  public static void success(String message) {
    log("SUCCESS", message, GREEN);
  }

  public static void warn(String message) {
    log("WARN", message, YELLOW);
  }
}
