package game;

import game.core.*;
import game.dto.CombatLog;
import game.entity.Monster;
import game.entity.Player;
import game.ui.*;

public class Main {

  public static void main(String[] args) {
    Player player = new Player("nahtzm", 1000, 30, 50);
    Monster monster = new Monster("Dragon", 300, 30, 1000);

    GameUI ui = new GameUI();
    Input input = new Input();
    CombatLog combatLog = new CombatLog();

    GameEngine gameEngine = new GameEngine(ui, input, combatLog);
    gameEngine.run(player, monster);
  }
}
