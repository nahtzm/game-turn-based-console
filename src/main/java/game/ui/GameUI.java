package game.ui;

import game.core.Character;

public class GameUI {

  public void showStatus(Character player, Character monster) {
    System.out.printf(
      """
      ====================================
              TURN-BASED BATTLE
      ====================================
      Player: %s
      HP: %s

      Monster: %s
      HP: %s
      """,
      player.getName(),
      renderHpBar(player),
      monster.getName(),
      renderHpBar(monster)
    );
  }

  public void showMenu() {
    System.out.printf(
      """
      ------------------------------------
      Choose action:
      1. Attack
      ------------------------------------
      """
    );
  }

  public void showMessage(String message) {
    System.out.println(message);
  }

  private String renderHpBar(Character c) {
    int totalBars = 20;
    int filledBars = (int) (((double) c.getHp() / c.getMaxHP()) * totalBars);

    StringBuilder bar = new StringBuilder("[");
    for (int i = 0; i < totalBars; i++) {
      bar.append(i < filledBars ? "█" : "░");
    }
    bar.append("] ").append(c.getHp()).append("/").append(c.getMaxHP());

    return bar.toString();
  }

  public void clearConsole() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }
}
