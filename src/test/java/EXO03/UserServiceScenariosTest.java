package EXO03;

import org.example.com.EXO3.UtilisateurApi;
import org.example.com.EXO3.Utilisateur;
import org.example.com.EXO3.UserService;
import org.example.com.EXO3.ServiceException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

<<<<<<< HEAD
/* EXO 03 AVEC LES MOCKS */
=======
>>>>>>> c863727d200c5b74a20c09ae4f205deca63a0bc5
@ExtendWith(MockitoExtension.class)
public class UserServiceScenariosTest {
    @Mock
    private UtilisateurApi utilisateurApiMock;

    @Test
    public void testCreerUtilisateurThrowsServiceException() throws ServiceException {
        Utilisateur utilisateur = new Utilisateur("Mounir", "Boukhiber", "mounirBOUKHIBAR@email.com");
        doThrow(new ServiceException("Echec de la création de l'utilisateur"))
                .when(utilisateurApiMock).creerUtilisateur(utilisateur);
        UserService userService = new UserService(utilisateurApiMock);
        assertThrows(ServiceException.class, () -> userService.creerUtilisateur(utilisateur));
        verify(utilisateurApiMock).creerUtilisateur(utilisateur);
    }

    @Test
    public void testCreerUtilisateurValidationError() throws ServiceException {
        Utilisateur utilisateur = new Utilisateur("", "", "");
        UserService userService = new UserService(utilisateurApiMock);
        verify(utilisateurApiMock, never()).creerUtilisateur(any(Utilisateur.class));
    }

    @Test
    public void testCreerUtilisateurSetsId() throws ServiceException {
        Utilisateur utilisateur = new Utilisateur("Mounir", "BOUKHIBAR", "mounirBOUKHIBAR@email.com");
        int idUtilisateur = 123;
        doAnswer(invocation -> {
            Utilisateur u = invocation.getArgument(0);
            u.setId(idUtilisateur);
            return null;
        }).when(utilisateurApiMock).creerUtilisateur(utilisateur);
        UserService userService = new UserService(utilisateurApiMock);
        userService.creerUtilisateur(utilisateur);
        assertEquals(idUtilisateur, utilisateur.getId());
        verify(utilisateurApiMock).creerUtilisateur(utilisateur);
    }

    @Test
    public void testCreerUtilisateurArgumentCapture() throws ServiceException {
        Utilisateur utilisateur = new Utilisateur("mounir", "BOUKHIBAR", "mounirBOUKHIBAR@email.com");
        ArgumentCaptor<Utilisateur> argumentCaptor = ArgumentCaptor.forClass(Utilisateur.class);
        doNothing().when(utilisateurApiMock).creerUtilisateur(any(Utilisateur.class));
        UserService userService = new UserService(utilisateurApiMock);
        userService.creerUtilisateur(utilisateur);
        verify(utilisateurApiMock).creerUtilisateur(argumentCaptor.capture());
        Utilisateur utilisateurCapture = argumentCaptor.getValue();
        assertEquals("mounir", utilisateurCapture.getPrenom());
        assertEquals("BOUKHIBAR", utilisateurCapture.getNom());
        assertEquals("mounirBOUKHIBAR@email.com", utilisateurCapture.getEmail());
    }
}