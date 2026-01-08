package game.utils;

public final class GameTime {

  private GameTime() {}

  public static void waitMs(long ms) {
    try {
      Thread.sleep(ms);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }
}
