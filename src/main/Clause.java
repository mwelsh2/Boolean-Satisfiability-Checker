package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Clause {
    Set<Integer> literals;

    public Clause(int ...input) {
        this.literals = new TreeSet<Integer>();
        for (int i : input) {
            literals.add(i);
        }
    }

    public Clause(Set<Integer> input) {
        this.literals = new TreeSet<Integer>();
        for (int i : input) {
            literals.add(i);
        }
    }

    public String toString() {
        String s = literals.toString().replace("[","{").replace("]","}");
        return s;
    }

    public void setClause(int ...input) {
        this.literals.clear();
        for (int i : input) {
            literals.add(i);
        }
    }

    public static Set<Clause> scanCNF(String cnf_string) {
        try {
            File cnf = new File(cnf_string);
            Scanner scan = new Scanner(cnf);
            String line = scan.nextLine();

            while (!line.startsWith("p")) {
                line = scan.nextLine();
            }
        
            int i = scan.nextInt();
            Set<Clause> sentence = new LinkedHashSet<Clause>();
            Set<Integer> input = new LinkedHashSet<Integer>();
            input.add(i);

            while (scan.hasNextInt()) {
        
                i = scan.nextInt();
                while(i != 0) {
                    input.add(i);
                    i = scan.nextInt();
                }

                Clause c = new Clause(input);
                input.clear();
                sentence.add(c);
                
            }
            scan.close();
            System.out.println(Part2.stringOfClauses(sentence));
            return sentence;

        } catch (FileNotFoundException e) {
            System.out.println("No file found.");
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String args[]) {
        scanCNF("quinn.cnf.txt");
    }

}