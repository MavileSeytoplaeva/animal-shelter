package com.shelter.animalshelter.service;

import com.shelter.animalshelter.repository.AnimalAdopterRepository;
import com.shelter.animalshelter.service.implement.AnimalAdopterServiceImlp;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AnimalAdopterServiceImlpTest {
    @Mock
    private AnimalAdopterRepository animalAdopterRepository;

    @InjectMocks
    private AnimalAdopterServiceImlp animalAdopterService;

    @Test
    public void returnIfExists() {
        when(animalAdopterRepository.existsById(any(Long.class))).thenReturn(true);
    }
}
