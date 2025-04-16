package EXO02;

import org.example.com.EXO02.UserService;
import org.example.com.EXO02.Utilisateur;
import org.example.com.EXO02.UtilisateurApi;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UtilisateurApi utilisateurApiMock;

    @Test
    public void testCreerUtilisateur()  {
        // 🔸 Création d'un nouvel utilisateur
        Utilisateur utilisateur = new Utilisateur("Jean", "Dupont", "jeandupont@email.com");

        // ✅ Configuration du comportement du mock (optionnel ici car la méthode retourne void)
        // → Pas de thenReturn car la méthode est void, donc on fait simplement : doNothing()
        doNothing().when(utilisateurApiMock).creerUtilisateur(utilisateur);

        // ✅ Création du service avec le mock
        UserService userService = new UserService(utilisateurApiMock);

        // ✅ Appel de la méthode à tester
        userService.creerUtilisateur(utilisateur);

        // ✅ Vérification de l'appel à l'API
        verify(utilisateurApiMock).creerUtilisateur(utilisateur);

        // ✅ Optionnel : vérifier qu'aucune autre méthode n’a été appelée sur le mock
        verifyNoMoreInteractions(utilisateurApiMock);
    }
}
