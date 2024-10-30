package org.example.spring1.weekly;

import javax.annotation.processing.Generated;
import org.example.spring1.weekly.model.dto.Weekly;
import org.example.spring1.weekly.model.dto.Weekly.WeeklyBuilder;
import org.example.spring1.weekly.model.dto.WeeklyDTO;
import org.example.spring1.weekly.model.dto.WeeklyDTO.WeeklyDTOBuilder;
import org.example.spring1.weekly.model.dto.WeeklyRequestDTO;
import org.example.spring1.weekly.model.dto.WeeklyRequestDTO.WeeklyRequestDTOBuilder;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-01T15:28:31+0300",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.6.jar, environment: Java 21.0.3 (Amazon.com Inc.)"
)
@Component
public class WeeklyMapperImpl implements WeeklyMapper {

    @Override
    public WeeklyDTO toWeeklyDto(Weekly weekly) {
        if ( weekly == null ) {
            return null;
        }

        WeeklyDTOBuilder weeklyDTO = WeeklyDTO.builder();

        weeklyDTO.id( weekly.getId() );
        weeklyDTO.day( weekly.getDay() );
        weeklyDTO.text( weekly.getText() );
        weeklyDTO.image( weekly.getImage() );
        weeklyDTO.hour( weekly.getHour() );

        return weeklyDTO.build();
    }

    @Override
    public Weekly toEntity(WeeklyDTO weekly) {
        if ( weekly == null ) {
            return null;
        }

        WeeklyBuilder weekly1 = Weekly.builder();

        weekly1.id( weekly.getId() );
        weekly1.day( weekly.getDay() );
        weekly1.text( weekly.getText() );
        weekly1.image( weekly.getImage() );
        weekly1.hour( weekly.getHour() );

        return weekly1.build();
    }

    @Override
    public WeeklyRequestDTO entityToRequestDTO(Weekly weekly) {
        if ( weekly == null ) {
            return null;
        }

        WeeklyRequestDTOBuilder weeklyRequestDTO = WeeklyRequestDTO.builder();

        weeklyRequestDTO.day( weekly.getDay() );
        weeklyRequestDTO.text( weekly.getText() );
        weeklyRequestDTO.image( weekly.getImage() );
        weeklyRequestDTO.hour( weekly.getHour() );

        return weeklyRequestDTO.build();
    }

    @Override
    public Weekly requestDTOToEntity(WeeklyRequestDTO weeklyRequestDTO) {
        if ( weeklyRequestDTO == null ) {
            return null;
        }

        WeeklyBuilder weekly = Weekly.builder();

        weekly.day( weeklyRequestDTO.getDay() );
        weekly.text( weeklyRequestDTO.getText() );
        weekly.image( weeklyRequestDTO.getImage() );
        weekly.hour( weeklyRequestDTO.getHour() );

        return weekly.build();
    }
}
