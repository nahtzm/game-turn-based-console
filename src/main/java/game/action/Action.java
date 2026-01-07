package game.action;

import game.core.Character;
import game.dto.ActionResult;

public interface Action {
  ActionResult execute(Character actor, Character target);
}
