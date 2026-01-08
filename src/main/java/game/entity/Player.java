package game.entity;

import game.gem.active.*;

public class Player extends Character {

  public Player(String name, int maxHp, int dame, int mana) {
    super(name, maxHp, dame, mana);
    activeGems[0] = new FireBallGem();
    activeGems[1] = new FireBallGem();
    activeGems[2] = null;
    activeGems[3] = null;
    activeGems[4] = null;
  }
}
