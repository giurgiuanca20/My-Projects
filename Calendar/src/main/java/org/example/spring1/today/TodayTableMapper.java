package org.example.spring1.today;

import org.example.spring1.monthly.model.dto.Monthly;
import org.example.spring1.today.model.TodayTable;
import org.example.spring1.today.model.dto.Today;
import org.example.spring1.weekly.model.dto.Weekly;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TodayTableMapper {

  //today

  Today toToday(TodayTable today);

  //weekly

  Weekly toWeekly(TodayTable today);

  //monthly

  Monthly toMonthly(TodayTable today);





}
