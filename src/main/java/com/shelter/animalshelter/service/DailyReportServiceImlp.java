package com.shelter.animalshelter.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.PhotoSize;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.GetFile;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.GetFileResponse;
import com.pengrad.telegrambot.response.SendResponse;
import com.shelter.animalshelter.model.AnimalAdopter;
import com.shelter.animalshelter.model.DailyReport;

import com.shelter.animalshelter.repository.DailyReportRepository;
import com.shelter.animalshelter.service.implement.AnimalAdopterServiceImlp;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.scheduling.annotation.Scheduled;

import org.springframework.scheduling.annotation.Scheduled;

import com.shelter.animalshelter.repository.DailyReportRepository;
import com.shelter.animalshelter.service.implement.AnimalAdopterServiceImlp;
import org.springframework.beans.factory.annotation.Value;
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
    private AnimalAdopterServiceImlp animalAdopterServiceImlp;

    public DailyReportServiceImlp(DailyReportRepository dailyReportRepository) {
        this.dailyReportRepository = dailyReportRepository;
    }

    @Value("${telegram.bot.token}")
    TelegramBot bot = new TelegramBot("${telegram.bot.token}");

    /**
     * The method receives the update and checks if the user had sent both a photo and the caption below.
     * If not bot asks to send the photo both with the caption.
     * <br>
     * When photo is provided {@link PhotoSize} writes down the size of the photo. {@link Path} writes the path of the photo.
     * <br>
     * Trigger every day at 12a.m.
     * The report is being saved to the DB.
     * @throws IOException
     *
     * @param update
     */
    @Scheduled(cron = "0 0 12 * * ?")
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

    @Scheduled(cron = "0 0 12 * * ?")
    private SendResponse sendWarning() {
        LocalDate todayDate = LocalDate.now();
        LocalDate lastReportDate;
        LocalDate firstReportDate;
        LocalDate twoDaysFromLastReportDate;
        LocalDate probationPeriod;
        int additionalProbationPeriod = 14;

        for (AnimalAdopter animalAdopter : animalAdopterServiceImlp.getAll()) {
            if (animalAdopter.isTookAnimal()) {
                SendMessage messageText = new SendMessage(animalAdopter.getId(), "Пожалуйста, отправьте отчёт, " +
                        "отправьте одним сообщением фотографии и описание");
                SendResponse response = bot.execute(messageText);
                return response;
            }
            DailyReport lastDailyReport = dailyReportRepository.getLastDailyReportSent(animalAdopter.getId());
            lastReportDate = lastDailyReport.getDate();
//            reportDate = dailyReportRepository.getDateByChatId(animalAdopter.getId());
            twoDaysFromLastReportDate = lastReportDate.plusDays(2);


            if (twoDaysFromLastReportDate.equals(todayDate)) {
                SendMessage messageText1 = new SendMessage(animalAdopter.getId(), "Вы не отправляли " +
                        "отчёты уже более двух дней");
                SendResponse response1 = bot.execute(messageText1);
                return response1;
            }
//            reportDate = dailyReportRepository.getDateByChatId(animalAdopter.getId());
            DailyReport firstDailyReport = dailyReportRepository.getFirstDailyReport(animalAdopter.getId());
            probationPeriod = firstDailyReport.getDate().plusDays(30);
            Long numberOfRecordsInTable = dailyReportRepository.getNumberOfRecords(animalAdopter.getId());
            if ((probationPeriod.equals(todayDate) && numberOfRecordsInTable == 30) || numberOfRecordsInTable >= 30) {
                SendMessage messageText2 = new SendMessage(animalAdopter.getId(), "Поздравляем! " +
                        "Вы успешно прошли испытательный срок.");
                SendResponse response2 = bot.execute(messageText2);
                return response2;
            } else if (!todayDate.equals(probationPeriod)) {
                SendMessage messageText3 = new SendMessage(animalAdopter.getId(), "К сожалению, " +
                        "вы не прошли испытательный срок. Пожалуйста, следуйте инструкциям для дальнейших шагов.");
                SendResponse response3 = bot.execute(messageText3);
                return response3;
            }
        }
        return null;
    }
}








