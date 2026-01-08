package game.entity;

import game.gem.active.*;

public class Monster extends Character {

  public Monster(String name, int maxHP, int dame, int mana) {
    super(name, maxHP, dame, mana);
    activeGems[0] = new FireBallGem();
    activeGems[1] = null;
    activeGems[2] = null;
    activeGems[3] = null;
    activeGems[4] = null;
  }
}
