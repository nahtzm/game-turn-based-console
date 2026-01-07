package game.ui;

import java.util.Scanner;

public class Input {

  private final Scanner scanner = new Scanner(System.in);

  public int askAction() {
    System.out.print("Your choice: ");
    return scanner.nextInt();
  }

  public void waitForEnter() {
    System.out.println("\nPress ENTER to continue...");
    scanner.nextLine();
    scanner.nextLine();
  }
}
