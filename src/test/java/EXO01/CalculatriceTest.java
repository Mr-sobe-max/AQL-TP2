package EXO01;

import org.example.com.EXO01.Calculatrice;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

<<<<<<< HEAD
/* EXO 01 AVEC LES MOCKS */
=======
>>>>>>> c863727d200c5b74a20c09ae4f205deca63a0bc5
@ExtendWith(MockitoExtension.class)
public class CalculatriceTest {
    @Mock
    private Calculatrice calculatrice;

    @Test
    public void testAdditionner() {
        // Arrange: Define mock behavior
        when(calculatrice.additionner(2, 3)).thenReturn(5);

        // Act: Call the method to test
        int resultat = calculatrice.additionner(2, 3);

        // Assert: Verify the result
        assertEquals(5, resultat);

        // Verify: Check method call
        verify(calculatrice).additionner(2, 3);
        verifyNoMoreInteractions(calculatrice);
    }
}