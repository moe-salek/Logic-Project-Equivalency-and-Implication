package tools;

import logic.model.Atom;
import logic.model.Formula;

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

    public static Formula[] createFormulas(String input0, String input1) {
        Formula[] formulas = new Formula[2];
        formulas[0] = new Formula("formula0", Parser.infixToPostfix(input0));
        formulas[1] = new Formula("formula1", Parser.infixToPostfix(input1));
        return formulas;
    }

}
