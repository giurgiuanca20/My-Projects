package org.example.spring1.color;

import javax.annotation.processing.Generated;
import org.example.spring1.color.model.Color;
import org.example.spring1.color.model.Color.ColorBuilder;
import org.example.spring1.color.model.dto.ColorDTO;
import org.example.spring1.color.model.dto.ColorDTO.ColorDTOBuilder;
import org.example.spring1.color.model.dto.ColorRequestDTO;
import org.example.spring1.color.model.dto.ColorRequestDTO.ColorRequestDTOBuilder;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-01T15:28:31+0300",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.6.jar, environment: Java 21.0.3 (Amazon.com Inc.)"
)
@Component
public class ColorMapperImpl implements ColorMapper {

    @Override
    public ColorDTO toColorDto(Color color) {
        if ( color == null ) {
            return null;
        }

        ColorDTOBuilder colorDTO = ColorDTO.builder();

        colorDTO.id( color.getId() );
        colorDTO.color( color.getColor() );
        colorDTO.nrDay( color.getNrDay() );

        return colorDTO.build();
    }

    @Override
    public Color toEntity(ColorDTO color) {
        if ( color == null ) {
            return null;
        }

        ColorBuilder color1 = Color.builder();

        color1.id( color.getId() );
        color1.color( color.getColor() );
        color1.nrDay( color.getNrDay() );

        return color1.build();
    }

    @Override
    public ColorRequestDTO entityToRequestDTO(Color color) {
        if ( color == null ) {
            return null;
        }

        ColorRequestDTOBuilder colorRequestDTO = ColorRequestDTO.builder();

        colorRequestDTO.color( color.getColor() );
        colorRequestDTO.nrDay( color.getNrDay() );

        return colorRequestDTO.build();
    }

    @Override
    public Color requestDTOToEntity(ColorRequestDTO colorRequestDTO) {
        if ( colorRequestDTO == null ) {
            return null;
        }

        ColorBuilder color = Color.builder();

        color.color( colorRequestDTO.getColor() );
        color.nrDay( colorRequestDTO.getNrDay() );

        return color.build();
    }
}
