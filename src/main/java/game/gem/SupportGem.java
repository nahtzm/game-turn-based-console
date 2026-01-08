package game.gem;

public abstract class SupportGem implements Gem {

  protected final GemType type = GemType.SUPPORT;

  public GemType getType() {
    return type;
  }

  public abstract void applyEffect(ActiveGem activeGem);
}
