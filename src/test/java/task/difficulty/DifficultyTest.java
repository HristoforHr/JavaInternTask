package task.difficulty;


import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runner.RunWith;
import task.exceptions.MineClickException;

@RunWith(DataProviderRunner.class)
class DifficultyTest {
  @DataProvider
  static Object[] dataProvider(){
    return new Object [] { new BeginnerDifficulty(),
                           new AdvancedDifficulty(),
                          new IntermediateDifficulty()};

  }
  @ParameterizedTest
  @MethodSource("dataProvider")
  void positionCheck(Difficulty difficulty) throws MineClickException {
      difficulty.positionCheck(2,3);
  }

}