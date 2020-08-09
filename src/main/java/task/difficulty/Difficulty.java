package task.difficulty;

import task.exceptions.MineClickException;

import java.util.Random;

public abstract class Difficulty {
  private int rows;
  private int columns;
  private int mines;
  private char[][] table;

  public Difficulty(int rows, int columns, int mines) {
    this.rows = rows;
    this.columns = columns;
    this.mines = mines;
  }

  private char[][] createTable(int row, int column) {
    char[][] temp = new char[rows][columns];
    for (int i = 0; i < mines; i++) {
      while (true) {
        int randomRow = new Random().nextInt(rows);
        int randomCol = new Random().nextInt(columns);
        if (randomRow == row && randomCol == column) {
          continue;
        }
        temp[randomRow][randomCol] = '*';
        break;
      }
    }
    for (int i = 0; i < temp.length; i++) {
      for (int j = 0; j < temp[i].length; j++) {
        if (temp[i][j] != '*') {
          temp[i][j] = '-';
        }
      }
    }
    return temp;
  }

  public int getColumns() {
    return columns;
  }

  public int getRows() {
    return rows;
  }

  public void positionCheck(int row, int column) throws MineClickException {
    if (table == null) {
      table = createTable(row, column);
    }
    insertOnPosition(row, column);
  }

  protected void insertOnPosition(int row, int column) throws MineClickException {
    if (table[row][column] == '*') {
      throw new MineClickException("You lose");
    }
    recursiveInserting(row, column);
  }

  protected void recursiveInserting(int row, int column) {
    int i = 0;
    if (row < 0 || row >= rows) {
      return;
    }
    if (column < 0 || column >= columns) {
      return;
    }
    if (table[row][column] != '-') {
      return;
    }
    try {
      if (table[row - 1][column - 1] == '*') {
        i++;
      }
    } catch (IndexOutOfBoundsException e) {
    }

    try {
      if (table[row - 1][column] == '*') {
        i++;
      }
    } catch (IndexOutOfBoundsException e) {
    }
    try {
      if (table[row - 1][column + 1] == '*') {
        i++;
      }
    } catch (IndexOutOfBoundsException e) {
    }
    try {
      if (table[row][column - 1] == '*') {
        i++;
      }
    } catch (IndexOutOfBoundsException e) {
    }
    try {
      if (table[row][column + 1] == '*') {
        i++;
      }
    } catch (IndexOutOfBoundsException e) {
    }
    try {
      if (table[row + 1][column - 1] == '*') {
        i++;
      }
    } catch (IndexOutOfBoundsException e) {
    }
    try {
      if (table[row + 1][column] == '*') {
        i++;
      }
    } catch (IndexOutOfBoundsException e) {
    }
    try {
      if (table[row + 1][column + 1] == '*') {
        i++;
      }
    } catch (IndexOutOfBoundsException e) {
    }
    if (i == 0) {
      table[row][column] = ' ';
      recursiveInserting(row - 1, column - 1);
      recursiveInserting(row - 1, column);
      recursiveInserting(row - 1, column + 1);
      recursiveInserting(row, column - 1);
      recursiveInserting(row, column + 1);
      recursiveInserting(row + 1, column - 1);
      recursiveInserting(row + 1, column);
      recursiveInserting(row + 1, column + 1);
    } else {
      table[row][column] = (char) (i + '0');
    }
  }

  public boolean showTable() {
    boolean flag = true;
    System.out.println("Current Status Of The Board : ");
    System.out.print("    ");
    for (int i = 0; i < rows; i++) {
      System.out.print(i + " ");
    }
    System.out.println();
    for (int i = 0; i < table.length; i++) {
      System.out.print(i + "   ");
      for (int j = 0; j < table[i].length; j++) {
        if (table[i][j] == '*') {
          System.out.print("- ");
          continue;
        }
        if (table[i][j] == '-') {
          flag = false;
        }
        System.out.print(table[i][j] + " ");
      }
      System.out.println();
    }
    return flag;
  }

  public void showFullTable() {
    for (char[] characters : table) {
      for (char temp : characters) {
        System.out.print(temp + " ");
      }
      System.out.println();
    }
  }
}
