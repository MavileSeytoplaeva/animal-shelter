package com.shelter.animalshelter.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.PhotoSize;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.GetFile;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SendPhoto;
import com.pengrad.telegrambot.response.GetFileResponse;
import com.pengrad.telegrambot.response.SendResponse;
import com.shelter.animalshelter.model.DailyReport;
import com.shelter.animalshelter.repository.DailyReportRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.UUID;
@Service
public class DailyReportServiceImlp {

    private DailyReportRepository dailyReportRepository;

    public DailyReportServiceImlp(DailyReportRepository dailyReportRepository) {
        this.dailyReportRepository = dailyReportRepository;
    }

    @Value("${telegram.bot.token}")
    TelegramBot bot = new TelegramBot("${telegram.bot.token}");

//    @Scheduled(cron = "0 0 12 * * ?")
//    public SendResponse reminderToSendDailyReport(Long chatId) {
//        if ( )
//    }


    public SendResponse postReport(Update update) {
        Long chatId = update.message().chat().id();
        DailyReport dailyReport = new DailyReport();
        dailyReport.setChatId(chatId);
        dailyReport.setDate(LocalDate.now());
        PhotoSize photoSize = update.message().photo()[update.message().photo().length - 1];
        GetFileResponse getFileResponse = bot.execute(new GetFile(photoSize.fileId()));
        if (getFileResponse.isOk()) {
            try {
                String extension = StringUtils.getFilenameExtension(getFileResponse.file().filePath());
                byte[] image = bot.getFileContent(getFileResponse.file());
                Path write = Files.write(Paths.get(UUID.randomUUID() + "." + extension), image);
                dailyReport.setPhoto(write.toString());

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if (update.message().caption() == null) {
            SendMessage messageText = new SendMessage(chatId, "Нужно отправить фото вместе с описанием");
            SendResponse response = bot.execute(messageText);
            return response;
        }

        dailyReport.setReportTextUnderPhoto(update.message().caption());
        dailyReportRepository.save(dailyReport);

        SendMessage messageText = new SendMessage(chatId, "Отчет добавлен. Не забывайте отправлять отчеты о вашем питомце ежедневно");
        SendResponse response = bot.execute(messageText);
        return response;
    }
}
