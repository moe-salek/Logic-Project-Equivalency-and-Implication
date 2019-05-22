package logic.model;

import java.util.HashMap;

public enum Operator {

    OPERATOR_NOT("~", 3),
    OPERATOR_AND("^", 2),
    OPERATOR_OR("v", 2),
    OPERATOR_IMP("->", 1),
    OPERATOR_IFF("<->", 1);

    private final String sign;
    private final int priority;

    private static HashMap<String, Integer> operators = new HashMap<>();

    static {
        operators.put(Operator.OPERATOR_AND.getSign(), Operator.OPERATOR_AND.getPriority());
        operators.put(Operator.OPERATOR_OR.getSign(), Operator.OPERATOR_OR.getPriority());
        operators.put(Operator.OPERATOR_NOT.getSign(), Operator.OPERATOR_NOT.getPriority());
        operators.put(Operator.OPERATOR_IMP.getSign(), Operator.OPERATOR_IMP.getPriority());
        operators.put(Operator.OPERATOR_IFF.getSign(), Operator.OPERATOR_IFF.getPriority());
    }

    public static HashMap<String, Integer> getOperators() {
        return operators;
    }

    Operator(final String sign, final int priority) {
        this.sign = sign;
        this.priority = priority;
    }

    public String getSign() {
        return sign;
    }

    public int getPriority() {
        return priority;
    }

    public static Operator getOperatorByName(String name) {
        switch (name) {
            case "^":
                return OPERATOR_AND;
            case "v":
                return OPERATOR_OR;
            case "~":
                return OPERATOR_NOT;
            case "->":
                return OPERATOR_IMP;
            case "<->":
                return OPERATOR_IFF;
            default:
                System.out.println("Operator Error: Operator not found");
                break;
        }
        return null;
    }

}