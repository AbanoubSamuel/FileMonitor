package org.abg.filemonitor.mapper;

import org.abg.filemonitor.dto.FileDto;
import org.abg.filemonitor.entity.File;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface FileMapper {
    File toEntity(FileDto fileDto);

    FileDto toDto(File file);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    File partialUpdate(FileDto fileDto, @MappingTarget File file);
}