package com.shelter.animalshelter.repository;

import com.shelter.animalshelter.model.DailyReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Repository
public interface DailyReportRepository extends JpaRepository<DailyReport, Long> {

 @Query (value = "select sent_date from daily_report where chat_id=?", nativeQuery = true)
    LocalDate getDateByChatId (Long chatId);
}
