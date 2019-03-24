package logic;

import logic.model.*;
import tools.Assign;
import tools.Parser;
import tools.Validation;

import java.util.*;

public class Logic {

    private static List<Atom> allAtoms ;
    private static HashSet<Character> operators;
    private static List<String> combinations;
    private static int currentPhase;

    public static String function(String input0, String input1) {
        input0 = Parser.noSpace(input0);
        input1 = Parser.noSpace(input1);
        if(!Validation.validate(input0) || !Validation.validate(input1)) {
            return null;
        }
        allAtoms = Assign.createAllAtoms(input0, input1);
        Statement[] statements = Assign.createStatements(input0, input1);
        operators = new HashSet<>();
        for (Operator operator : Operator.values()) {
            operators.add(operator.getSign());
        }
        combinations = getCombinations(allAtoms.size());
        currentPhase = combinations.size();

        boolean check = true;
        while (assignAtomValue(allAtoms) >= 0) {
            Value s0Value = findColumnValue(statements[0].getPostfix());
            Value s1Value = findColumnValue(statements[1].getPostfix());
            if (!s0Value.equals(s1Value)) {
                check = false;
                break;
            }
        }
        String result;
        if (check) {
            result = "\"" + input0 + "\"" + " and " + "\"" + input1 + "\"" + " ARE equal";
        } else {
            result = "\"" + input0 + "\"" + " and " + "\"" + input1 + "\"" +  " are NOT equal";
        }
        return result;
    }

    private static Value findColumnValue(String postfix) {
        if(Parser.getVars(postfix).size() == 1) {
            for (Atom atom : allAtoms) {
                if (atom.getID().equals(postfix)) {
                    return atom.getValue();
                }
            }
        }
        Stack<Value> stack = new Stack<>();
        Queue<Character> atomName = new LinkedList<>();
        for (int i = 0; i < postfix.length(); ++i) {
            char ch = postfix.charAt(i);
            if (operators.contains(ch)) {
                if (stack.size() >= 2) {
                    Value value = Operation.Operate(stack.pop(), stack.pop(), Operator.getOperatorByName(ch));
                    stack.add(value);
                }
                else {
                    System.out.println("Logic Error: Not enough values in queue");
                }
            }
            else {
                atomName.add(ch);
                StringBuilder name = new StringBuilder();
                for (char chr : atomName) {
                    name.append(chr);
                }
                for (Atom atom : allAtoms) {
                    if (atom.getID().equals(name.toString())) {
                        stack.add(atom.getValue());
                        atomName.clear();
                    }
                }
            }
        }
        if (stack.size() != 1) {
            System.out.println("Logic Error: More values in stack than expected");
        }
        return stack.pop();
    }

    //Returns the number of the left combinations:
    private static int assignAtomValue(List<Atom> allAtoms) {
        --currentPhase;
        if (currentPhase >= 0) {
            String phase = combinations.get(currentPhase);
            for (int i = 0; i < phase.length(); ++i) {
                if (phase.charAt(i) == '0') {
                    allAtoms.get(i).setValue(Value.FALSE);
                } else {
                    allAtoms.get(i).setValue(Value.TRUE);
                }
            }
        }
        return currentPhase;
    }

    private static List<String> getCombinations(int num) {
        List<String> combinations = new ArrayList<>();
        int number = 0;
        for (int i = 0; i < Math.pow(2, num); ++i) {
            StringBuilder res = new StringBuilder(Integer.toString(number, 2));
            while (res.length() < num) {
                res.insert(0, '0');
            }
            combinations.add(res.toString());
            number++;
        }
        return combinations;
    }

}
