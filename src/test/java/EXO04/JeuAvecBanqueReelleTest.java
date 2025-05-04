package EXO04;

import org.example.com.EXO4.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/* EXO 04 AVEC LES MOCKS */
@ExtendWith(MockitoExtension.class)
public class JeuAvecBanqueReelleTest {
    private Banque banque;
    @Mock
    private Joueur joueur;
    @Mock
    private De de1, de2;
    private Jeu jeu;

    @BeforeEach
    void setUp() {
        banque = new BanqueImpl(100, 50);
        jeu = new Jeu(banque);
    }

    @Test
    void testJouerSommeSeptBanqueDevientNonSolvable() throws JeuFermeException, DebitImpossibleException {
        when(joueur.mise()).thenReturn(30);
        when(de1.lancer()).thenReturn(4);
        when(de2.lancer()).thenReturn(3);
        jeu.jouer(joueur, de1, de2);
        verify(joueur).debiter(30);
        verify(joueur).crediter(60);
        assertTrue(jeu.estOuvert());
    }
}
