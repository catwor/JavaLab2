package org.example;

import java.util.Stack;

/**
 * Класс для вычисления математических выражений, содержащих цифры, операторы и скобки.
 */
public class ExpressionEvaluation {

  /**
   * Вычисляет математическое выражение.
   * @param expression математическое выражение для вычисления
   * @return результат вычисления выражения
   */
  public static double evaluateExpression(String expression) {
    if (!isValidExpression(expression)) {
      System.out.println("Некорректное выражение");
      return Double.NaN;
    }

    Stack<Double> numbers = new Stack<>();
    Stack<Character> operators = new Stack<>();

    for (int i = 0; i < expression.length(); i++) {
      char ch = expression.charAt(i);

      if (Character.isDigit(ch)) {
        StringBuilder numStr = new StringBuilder();
        while (i < expression.length() && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
          numStr.append(expression.charAt(i));
          i++;
        }
        i--;
        numbers.push(Double.parseDouble(numStr.toString()));
      } else if (ch == '(') {
        operators.push(ch);
      } else if (ch == ')') {
        while (operators.peek() != '(') {
          numbers.push(performOperation(operators.pop(), numbers.pop(), numbers.pop()));
        }
        operators.pop();
      } else if (isOperator(ch)) {
        while (!operators.isEmpty() && precedence(operators.peek()) >= precedence(ch)) {
          numbers.push(performOperation(operators.pop(), numbers.pop(), numbers.pop()));
        }
        operators.push(ch);
      }
    }

    while (!operators.isEmpty()) {
      numbers.push(performOperation(operators.pop(), numbers.pop(), numbers.pop()));
    }

    return numbers.pop();
  }

  /**
   * Выполняет математическую операцию.
   * @param operator оператор для выполнения
   * @param b второй операнд
   * @param a первый операнд
   * @return результат операции
   */
  private static double performOperation(char operator, double b, double a) {
    switch (operator) {
      case '+':
        return a + b;
      case '-':
        return a - b;
      case '*':
        return a * b;
      case '/':
        if (b == 0) {
          throw new ArithmeticException("Деление на ноль!");
        }
        return a / b;
      default:
        return Double.NaN;
    }
  }

  /**
   * Возвращает приоритет оператора.
   * @param operator оператор
   * @return приоритет оператора
   */
  private static int precedence(char operator) {
    switch (operator) {
      case '+':
      case '-':
        return 1;
      case '*':
      case '/':
        return 2;
      default:
        return -1;
    }
  }

  /**
   * Проверяет, является ли символ оператором.
   * @param ch символ
   * @return true, если символ является оператором, иначе false
   */
  private static boolean isOperator(char ch) {
    return ch == '+' || ch == '-' || ch == '*' || ch == '/';
  }

  /**
   * Проверяет, является ли выражение корректным.
   * @param expression выражение
   * @return true, если выражение корректно, иначе false
   */
  private static boolean isValidExpression(String expression) {
    Stack<Character> stack = new Stack<>();
    for (char ch : expression.toCharArray()) {
      if (ch == '(' || ch == '[' || ch == '{') {
        stack.push(ch);
      } else if (ch == ')' || ch == ']' || ch == '}') {
        if (stack.isEmpty() || !isMatchingPair(stack.pop(), ch)) {
          return false;
        }
      }
    }
    return stack.isEmpty();
  }

  /**
   * Проверяет, являются ли скобки парными.
   * @param left  левая скобка
   * @param right правая скобка
   * @return true, если скобки парные, иначе false
   */
  private static boolean isMatchingPair(char left, char right) {
    return (left == '(' && right == ')') ||
        (left == '[' && right == ']') ||
        (left == '{' && right == '}');
  }
}
