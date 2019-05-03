package tools;

import java.util.ArrayList;
import java.util.List;

public class Validation {

    public static boolean validate(String input) {
        return !isValid(input);
    }

    private static boolean isValid(String input) {

        if (input.length() == 0) {
            System.out.println("Syntax Error: No Formulas");
            return false;
        }

        int indx = 0;
        while((indx < input.length() - 1) &&
                ((input.charAt(indx) == ')') ||
                (input.charAt(indx) == '(') ||
                (input.charAt(indx) == '~'))) {
            ++indx;
        }
        if (input.charAt(indx) == '$') {
            System.out.println("Syntax Error: Began with operator");
            return false;
        }

        indx = input.length() - 1;
        while ((indx > 0) &&
                ((input.charAt(indx) == ')') ||
                (input.charAt(indx) == '(')) ||
                (input.charAt(indx) == '~')) {
            --indx;
        }
        if (input.charAt(indx) == '@') {
            System.out.println("Syntax Error: Ended with operator");
            return false;
        }

        int[] varAndOperatorCount = Parser.getVarAndOperatorCount(input);
        if ((varAndOperatorCount[0] - 1) != varAndOperatorCount[1]) {
            if ((varAndOperatorCount[0] != 1) || (varAndOperatorCount[1] != 0)) {
                System.out.println("Syntax Error: Variable and operator number do not match");
                return false;
            }
        }
        else if (varAndOperatorCount[0] == 0) {
            System.out.println("Syntax Error: Not enough variables");
            return false;
        }

        List<Boolean> openPrnths = new ArrayList<>();
        List<Boolean> closePrnths = new ArrayList<>();
        for (int i = 0; i < input.length(); ++i) {
            char ch = input.charAt(i);

            if (ch == '~') {
                if (i == input.length() - 1) {
                    System.out.println("Syntax Error: ~ used in the end of formula");
                    return false;
                }
                if (i < input.length() - 1) {
                    char nextCh = input.charAt(i + 1);
                    if (nextCh == '$') {
                        System.out.println("Syntax Error: ~ used before an operator");
                        return false;
                    }
                }
            }

            if ((i < input.length() - 1) && (i > 0) && (ch == '$')) {
                if (input.charAt(i - 1) == '@') {
                    if (input.charAt(i - 1) != '~'){
                        System.out.println("Syntax Error: Operators next to each other");
                        return false;
                    }
                }
            }

            if (ch == '(') {
                if (i < input.length() - 1) {
                    if (input.charAt(i + 1) == ')') {
                        System.out.println("Syntax Error: Empty parentheses");
                        return false;
                    }
                }
                openPrnths.add(true);
            }
            else if (ch == ')') {
                closePrnths.add(true);
                if (openPrnths.isEmpty() || (openPrnths.size() < closePrnths.size())) {
                    System.out.println("Syntax Error: ')' before '(' ");
                    return false;
                }
            }
        }
        if (openPrnths.size() != closePrnths.size()) {
            System.out.println("Syntax Error: check parentheses");
            return false;
        }

        return true;
    }

}
