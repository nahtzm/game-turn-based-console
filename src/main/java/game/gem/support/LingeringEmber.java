package game.gem.support;

import game.gem.ActiveGem;
import game.gem.GemTag;
import game.gem.SupportGem;

public class LingeringEmber implements SupportGem {

  @Override
  public int modifyDuration(int baseDuration) {
    return baseDuration + 1;
  }

  @Override
  public String getName() {
    return "Dư Diệm";
  }

  @Override
  public String getDescription() {
    return "Burn tồn tại +1 lượt. Skill không có Burn sẽ gây Burn nhẹ (1x2)";
  }

  @Override
  public boolean canApplyTo(ActiveGem activeGem) {
    return activeGem.hasTag(GemTag.FIRE) && activeGem.hasTag(GemTag.BURN);
  }
}
