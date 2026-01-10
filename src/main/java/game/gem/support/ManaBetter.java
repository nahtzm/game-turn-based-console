package game.gem.support;

import game.gem.SupportGem;

public class ManaBetter implements SupportGem {

  @Override
  public String getName() {
    return "Mana Better";
  }

  @Override
  public String getDescription() {
    return "Decreases the mana cost of the active gem by 10.";
  }

  @Override
  public int modifyManaCost(int baseManaCost) {
    return baseManaCost > 10 ? baseManaCost - 10 : 0;
  }
}
