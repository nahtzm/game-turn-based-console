package game.effect;

import game.dto.ActionMessage;
import game.dto.ActionResult;
import game.dto.ActionType;
import game.entity.Character;

public class BurningSealEffect extends StatusEffect {

  public BurningSealEffect(int duration) {
    super("Burning Seal", duration);
  }

  @Override
  public ActionResult applyEffect(Character target) {
    reduceDuration();

    return new ActionResult(
      ActionType.BURN,
      ActionMessage.burningSealEffect(target.getName())
    );
  }
}
