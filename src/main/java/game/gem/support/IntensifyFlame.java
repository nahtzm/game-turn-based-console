package game.gem.support;

import game.gem.ActiveGem;
import game.gem.GemTag;
import game.gem.SupportGem;

public class IntensifyFlame implements SupportGem {

  @Override
  public double getMoreMultiplier() {
    return 1.30;
  }

  @Override
  public int getExtraCooldown() {
    return 1;
  }

  @Override
  public String getName() {
    return "Cường Diệm";
  }

  @Override
  public String getDescription() {
    return "+30% more damage, nhưng +1 cooldown cho skill";
  }

  @Override
  public boolean canApplyTo(ActiveGem activeGem) {
    return activeGem.hasTag(GemTag.FIRE);
  }
}
