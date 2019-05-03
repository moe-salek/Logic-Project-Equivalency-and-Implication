package logic.model;

public enum Operator {

    OPERATOR_AND("^", 1),
    OPERATOR_OR("v", 1),
    OPERATOR_NOT("~", 2);

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
            default:
                System.out.println("Operator Error: Operator not found");
                break;
        }
        return null;
    }

}
