package game.core;

import game.dto.ActionResult;
import game.dto.ActionType;
import game.dto.CombatLog;
import game.entity.Character;
import game.gem.*;
import game.ui.*;

public class GameEngine {

  private final GameUI ui;
  private final Input input;
  private final CombatLog combatLog;

  public GameEngine(GameUI gameUI, Input input, CombatLog combatLog) {
    this.ui = gameUI;
    this.input = input;
    this.combatLog = combatLog;
  }

  public void run(Character player, Character monster) {
    while (player.isAlive() && monster.isAlive()) {
      startPhase(player, monster);
      ui.clearConsole();
      ui.showTitle();
      ui.showStatus(player, monster);
      ui.showMenu(player);
      actionPhase(player, monster);

      if (!monster.isAlive()) {
        ui.clearConsole();
        ui.showStatus(player, monster);
        ui.showMessage("You defeated the " + monster.getName() + "!");
        input.waitForEnter();
        break;
      }

      if (player.isAlive()) {
        useGem(monster.getGem(0), monster, player);
      }

      effectPhase(player, monster);
      ui.showCombatLog(combatLog.flush());
      input.waitForEnter();
    }

    if (player.isAlive()) {
      ui.showMessage("Victory! You defeated the monster.");
    } else {
      ui.showMessage("Game Over! You were defeated...");
    }
  }

  private void handleEffect(Character target) {
    combatLog.addAll(target.applyEffects());
  }

  private void effectPhase(Character player, Character monster) {
    handleEffect(player);
    handleEffect(monster);
  }

  private void startPhase(Character player, Character monster) {
    player.regenMana();
    monster.regenMana();
    for (ActiveGem gem : player.getActiveGems()) {
      if (gem != null) gem.reduceCooldown();
    }
    for (ActiveGem gem : monster.getActiveGems()) {
      if (gem != null) gem.reduceCooldown();
    }
  }

  private void actionPhase(Character player, Character monster) {
    boolean actionPerformed = false;
    while (!actionPerformed) {
      int choice = input.askAction();

      switch (choice) {
        case 1:
          actionPerformed = useGem(player.getGem(0), player, monster);
          break;
        case 2:
          actionPerformed = useGem(player.getGem(1), player, monster);
          break;
        case 3:
          actionPerformed = useGem(player.getGem(2), player, monster);
          break;
        case 0:
          ui.showMessage("Exiting game...");
          System.exit(0);
          break;
        default:
          ui.showMessage("Invalid choice! Please select a valid action.");
          break;
      }
    }
  }

  private boolean useGem(ActiveGem gem, Character actor, Character target) {
    ActionResult result = gem.use(actor, target);
    if (result.getType() == ActionType.ERROR) {
      ui.showMessage(result.getMessage());
    } else {
      combatLog.add(result);
    }
    return result.getType() != ActionType.ERROR;
  }
}
