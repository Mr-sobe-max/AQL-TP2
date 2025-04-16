package EXO03;

import org.example.com.EXO03.ServiceException;
import org.example.com.EXO03.UserService;
import org.example.com.EXO03.Utilisateur;
import org.example.com.EXO03.UtilisateurApi;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UtilisateurApi utilisateurApiMock;

    // SCENARIO 01
    @Test(expected = ServiceException.class)
    public void testCreerUtilisateur_Exception() throws ServiceException {
        Utilisateur utilisateur = new Utilisateur("Jean", "Dupont", "jean@email.com");

        doThrow(new ServiceException("Échec de la création de l'utilisateur"))
                .when(utilisateurApiMock).creerUtilisateur(utilisateur);

        UserService userService = new UserService(utilisateurApiMock);
        userService.creerUtilisateur(utilisateur);
    }

    // SCENARIO 02
    @Test
    public void testCreerUtilisateur_ValidationFail() throws ServiceException {
        Utilisateur utilisateur = new Utilisateur("Jean", "Dupont", null);

        UserService userService = new UserService(utilisateurApiMock);
        userService.creerUtilisateur(utilisateur);

        verify(utilisateurApiMock, never()).creerUtilisateur(any());
    }

    // SCENARIO 03
    @Test
    public void testCreerUtilisateur_AssignId() throws ServiceException {
        Utilisateur utilisateur = new Utilisateur("Jean", "Dupont", "jean@email.com");
        int idUtilisateur = 123;

        when(utilisateurApiMock.creerUtilisateur(utilisateur)).thenReturn(idUtilisateur);

        UserService userService = new UserService(utilisateurApiMock);
        userService.creerUtilisateur(utilisateur);

        assertEquals(idUtilisateur, utilisateur.getId());
    }

    // SCENARIO 04
    @Test
    public void testCreerUtilisateur_ArgumentCaptor() throws ServiceException {
        Utilisateur utilisateur = new Utilisateur("Jean", "Dupont", "jean@email.com");

        when(utilisateurApiMock.creerUtilisateur(any(Utilisateur.class))).thenReturn(123);
        UserService userService = new UserService(utilisateurApiMock);

        ArgumentCaptor<Utilisateur> argumentCaptor = ArgumentCaptor.forClass(Utilisateur.class);

        userService.creerUtilisateur(utilisateur);

        verify(utilisateurApiMock).creerUtilisateur(argumentCaptor.capture());

        Utilisateur utilisateurCapture = argumentCaptor.getValue();

        assertEquals("Jean", utilisateurCapture.getPrenom());
        assertEquals("Dupont", utilisateurCapture.getNom());
        assertEquals("jean@email.com", utilisateurCapture.getEmail());
    }
}

