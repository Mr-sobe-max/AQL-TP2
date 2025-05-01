package org.example.com.EXO4;

public interface Joueur {
    int mise();
    void debiter(int somme) throws DebitImpossibleException;
    void crediter(int somme);
}
