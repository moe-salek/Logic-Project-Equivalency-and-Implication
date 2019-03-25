package tools;

import logic.model.Operator;

import java.util.*;

public class Parser {

    static HashMap<Character, Integer> operators = new HashMap<>();

    static {
        operators.put(Operator.OPERATOR_AND.getSign(), Operator.OPERATOR_AND.getPriority());
        operators.put(Operator.OPERATOR_OR.getSign(), Operator.OPERATOR_OR.getPriority());
        operators.put(Operator.OPERATOR_NOT.getSign(), Operator.OPERATOR_NOT.getPriority());
    }

    static String infixToPostfix(String input) {
        StringBuilder output = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < input.length(); ++i) {
            char ch = input.charAt(i);
            if (operators.keySet().contains(ch)) {
                while (!stack.isEmpty() &&
                        operators.containsKey(stack.peek()) &&
                        operators.get(stack.peek()) >= operators.get(ch)) {
                    output.append(stack.pop());
                }
                stack.push(ch);
            }
            else {
                if (ch == '(') {
                    stack.push(ch);
                }
                else if (ch == ')') {
                    while (stack.peek() != '(') {
                        output.append(stack.pop() );
                    }
                    stack.pop();
                }
                else {
                    output.append(ch);
                }
            }
        }
        while (!stack.isEmpty()) {
            output.append(stack.pop());
        }
        return output.toString();
    }

    static List<String> getVars(String input) {
        List<String> vars = new ArrayList<>();
        StringBuilder var = new StringBuilder();
        Queue<Character> queue = new LinkedList<>();
        for (int i = 0; i < input.length(); ++i) {
            char ch = input.charAt(i);
            if (ch != '(' &&
                    ch != ')' &&
                    !operators.containsKey(ch) &&
                    i != (input.length() - 1)) {
                queue.add(ch);
            }
            else {
                if (ch != '(' &&
                        ch != ')' &&
                        !operators.containsKey(ch) &&
                        i == (input.length() - 1)) {
                    queue.add(ch);
                }
                while(!queue.isEmpty()) {
                    var.append(queue.poll());
                }
                if (var.length() > 0) {
                    vars.add(var.toString());
                }
                var.setLength(0);
            }
        }
        return vars;
    }

    static int[] getVarAndOperatorCount(String input) {
        int[] result = new int[2];
        result[0] = getVars(input).size();
        for (int i = 0; i < input.length(); ++i) {
            char ch = input.charAt(i);
            if (operators.containsKey(ch) && (ch != '~')) {
                result[1]++;
            }
        }
        return result;
    }

    public static String noSpace(String input) {
        return input.replaceAll("\\s", "");
    }

    public static String noDoubleNot(String input) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < input.length(); ++i) {
            char ch = input.charAt(i);
            if ((i + 1 < input.length()) && (ch == '~') && (input.charAt(i + 1) == '~')) {
                i = i + 1;
            }
            else {
                output.append(ch);
            }
        }
        return output.toString();
    }

}
