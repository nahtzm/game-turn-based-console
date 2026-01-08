package game.gem;

import game.dto.ActionResult;
import game.entity.Character;

public abstract class ActiveGem implements Gem {

  protected final GemType type = GemType.ACTIVE;
  protected final int baseDamage;
  protected final int baseManaCost;
  protected final int baseCooldown;
  protected int currentCooldown;
  protected int damageAfterSupport;
  protected int manaAfterSupport;
  protected final SupportGem[] supportGems = new SupportGem[3];

  public ActiveGem(int baseDamage, int manaCost, int cooldown) {
    this.baseDamage = baseDamage;
    this.baseManaCost = manaCost;
    this.baseCooldown = cooldown;
    this.currentCooldown = 0;
    this.damageAfterSupport = baseDamage;
    this.manaAfterSupport = manaCost;
  }

  public int getBaseDamage() {
    return baseDamage;
  }

  public int getBaseManaCost() {
    return manaAfterSupport;
  }

  public int getBaseCooldown() {
    return baseCooldown;
  }

  public int getCurrentCooldown() {
    return currentCooldown;
  }

  public int getDamageAfterSupport() {
    return damageAfterSupport;
  }

  public int getManaAfterSupport() {
    return manaAfterSupport;
  }

  public void setDamageAfterSupport(int damageAfterSupport) {
    this.damageAfterSupport = damageAfterSupport;
  }

  public void setManaAfterSupport(int manaAfterSupport) {
    this.manaAfterSupport = manaAfterSupport;
  }

  public boolean canUse() {
    return currentCooldown == 0;
  }

  public void onUse() {
    currentCooldown = baseCooldown;
  }

  public void reduceCooldown() {
    if (currentCooldown > 0) currentCooldown--;
  }

  public SupportGem[] getSupportGems() {
    return supportGems;
  }

  public GemType getType() {
    return type;
  }

  public abstract ActionResult execute(Character actor, Character target);

  public abstract void applySupportGems();
}
