package main;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

public class Part3 {

   public static Boolean[] GSAT(Set<Clause> a, int MAX_FLIPS, int MAX_TRIES) {
       for (int i = 1; i <= MAX_TRIES; i++) {
            Boolean[] T = randomBoolArray(a);
            // System.out.println("\n\n\n");
            for (int j = 1; j <= MAX_FLIPS; j++) {
                // System.out.println("Checking:\n" + Part2.stringBoolArray(T));
                if (Part2.PL_True(a, T)) {
                    System.out.println("Satisfied by:\n" + Part2.stringBoolArray(T));
                    return T;
                }
                int p = findP(a, T);
                T[p] = oppBool(T[p]);
            }
       }
       System.out.println("No satisfying assignment found.");
       return null;
   }

   static Boolean[] randomBoolArray(Set<Clause> clauses) {
        // Create list of symbols
        // Only keep positive ints of the clauses
        TreeSet<Integer> symbols = new TreeSet<Integer>();
        
        for (Clause c: clauses) {
            for (int l: c.literals) {
                if (l > 0) {
                    symbols.add(l);
                } else {
                    symbols.add(l*(-1));
                }
            }
        }
        
       Boolean[] T = new Boolean[symbols.size()];
       for (int i = 0; i < T.length; i++) {
            double j = Math.random();
            if (j < 0.5) {
                T[i] = true;
            } else {
                T[i] = false;
            }
       }
       return T;
   }

   static int satisfiedClauses(Set<Clause> clauses, Boolean[] T) {
       int satisfied = 0;
       for (Clause c: clauses) {
            for (int i: c.literals) {
                Boolean value = Part2.lookupValue(i, T);

                if (value) { 
                    satisfied++;
                    break;
                }
            }
        }
        return satisfied;
    }

   static Boolean oppBool(Boolean b) {
       if (b.equals(true)) {
           return false;
       } else {
           return true;
       }
   }
 
   static int findP(Set<Clause> a, Boolean[] T) {
       int max = Integer.MIN_VALUE;
       ArrayList<Integer> indexes = new ArrayList<Integer>();
       for (int i = 0; i < T.length; i++) {
           System.out.println("\nFlipping index: " + i);
            T[i] = oppBool(T[i]);
            // System.out.print("Table: \n" + Part2.stringBoolArray(T));
            int val = satisfiedClauses(a, T);
            System.out.println("Total number satisfied: " + val);
            if (val > max) {
                max = val;
                indexes.clear();
                indexes.add(i);
                // System.out.println("Max indexes: " + indexes);
            } else if (val == max) {
                indexes.add(i);
                // System.out.println("Max indexes: " + indexes);
            }
            T[i] = oppBool(T[i]);
       }
    //    System.out.println("Max indexes: " + indexes);
       int result = indexes.get((int) Math.floor(Math.random() * indexes.size()));
       System.out.println("Choosing index: " + result);
       return result;
   }

   public static void Q1() {
  
    Clause c1 = new Clause(1, 3, -4);
    Clause c2 = new Clause(4);
    Clause c3 = new Clause(2, -3);
    Set<Clause> kb = new LinkedHashSet<Clause>();
    kb.add(c1);
    kb.add(c2);
    kb.add(c3);
   
    System.out.println("\nQUESTION 1");
    System.out.println("\nKnowledge Base: " + Part2.stringOfClauses(kb));
    GSAT(kb, 2, 10);
   }

   public static void Q2_1() {
    Set<Clause> kb = new LinkedHashSet<Clause>(Clause.scanCNF("nqueens_4.cnf"));
    System.out.println("\nNQUEENS 4");
    System.out.println("\nKnowledge Base: " + Part2.stringOfClauses(kb));
    GSAT(kb, 50, 500);
   }

  
   public static void Q2_2() {
    Set<Clause> kb = new LinkedHashSet<Clause>(Clause.scanCNF("nqueens_8.cnf"));
    System.out.println("\nNQUEENS 8");
    System.out.println("\nKnowledge Base: " + Part2.stringOfClauses(kb));
    GSAT(kb, 50, 16);
}

public static void Q2_3() {
    Set<Clause> kb = new LinkedHashSet<Clause>(Clause.scanCNF("nqueens_12.cnf"));
    System.out.println("\nNQUEENS 12");
    System.out.println("\nKnowledge Base: " + Part2.stringOfClauses(kb));
    GSAT(kb, 50, 150);
}

public static void Q3_1() {
    Set<Clause> kb = new LinkedHashSet<Clause>(Clause.scanCNF("quinn.cnf.txt"));
    System.out.println("\nQUESTION 3");
    System.out.println("\nKnowledge Base: " + Part2.stringOfClauses(kb));
    GSAT(kb, 50, 50);
}

   public static void Q3_2() {
    Set<Clause> kb = new LinkedHashSet<Clause>(Clause.scanCNF("par8-1-c.cnf"));
    System.out.println("\nQUESTION 4");
    System.out.println("\nKnowledge Base: " + Part2.stringOfClauses(kb));
    GSAT(kb, 60, 50);
}

   public static void main(String args[]) {
        Q2_3();
   }
}
