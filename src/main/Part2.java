package main;

import java.util.Set;
import java.util.TreeSet;

public class Part2 {

    public static Boolean TT_Entails(Set<Clause> kb, Set<Clause> a) {
        System.out.println("\nChecking if the knowledge base entails " + 
        stringOfClauses(a));

        // Create list of symbols
        // Only keep positive ints of the clauses
        TreeSet<Integer> symbols = new TreeSet<Integer>();
        
        for (Clause c: kb) {
            for (int l: c.literals) {
                if (l > 0) {
                    symbols.add(l);
                } else {
                    symbols.add(l*(-1));
                }
            }
        }
        for (Clause c: a) {
            for (int l: c.literals) {
                if (l > 0) {
                    symbols.add(l);
                } else {
                    symbols.add(l*(-1));
                }
            }
        }

        // Create an assignment table the size of symbols
        Boolean[] model = new Boolean[symbols.size()];
        // Initialize all to unassigned
        for (int i = 0; i < model.length; i++) {
            model[i] = null;
        }
        Boolean value = TT_CheckAll(kb, a, symbols, model);
        if (value == true) {
            System.out.println("\nANSWER");
            System.out.println("Yes, it does entail");
        } else {
            System.out.println("\nANSWER");
            System.out.println("No, it does not entail.");
        }
        return value;
    }

    static Boolean TT_CheckAll(Set<Clause> kb, Set<Clause> a, TreeSet<Integer> symbols, Boolean[] model) {
        if (symbols.isEmpty()) {
            if (PL_True(kb, model)) {
                System.out.print("\nKnowledge base holds in: \n" + stringBoolArray(model));
                System.out.println("Checking if the query " + stringOfClauses(a) + " holds.");
                Boolean val = PL_True(a, model);
                if (val == true) {
                    System.out.println("Query holds.");
                } else {
                    System.out.println("Query doesn't hold.");
                }
                return val;
            } else {
                return true;
            }
        } else {
            int p = symbols.first();
            TreeSet<Integer> rest = new TreeSet<Integer>(symbols);
            rest.remove(rest.first());
            Boolean[] model1 = model.clone();
            model1[p-1] = true;
            Boolean[] model2 = model.clone();
            model2[p-1] = false;

            return (TT_CheckAll(kb, a, rest, model1) && TT_CheckAll(kb, a, rest, model2));
        }
    }

    // Returns true if a given sentence holds in the given model
    static Boolean PL_True(Set<Clause> sentence, Boolean[] model) {

        for (Clause c: sentence) {

            // tracks if any literals are assigned true
            boolean exists_a_truth = false;
            // tracks if any literals are unassigned
            boolean exists_a_null = false;

            for (int i: c.literals) {
                Boolean value = lookupValue(i, model);

                if (value) { exists_a_truth = true; }
                if (value == null) { exists_a_null = true; }
            }

            // If there is no truth value assigned to a literal in the clause
            if (!exists_a_truth) {

                // If any of the variables are unassigned
                if (exists_a_null) { return null; } 
                // Else return false
                else { return false; }
            }
        }

        // If all clauses contain a truth value
       
        return true;
    }

    // Returns the assigned value of i in the given model
    static Boolean lookupValue(int i, Boolean[] model) {

        // if i is not negative, return its value
        // must use i-1 because java counts starting from 0
        if ((i-1) >= 0) { return model[i-1]; } 
        
        // else return the opposite value of i's positive counterpart
        else {
            if (model[(i+1)*(-1)]) {
                
                return false;
            } else if (!model[(i+1)*(-1)]) {
                return true;
            } else {
                return null;
            }
        }
    }

    // Method to help with printing sets of clauses
    public static String stringOfClauses(Set<Clause> clauses) {
        return clauses.toString().replace("[","").replace("]","");
    }

    // Method to help with printing a Boolean array
    static String stringBoolArray(Boolean[] bools) {
        String s = "";
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < bools.length-1; j++) {
                if (i == 0) {
                    s = s.concat(j + 1 + "|");
                } if (i == 1) {
                    s = s.concat("--");
                } if (i == 2) {
                    if (bools[j].equals(true)) {
                        s = s.concat("T|");
                    } else if (bools[j].equals(false)) {
                        s = s.concat("F|");
                    } else {
                        s = s.concat(" |");
                    }
                }
            }
            if (i == 0) { 
                s = s.concat(Integer.toString(bools.length)); 
            } else if (i == 1) {
                s = s.concat("-");
            } else if (i == 2) {
    
                if (bools[bools.length-1] == null) {
                    s = s.concat(" ");
                } else if (bools[bools.length-1].equals(true)) {
                    s = s.concat("T");
                } else if (bools[bools.length-1].equals(false)) {
                    s = s.concat("F");
                }
            }
            s = s.concat("\n");
        }
        return s;
     }

}