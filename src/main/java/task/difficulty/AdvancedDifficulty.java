package task.difficulty;

public class AdvancedDifficulty extends Difficulty {
  public static final int ROWS = 24;
  public static final int COLUMNS = 24;
  public static final int MINES = 99;

  public AdvancedDifficulty() {
    super(ROWS, COLUMNS, MINES);
  }
}
