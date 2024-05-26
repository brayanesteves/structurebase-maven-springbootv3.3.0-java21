package com.halconbit.structurebase_maven_springbootv33.__java21.utils;

import com.halconbit.structurebase_maven_springbootv33.__java21.dto.ShipDto;
import com.halconbit.structurebase_maven_springbootv33.__java21.dto.ShipInputDto;
import com.halconbit.structurebase_maven_springbootv33.__java21.dto.ShipInputUpdateDto;
import com.halconbit.structurebase_maven_springbootv33.__java21.models.Ship;

import java.util.Date;

public class CastUtil {

    public Ship castToModel(ShipInputDto dto) {
        return new Ship(null, dto.getName(), dto.getDescription(), dto.isActivation(), dto.isLocked(), dto.isDelete(), new Date(), null);
    }

    public ShipDto castToDto(Ship model) {
        var input = new ShipInputDto(model.getId(), new ShipInputUpdateDto(model.getName(), model.getDescription(), model.isActivation(), model.isLocked(), model.isDelete(), model.getModificationDate()));
        return new ShipDto(model.getId(), model.getCreationDate(), input);
    }

}
