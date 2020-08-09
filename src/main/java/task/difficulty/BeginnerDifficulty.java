package task.difficulty;

public class BeginnerDifficulty extends Difficulty {
  public static final int ROWS = 9;
  public static final int COLUMNS = 9;
  public static final int MINES = 10;

  public BeginnerDifficulty() {
    super(ROWS, COLUMNS, MINES);
  }
}
