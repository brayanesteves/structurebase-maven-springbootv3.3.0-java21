package com.halconbit.structurebase_maven_springbootv33.__java21.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class ShipResponseDto {

    private Long id;
    private String name;
    private String description;
    private boolean activation;
    private boolean locked;
    private boolean delete;
    private Date creationDate;
    private Date modificationDate;

    public ShipResponseDto(Long id, String name, String description, boolean activation, boolean locked, boolean delete, Date creationDate, Date modificationDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.activation = activation;
        this.locked = locked;
        this.delete = delete;
        this.creationDate = creationDate;
        this.modificationDate = modificationDate;
    }
}
