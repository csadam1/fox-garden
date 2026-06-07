package com.cherry.controller;

import com.cherry.exception.FoxNotFoundException;
import com.cherry.model.entity.Fox;
import com.cherry.model.enumerate.Gender;
import com.cherry.model.request.CreateFoxRequest;
import com.cherry.service.FoxServiceEjbLocal;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.ws.rs.core.Response;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FoxControllerTest {

    private static final int ID = 1;
    private static final long ID_LONG = 1L;
    private static final String IMAGE = "http:google.com/image";
    private static final Gender MALE = Gender.MALE;
    private static final String SPECIES = "Red Fox";
    private static final String NAME = "Foxy";

    @Mock
    private FoxServiceEjbLocal foxServiceEjb;

    @InjectMocks
    private FoxController controller;

    @Test
    public void getAllFoxesTest() {
        // Given
        when(foxServiceEjb.getAllFoxes()).thenReturn(getFoxList());

        // When
        List<Fox> foxes = controller.getAllFoxes();

        // Then
        assertEquals(3, foxes.size());
    }

    @Test
    public void getFoxByIdTest_success() {
        // Given
        Fox fox = getFox();

        when(foxServiceEjb.getFoxById(ID)).thenReturn(fox);

        // When
        Fox resultFox = controller.getFoxById(ID);

        // Then
        assertEquals(fox, resultFox);
    }

    @Test
    public void getFoxByIdTest_foxNotFound() {
        // Given
        FoxNotFoundException foxNotFoundException = new FoxNotFoundException(ID);

        when(foxServiceEjb.getFoxById(ID)).thenThrow(foxNotFoundException);

        // When
        FoxNotFoundException exception = assertThrows(FoxNotFoundException.class, () -> controller.getFoxById(ID));

        // Then
        assertEquals(foxNotFoundException, exception);
    }

    @Test
    public void createFoxTest_success() {
        // Given
        CreateFoxRequest request = getCreateFoxRequest();
        Fox fox = getFox();

        when(foxServiceEjb.createFox(request)).thenReturn(fox);

        // When
        Response response = controller.createFox(request);

        // Then
        assertEquals(fox, response.getEntity());
    }

    @Test
    public void deleteFoxById_success() {
        // When
        Response response = controller.deleteFoxById(ID);

        // Then
        assertEquals(Response.Status.NO_CONTENT.getStatusCode(), response.getStatus());
    }

    private CreateFoxRequest getCreateFoxRequest() {
        return new CreateFoxRequest(NAME, SPECIES, MALE.name(), IMAGE);
    }

    private Fox getFox() {
        return Fox.builder()
                .id(ID_LONG)
                .name(NAME)
                .species(SPECIES)
                .gender(MALE)
                .image(IMAGE)
                .build();
    }

    private List<Fox> getFoxList() {
        return List.of(
                Fox.builder().build(),
                Fox.builder().build(),
                Fox.builder().build());
    }
}
