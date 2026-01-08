package game.gem.active;

import game.dto.ActionResult;
import game.dto.ActionType;
import game.effect.BurnEffect;
import game.entity.Character;
import game.gem.ActiveGem;
import game.gem.SupportGem;
import game.gem.support.Bigger;
import game.gem.support.ManaBetter;

public class FireBallGem extends ActiveGem {

  public FireBallGem() {
    super(20, 10);
    supportGems[0] = new Bigger();
    supportGems[1] = new ManaBetter();
    applySupportGems();
  }

  @Override
  public String getName() {
    return "Fire Ball";
  }

  @Override
  public String getDescription() {
    return "A powerful fireball that deals damage to enemies.";
  }

  @Override
  public ActionResult execute(Character actor, Character target) {
    if (actor.getMana() < getManaCost()) {
      return new ActionResult(
        ActionType.ERROR,
        "You don't have enough mana to cast the fireball."
      );
    }
    target.takeDame(damageAfterSupport);
    target.addStatusEffect(new BurnEffect(3));
    actor.decreaseMana(manaAfterSupport);
    return new ActionResult(
      ActionType.DAMAGE,
      actor.getName() +
        " casts a fireball 🔥 at " +
        target.getName() +
        " dealing " +
        damageAfterSupport +
        " damage."
    );
  }

  @Override
  public void applySupportGems() {
    for (SupportGem supportGem : supportGems) {
      if (supportGem != null) {
        supportGem.applyEffect(this);
      }
    }
  }
}
