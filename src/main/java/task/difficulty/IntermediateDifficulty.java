package task.difficulty;

public class IntermediateDifficulty extends Difficulty {
  public static final int ROWS = 16;
  public static final int COLUMNS = 16;
  public static final int MINES = 40;

  public IntermediateDifficulty() {
    super(ROWS, COLUMNS, MINES);
  }
}
