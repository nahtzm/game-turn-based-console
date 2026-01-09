package game.gem.active;

import game.dto.ActionMessage;
import game.dto.ActionResult;
import game.dto.ActionType;
import game.effect.BurningSealEffect;
import game.entity.Character;
import game.gem.ActiveGem;
import game.gem.GemTag;

public class BurningSeal extends ActiveGem {

  public BurningSeal() {
    super("Burning Seal", 10, 5, 2, GemTag.FIRE);
    recalculateFinalStats();
  }

  @Override
  public String getDescription() {
    return "Đánh dấu mục tiêu bằng Viêm Ấn, cường hóa đòn lửa kế tiếp.";
  }

  @Override
  protected ActionResult execute(Character actor, Character target) {
    BurningSealEffect effect = new BurningSealEffect(3);
    target.takeDamage(getDamage());
    target.addStatusEffect(effect);
    return new ActionResult(
      ActionType.DAMAGE,
      ActionMessage.burningSeal(actor.getName(), target.getName(), getDamage())
    );
  }
}
