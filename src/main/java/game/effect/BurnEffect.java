package game.effect;

import game.dto.ActionResult;
import game.dto.ActionType;
import game.entity.Character;

public class BurnEffect extends StatusEffect {

  public BurnEffect(int duration) {
    super(duration);
  }

  @Override
  public ActionResult applyEffect(Character target) {
    target.takeDame(2);
    reduceDuration();
    return new ActionResult(
      ActionType.BURN,
      target.getName() + " is burning take 2 damage!"
    );
  }
}
