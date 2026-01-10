package game.gem;

import game.dto.ActionResult;
import game.dto.ActionType;
import game.entity.Character;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public abstract class ActiveGem implements Gem {

  // ================== BASE STATS ==================
  protected final int baseDamage;
  protected final int baseManaCost;
  protected final int baseCooldown;
  private final Set<GemTag> tags = new HashSet<>();

  // ================== CALCULATED STATS (sau support) ==================
  protected int calculatedDamage;
  protected int calculatedManaCost;
  protected int calculatedExtraCooldown;

  // ================== RUNTIME ==================
  protected int currentCooldown = 0;
  protected final SupportGem[] supportGems = new SupportGem[4];

  // ================== CONSTRUCTOR ==================
  protected ActiveGem(
    int baseDamage,
    int baseManaCost,
    int baseCooldown,
    GemTag... tags
  ) {
    this.baseDamage = baseDamage;
    this.baseManaCost = baseManaCost;
    this.baseCooldown = baseCooldown;
    this.tags.addAll(Arrays.asList(tags));
  }

  public void recalculateFinalStats() {
    int damage = baseDamage;
    int mana = baseManaCost;
    int extraCD = 0;

    double increasedPercent = 0.0;
    double moreMultiplier = 1.0;

    for (SupportGem support : supportGems) {
      if (support == null) continue;

      damage = support.addFlatDamage(damage);

      increasedPercent += support.getIncreasedPercent();

      moreMultiplier *= support.getMoreMultiplier();

      extraCD += support.getExtraCooldown();
      mana = support.modifyManaCost(mana);
    }

    damage = (int) (damage * (1 + increasedPercent / 100.0));
    damage = (int) (damage * moreMultiplier);

    this.calculatedDamage = Math.max(0, damage);
    this.calculatedManaCost = Math.max(0, mana);
    this.calculatedExtraCooldown = extraCD;
  }

  // ================== GETTER CHO COMBAT & UI ==================

  public int getDamage() {
    return calculatedDamage;
  }

  public int getManaCost() {
    return calculatedManaCost;
  }

  public int getCurrentCooldown() {
    return currentCooldown;
  }

  public SupportGem[] getSupportGems() {
    return supportGems;
  }

  public boolean isOnCooldown() {
    return currentCooldown > 0;
  }

  // ================== COOLDOWN MANAGEMENT ==================
  public void startCooldown() {
    currentCooldown = baseCooldown + calculatedExtraCooldown;
  }

  public void reduceCooldown() {
    if (currentCooldown > 0) {
      currentCooldown--;
    }
  }

  // ================== CHECK CAN USE ==================
  public ActionResult canUse(Character actor) {
    if (isOnCooldown()) {
      return new ActionResult(
        ActionType.ERROR,
        getName() + " đang cooldown (" + currentCooldown + " lượt còn lại)."
      );
    }
    if (!actor.hasEnoughMana(getManaCost())) {
      return new ActionResult(
        ActionType.ERROR,
        "Không đủ mana: cần " + getManaCost() + " (có " + actor.getMana() + ")."
      );
    }
    return new ActionResult(ActionType.INFO, "Sẵn sàng sử dụng.");
  }

  // ================== USE GEM ==================
  public ActionResult use(Character actor, Character target) {
    ActionResult check = canUse(actor);
    if (check.getType() == ActionType.ERROR) {
      return check;
    }

    actor.spendMana(getManaCost());
    startCooldown();

    return execute(actor, target);
  }

  protected abstract ActionResult execute(Character actor, Character target);

  // ================== EQUIP SUPPORT (gọi ở Prep Phase) ==================
  public boolean attachSupport(int slot, SupportGem support) {
    if (slot < 0 || slot >= 4) return false;
    if (supportGems[slot] != null) return false; // đã có gem
    supportGems[slot] = support;
    recalculateFinalStats();
    return true;
  }

  public boolean detachSupport(int slot) {
    if (slot < 0 || slot >= 4 || supportGems[slot] == null) return false;
    supportGems[slot] = null;
    recalculateFinalStats();
    return true;
  }

  public boolean hasTag(GemTag tag) {
    return tags.contains(tag);
  }
}
