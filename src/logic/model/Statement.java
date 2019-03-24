package logic.model;

public class Statement {

    private String ID;
    private String postfix;

    public Statement(String ID, String postfix) {
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
