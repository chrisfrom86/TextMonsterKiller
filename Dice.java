import java.util.*;

public class Dice {

  Random random = new Random();

public static int d4(int rolls) {
    int sum = 0;

    for (int i=0; i<rolls; ++i) {
      sum += random.nextInt(4) +1;
    }
    return sum;
  }

  public static int d6(int rolls) {
    int sum = 0;

    for (int i=0; i<rolls; ++i) {
      sum += random.nextInt(6) +1;
    }
    return sum;
  }

  public static int d8(int rolls) {
    int sum = 0;

    for (int i=0; i<rolls; ++i) {
      sum += random.nextInt(8) +1;
    }
    return sum;
  }

  public static int d10(int rolls) {
    int sum = 0;

    for (int i=0; i<rolls; ++i) {
      sum += random.nextInt(10) +1;
    }
    return sum;
  }

 public static int d12(int rolls) {
    int sum = 0;

    for (int i=0; i<rolls; ++i) {
      sum += random.nextInt(12) +1;
    }
    return sum;
  }

 public static int d20(int rolls) {
    int sum = 0;

    for (int i=0; i<rolls; ++i) {
      sum += random.nextInt(20) +1;
    }
    return sum;
  }

}