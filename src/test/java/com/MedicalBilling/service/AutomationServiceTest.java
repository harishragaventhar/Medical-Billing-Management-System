package com.MedicalBilling.service;

import com.MedicalBilling.Entity.DenialEntity;
import com.MedicalBilling.Repository.DenialRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AutomationServiceTest {

    @Mock
    private DenialRepository denialRepository; 

    @InjectMocks
    private AutomationService automationService; 

    @Test
    void testCO16Rule() {
        
        Long denialId = 1L;
        DenialEntity mockEntity = new DenialEntity();
        mockEntity.setDenialCode("CO-16"); 

        
        
        when(denialRepository.findById(denialId)).thenReturn(Optional.of(mockEntity));

        
        String actualStatus = automationService.runAutomation(denialId);

        
        String expectedStatus = "SUCCESS: Logic Applied. New Status: AUTO_ACTION: Generate Missing Info Email";

        assertEquals(expectedStatus, actualStatus);
    }
}