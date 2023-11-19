package com.shelter.animalshelter.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.request.SendMessage;
import com.shelter.animalshelter.service.implement.CatShelterServiceImpl;
import com.shelter.animalshelter.service.implement.DogShelterServiceImpl;
import com.shelter.animalshelter.service.implement.ShelterInfoTakeAnimalImlp;
import com.shelter.animalshelter.util.CallbackDataRequest;
import com.shelter.animalshelter.util.MessageSender;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ButtonReactionService {
    @Value("${telegram.bot.token}")
    TelegramBot bot = new TelegramBot("${telegram.bot.token}");
    private final MenuService menuService;
    // private final CallbackDataRequest callbackDataRequest;
    private final MessageSender messageSender;
    private final CatShelterServiceImpl catShelterService;

    private final DogShelterServiceImpl dogShelterService;
    private final ShelterInfoTakeAnimalImlp shelterInfoTakeAnimal;

    private final UpdateTextHandlerImpl updateTextHandler;
    private boolean isCat = false;

    public ButtonReactionService(MenuService menuService, MessageSender messageSender, CatShelterServiceImpl catShelterService, DogShelterServiceImpl dogShelterService, ShelterInfoTakeAnimalImlp shelterInfoTakeAnimal, UpdateTextHandlerImpl updateTextHandler) {
        this.menuService = menuService;
        this.messageSender = messageSender;
        this.catShelterService = catShelterService;
        this.dogShelterService = dogShelterService;
        this.shelterInfoTakeAnimal = shelterInfoTakeAnimal;
        this.updateTextHandler = updateTextHandler;
    }

    public SendMessage buttonReaction(CallbackQuery callbackQuery) {

        Long chatId = callbackQuery.message().chat().id();
        String data = callbackQuery.data();
        CallbackDataRequest constantByRequest = CallbackDataRequest.getConstantByRequest(data);

        switch (constantByRequest) {

            case CAT:
                isCat = true;
                return menuService.getCatMenu(chatId);
            case DOG:
                isCat = false;
                return menuService.getDogMenu(chatId);
            case GENERAL_SHELTER_INFO:
                return menuService.getInfoAboutShelter(chatId);
            case ABOUT_SHELTER:
                if (isCat) {
                    return catShelterService.getInfoAboutMe(chatId);
                } else
                    return dogShelterService.getInfoAboutMe(chatId);
            case LOCATION:
                if (isCat) {
                    return catShelterService.getLocation(chatId);
                } else
                    return dogShelterService.getLocation(chatId);
            case TIMETABLE:
                if (isCat) {
                    return catShelterService.getTimetable(chatId);
                } else
                    return dogShelterService.getTimetable(chatId);
            case SECURITY:
                if (isCat) {
                    return catShelterService.getSecurity(chatId);
                } else
                    return dogShelterService.getSecurity(chatId);
            case SAFETY_IN_SHELTER_TERRITORY:
                if (isCat) {
                    return catShelterService.getSafetyAdvice(chatId);
                } else
                    return dogShelterService.getSafetyAdvice(chatId);
            case HOW_TO_TAKE_ANIMAL:
                return menuService.getInfoAboutTakeAnimal(chatId);
            case GIVE_MY_CONTACT:
                return messageSender.sendMessage(chatId, "Введите пожалуйста ваш номер, имя и электронную почту и наш волонтёр свяжется с вами в ближайшее время. Порядок написания данных не важен.");
            case VOLUNTEER:
                return updateTextHandler.getVolunteerHelp(chatId);
            case ROLLBACK:
                return menuService.getCatAndDogBottonsOnly(chatId);
            case SHELTER_RULES_BEFORE_MEETING_ANIMAL:
                return shelterInfoTakeAnimal.getRulesForMeeting(chatId);
            case DOCUMENTS_TO_TAKE_ANIMAL:
                return shelterInfoTakeAnimal.getDocumentList(chatId);
            case TRANSPORTATION_ADVICE:
                return shelterInfoTakeAnimal.getRecForTransport(chatId);
            case HOUSE_RULES_FOR_SMALL_ANIMAL:
                return shelterInfoTakeAnimal.getHomeRecommendForSmall(chatId);
            case HOUSE_RULES_FOR_ADULT_ANIMAL:
                return shelterInfoTakeAnimal.getHomeRecommendForBig(chatId);
            case HOUSE_RULES_FOR_ANIMAL_WITH_DISABILITY:
                return shelterInfoTakeAnimal.getHomeRecommendForDisable(chatId);
            case CYNOLOGIST_ADVICE:
                return shelterInfoTakeAnimal.getDogHandlerTips(chatId);
            case CYNOLOGISTS:
                return shelterInfoTakeAnimal.getRecForProvenDogHandlers(chatId);
            case REFUSE_REASONS:
                return shelterInfoTakeAnimal.getReasonsForRefusal(chatId);
//            case TAKE_CAT:
//            case TAKE_DOG:
//                return takeAnimal.takeAnimal(chatId, isCat);
//            case REPORT_ANIMAL:
//                if (reportService.checkIsFullReportPostToday()) {
//                    return messageSender.sendMessage(chatId, "Сегодня Вы уже отправили отчет о своем питомце. Наши волонтеры " +
//                            "посмотрят его в ближайшее время");
//                }
//                return messageSender.sendMessage(chatId, "Отправьте фото животного и текст с указанием следующей информации:\n * Рацион животного. \n" +
//                        " * Общее самочувствие и привыкание к новому месту.*\n" +
//                        " * Изменения в поведении: отказ от старых привычек, приобретение новых и т.д.*");

//            default:
//                return messageSender.sendMessage(chatId, "Обратитесь к волонтеру по телефону: 89111111111");*/
            //}
            //}

            //    }


            //  SendMessage message = new SendMessage(chatId, "text");
            // SendResponse response = bot.execute(message);
            // return response;
            default:
                return messageSender.sendMessage(chatId, "Обратитесь к волонтеру по телефону: 89111111111");
        }
    }
}

