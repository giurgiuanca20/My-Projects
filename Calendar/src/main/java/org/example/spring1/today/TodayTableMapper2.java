package org.example.spring1.today;

import org.example.spring1.monthly.model.dto.Monthly;
import org.example.spring1.today.model.TodayTable;
import org.example.spring1.today.model.dto.Today;
import org.example.spring1.weekly.model.dto.Weekly;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TodayTableMapper2 {

    public TodayTable toTodayTableFromMonthly(Monthly today) {
        if ( today == null ) {
            return null;
        }

        TodayTable.TodayTableBuilder todayTable = TodayTable.builder();

        todayTable.id( today.getId() );
        todayTable.image( today.getImage() );
        todayTable.text( today.getText() );
        todayTable.hour( today.getHour() );
        todayTable.nrDay( today.getNrDay() );
        todayTable.user( today.getUser() );
        todayTable.type( "monthly" );

        return todayTable.build();
    }
    public TodayTable toTodayTableFromToday(Today today) {
        if ( today == null ) {
            return null;
        }

        TodayTable.TodayTableBuilder todayTable = TodayTable.builder();

        todayTable.id( today.getId() );
        todayTable.image( today.getImage() );
        todayTable.text( today.getText() );
        todayTable.hour( today.getHour() );
        todayTable.user( today.getUser() );
        todayTable.type( "today" );

        return todayTable.build();
    }

    public TodayTable toTodayTableFromWeekly(Weekly today) {
        if ( today == null ) {
            return null;
        }

        TodayTable.TodayTableBuilder todayTable = TodayTable.builder();

        todayTable.id( today.getId() );
        todayTable.image( today.getImage() );
        todayTable.text( today.getText() );
        todayTable.hour( today.getHour() );
        todayTable.day( today.getDay() );
        todayTable.user( today.getUser() );
        todayTable.type( "weekly" );
        return todayTable.build();
    }


}
