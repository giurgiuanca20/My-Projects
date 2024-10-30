package org.example.spring1.monthly;

import javax.annotation.processing.Generated;
import org.example.spring1.monthly.model.dto.Monthly;
import org.example.spring1.monthly.model.dto.Monthly.MonthlyBuilder;
import org.example.spring1.monthly.model.dto.MonthlyDTO;
import org.example.spring1.monthly.model.dto.MonthlyDTO.MonthlyDTOBuilder;
import org.example.spring1.monthly.model.dto.MonthlyRequestDTO;
import org.example.spring1.monthly.model.dto.MonthlyRequestDTO.MonthlyRequestDTOBuilder;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-01T15:28:30+0300",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.6.jar, environment: Java 21.0.3 (Amazon.com Inc.)"
)
@Component
public class MonthlyMapperImpl implements MonthlyMapper {

    @Override
    public MonthlyDTO toMonthlyDto(Monthly today) {
        if ( today == null ) {
            return null;
        }

        MonthlyDTOBuilder monthlyDTO = MonthlyDTO.builder();

        monthlyDTO.id( today.getId() );
        monthlyDTO.image( today.getImage() );
        monthlyDTO.text( today.getText() );
        monthlyDTO.hour( today.getHour() );
        monthlyDTO.nrDay( today.getNrDay() );

        return monthlyDTO.build();
    }

    @Override
    public Monthly toEntity(MonthlyDTO today) {
        if ( today == null ) {
            return null;
        }

        MonthlyBuilder monthly = Monthly.builder();

        monthly.id( today.getId() );
        monthly.nrDay( today.getNrDay() );
        monthly.text( today.getText() );
        monthly.image( today.getImage() );
        monthly.hour( today.getHour() );

        return monthly.build();
    }

    @Override
    public MonthlyRequestDTO entityToRequestDTO(Monthly monthly) {
        if ( monthly == null ) {
            return null;
        }

        MonthlyRequestDTOBuilder monthlyRequestDTO = MonthlyRequestDTO.builder();

        monthlyRequestDTO.image( monthly.getImage() );
        monthlyRequestDTO.text( monthly.getText() );
        monthlyRequestDTO.hour( monthly.getHour() );
        monthlyRequestDTO.nrDay( monthly.getNrDay() );

        return monthlyRequestDTO.build();
    }

    @Override
    public Monthly requestDTOToEntity(MonthlyRequestDTO monthlyRequestDTO) {
        if ( monthlyRequestDTO == null ) {
            return null;
        }

        MonthlyBuilder monthly = Monthly.builder();

        monthly.nrDay( monthlyRequestDTO.getNrDay() );
        monthly.text( monthlyRequestDTO.getText() );
        monthly.image( monthlyRequestDTO.getImage() );
        monthly.hour( monthlyRequestDTO.getHour() );

        return monthly.build();
    }
}
