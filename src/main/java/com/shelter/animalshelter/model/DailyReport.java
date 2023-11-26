package com.shelter.animalshelter.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@Entity
@Table(name = "daily_report")
public class DailyReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "chat_id")
    private long chatId;
    @Column(name = "photo")
    private String photo;
    @Column(name = "sent_date")
    private LocalDate date;
    @Column(name = "report_text_under_photo")
    private String reportTextUnderPhoto;

}
