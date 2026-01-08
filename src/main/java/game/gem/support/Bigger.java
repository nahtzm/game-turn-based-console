package game.gem.support;

import game.gem.ActiveGem;
import game.gem.SupportGem;

public class Bigger extends SupportGem {

  @Override
  public String getName() {
    return "Bigger";
  }

  @Override
  public String getDescription() {
    return "Increases the damage of the active gem by 10.";
  }

  @Override
  public void applyEffect(ActiveGem activeGem) {
    activeGem.setDamageAfterSupport(activeGem.getBaseDamage() + 10);
  }
}
