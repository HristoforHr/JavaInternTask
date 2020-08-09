package task;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Demo {
  public static void main(String[] args) {
      BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
      new Game(bufferedReader).startGame();
  }
}
