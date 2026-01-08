package game.gem.active;

import game.dto.ActionMessage;
import game.dto.ActionResult;
import game.dto.ActionType;
import game.entity.Character;
import game.gem.ActiveGem;
import game.gem.SupportGem;
import game.gem.support.Bigger;

public class FlameBurst extends ActiveGem {

  public FlameBurst() {
    super(100, 30, 3);
    supportGems[0] = new Bigger();
    applySupportGems();
  }

  @Override
  public String getName() {
    return "Flame Burst";
  }

  @Override
  public String getDescription() {
    return "Gây bộc phát liệt diệm. Mạnh hơn nếu mục tiêu đang cháy.";
  }

  @Override
  public ActionResult execute(Character actor, Character target) {
    if (!actor.engoughMana(getBaseManaCost())) {
      return new ActionResult(ActionType.ERROR, "You don't have enough mana.");
    }
    if (!this.canUse()) {
      return new ActionResult(
        ActionType.ERROR,
        "You can't use this gem right now."
      );
    }
    target.takeDamage(baseDamage);
    actor.spendMana(getBaseManaCost());
    onUse();
    return new ActionResult(
      ActionType.DAMAGE,
      ActionMessage.flameburst(
        actor.getName(),
        target.getName(),
        damageAfterSupport
      )
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
