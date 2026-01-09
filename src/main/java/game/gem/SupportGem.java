package game.gem;

import game.entity.Character;

public interface SupportGem extends Gem {
  default int modifyDuration(int baseDuration) {
    return baseDuration;
  }

  default int addFlatDamage(int currentDamage) {
    return currentDamage;
  }

  default double getIncreasedPercent() {
    return 0.0;
  }

  default double getMoreMultiplier() {
    return 1.0;
  }

  default int getExtraCooldown() {
    return 0;
  }

  default int modifyManaCost(int baseManaCost) {
    return baseManaCost;
  }

  default int applySpecialEffect(
    int finalDamage,
    Character target,
    ActiveGem activeGem,
    Character caster
  ) {
    return 0;
  }

  default boolean canApplyTo(ActiveGem activeGem) {
    return true;
  }

  String getName();
  String getDescription();
}
