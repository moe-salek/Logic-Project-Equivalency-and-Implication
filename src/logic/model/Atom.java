package logic.model;

public class Atom {

    private String ID;
    private Value value = Value.NOT_DEFINED;

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

    public Value getValue() {
        return value;
    }
}
