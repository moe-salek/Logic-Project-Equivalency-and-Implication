package tools;

import logic.model.Characters;
import logic.model.Operator;

import java.util.*;

public class Parser {

    private static HashMap<String, Integer> operators = new HashMap<>();

    static {
        operators.put(Operator.OPERATOR_AND.getSign(), Operator.OPERATOR_AND.getPriority());
        operators.put(Operator.OPERATOR_OR.getSign(), Operator.OPERATOR_OR.getPriority());
        operators.put(Operator.OPERATOR_NOT.getSign(), Operator.OPERATOR_NOT.getPriority());
    }

    public static String infixToPostfix(String input) {
        StringBuilder output = new StringBuilder();
        Stack<String> stackOprtr = new Stack<>();
        for (int i = 0; i < input.length(); ++i) {
            char ch = input.charAt(i);

            if (Characters.getChars4Var().indexOf(ch) != -1) {
                output.append(ch);
            }

            else if (ch == '(' || ch == ')') {
                if (ch == '(') {
                    stackOprtr.push(ch + "");
                }
                else {
                    while (!stackOprtr.peek().equals("(")) {
                        output.append(stackOprtr.pop());
                    }
                    stackOprtr.pop(); //trash the '('
                }
            }

            else if (ch == '$') {

                //get the operator:
                StringBuilder tmp = new StringBuilder();
                do {
                    tmp.append(ch);
                    ++i;
                    ch = input.charAt(i);
                } while (ch != '@');
                tmp.append('@');
                String operator = tmp.toString();

                while (!stackOprtr.isEmpty() &&
                        !stackOprtr.peek().equals("(") &&
                        (Operator.getOperatorByName(stackOprtr.peek().substring(1, stackOprtr.peek().length() - 1)).getPriority() >=
                                Operator.getOperatorByName(operator.substring(1, operator.length() - 1)).getPriority() )
                ) {
                    output.append(stackOprtr.pop());
                }

                stackOprtr.push(operator);
            }

        }
        while (!stackOprtr.isEmpty()) {
            output.append(stackOprtr.pop());
        }
//        System.out.println(output.toString());
        return output.toString();
    }

    static List<String> getVars(String input) {
        List<String> vars = new ArrayList<>();
        StringBuilder var = new StringBuilder();
        Queue<Character> queue = new LinkedList<>();
        boolean iteratingOperator = false;
        for (int i = 0; i < input.length(); ++i) {
            char ch = input.charAt(i);
            if (ch == '$') {
                iteratingOperator = true;
            }
            else if (ch == '@') {
                iteratingOperator = false;
            }
            else if (ch != '(' &&
                    ch != ')' &&
                    !iteratingOperator &&
                    i != (input.length() - 1)) {
                queue.add(ch);
            }
            else {
                if (ch != '(' &&
                        ch != ')' &&
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
        for (String operator : operators.keySet()) {
            if (operator.equals("~")) {
                continue;
            }
            int idx = 0;
            while (idx != -1) {
                idx = input.indexOf(operator, idx + 1);
                if (idx != -1) {
                    ++result[1];
                }
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

    public static String putSignForOperator(String input) {
        String output = input;
        for (String operator : operators.keySet()) {
            int idx = output.indexOf(operator);
            while (idx != -1) {
                output = output.substring(0, idx) + "$" + output.substring(idx, idx + operator.length()) + '@' +
                        output.substring(idx + operator.length());
                idx = output.indexOf(operator, idx + operator.length() + 2);
            }
        }
        return output;
    }

}
