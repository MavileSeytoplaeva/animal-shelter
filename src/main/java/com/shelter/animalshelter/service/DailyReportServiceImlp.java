package com.shelter.animalshelter.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.PhotoSize;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.GetFile;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SendPhoto;
import com.pengrad.telegrambot.response.GetFileResponse;
import com.pengrad.telegrambot.response.SendResponse;
import com.shelter.animalshelter.model.AnimalAdopter;
import com.shelter.animalshelter.model.DailyReport;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.shelter.animalshelter.repository.DailyReportRepository;
import com.shelter.animalshelter.service.implement.AnimalAdopterServiceImlp;
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
    private AnimalAdopterServiceImlp animalAdopterServiceImlp;

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

    @Scheduled(cron = "@daily")
    private SendResponse sendWarning() {
        LocalDate todayDate = LocalDate.now();
        LocalDate reportDate;
        LocalDate twoDaysFromReportDate;


        for (AnimalAdopter animalAdopter : animalAdopterServiceImlp.getAll()) {
            if (animalAdopter.isTookAnimal()) {
                SendMessage messageText = new SendMessage(animalAdopter.getId(), "Пожалуйста, отправьте отчёт, " +
                        "отправьте одним сообщением фотографии и описание");
                SendResponse response = bot.execute(messageText);
                return response;
            }
            reportDate = dailyReportRepository.getDateByChatId(animalAdopter.getId());
            twoDaysFromReportDate = reportDate.plusDays(2);


            if (twoDaysFromReportDate.equals(todayDate)) {
                SendMessage messageText1 = new SendMessage(animalAdopter.getId(), "Вы не отправляли " +
                        "отчёты уже более двух дней");
                SendResponse response1 = bot.execute(messageText1);
                return response1;

            }


        }
        return null;
    }

    public class AdoptionPeriod {
        int probationPeriod = 30; // Испытательный срок в днях
        int additionalProbationPeriod = 14; // Дополнительный испытательный срок в днях

        boolean passedProbation = true; // Прошел ли усыновитель испытательный срок
        boolean additionalTimeAssigned = true; // Назначено ли усыновителю дополнительное время испытательного срока

            if(passedProbation &&!additionalTimeAssigned) {
            System.out.println("Поздравляем! Вы успешно прошли испытательный срок.");
        }
            else if(additionalTimeAssigned)

        {
            System.out.println("Вам назначено дополнительное время испытательного срока на " + additionalProbationPeriod + " дней.");
        } else

        {
            System.out.println("К сожалению, вы не прошли испытательный срок. Пожалуйста, следуйте инструкциям для дальнейших шагов.");
        }
    }
}




















//    @Scheduled(cron = "@daily")
//    private SendResponse sendWarning() {
//        LocalDate todayDate = LocalDate.now();
//        for (AnimalAdopter animalAdopter : animalAdopterServiceImlp.getAll()) {
//            if (animalAdopter.isTookAnimal()) {
//
//                SendMessage messageText = new SendMessage(animalAdopter.getId(), "Пожалуйста, отправьте отчёт, " +
//                        "отправьте одним сообщением фотографии и описание");
//                SendResponse response = bot.execute(messageText);
//
//                return response;
//            }
//
//            LocalDate twoDaysFromReportDate;
//            if (twoDaysFromReportDate.equals(todayDate)) {
//                SendMessage messageText1 = new SendMessage(animalAdopter.getId(), "Вы не отправляли " +
//                        "отчёты уже более двух дней");
//                SendResponse response1 = bot.execute(messageText1);
//                LocalDate reportDate = dailyReportRepository.getDateByChatId(animalAdopter.getId());
//                twoDaysFromReportDate = reportDate.plusDays(2);
//                return response1;
//            }
//        }
//    }
//}







