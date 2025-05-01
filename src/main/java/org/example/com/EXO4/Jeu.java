package org.example.com.EXO4;

public class Jeu {
    private final Banque banque;
    private boolean ouvert;

    public Jeu(Banque banque) {
        this.banque = banque;
        this.ouvert = true;
    }

    public void jouer(Joueur joueur, De de1, De de2) throws JeuFermeException, DebitImpossibleException {
        if (!ouvert) {
            throw new JeuFermeException("Le jeu est fermé");
        }
        int mise = joueur.mise();
        joueur.debiter(mise);
        banque.crediter(mise);
        int sommeDes = de1.lancer() + de2.lancer();
        if (sommeDes == 7) {
            int gain = mise * 2;
            banque.debiter(gain);
            joueur.crediter(gain);
            if (!banque.est_solvable()) {
                fermer();
            }
        }
    }

    public void fermer() {
        this.ouvert = false;
    }

    public boolean estOuvert() {
        return ouvert;
    }
}
