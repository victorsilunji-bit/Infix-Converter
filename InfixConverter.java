import java.util.Scanner;
import java.util.Stack;

public class InfixConverter {

    // Function to define operator precedence
    static int precedence(char ch) {
        switch (ch) {
            case '+':
            case '-':
                return 1;

            case '*':
            case '/':
                return 2;

            case '^':
                return 3;
        }
        return -1;
    }

    // Function: Infix to Postfix
    static String infixToPostfix(String expression) {
        StringBuilder result = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);

            // If operand, add to result
            if (Character.isLetterOrDigit(ch)) {
                result.append(ch);
            }

            // If '(' push to stack
            else if (ch == '(') {
                stack.push(ch);
            }

            // If ')' pop until '('
            else if (ch == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    result.append(stack.pop());
                }
                stack.pop(); // remove '('
            }

            // If operator
            else {
                while (!stack.isEmpty() &&
                        precedence(ch) <= precedence(stack.peek())) {
                    result.append(stack.pop());
                }
                stack.push(ch);
            }
        }

        // Pop remaining operators
        while (!stack.isEmpty()) {
            result.append(stack.pop());
        }

        return result.toString();
    }

    // Function: Infix to Prefix
    static String infixToPrefix(String expression) {

        // Step 1: Reverse expression
        StringBuilder reversed = new StringBuilder(expression);
        reversed.reverse();

        // Step 2: Swap brackets
        for (int i = 0; i < reversed.length(); i++) {
            if (reversed.charAt(i) == '(')
                reversed.setCharAt(i, ')');
            else if (reversed.charAt(i) == ')')
                reversed.setCharAt(i, '(');
        }

        // Step 3: Convert to postfix
        String postfix = infixToPostfix(reversed.toString());

        // Step 4: Reverse postfix to get prefix
        StringBuilder prefix = new StringBuilder(postfix);
        return prefix.reverse().toString();
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Infix Expression: ");
        String infix = scanner.nextLine();

        String postfix = infixToPostfix(infix);
        String prefix = infixToPrefix(infix);

        System.out.println("Postfix Expression: " + postfix);
        System.out.println("Prefix Expression: " + prefix);

        scanner.close();
    }
}