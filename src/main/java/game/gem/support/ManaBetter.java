package game.gem.support;

import game.gem.ActiveGem;
import game.gem.SupportGem;

public class ManaBetter extends SupportGem {

  @Override
  public String getName() {
    return "Mana Better";
  }

  @Override
  public String getDescription() {
    return "Decreases the mana cost of the active gem by 10.";
  }

  @Override
  public void applyEffect(ActiveGem activeGem) {
    activeGem.setManaAfterSupport(
      activeGem.getManaCost() > 10 ? activeGem.getManaCost() - 10 : 0
    );
  }
}
