package com.halconbit.structurebase_maven_springbootv33.__java21.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShipInputUpdateDto {

    private String name;
    private String description;
    private boolean activation;
    private boolean locked;
    private boolean delete;
    private Date modificationDate;

}
