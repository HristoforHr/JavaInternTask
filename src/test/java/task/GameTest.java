package task;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.BufferedReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
  BufferedReader bufferedReader = Mockito.mock(BufferedReader.class);

  @Test
  void startGame() throws IOException {
    new Game(bufferedReader).startGame();
    Mockito.when(bufferedReader.readLine()).thenReturn("5","12","O" , "null","0");
  }
}