package org.example;

import java.util.Stack;

public class ExpressionEvaluation {

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

  private static boolean isOperator(char ch) {
    return ch == '+' || ch == '-' || ch == '*' || ch == '/';
  }

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

  private static boolean isMatchingPair(char left, char right) {
    return (left == '(' && right == ')') ||
        (left == '[' && right == ']') ||
        (left == '{' && right == '}');
  }
}
