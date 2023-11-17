package com.shelter.animalshelter.repository;

import com.shelter.animalshelter.model.DailyReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DailyReportRepository extends JpaRepository<DailyReport, Long> {
}
