package com.halconbit.structurebase_maven_springbootv33.__java21.services.impl;

import com.halconbit.structurebase_maven_springbootv33.__java21.dto.ShipDto;
import com.halconbit.structurebase_maven_springbootv33.__java21.dto.ShipInputDto;
import com.halconbit.structurebase_maven_springbootv33.__java21.dto.ShipInputUpdateDto;
import com.halconbit.structurebase_maven_springbootv33.__java21.exceptions.CustomException;
import com.halconbit.structurebase_maven_springbootv33.__java21.models.Ship;
import com.halconbit.structurebase_maven_springbootv33.__java21.repositories.ShipRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class ShipServiceImplTest {

    private ShipRepository shipRepository;
    private ShipServiceImpl shipServiceImpl;
    Ship ship;
    ShipDto shipDto;
    ShipInputDto shipInputDto;
    ShipInputUpdateDto shipInputUpdateDto;

    Ship expectedShip;
    ShipDto expectedShipDto;
    ShipInputDto expectedShipInputDto;
    ShipInputUpdateDto expectedShipInputUpdateDto;

    Date date = new Date();

    @BeforeEach
    public void setup() {
        this.shipRepository = mock(ShipRepository.class);
        this.ship = new Ship(null, "test", "test description", true, false, false, date, date);
        this.shipInputUpdateDto = new ShipInputUpdateDto("test", "test description", true, false, false, date);
        this.shipInputDto = new ShipInputDto(1L, this.shipInputUpdateDto);
        this.shipDto = new ShipDto(1L, this.date, this.shipInputDto);
        this.shipServiceImpl = new ShipServiceImpl(shipRepository);
        this.expectedShip = new Ship(1L, "test", "test", false, false, false, date, date);

        this.expectedShipInputUpdateDto = new ShipInputUpdateDto(null, null, false, false, false, date);
        this.expectedShipInputDto = new ShipInputDto(1L, this.expectedShipInputUpdateDto);
        this.expectedShipDto = new ShipDto(1L, date, this.expectedShipInputDto);
    }

    @Test
    @DisplayName("Should create 'ship'")
    void testCreateSuccess() throws CustomException {
        // Prepare mock data
        var expectedEntity = new Ship(null, "test", "test description", true, false, false, date, date);
        // Mock repository behavior
        when(this.shipRepository.save(any())).thenReturn(expectedEntity);

        // Call the service method
        var actualDto = this.shipServiceImpl.create(this.shipInputDto);

        // Verify results
        assertEquals(this.expectedShipDto, actualDto);
        verify(this.shipRepository, times(1)).save(any(Ship.class));
    }

    @Test
    @DisplayName("Should update 'ship'")
    void testUpdateSuccess() throws CustomException {
        // Prepare mock data
        Long existingId = 1L;
        ShipInputUpdateDto inputDto = new ShipInputUpdateDto("test", "test description", true, false, false, date);
        Ship existingEntity = new Ship(existingId, "X-Wing", "T-65 X-wing fighter", true, false, false, date, date);
        Ship updatedEntity = new Ship(existingId, inputDto.getName(), existingEntity.getDescription(), existingEntity.isActivation(), existingEntity.isLocked(), existingEntity.isDelete(), date, date);
        ShipDto expectedDto = new ShipDto(updatedEntity.getId(), date, this.shipInputDto);

        // Mock repository behavior
        when(this.shipRepository.findById(existingId)).thenReturn(Optional.of(existingEntity));
        when(this.shipRepository.save(any(Ship.class))).thenReturn(updatedEntity);

        // Call the service method
        ShipDto actualDto = this.shipServiceImpl.update(inputDto, existingId);

        // Verify results
        assertEquals(expectedDto, actualDto);
        verify(this.shipRepository, times(1)).findById(existingId);
        verify(this.shipRepository, times(1)).save(updatedEntity);
    }

    @Test
   void testUpdateNotFound() throws CustomException {
        // Prepare mock data with non-existent id
        Long nonExistentId = 10L;
        ShipInputUpdateDto inputDto = new ShipInputUpdateDto("test", "test description", true, false, false, date);

        // Mock repository behavior (no entity found)
        when(this.shipRepository.findById(nonExistentId)).thenReturn(Optional.empty());
        assertThrows(CustomException.class, () -> this.shipServiceImpl.update(inputDto, nonExistentId));
    }

}
