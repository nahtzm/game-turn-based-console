package game.dto;

import java.util.ArrayList;
import java.util.List;

public class CombatLog {

  private final List<ActionResult> logs = new ArrayList<>();

  public void add(ActionResult result) {
    logs.add(result);
  }

  public void addAll(List<ActionResult> results) {
    logs.addAll(results);
  }

  public List<ActionResult> flush() {
    List<ActionResult> copy = new ArrayList<>(logs);
    logs.clear();
    return copy;
  }

  public boolean isEmpty() {
    return logs.isEmpty();
  }
}
