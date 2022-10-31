package Task2;

import java.util.Scanner;

public class NumberGuessing {

  public static void guessingNumberGame() {
    try (Scanner sc = new Scanner(System.in)) {

      int number = 1 + (int) (100
          * Math.random());

      int K = 5;

      int i, guess;

      System.out.println(
          "\n- A number is chosen"
              + " between 1 to 100 by the system.\n"
              + "- Guess the number"
              + " within " + K + " trials.\n");

      for (i = 1; i <= K; i++) {

        System.out.println(
            "Guess the number:");

        guess = sc.nextInt();

        if (number == guess) {
          System.out.println(
              "Congratulations!"
                  + " You guessed the right number.");
          System.out.println("The number was " + guess + ".");
          System.out.println("You used " + i + " turns to guess the right number.");
          System.out.println("Your score is " + ((6 - i) * 20) + " out of 100.");
          System.out.println("--------------------------------------------\n");

          break;
        } else if (number > guess
            && i != K) {
          System.out.println(
              "The number is "
                  + "greater than " + guess + ".");
        } else if (number < guess
            && i != K) {
          System.out.println(
              "The number is"
                  + " less than " + guess + ".");
        }
      }

      if (i > K) {
        System.out.println(
            "You have exhausted " + K
                + " trials.");

        System.out.println(
            "The number was " + number + ".");
        System.out.println("Better luck Next Time!!!");
        System.out.println("--------------------------------------------\n");
      }

      System.out.println("Want to try again?");
      System.out.println("Enter 1 for YES and 0 for NO: ");

      int m = sc.nextInt();
      if (m == 1) {
        guessingNumberGame();

      } else {
        System.out.println("Thank You!!");
        System.out.println("--------------------------------------------");
      }

    }
  }

  public static void main(String arg[]) {

    System.out.println("\n\n\t********************  Number Guessing Game ********************\n");

    System.out.println("\nRules:");
    System.out.println("- You have to enter a number between 1 to 100.");
    System.out.println("- You have to guess the number in the 5 trials.");
    System.out.println("- After 5 turns your game will end.\n");

    System.out.println("- Press '1' to start the game");
    System.out.println("- Press '0' to end the game\n");

    try (Scanner sc = new Scanner(System.in)) {
      if (sc.nextInt() == 1) {
        guessingNumberGame();
      } else {
        System.out.println("Thank You!!");
        System.out.println("--------------------------------------------");
      }
    }
  }
}