  import static org.junit.jupiter.api.Assertions.assertEquals;

  import org.example.ExpressionEvaluation;
  import org.junit.jupiter.api.Test;

  public class ExpressionEvaluationTest {

    @Test
    void expressionCountingTest(){
      String[] expressions = new String[] {
          "5123",
          "145 + 5",
          "2*7",
          "2 * 3 + 4 * (1 + 2)",
          "2+3*4",
          "10 / (3 + 2)",
          "100 - (50 + 25)",
          "2.5 + 3.5 * 2 - 1.5",
          "(6 / 2) + (5 * 3)",
          "((9 - 3) * (8 / 2)) - 4"
      };

      double[] results = new double[] {
          5123.0,
          150.0,
          14.0,
          18.0,
          14.0,
          2.0,
          25.0,
          8.0,
          18.0,
          20.0
      };

      for (int i = 0; i < expressions.length; ++i) {
        assertEquals(ExpressionEvaluation.evaluateExpression(expressions[i]),results[i]);
      }
    }
  }
