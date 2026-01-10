package game.core;

import game.dto.ActionResult;
import game.dto.ActionType;
import game.dto.CombatLog;
import game.entity.Character;
import game.gem.ActiveGem;
import game.ui.GameUI;
import game.ui.Input;

public class GameEngine {

  private final GameUI ui;
  private final Input input;
  private final CombatLog combatLog;

  public GameEngine(GameUI ui, Input input, CombatLog combatLog) {
    this.ui = ui;
    this.input = input;
    this.combatLog = combatLog;
  }

  // ===================== MAIN LOOP =====================
  public void run(Character player, Character monster) {
    while (player.isAlive() && monster.isAlive()) {
      startTurn(player, monster);

      ui.clearConsole();
      ui.showTitle();
      ui.showStatus(player, monster);
      ui.showMenu(player);

      playerActionPhase(player, monster);

      if (!monster.isAlive()) break;

      enemyActionPhase(monster, player);

      endTurn(player, monster);

      ui.showCombatLog(combatLog.flush());
      input.waitForEnter();
    }

    showFinalResult(player, monster);
  }

  // ===================== TURN PHASES =====================

  private void startTurn(Character... characters) {
    for (Character c : characters) {
      c.regenMana();
      reduceCooldowns(c);
    }
  }

  private void playerActionPhase(Character player, Character monster) {
    boolean actionDone = false;

    while (!actionDone) {
      int choice = input.askAction();
      ActiveGem selected = getPlayerGemByChoice(player, choice);

      if (selected == null) {
        ui.showMessage("Invalid action.");
        continue;
      }

      ActionResult result = executeSkill(selected, player, monster);

      if (result.getType() == ActionType.ERROR) {
        ui.showMessage(result.getMessage());
      } else {
        combatLog.add(result);
        actionDone = true;
      }
    }
  }

  private void enemyActionPhase(Character monster, Character player) {
    ActiveGem gem = monster.getGem(0);
    ActionResult result = executeSkill(gem, monster, player);
    combatLog.add(result);
  }

  private void endTurn(Character... characters) {
    for (Character c : characters) {
      combatLog.addAll(c.applyEffects());
    }
  }

  // ===================== HELPERS =====================

  private ActionResult executeSkill(
    ActiveGem gem,
    Character caster,
    Character target
  ) {
    if (gem == null) {
      return new ActionResult(ActionType.ERROR, "No skill equipped.");
    }
    return gem.use(caster, target);
  }

  private void reduceCooldowns(Character c) {
    for (ActiveGem gem : c.getActiveGems()) {
      if (gem != null) {
        gem.reduceCooldown();
      }
    }
  }

  private ActiveGem getPlayerGemByChoice(Character player, int choice) {
    return switch (choice) {
      case 1 -> player.getGem(0);
      case 2 -> player.getGem(1);
      case 3 -> player.getGem(2);
      default -> null;
    };
  }

  private void showFinalResult(Character player, Character monster) {
    ui.clearConsole();
    ui.showStatus(player, monster);

    if (player.isAlive()) {
      ui.showMessage("Victory! You defeated the monster.");
    } else {
      ui.showMessage("Game Over! You were defeated...");
    }
  }
}
