package game.entity;

import game.dto.ActionResult;
import game.effect.StatusEffect;
import game.gem.ActiveGem;
import java.util.ArrayList;
import java.util.List;

public abstract class Character {

  protected String name;
  protected int hp;
  protected int maxHP;
  protected int dame;
  protected int mana;
  protected int maxMana;
  protected int manaRegen;
  protected final ActiveGem[] activeGems = new ActiveGem[5];
  protected List<StatusEffect> statuses = new ArrayList<>();

  public int getManaRegen() {
    return manaRegen;
  }

  public ActiveGem[] getActiveGems() {
    return activeGems;
  }

  public List<StatusEffect> getStatuses() {
    return statuses;
  }

  public Character(String name, int maxHp, int dame, int mana) {
    this.name = name;
    this.hp = maxHp;
    this.maxHP = hp;
    this.dame = dame;
    this.mana = mana;
    this.maxMana = mana;
    this.manaRegen = 10;
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

  public int getMana() {
    return this.mana;
  }

  public int getMaxMana() {
    return this.maxMana;
  }

  public boolean isAlive() {
    return this.hp > 0;
  }

  public void takeDame(int dmg) {
    this.hp = Math.max(0, this.hp - dmg);
  }

  public void decreaseMana(int amount) {
    this.mana -= amount;
    if (this.mana < 0) {
      mana = 0;
    }
  }

  public void regenMana() {
    this.mana += manaRegen;
    if (this.mana > maxMana) {
      mana = maxMana;
    }
  }

  public boolean engoughMana(int cost) {
    return mana >= cost;
  }

  public boolean checkMana(ActiveGem gem) {
    return engoughMana(gem.getManaCost());
  }

  public ActiveGem getGem(int slot) {
    return (slot >= 0 && slot < 5) ? activeGems[slot] : null;
  }

  public void addStatusEffect(StatusEffect status) {
    statuses.add(status);
  }

  public List<ActionResult> applyEffects() {
    List<ActionResult> results = new ArrayList<>();
    for (StatusEffect status : statuses) {
      results.add(status.applyEffect(this));
    }
    statuses.removeIf(StatusEffect::isExpired);
    return results;
  }
}
