package logic.model;

public enum Operator {
    OPERATOR_AND('^', 1),
    OPERATOR_OR('v', 1),
    OPERATOR_NOT('~', 2);

    private final Character sign;
    private final int priority;

    Operator(final Character sign, final int priority) {
        this.sign = sign;
        this.priority = priority;
    }

    public Character getSign() {
        return sign;
    }

    public int getPriority() {
        return priority;
    }

    public static Operator getOperatorByName(Character name) {
        if (name == '^') {
            return OPERATOR_AND;
        }
        else if (name == 'v') {
            return OPERATOR_OR;
        }
        else if (name == '~') {
            return OPERATOR_NOT;
        }
        else {
            System.out.println("Operator Error: Operator not found");
        }
        return null;
    }

}
