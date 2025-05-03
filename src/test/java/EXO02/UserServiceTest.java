package EXO02;

import org.example.com.EXO2.ServiceException;
import org.example.com.EXO2.UserService;
import org.example.com.EXO2.Utilisateur;
import org.example.com.EXO2.UtilisateurApi;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;

/* EXO 02 AVEC LES MOCKS */
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UtilisateurApi utilisateurApiMock;

    @Test
    public void testCreerUtilisateur() throws ServiceException {
        // Création d'un nouvel utilisateur
            Utilisateur utilisateur = new Utilisateur("Mounir", "Boukhiber", "jeandupont@email.com");

        // Configuration du comportement du mock
        doNothing().when(utilisateurApiMock).creerUtilisateur(utilisateur);

        // Création du service avec le mock
        UserService userService = new UserService(utilisateurApiMock);

        // Appel de la méthode à tester
        userService.creerUtilisateur(utilisateur);

        // Vérification de l'appel à l'API
        verify(utilisateurApiMock).creerUtilisateur(utilisateur);
    }
}
