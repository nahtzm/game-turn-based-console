package game.action;

import game.core.Character;

public interface Action {
  ActionResult execute(Character actor, Character target);
}
