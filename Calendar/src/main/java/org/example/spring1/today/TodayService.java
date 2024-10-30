package org.example.spring1.today;

import lombok.RequiredArgsConstructor;
import org.example.spring1.exception.EntityNotFoundException;
import org.example.spring1.today.model.dto.Today;
import org.example.spring1.today.model.dto.TodayDTO;
import org.example.spring1.today.model.dto.TodayRequestDTO;
import org.example.spring1.user.UserRepository;
import org.example.spring1.user.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class TodayService {

    private final TodayMapper todayMapper;
    private final TodayTableMapper2 todayTableMapper2;

    private final TodayRepository todayRepository;

    private final UserRepository userRepository;

    private final TodayTableMapper todayTableMapper;

    public TodayDTO createToday( TodayRequestDTO dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("Cannot find user"));

        Today today=todayMapper.requestDTOToEntity(dto);
        today.setUser(user);

        Today savedToday=todayTableMapper.toToday(todayRepository.save(todayTableMapper2.toTodayTableFromToday(today)));
        return todayMapper.toTodayDto(savedToday);
    }
    public void deleteToday(String text) {

        todayRepository.deleteByText(text,"today");
    }


    public List<TodayDTO> findByUserId(Long userId, String dayOfWeek, String nrDay) {
        // Obține lista Today și mapează la TodayDTO
        List<TodayDTO> todayDTOList = todayRepository.findByUserId(userId,"today").stream()
                .map(todayTableMapper::toToday)
                .map(todayMapper::toTodayDto)
                .toList();

        // Obține lista Weekly, mapează la Today și apoi la TodayDTO
        List<TodayDTO> weeklyDTOList = todayRepository.findByUserId(userId,"weekly").stream()
                .map(todayTableMapper::toWeekly)
                .map(todayMapper::weeklyToToday)
                .map(todayMapper::toTodayDto)
                .toList();

        // Obține lista Monthly, mapează la Today și apoi la TodayDTO
        List<TodayDTO> monthlyDTOList = todayRepository.findByUserIdAndNrDay(userId, nrDay,"monthly").stream()
                .map(todayTableMapper::toMonthly)
                .map(todayMapper::monthlyToToday)
                .map(todayMapper::toTodayDto)
                .toList();

        // Concatenează toate listele într-o singură listă
        List<TodayDTO> combinedList = Stream.concat(Stream.concat(todayDTOList.stream(), weeklyDTOList.stream()), monthlyDTOList.stream())
                .toList();

        return combinedList;
    }



}
