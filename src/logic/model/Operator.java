package logic.model;

public enum Operator {

    OPERATOR_NOT("~", 3),
    OPERATOR_AND("^", 2),
    OPERATOR_OR("v", 2),
    OPERATOR_IMP("->", 1),
    OPERATOR_IFF("<->", 1);

    private final String sign;
    private final int priority;

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