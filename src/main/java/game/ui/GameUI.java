package game.ui;

import game.dto.ActionResult;
import game.entity.Character;
import game.utils.GameTime;
import java.util.List;

public class GameUI {

  public void showTitle() {
    System.out.println(
      """
      ====================================
              TURN-BASED BATTLE
      ====================================
      """
    );
  }

  public void showStatus(Character player, Character monster) {
    System.out.printf(
      """
      Player: %s
      HP: %s
      MP: %s

      Monster: %s
      HP: %s
      MP: %s
      """,
      player.getName(),
      renderBar(player, player.getHp(), player.getMaxHP()),
      renderBar(player, player.getMana(), player.getMaxMana()),
      monster.getName(),
      renderBar(monster, monster.getHp(), monster.getMaxHP()),
      renderBar(monster, monster.getMana(), monster.getMaxMana())
    );
  }

  public void showMenu(Character player) {
    System.out.printf(
      """
      ------------------------------------
      Choose action:
      1. %s (CD: %d)
      2. %s (CD: %d)
      ------------------------------------
      """,
      player.getGem(0).getName(),
      player.getGem(0).getCurrentCooldown(),
      player.getGem(1).getName(),
      player.getGem(1).getCurrentCooldown()
    );
  }

  public void showMessage(String message) {
    System.out.println(message);
  }

  public void showCombatLog(List<ActionResult> logs) {
    for (ActionResult log : logs) {
      printByType(log);
      GameTime.waitMs(200);
    }
  }

  private void printByType(ActionResult log) {
    switch (log.getType()) {
      case DAMAGE:
        System.out.println("> " + log.getMessage());
        break;
      case BURN:
        System.out.println("🔥~ " + log.getMessage());
        break;
      case STUN:
        System.out.println("! " + log.getMessage());
        break;
      case ERROR:
        System.out.println("[!] " + log.getMessage());
        break;
      default:
        System.out.println(log.getMessage());
    }
  }

  private String renderBar(Character c, int current, int max) {
    int totalBars = 20;
    int filledBars = (int) (((double) current / max) * totalBars);

    StringBuilder bar = new StringBuilder("[");
    for (int i = 0; i < totalBars; i++) {
      bar.append(i < filledBars ? "█" : "░");
    }
    bar.append("] ").append(current).append("/").append(max);

    return bar.toString();
  }

  public void clearConsole() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }
}
