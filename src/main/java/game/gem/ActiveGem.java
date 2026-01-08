package game.gem;

import game.dto.ActionResult;
import game.entity.Character;

public abstract class ActiveGem implements Gem {

  protected final GemType type = GemType.ACTIVE;
  protected final int baseDamage;
  protected final int manaCost;
  protected int damageAfterSupport;
  protected int manaAfterSupport;
  protected final SupportGem[] supportGems = new SupportGem[3];

  public ActiveGem(int baseDamage, int manaCost) {
    this.baseDamage = baseDamage;
    this.manaCost = manaCost;
    this.damageAfterSupport = baseDamage;
    this.manaAfterSupport = manaCost;
  }

  public void setDamageAfterSupport(int damageAfterSupport) {
    this.damageAfterSupport = damageAfterSupport;
  }

  public void setManaAfterSupport(int manaAfterSupport) {
    this.manaAfterSupport = manaAfterSupport;
  }

  public int getBaseDamage() {
    return baseDamage;
  }

  public int getDamageAfterSupport() {
    return damageAfterSupport;
  }

  public int getManaAfterSupport() {
    return manaAfterSupport;
  }

  public SupportGem[] getSupportGems() {
    return supportGems;
  }

  public GemType getType() {
    return type;
  }

  public int getManaCost() {
    return manaAfterSupport;
  }

  public abstract ActionResult execute(Character actor, Character target);

  public abstract void applySupportGems();
}
