package EXO01;

import org.example.com.EXO01.Calculatrice;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CalculatriceTest {

    @Mock
    private Calculatrice calculatrice;

    @Test
    public void testAdditionner() {
        // Définition du comportement de la méthode "additionner"
        when(calculatrice.additionner(2, 3)).thenReturn(5);

        // ✅ Appel de la méthode à tester
        int resultat = calculatrice.additionner(2, 3);

        // ✅ Vérification du résultat
        assert(resultat == 5);

        // ✅ Vérification que la méthode "additionner" a été appelée avec les arguments 2 et 3
        verify(calculatrice).additionner(2, 3);

        // ✅ Vérification qu'aucune autre méthode n'a été appelée
        verifyNoMoreInteractions(calculatrice);

        // ⛔️ Problème ici : on ne peut pas vérifier l’état réel car c’est un mock
        // La méthode suivante ne fonctionnera que sur un vrai objet, pas un mock :
        // verify(calculatrice).getResult();

        // 👉 Si on veut tester l'état (comme result = 5), il faut utiliser une vraie instance :
        Calculatrice vraieCalculatrice = new Calculatrice();
        int vraiResultat = vraieCalculatrice.additionner(2, 3);
        assert(vraiResultat == 5);
        assert(vraieCalculatrice.getResult() == 5);
    }
}
