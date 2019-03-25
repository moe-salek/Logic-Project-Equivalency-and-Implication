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
        String noSpaceInput0 = Parser.noSpace(input0);
        String noSpaceInput1 = Parser.noSpace(input1);
        input0 = noSpaceInput0;
        input1 = noSpaceInput1;
        if(Validation.validate(input0) || Validation.validate(input1)) {
            return null;
        }
        input0 = Parser.noDoubleNot(input0);
        input1 = Parser.noDoubleNot(input1);
        allAtoms = Assign.createAllAtoms(input0, input1);
        Formula[] formulas = Assign.createFormulas(input0, input1);
        operators = new HashSet<>();
        for (Operator operator : Operator.values()) {
            operators.add(operator.getSign());
        }
        combinations = getCombinations(allAtoms.size());
        currentPhase = combinations.size();

        boolean check = true;
        while (currentPhase >= 0) {
            assignAtomValue(allAtoms);
            Value f0Value = findColumnValue(formulas[0].getPostfix());
            Value f1Value = findColumnValue(formulas[1].getPostfix());
            if (!f0Value.equals(f1Value)) {
                check = false;
                break;
            }
        }

        String result;
        if (check) {
            result = "Formulas: \"" + noSpaceInput0 + "\"" + " and " + "\"" + noSpaceInput1 + "\"" + " ARE equivalent.";
        } else {
            result = "Formulas: \"" + noSpaceInput0 + "\"" + " and " + "\"" + noSpaceInput1 + "\"" +  " are NOT equivalent.";
        }
        return result;
    }

    private static Value findColumnValue(String postfix) {
        Stack<Value> stack = new Stack<>();
        Queue<Character> atomName = new LinkedList<>();
        for (int i = 0; i < postfix.length(); ++i) {
            char ch = postfix.charAt(i);
            if (operators.contains(ch)) {

                if (ch == '~') {
                    Value value = Operation.Operate(stack.pop(), Operator.getOperatorByName(ch));
                    stack.add(value);
                }
                else if (stack.size() >= 2) {
                    Value value = Operation.Operate(stack.pop(), stack.pop(), Operator.getOperatorByName(ch));
                    stack.add(value);
                }
                else {
                    System.out.println("Logic Error: Not enough values in stack");
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
        if (stack.size() > 1) {
            System.out.println("Logic Error: More values in stack than expected");
        }
        else if (stack.size() < 1) {
            System.out.println("Logic Error: No values in stack");
        }
        return stack.pop();
    }

    private static void assignAtomValue(List<Atom> allAtoms) {
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
