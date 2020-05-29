package org.century.scp.spocr.base.mappers;

import org.mapstruct.MapperConfig;
import org.mapstruct.MappingInheritanceStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@MapperConfig(
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL,
    mappingInheritanceStrategy = MappingInheritanceStrategy.AUTO_INHERIT_FROM_CONFIG)
public interface BaseMapper {

}
