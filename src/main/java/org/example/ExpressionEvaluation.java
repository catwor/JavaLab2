package org.example;

import java.util.Stack;

public class ExpressionEvaluation {




  private boolean isOperator(char ch) {
    return ch == '+' || ch == '-' || ch == '*' || ch == '/';
  }

  public boolean isValidExpression(String expression) {
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

  private boolean isMatchingPair(char left, char right) {
    return (left == '(' && right == ')') ||
        (left == '[' && right == ']') ||
        (left == '{' && right == '}');
  }
}
