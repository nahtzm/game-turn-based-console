package game.effect;

import game.dto.ActionResult;
import game.entity.Character;

public abstract class StatusEffect {

  private String name;
  private int duration;
  protected EffectType effectType;

  public StatusEffect(String name, int duration) {
    this.name = name;
    this.duration = duration;
  }

  public abstract ActionResult applyEffect(Character target);

  public String getName() {
    return name;
  }

  public EffectType getEffectType() {
    return effectType;
  }

  public int getDuration() {
    return duration;
  }

  public boolean isExpired() {
    return duration <= 0;
  }

  public void reduceDuration() {
    duration--;
  }

  public void refresh(int newDuration) {
    this.duration = Math.max(this.duration, newDuration);
  }
}
