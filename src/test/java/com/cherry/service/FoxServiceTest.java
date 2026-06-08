package com.cherry.service;

import com.cherry.dao.FoxDaoLocal;
import com.cherry.exception.FoxNotFoundException;
import com.cherry.model.entity.Fox;
import com.cherry.model.enumerate.Gender;
import com.cherry.model.request.CreateFoxRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FoxServiceTest {

    private static final long ID = 1L;
    private static final int ID_INT = 1;
    private static final String IMAGE = "http:google.com/image";
    private static final Gender MALE = Gender.MALE;
    private static final String SPECIES = "Red Fox";
    private static final String NAME = "Foxy";

    @Mock
    private FoxDaoLocal foxDao;

    @InjectMocks
    private FoxServiceEjb foxServiceEjb;

    @Test
    public void getAllFoxesTest() {
        // Given
        when(foxDao.findAll()).thenReturn(getFoxList());

        // When
        List<Fox> foxes = foxServiceEjb.getAllFoxes();

        // Then
        assertEquals(3, foxes.size());
    }

    @Test
    public void getFoxByIdTest_success() {
        // Given
        Fox fox = getFox();

        when(foxDao.findById(ID)).thenReturn(Optional.of(fox));

        // When
        Fox result = foxServiceEjb.getFoxById(ID_INT);

        // Then
        assertEquals(fox, result);
    }

    @Test
    public void getFoxByIdTest_foxNotFound() {
        // Given
        when(foxDao.findById(ID)).thenReturn(Optional.empty());

        // When
        FoxNotFoundException exception = assertThrows(FoxNotFoundException.class, () -> foxServiceEjb.getFoxById(ID_INT));

        // Then
        FoxNotFoundException foxNotFoundException = new FoxNotFoundException(ID);
        assertEquals(foxNotFoundException.getMessage(), exception.getMessage());
    }

    @Test
    public void createFoxTest_success() {
        // Given
        Fox savedFox = getFox();
        CreateFoxRequest request = getCreateFoxRequest();

        when(foxDao.save(any(Fox.class))).thenReturn(savedFox);

        // When
        Fox result = foxServiceEjb.createFox(request);

        // Then
        assertEquals(savedFox, result);
    }

    @Test
    public void deleteFoxTest_success() {
        // Given
        Fox fox = getFox();

        when(foxDao.findById(ID)).thenReturn(Optional.of(fox));

        // When
        foxServiceEjb.deleteFoxById(ID_INT);

        // Then
        verify(foxDao).delete(any(Fox.class));
    }

    private CreateFoxRequest getCreateFoxRequest() {
        return new CreateFoxRequest(NAME, SPECIES, MALE.name(), IMAGE);
    }

    private Fox getFox() {
        return Fox.builder()
                .id(ID)
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
