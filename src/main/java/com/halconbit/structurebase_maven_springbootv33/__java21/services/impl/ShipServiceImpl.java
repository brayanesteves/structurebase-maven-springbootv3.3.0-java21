package com.halconbit.structurebase_maven_springbootv33.__java21.services.impl;

import com.halconbit.structurebase_maven_springbootv33.__java21.dto.*;
import com.halconbit.structurebase_maven_springbootv33.__java21.exceptions.CustomException;
import com.halconbit.structurebase_maven_springbootv33.__java21.models.Ship;
import com.halconbit.structurebase_maven_springbootv33.__java21.repositories.ShipRepository;
import com.halconbit.structurebase_maven_springbootv33.__java21.services.ShipService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@Service("ShipService")
@RequiredArgsConstructor
public class ShipServiceImpl implements ShipService {

    private static final String NOT_FOUND = "NOT FOUND";

    final ShipRepository repository;

    @Override
    public ShipDto create(ShipInputDto input) throws CustomException {
        Ship entity = repository.save(castToModel(input));
        return castToDto(entity);
    }

    @Override
    @CachePut(value = "ship", key = "#id")
    public ShipDto update(ShipInputUpdateDto input, Long id) throws CustomException {
        var found = repository.findById(id);
        if (found.isEmpty()) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "id", NOT_FOUND);
        }
        var f = found.get();
        var entity = castToDto(repository.save(castToModel(input, f)));
        return entity;
    }

    @Override
    @Cacheable(value = "ships", key = "#id")
    public ShipDto getById(Long id) throws CustomException {
        var entity = repository.findById(id).orElse(null);
        if (entity == null) throw new CustomException(HttpStatus.NOT_FOUND, "ID", NOT_FOUND);
        var dto = castToDto(entity);
        return dto;
    }

    @Override
    public Page<ShipResponseDto> getBy(Pageable page, CustomsQuery dynamicQuery) throws CustomException {
        return null;
    }

    @Override
    public Integer delete(Long id) throws CustomException {
        var entity = repository.findById(id).orElse(null);
        if (entity == null) throw new CustomException(HttpStatus.NOT_FOUND, "ID", NOT_FOUND);
        entity.setDelete(Boolean.TRUE);
        repository.save(entity);
        return 1;
    }

    @Override
    public Page<ShipResponseDto> getAll(Pageable page, String queries) throws CustomException {
        System.out.println(queries);
        var entities = repository.findByName(queries, page);
        return castToDtoMap(entities);
    }

    public Ship castToModel(ShipInputDto dto) {
        return new Ship(null, dto.getName(), dto.getDescription(), dto.isActivation(), dto.isLocked(), dto.isDelete(), new Date(), null);
    }

    public Ship castToModel(ShipInputUpdateDto dto, Ship ship) {
        return new Ship(ship.getId(), dto.getName(), dto.getDescription(), dto.isActivation(), dto.isLocked(), dto.isDelete(), new Date(), ship.getModificationDate());
    }

    public ShipDto castToDto(Ship model) {
        var input = new ShipInputDto(model.getId(), new ShipInputUpdateDto(model.getName(), model.getDescription(), model.isActivation(), model.isLocked(), model.isDelete(), model.getModificationDate()));
        return new ShipDto(model.getId(), model.getCreationDate(), input);
    }

    public List<ShipResponseDto> castToDto(List<Ship> models) {
        return models.stream()
                .map(model -> new ShipResponseDto(model.getId(), "x" + model.getName(),
                        model.getDescription(),
                        model.isActivation(),
                        model.isLocked(),
                        model.isDelete(),
                        model.getCreationDate(),
                        model.getModificationDate()

                )).toList();
    }

    public Page<ShipResponseDto> castToDtoMap(Page<Ship> models) {
        // Use map with a collector to create a Page of ShipResponseDto
        return models.map(model -> new ShipResponseDto(model.getId(), "x-" + model.getName(),
                model.getDescription(),
                model.isActivation(),
                model.isLocked(),
                model.isDelete(),
                model.getCreationDate(),
                model.getModificationDate()
        ));
    }
}
