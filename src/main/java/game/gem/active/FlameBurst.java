package game.gem.active;

import game.dto.ActionMessage;
import game.dto.ActionResult;
import game.dto.ActionType;
import game.effect.EffectType;
import game.effect.StatusEffect;
import game.entity.Character;
import game.gem.ActiveGem;
import game.gem.support.IntensifyFlame;
import game.gem.support.ManaBetter;

public class FlameBurst extends ActiveGem {

  public FlameBurst() {
    super("Flame Burst", 100, 30, 3);
    supportGems[0] = new IntensifyFlame();
    supportGems[1] = new ManaBetter();
    recalculateFinalStats();
  }

  @Override
  public String getDescription() {
    return "Gây bộc phát liệt diệm. Mạnh hơn nếu mục tiêu đang cháy.";
  }

  @Override
  public ActionResult execute(Character actor, Character target) {
    int dmg = checkTargetStatus(target, getDamage());
    target.takeDamage(dmg);

    return new ActionResult(
      ActionType.DAMAGE,
      ActionMessage.flameburst(actor.getName(), target.getName(), dmg)
    );
  }

  private int checkTargetStatus(Character target, int dmg) {
    for (StatusEffect statusEffect : target.getStatuses()) {
      if (statusEffect.getEffectType() == EffectType.BURN) {
        return dmg + 30;
      }
    }
    return dmg;
  }
}
