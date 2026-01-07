package game.action;

import game.core.Character;
import game.dto.ActionResult;

public class AttackAction implements Action {

  @Override
  public ActionResult execute(Character actor, Character target) {
    target.takeDame(actor.getDame());
    return new ActionResult(
      actor.getName() +
        " attacks " +
        target.getName() +
        " for " +
        actor.getDame() +
        " damage!"
    );
  }
}
