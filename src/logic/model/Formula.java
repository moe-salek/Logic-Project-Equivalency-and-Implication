package logic.model;

public class Formula {

    private String ID;
    private String postfix;

    public Formula(String ID, String postfix) {
        this.ID = ID;
        this.postfix = postfix;
    }

    public String getID() {
        return ID;
    }

    public String getPostfix() {
        return postfix;
    }

}
