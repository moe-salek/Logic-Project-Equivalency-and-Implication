package logic.model;

public class Operation {

    public static Value Operate(Value value0, Value value1, Operator operator) {
        if (value0.equals(Value.NOT_DEFINED) || value1.equals(Value.NOT_DEFINED)) {
            System.out.println("Operation Error: The atom Value is NOT_DEFINED");
            return null;
        }
        Value result;
        switch (operator) {
            case OPERATOR_AND:
                result = andOperation(value0, value1);
                break;
            case OPERATOR_OR:
                result = orOperation(value0, value1);
                break;
            case OPERATOR_IMP:
                result = impOperation(value0, value1);
                break;
//            case OPERATOR_IFF:
                //TODO: implement this kid
//                result = orOperation(value0, value1);
//                break;
            default:
                System.out.println("Operation Error: Unknown operator");
                result = null;
        }
        return result;
    }

    public static Value Operate(Value value, Operator operator) {
        if (value.equals(Value.NOT_DEFINED)) {
            System.out.println("Operation Error: The atom Value is NOT_DEFINED");
            return null;
        }
        Value result;
        if (operator == Operator.OPERATOR_NOT) {
            result = notOperation(value);
        } else {
            System.out.println("Operation Error: Unknown operator");
            result = null;
        }
        return result;
    }

    private static Value notOperation(Value value) {
        if (value == Value.TRUE) {
            return Value.FALSE;
        }
        return Value.TRUE;
    }

    private static Value andOperation(Value value0, Value value1) {
        if (value0.equals(Value.TRUE) && value1.equals(Value.TRUE)) {
            return Value.TRUE;
        }
        return Value.FALSE;
    }

    private static Value orOperation(Value value0, Value value1) {
        if (value0.equals(Value.TRUE) || value1.equals(Value.TRUE)) {
            return Value.TRUE;
        }
        return Value.FALSE;
    }

    private static Value impOperation(Value value0, Value value1) {
//        System.out.println("Input: " + value0 + " and " + value1);
//        System.out.println("Output: " + orOperation(notOperation(value0), value1));
        return orOperation(value0, notOperation(value1)); //NOT the 2nd, cuz in stack 2nd is 1st
    }

}
