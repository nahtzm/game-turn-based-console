package game.effect;

import game.dto.ActionResult;
import game.entity.Character;

public abstract class StatusEffect {

  private int duration;

  public StatusEffect(int duration) {
    this.duration = duration;
  }

  public abstract ActionResult applyEffect(Character target);

  public int getDuration() {
    return duration;
  }

  public boolean isExpired() {
    return duration <= 0;
  }

  public void reduceDuration() {
    duration--;
  }
}
