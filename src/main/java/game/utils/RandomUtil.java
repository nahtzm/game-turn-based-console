package game.utils;

import java.util.Random;

public final class RandomUtil {

  private static final Random RNG = new Random();

  public static boolean roll(double chance) {
    return RNG.nextDouble() < chance;
  }
}
