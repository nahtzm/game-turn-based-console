package game;

import game.action.*;
import game.core.*;
import game.ui.*;

public class Main {

  public static void main(String[] args) {
    Player player = new Player("nahtzm", 100, 30);
    Monster monster = new Monster("Dragon", 80, 30);

    GameUI ui = new GameUI();
    Input input = new Input();

    AttackAction attackAction = new AttackAction();

    GameEngine gameEngine = new GameEngine(ui, input, attackAction);
    gameEngine.run(player, monster);
  }
}
