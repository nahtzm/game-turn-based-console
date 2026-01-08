package game.dto;

public class ActionResult {

  private final String message;
  private final ActionType type;

  public ActionResult(ActionType type, String message) {
    this.type = type;
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  public ActionType getType() {
    return type;
  }
}
