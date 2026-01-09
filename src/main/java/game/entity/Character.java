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
  protected int mana;
  protected int maxMana;
  protected int manaRegen;
  protected final ActiveGem[] activeGems = new ActiveGem[5];
  protected List<StatusEffect> statuses = new ArrayList<>();

  public Character(String name, int maxHp, int maxMana) {
    this.name = name;
    this.hp = maxHp;
    this.maxHP = hp;
    this.mana = maxMana;
    this.maxMana = maxMana;
    this.manaRegen = 10;
  }

  public ActiveGem[] getActiveGems() {
    return activeGems;
  }

  public List<StatusEffect> getStatuses() {
    return statuses;
  }

  public String getName() {
    return this.name;
  }

  public int getMaxHP() {
    return this.maxHP;
  }

  public int getMaxMana() {
    return this.maxMana;
  }

  public int getManaRegen() {
    return manaRegen;
  }

  public int getHp() {
    return this.hp;
  }

  public int getMana() {
    return this.mana;
  }

  public boolean isAlive() {
    return this.hp > 0;
  }

  public void takeDamage(int dmg) {
    this.hp = Math.max(0, this.hp - dmg);
  }

  public void spendMana(int amount) {
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

  public void setMaxHP(int maxHP) {
    this.maxHP = maxHP;
    this.hp = Math.min(this.hp, maxHP);
  }

  public boolean hasEnoughMana(int cost) {
    return mana >= cost;
  }

  public void heal(int amount) {
    this.hp = Math.min(maxHP, this.hp + amount);
  }

  public void fullHeal() {
    this.hp = maxHP;
    this.mana = maxMana;
  }

  public ActiveGem getGem(int slot) {
    return (slot >= 0 && slot < 5) ? activeGems[slot] : null;
  }

  public void equipGem(int slot, ActiveGem gem) {
    if (slot >= 0 && slot < 5) {
      activeGems[slot] = gem;
    }
  }

  public void addStatusEffect(StatusEffect status) {
    statuses.add(status);
  }

  public StatusEffect getStatusByName(String name) {
    for (StatusEffect status : statuses) {
      if (status.getName().equals(name)) {
        return status;
      }
    }
    return null;
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
