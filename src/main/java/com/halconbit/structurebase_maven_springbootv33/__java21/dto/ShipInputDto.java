package com.halconbit.structurebase_maven_springbootv33.__java21.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class ShipInputDto extends ShipInputUpdateDto {

    private Long id;
    private String name;
    private String description;
    private boolean activation;
    private boolean locked;
    private boolean delete;
    private Date creationDate;

    public ShipInputDto(Long id, ShipInputUpdateDto shipInputUpdateDto) {
        super(shipInputUpdateDto.getName(), shipInputUpdateDto.getDescription(), shipInputUpdateDto.isActivation(), shipInputUpdateDto.isLocked(), shipInputUpdateDto.isDelete(), shipInputUpdateDto.getModificationDate());
        this.id = id;
    }

}
