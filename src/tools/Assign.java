package tools;

import logic.model.Atom;
import logic.model.Statement;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Assign {

    public static List<Atom> createAllAtoms(String input0, String input1) {
        List<Atom> allAtomList = new ArrayList<>();
        HashSet<String> uniqueName = new HashSet<>();
        uniqueName.addAll(Parser.getVars(input0));
        uniqueName.addAll(Parser.getVars(input1));
        for (String name : uniqueName) {
            Atom atom = new Atom(name);
            allAtomList.add(atom);
        }
        return allAtomList;
    }

    public static Statement[] createStatements(String input0, String input1) {
        Statement[] statements = new Statement[2];
        statements[0] = new Statement("statement0", Parser.infixToPostfix(input0));
        statements[1] = new Statement("statement1", Parser.infixToPostfix(input1));
        return statements;
    }

}
