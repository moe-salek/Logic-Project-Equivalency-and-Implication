package logic.model;

public class Atom {

    private String ID;
    private Value value = Value.NOT_DEFINED;
    private Boolean NOT = false;

    public Atom(String ID) {
        this.ID = ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getID() {
        return ID;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    public void setNOT(Boolean NOT) {
        this.NOT = NOT;
    }

    public Value getValue() {
        if (NOT) {
            if (value == Value.FALSE) {
                return Value.TRUE;
            }
            else if (value == Value.TRUE) {
                return Value.FALSE;
            }
        }
        return value;
    }
}
