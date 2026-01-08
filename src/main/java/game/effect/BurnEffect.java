package game.effect;

import game.dto.ActionMessage;
import game.dto.ActionResult;
import game.dto.ActionType;
import game.entity.Character;

public class BurnEffect extends StatusEffect {

  public BurnEffect(int duration) {
    super(duration);
  }

  @Override
  public ActionResult applyEffect(Character target) {
    target.takeDamage(2);
    reduceDuration();
    return new ActionResult(
      ActionType.BURN,
      ActionMessage.burn(target.getName(), 2)
    );
  }
}
