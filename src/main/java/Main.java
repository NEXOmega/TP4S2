import view.Controller;
//import test.TestRunner;

public class Main {

  private static final boolean isInTestMode = false; // Change this to execute tests
  
  public static void main(String[] args) {
    /*if (isInTestMode) {
      runTest();
    } else {
      new Controller();
    }*/
    new Controller();
  }

  /*public static void runTest() {
    TestRunner.main(
      model.DieTest.class//,
      //model.ScoreRowTest.class
    );
  }*/

}
