package com.shelter.animalshelter.repository;

import com.shelter.animalshelter.model.DailyReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Repository
public interface DailyReportRepository extends JpaRepository<DailyReport, Long> {

    @Query(value = "select sent_date from daily_report where chat_id=?", nativeQuery = true)
    LocalDate getDateByChatId(Long chatId);

    @Query(value = "select * from daily_report where chat_id=? ORDER BY sent_date DESC LIMIT 1", nativeQuery = true)
    DailyReport getLastDailyReportSent(Long chatId);

    @Query(value = "select COUNT(*) AS record_count daily_report where chat_id=?", nativeQuery = true)
    Long getNumberOfRecords(Long chatId);
//    @Query("select * from daily_report WHERE id = (SELECT MIN(id) FROM daily_report) and chat_id=?")

    @Query(value = "select * from daily_report where chat_id=? ORDER BY sent_date LIMIT 1", nativeQuery = true)
    DailyReport getFirstDailyReport(Long chatId);
}

