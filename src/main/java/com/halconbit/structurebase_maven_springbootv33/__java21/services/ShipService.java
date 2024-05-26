package com.halconbit.structurebase_maven_springbootv33.__java21.services;

import com.halconbit.structurebase_maven_springbootv33.__java21.dto.*;
import com.halconbit.structurebase_maven_springbootv33.__java21.exceptions.CustomException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ShipService {

    ShipDto create(ShipInputDto input) throws CustomException;

    ShipDto update(ShipInputUpdateDto input, Long id) throws CustomException;

    ShipDto getById(Long id) throws CustomException;

    Page<ShipResponseDto> getBy(Pageable page, CustomsQuery dynamicQuery) throws CustomException;

    Integer delete(Long id) throws CustomException;

    Page<ShipResponseDto> getAll(Pageable page, String queries) throws CustomException;
}
