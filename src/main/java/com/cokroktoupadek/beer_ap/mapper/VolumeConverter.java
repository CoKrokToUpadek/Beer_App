package com.cokroktoupadek.beer_ap.mapper;

import com.cokroktoupadek.beer_ap.domain.dto.beer.VolumeDto;
import com.cokroktoupadek.beer_ap.domain.entity.beer.VolumeEntity;
import lombok.NonNull;
import org.modelmapper.AbstractConverter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VolumeConverter extends AbstractConverter<VolumeEntity, VolumeDto>
{
    ModelMapper modelMapper;

    public VolumeConverter(ModelMapper modelMapper) {
        this.modelMapper=modelMapper;
    }

    @Override
    public VolumeDto convert(@NonNull final VolumeEntity source)
    {
        VolumeDto volumeDto = new VolumeDto();
        modelMapper.map(source, volumeDto);
        return volumeDto;
    }
}