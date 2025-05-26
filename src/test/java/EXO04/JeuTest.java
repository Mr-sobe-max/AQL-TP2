package EXO04;

import org.example.com.EXO4.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

<<<<<<< HEAD
/* EXO 04 AVEC LES MOCKS */
=======
>>>>>>> c863727d200c5b74a20c09ae4f205deca63a0bc5
@ExtendWith(MockitoExtension.class)
public class JeuTest {
    @Mock
    private Banque banque;
    @Mock
    private Joueur joueur;
    @Mock
    private De de1, de2;
    private Jeu jeu;

    @BeforeEach
    void setUp() {
        jeu = new Jeu(banque);
    }

    @Test
    void testJouerJeuFerme() {
        jeu.fermer();
        assertThrows(JeuFermeException.class, () -> jeu.jouer(joueur, de1, de2));
    }

    @Test
    void testJouerJoueurInsolvable() throws DebitImpossibleException {
        when(joueur.mise()).thenReturn(10);
        doThrow(new DebitImpossibleException("Solde insuffisant")).when(joueur).debiter(10);
        assertThrows(DebitImpossibleException.class, () -> jeu.jouer(joueur, de1, de2));
        verifyNoInteractions(de1, de2);
    }

    @Test
    void testJouerSommeNonSept() throws JeuFermeException, DebitImpossibleException {
        when(joueur.mise()).thenReturn(10);
        when(de1.lancer()).thenReturn(2);
        when(de2.lancer()).thenReturn(3);
        jeu.jouer(joueur, de1, de2);
        verify(joueur).debiter(10);
        verify(banque).crediter(10);
        verifyNoMoreInteractions(joueur, banque);
    }

    @Test
    void testJouerSommeSeptBanqueSolvable() throws JeuFermeException, DebitImpossibleException {
        when(joueur.mise()).thenReturn(10);
        when(de1.lancer()).thenReturn(4);
        when(de2.lancer()).thenReturn(3);
        when(banque.est_solvable()).thenReturn(true);
        jeu.jouer(joueur, de1, de2);
        verify(joueur).debiter(10);
        verify(banque).crediter(10);
        verify(banque).debiter(20);
        verify(joueur).crediter(20);
        assertTrue(jeu.estOuvert());
    }

    @Test
    void testJouerSommeSeptBanqueNonSolvable() throws JeuFermeException, DebitImpossibleException {
        when(joueur.mise()).thenReturn(10);
        when(de1.lancer()).thenReturn(4);
        when(de2.lancer()).thenReturn(3);
        when(banque.est_solvable()).thenReturn(false);
        jeu.jouer(joueur, de1, de2);
        verify(joueur).debiter(10);
        verify(banque).crediter(10);
        verify(banque).debiter(20);
        verify(joueur).crediter(20);
        assertFalse(jeu.estOuvert());
    }
}