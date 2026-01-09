package game.effect;

import game.dto.ActionMessage;
import game.dto.ActionResult;
import game.dto.ActionType;
import game.entity.Character;

public class BurnEffect extends StatusEffect {

  private int damagePerTurn;

  public BurnEffect(int damagePerTurn, int duration) {
    super("Burn", duration);
    this.effectType = EffectType.BURN;
    this.damagePerTurn = damagePerTurn;
  }

  @Override
  public ActionResult applyEffect(Character target) {
    target.takeDamage(damagePerTurn);
    reduceDuration();
    return new ActionResult(
      ActionType.BURN,
      ActionMessage.burn(target.getName(), damagePerTurn, getDuration())
    );
  }
}
