package com.halconbit.structurebase_maven_springbootv33.__java21.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class ShipDto extends ShipInputDto {

    private Long id;
    private Date creationDate;

    public ShipDto(Long id, Date creationDate, ShipInputDto shipInputDto) {
        super(shipInputDto.getId(), new ShipInputUpdateDto(shipInputDto.getName(), shipInputDto.getDescription(), shipInputDto.isActivation(), shipInputDto.isLocked(), shipInputDto.isDelete(), shipInputDto.getModificationDate()));
        this.id = id;
        this.creationDate = creationDate;
    }

}
