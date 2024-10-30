package org.example.spring1.today;

import javax.annotation.processing.Generated;
import org.example.spring1.monthly.model.dto.Monthly;
import org.example.spring1.monthly.model.dto.Monthly.MonthlyBuilder;
import org.example.spring1.today.model.TodayTable;
import org.example.spring1.today.model.dto.Today;
import org.example.spring1.today.model.dto.Today.TodayBuilder;
import org.example.spring1.weekly.model.dto.Weekly;
import org.example.spring1.weekly.model.dto.Weekly.WeeklyBuilder;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-01T15:28:30+0300",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.6.jar, environment: Java 21.0.3 (Amazon.com Inc.)"
)
@Component
public class TodayTableMapperImpl implements TodayTableMapper {

    @Override
    public Today toToday(TodayTable today) {
        if ( today == null ) {
            return null;
        }

        TodayBuilder today1 = Today.builder();

        today1.id( today.getId() );
        today1.image( today.getImage() );
        today1.text( today.getText() );
        today1.hour( today.getHour() );
        today1.user( today.getUser() );

        return today1.build();
    }

    @Override
    public Weekly toWeekly(TodayTable today) {
        if ( today == null ) {
            return null;
        }

        WeeklyBuilder weekly = Weekly.builder();

        weekly.id( today.getId() );
        weekly.day( today.getDay() );
        weekly.text( today.getText() );
        weekly.image( today.getImage() );
        weekly.hour( today.getHour() );
        weekly.user( today.getUser() );

        return weekly.build();
    }

    @Override
    public Monthly toMonthly(TodayTable today) {
        if ( today == null ) {
            return null;
        }

        MonthlyBuilder monthly = Monthly.builder();

        monthly.id( today.getId() );
        monthly.nrDay( today.getNrDay() );
        monthly.text( today.getText() );
        monthly.image( today.getImage() );
        monthly.hour( today.getHour() );
        monthly.user( today.getUser() );

        return monthly.build();
    }
}
