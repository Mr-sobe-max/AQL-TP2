package org.example.com.EXO4;

public interface Banque {
    void crediter(int somme);
    boolean est_solvable();
    void debiter(int somme);
}
