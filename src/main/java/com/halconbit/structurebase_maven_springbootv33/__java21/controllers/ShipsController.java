package com.halconbit.structurebase_maven_springbootv33.__java21.controllers;

import com.halconbit.structurebase_maven_springbootv33.__java21.dto.*;
import com.halconbit.structurebase_maven_springbootv33.__java21.exceptions.CustomException;
import com.halconbit.structurebase_maven_springbootv33.__java21.services.ShipService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(value = "/ships")
@RequiredArgsConstructor
public class ShipsController {

    private final ShipService service;

    @GetMapping("/view")
    public ResponseEntity<Page<ShipResponseDto>> getAll(@RequestParam String query, Pageable page)
            throws CustomException {
        return ResponseEntity.ok(service.getAll(page, query));
    }

    @PostMapping("/add")
    public ResponseEntity<ShipDto> post(@Valid @RequestBody ShipInputDto input) throws CustomException {
        return new ResponseEntity<>(service.create(input), HttpStatus.CREATED);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<ShipDto> put(@PathVariable("id") Long id, @Valid @RequestBody ShipInputUpdateDto ship) throws CustomException {
        return new ResponseEntity<>(service.update(ship, id), HttpStatus.OK);
    }

}
