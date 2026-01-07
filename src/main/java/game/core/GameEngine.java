package game.core;

import game.action.*;
import game.dto.ActionResult;
import game.ui.*;

public class GameEngine {

  private final GameUI ui;
  private final Input input;
  private final Action attackAction;

  public GameEngine(GameUI gameUI, Input input, Action attackAction) {
    this.ui = gameUI;
    this.input = input;
    this.attackAction = attackAction;
  }

  public void run(Character player, Character monster) {
    while (player.isAlive() && monster.isAlive()) {
      ui.clearConsole();
      ui.showStatus(player, monster);
      ui.showMenu();

      int choice = input.askAction();
      switch (choice) {
        case 1:
          performAction(attackAction, player, monster);
          performAction(attackAction, monster, player);
          input.waitForEnter();
          break;
        default:
          return;
      }
    }
  }

  private void performAction(
    Action action,
    Character attacker,
    Character target
  ) {
    ActionResult result = action.execute(attacker, target);
    ui.showMessage(result.getMessage());
  }
}
