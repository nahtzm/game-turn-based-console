package game.core;

public abstract class Character {

  protected String name;
  protected int hp;
  protected int maxHP;
  protected int dame;

  public Character(String name, int maxHp, int dame) {
    this.name = name;
    this.hp = maxHp;
    this.maxHP = hp;
    this.dame = dame;
  }

  public int getMaxHP() {
    return this.maxHP;
  }

  public String getName() {
    return this.name;
  }

  public int getHp() {
    return this.hp;
  }

  public int getDame() {
    return this.dame;
  }

  public boolean isAlive() {
    return this.hp > 0;
  }

  public void takeDame(int dmg) {
    this.hp = Math.max(0, this.hp - dmg);
  }
}
