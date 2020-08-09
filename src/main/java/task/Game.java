package task;

import task.difficulty.AdvancedDifficulty;
import task.difficulty.BeginnerDifficulty;
import task.difficulty.Difficulty;
import task.difficulty.IntermediateDifficulty;
import task.exceptions.MineClickException;

import java.io.BufferedReader;
import java.io.IOException;

public class Game {
  private final int numberOfDifficulties = 3;
  private BufferedReader bufferedReader;
  private Difficulty difficulty;
  private final String enterNumberMessage = "Enter your move (row , column ) ";
  private final String difficultyChoiceMessage =
            "Enter the difficulty level" + System.lineSeparator() +
            "Press 0 for BEGINNER ( " + BeginnerDifficulty.ROWS + " * "
                    + BeginnerDifficulty.COLUMNS + " and " + BeginnerDifficulty.MINES + " )" + System.lineSeparator() +
            "Press 1 for INTERMEDIATE ( " + IntermediateDifficulty.ROWS + " * "
                    + IntermediateDifficulty.COLUMNS + " and " + IntermediateDifficulty.MINES + " )" + System.lineSeparator() +
            "Press 2 for ADVANCED ( " + AdvancedDifficulty.ROWS + " * "
                    + AdvancedDifficulty.COLUMNS + " and " + AdvancedDifficulty.MINES + " )" + System.lineSeparator();

  public Game(BufferedReader bufferedReader) {
    this.bufferedReader = bufferedReader;
  }

  public void startGame() {
    choiceDifficulty(this.bufferedReader);
    try {
      while (true) {
        if (clickOnPosition(this.bufferedReader)) {
          System.out.println("You win");
          difficulty.showFullTable();
          break;
        }
      }
    } catch (MineClickException e) {
      difficulty.showFullTable();
      System.out.println(e.getMessage());
    }
  }

  private void choiceDifficulty(BufferedReader bufferedReader) {
    System.out.println(difficultyChoiceMessage);
    while (true) {
      try {
        int number = Integer.parseInt(bufferedReader.readLine());
        difficulty = createDifficulty(number);
        break;
      } catch (IOException e) {
        throw new RuntimeException("Somethings go wrong");
      } catch (NumberFormatException e) {
        System.out.println("Please enter only numbers");
      } catch (IllegalArgumentException e) {
        System.out.println("Number must be between 0 and " + (numberOfDifficulties - 1));
      }
    }
  }

  private boolean clickOnPosition(BufferedReader bufferedReader) throws MineClickException {
    System.out.println(enterNumberMessage);
    while (true) {
      try {
        String readLine = bufferedReader.readLine();
        String[] numbers = readLine.split(" ");
        int[] checkedNumbers = checkNumber(numbers);
        difficulty.positionCheck(checkedNumbers[0], checkedNumbers[1]);
        return difficulty.showTable();
      } catch (IOException e) {
        throw new RuntimeException("Somethings go wrong");
      } catch (NumberFormatException e) {
        System.out.println("Please enter only numbers");
      } catch (IllegalArgumentException e) {
        System.out.println(e.getMessage());
      }
    }
  }

  private int[] checkNumber(String[] numbers) {
    if (numbers.length != 2) {
      throw new IllegalArgumentException(
          "Insert only two number, they must be split with space, between them");
    }
    int row = Integer.parseInt(numbers[0]);
    int column = Integer.parseInt(numbers[1]);
    if (row < 0 || row >= difficulty.getRows()) {
      throw new IllegalArgumentException("Row must be between 0 and " + (difficulty.getRows() - 1));
    }
    if (column < 0 || column >= difficulty.getColumns()) {
      throw new IllegalArgumentException(
          "Column must be between 0 and " + (difficulty.getColumns() - 1));
    }
    return new int[] {row, column};
  }

  private Difficulty createDifficulty(int number) {
    if (number < 0 || number >= numberOfDifficulties) {
      throw new IllegalArgumentException();
    }
    switch (number) {
      case 0:
        return new BeginnerDifficulty();
      case 1:
        return new IntermediateDifficulty();
      case 2:
        return new AdvancedDifficulty();
    }
    return null;
  }
}
