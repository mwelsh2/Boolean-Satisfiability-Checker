package main;

import java.io.File;
import java.io.FileNotFoundException;
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

    public static void scanCNF(String cnf_string) {
        try {
            File cnf = new File(cnf_string);
            Scanner scan = new Scanner(cnf);
            String line = scan.nextLine();

            while (scan.hasNextLine()) {
                while (line.startsWith("c")) {
                    line = scan.nextLine();
                    
                }
                line = scan.nextLine();
                System.out.println(line);
            }

            scan.close();
        } catch (FileNotFoundException e) {
            System.out.println("No file found.");
            e.printStackTrace();
        }

    }

    public static void main(String args[]) {
        scanCNF("hole6.cnf.txt");
    }

}