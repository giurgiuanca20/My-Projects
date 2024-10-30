package org.example.spring1.monthly;

import lombok.RequiredArgsConstructor;
import org.example.spring1.exception.EntityNotFoundException;
import org.example.spring1.monthly.model.dto.Monthly;
import org.example.spring1.monthly.model.dto.MonthlyDTO;
import org.example.spring1.monthly.model.dto.MonthlyRequestDTO;
import org.example.spring1.today.TodayRepository;
import org.example.spring1.today.TodayTableMapper;
import org.example.spring1.today.TodayTableMapper2;
import org.example.spring1.today.model.TodayTable;
import org.example.spring1.user.UserRepository;
import org.example.spring1.user.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MonthlyService {

    private final MonthlyMapper monthlyMapper;
    private final TodayTableMapper todayTableMapper;
    private final TodayTableMapper2 todayTableMapper2;
    private final TodayRepository monthlyRepository;
    private final UserRepository userRepository;

    public MonthlyDTO createMonthly(MonthlyRequestDTO dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("Cannot find user"));

        Monthly monthly=monthlyMapper.requestDTOToEntity(dto);
        monthly.setUser(user);
        Monthly savedMonthly=todayTableMapper.toMonthly(monthlyRepository.save(todayTableMapper2.toTodayTableFromMonthly(monthly)));
        return monthlyMapper.toMonthlyDto(savedMonthly);
    }
    public void deleteMonthly(String text,String nrDay) {
        monthlyRepository.deleteByTextAndNrDay(text,nrDay,"monthly");
    }



    public List<MonthlyDTO> findByUserIdAndNrDay(Long userId,String nrDay) {
        List<TodayTable> monthlyList = monthlyRepository.findByUserIdAndNrDay(userId,nrDay,"monthly");
        return monthlyList.stream()
                .map(todayTableMapper::toMonthly)
                .map(monthlyMapper::toMonthlyDto)
                .toList();
    }




}
