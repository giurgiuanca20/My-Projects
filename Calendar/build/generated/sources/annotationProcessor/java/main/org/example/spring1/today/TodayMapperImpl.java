package org.example.spring1.today;

import javax.annotation.processing.Generated;
import org.example.spring1.monthly.model.dto.Monthly;
import org.example.spring1.monthly.model.dto.Monthly.MonthlyBuilder;
import org.example.spring1.today.model.dto.Today;
import org.example.spring1.today.model.dto.Today.TodayBuilder;
import org.example.spring1.today.model.dto.TodayDTO;
import org.example.spring1.today.model.dto.TodayDTO.TodayDTOBuilder;
import org.example.spring1.today.model.dto.TodayRequestDTO;
import org.example.spring1.today.model.dto.TodayRequestDTO.TodayRequestDTOBuilder;
import org.example.spring1.weekly.model.dto.Weekly;
import org.example.spring1.weekly.model.dto.Weekly.WeeklyBuilder;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-01T15:28:31+0300",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.6.jar, environment: Java 21.0.3 (Amazon.com Inc.)"
)
@Component
public class TodayMapperImpl implements TodayMapper {

    @Override
    public TodayDTO toTodayDto(Today today) {
        if ( today == null ) {
            return null;
        }

        TodayDTOBuilder todayDTO = TodayDTO.builder();

        todayDTO.id( today.getId() );
        todayDTO.image( today.getImage() );
        todayDTO.text( today.getText() );
        todayDTO.hour( today.getHour() );

        return todayDTO.build();
    }

    @Override
    public Today toEntity(TodayDTO today) {
        if ( today == null ) {
            return null;
        }

        TodayBuilder today1 = Today.builder();

        today1.id( today.getId() );
        today1.image( today.getImage() );
        today1.text( today.getText() );
        today1.hour( today.getHour() );

        return today1.build();
    }

    @Override
    public TodayRequestDTO entityToRequestDTO(Today today) {
        if ( today == null ) {
            return null;
        }

        TodayRequestDTOBuilder todayRequestDTO = TodayRequestDTO.builder();

        todayRequestDTO.image( today.getImage() );
        todayRequestDTO.text( today.getText() );
        todayRequestDTO.hour( today.getHour() );

        return todayRequestDTO.build();
    }

    @Override
    public Today requestDTOToEntity(TodayRequestDTO todayRequestDTO) {
        if ( todayRequestDTO == null ) {
            return null;
        }

        TodayBuilder today = Today.builder();

        today.image( todayRequestDTO.getImage() );
        today.text( todayRequestDTO.getText() );
        today.hour( todayRequestDTO.getHour() );

        return today.build();
    }

    @Override
    public Today weeklyToToday(Weekly weekly) {
        if ( weekly == null ) {
            return null;
        }

        TodayBuilder today = Today.builder();

        today.id( weekly.getId() );
        today.image( weekly.getImage() );
        today.text( weekly.getText() );
        today.hour( weekly.getHour() );
        today.user( weekly.getUser() );

        return today.build();
    }

    @Override
    public Weekly todayToWeekly(Today today) {
        if ( today == null ) {
            return null;
        }

        WeeklyBuilder weekly = Weekly.builder();

        weekly.id( today.getId() );
        weekly.text( today.getText() );
        weekly.image( today.getImage() );
        weekly.hour( today.getHour() );
        weekly.user( today.getUser() );

        return weekly.build();
    }

    @Override
    public Today monthlyToToday(Monthly monthly) {
        if ( monthly == null ) {
            return null;
        }

        TodayBuilder today = Today.builder();

        today.id( monthly.getId() );
        today.image( monthly.getImage() );
        today.text( monthly.getText() );
        today.hour( monthly.getHour() );
        today.user( monthly.getUser() );

        return today.build();
    }

    @Override
    public Monthly todayToMonthly(Today today) {
        if ( today == null ) {
            return null;
        }

        MonthlyBuilder monthly = Monthly.builder();

        monthly.id( today.getId() );
        monthly.text( today.getText() );
        monthly.image( today.getImage() );
        monthly.hour( today.getHour() );
        monthly.user( today.getUser() );

        return monthly.build();
    }
}
