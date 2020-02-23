package main;

import java.util.LinkedHashSet;
import java.util.Set;

public class Test {
    
    public static void Q1() {
        // P is represented as 1
        Clause c1 = new Clause(1);
        // P -> Q in CNF is ~P or Q
        Clause c2 = new Clause(-1, 2);
        Set<Clause> kb = new LinkedHashSet<Clause>();
        kb.add(c1);
        kb.add(c2);
        Clause q1 = new Clause(2);
        Set<Clause> a = new LinkedHashSet<Clause>();
        a.add(q1);
        System.out.println("\nQUESTION 1");
        System.out.println("\nKnowledge Base: " + Part2.stringOfClauses(kb));
        Part2.TT_Entails(kb, a);
    }

    public static void Q2() {
        
        LinkedHashSet<Clause> kb = new LinkedHashSet<Clause>();
        // B_1,1 = 1, B_1,2 = 2, B_2,1 = 3
        // P_1,1 = 4, P_1,2 = 5, P_2,1 = 6, P_2,2 = 7, P_1,3 = 8, P_3,1 = 9

        // ~P_1,1
        Clause R1 = new Clause(-4);
        kb.add(R1);
        // B_1,1 <-> P_1,2 or P_2,1 in CNF...
        // (~B_1,1 or P_1,2 or P_2,1)
        Clause R2_1 = new Clause(-1, 5, 6);
        kb.add(R2_1);
        // (~P_1,2 or B_1,1)
        Clause R2_2 = new Clause(-5, 1);
        kb.add(R2_2);
        // (~P_2,1 or B_1,1)
        Clause R2_3 = new Clause(-6, 1);
        kb.add(R2_3);

        // B_2,1 <-> P_1,1 or P_2,2 or P_3,1 in CNF...
        // (~B_2,1 or P_1,1 or P_2,2 or P_3,1)
        Clause R3_1 = new Clause(-3, 4, 7, 9);
        kb.add(R3_1);
        // (~P_1,1 or B_2,1)
        Clause R3_2 = new Clause(-4, 3);
        kb.add(R3_2);
        // (~P_2,2 or B_2,1)
        Clause R3_3 = new Clause(-7, 3);
        kb.add(R3_3);
        // (~P_3,1 or B_2,1)
        Clause R3_4 = new Clause(-9, 3);
        kb.add(R3_4);

        // B_1,2 <-> P_1,1 or P_2,2 or P_1,3
        // (~B_1,2 or P_1,1 or P_2,2 or P_1,3)
        Clause R7_1 = new Clause(-2, 4, 7, 8);
        kb.add(R7_1);
        // (~P_1,1 or B_1,2)
        Clause R7_2 = new Clause(-4, 2);
        kb.add(R7_2);
        // (~P_2,2 or B_1,2)
        Clause R7_3 = new Clause(-7, 2);
        kb.add(R7_3);
        // (~P_1,3 or B_1,2)
        Clause R7_4 = new Clause(-8, 2);
        kb.add(R7_4);

        System.out.println("\nQUESTION 2");

        System.out.println("\nKnowledge Base: " + Part2.stringOfClauses(kb));

        // Add Percetion R4
        // (~B_1,1)
        Clause R4 = new Clause(-1);
        System.out.print("\nAdding to the knowledge base: " + R4);
        kb.add(R4);

        // Does it entail (~P_1,2)
        Clause q1 = new Clause(-5);
        Set<Clause> a = new LinkedHashSet<Clause>();
        a.add(q1);
        Part2.TT_Entails(kb, a);
        
        // Does it entail (~P_2,1)
        a.clear();
        q1.setClause(-6);
        a.add(q1);
        Part2.TT_Entails(kb, a);

        // Does it entail (P_2,2)
        a.clear();
        q1.setClause(7);
        a.add(q1);
        Part2.TT_Entails(kb, a);

        // Does it entail (~P_2,2)
        a.clear();
        q1.setClause(-7);
        a.add(q1);
        Part2.TT_Entails(kb, a);

        // Add Perception R5
        // (B_2,1)
        Clause R5 = new Clause(3);
        System.out.print("\nAdding to the knowledge base: " + R5);
        kb.add(R5);
        
        //Does it entail (P_2,2 or P_3,1)
        a.clear();
        q1.setClause(7, 9);
        a.add(q1);
        Part2.TT_Entails(kb, a);

        //Does it entail (P_2,2)
        a.clear();
        q1.setClause(7);
        a.add(q1);
        Part2.TT_Entails(kb, a);

        //Does it entail (~P_2,2)
        a.clear();
        q1.setClause(-7);
        a.add(q1);
        Part2.TT_Entails(kb, a);

        //Does it entail (P_3,1)
        a.clear();
        q1.setClause(9);
        a.add(q1);
        Part2.TT_Entails(kb, a);

        //Does it entail (~P_3,1)
        a.clear();
        q1.setClause(-9);
        a.add(q1);
        Part2.TT_Entails(kb, a);

        // Add Perception R6
        // (~B_1,2)
        Clause R6 = new Clause(-2);
        System.out.print("\nAdding to the knowledge base: " + R6);
        kb.add(R6);
        
        //Does it entail (~P_2,2 or P_3,1)
        a.clear();
        q1.setClause(-7, 9);
        a.add(q1);
        Part2.TT_Entails(kb, a);
    }

    public static void Q3() {
        LinkedHashSet<Clause> kb = new LinkedHashSet<Clause>();
        // 1 = Mythical, 2 = Immortal, 3 = Mammal, 4 = Horned, 5 = Magical
        // 1 -> 2 in CNF is (~1, 2)
        Clause c1 = new Clause(-1, 2);
        kb.add(c1);
        // ~1 -> (~2 ^ 3) in CNF is (1, ~2) ^ (1, 3)
        Clause c2 = new Clause(1, -2);
        kb.add(c2);
        Clause c3 = new Clause(1, 3);
        kb.add(c3);
        // (2 or 3) -> 4 in CNF is (~2, 4) ^ (~3, 4)
        Clause c4 = new Clause(-2, 4);
        kb.add(c4);
        Clause c5 = new Clause(-3, 4);
        kb.add(c5);
        // 4 -> 5 in CNF is (~5, 4)
        Clause c6 = new Clause(-5, 4);
        kb.add(c6);

        System.out.println("\nQUESTION 3");
        System.out.println("\nKnowledge Base: " + Part2.stringOfClauses(kb));

        // Does it entail 1
        Clause q1 = new Clause(1);
        Set<Clause> a = new LinkedHashSet<Clause>();
        a.add(q1);
        Part2.TT_Entails(kb, a);
        
         // Does it entail 5
         a.clear();
         q1.setClause(5);
         a.add(q1);
         Part2.TT_Entails(kb, a);
         
         // Does it entail 4
         a.clear();
         q1.setClause(4);
         a.add(q1);
         Part2.TT_Entails(kb, a);
    }

    public static void main(String args[]) {
        Q3();
    }
}