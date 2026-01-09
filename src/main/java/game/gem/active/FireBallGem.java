package game.gem.active;

import game.dto.ActionMessage;
import game.dto.ActionResult;
import game.dto.ActionType;
import game.effect.BurnEffect;
import game.effect.StatusEffect;
import game.entity.Character;
import game.gem.ActiveGem;
import game.gem.GemTag;
import game.gem.SupportGem;
import game.gem.support.IntensifyFlame;
import game.gem.support.LingeringEmber;
import game.utils.RandomUtil;

public class FireBallGem extends ActiveGem {

  private double burnChance = 0.5;
  private int baseBurnDuration = 2;
  private int baseBurnDamage = 4;
  private int calculatedBurnDuration;

  public FireBallGem() {
    super("Fire Ball", 10, 0, 0, GemTag.FIRE, GemTag.SPELL, GemTag.BURN);
    supportGems[0] = new IntensifyFlame();
    supportGems[1] = new LingeringEmber();
    supportGems[2] = new LingeringEmber();

    recalculateFinalStats();
  }

  @Override
  public void recalculateFinalStats() {
    super.recalculateFinalStats();

    int burnDuration = this.baseBurnDuration;
    for (SupportGem s : supportGems) {
      if (s != null) {
        burnDuration = s.modifyDuration(burnDuration);
      }
    }
    this.calculatedBurnDuration = burnDuration;
  }

  @Override
  protected ActionResult execute(Character actor, Character target) {
    int dmg = getDamage();
    target.takeDamage(dmg);

    if (RandomUtil.roll(burnChance)) {
      StatusEffect existing = target.getStatusByName("Burn");
      if (existing != null) {
        existing.refresh(calculatedBurnDuration);
      } else {
        target.addStatusEffect(
          new BurnEffect(baseBurnDamage, calculatedBurnDuration)
        );
      }
    }

    return new ActionResult(
      ActionType.DAMAGE,
      ActionMessage.fireball(actor.getName(), target.getName(), dmg)
    );
  }

  @Override
  public String getDescription() {
    return "Hỏa Cầu gây sát thương và gây Thiêu Đốt";
  }
}
