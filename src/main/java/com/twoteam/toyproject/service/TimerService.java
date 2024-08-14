package com.twoteam.toyproject.service;

import com.twoteam.toyproject.dto.TimerDTO;
import com.twoteam.toyproject.entity.TimerEntity;
import com.twoteam.toyproject.repository.TimerRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TimerService {
    private final TimerRepository timerRepository;

    public void save(TimerDTO timerDTO) {
        TimerEntity timerEntity = TimerEntity.toTimerEntity(timerDTO);
        timerRepository.save(timerEntity);
    }

    public List<TimerEntity> subAll() {
        return timerRepository.findAll();
    }

    public void delDataById(Long id) {
        timerRepository.deleteById(id);
    }

    public void updateTimer(Long id, TimerDTO timerDTO) {
        TimerEntity timerEntity = timerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Timer not found with id: " + id));
        timerEntity.setHours(timerDTO.getHours());
        timerEntity.setMinutes(timerDTO.getMinutes());
        timerEntity.setSeconds(timerDTO.getSeconds());
        timerRepository.save(timerEntity);
    }

    public TimerDTO getTime(Long id) {
        TimerEntity timerEntity = timerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Timer not found with id: " + id));

        return TimerDTO.fromTimerEntity(timerEntity);
    }

    public List<TimerEntity> subAllByEmail(String userEmail) {
        return timerRepository.findAllByEmail(userEmail);
    }
}