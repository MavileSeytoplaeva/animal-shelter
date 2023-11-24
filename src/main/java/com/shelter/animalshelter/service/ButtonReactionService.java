package com.shelter.animalshelter.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.request.SendMessage;
import com.shelter.animalshelter.service.implement.AnimalAdopterServiceImlp;
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
    private final TakeAnimalServiceImlp takeAnimalService;
    private final MessageSender messageSender;
    private final CatShelterServiceImpl catShelterService;
    private final AnimalAdopterServiceImlp animalAdopterService;

    private final DogShelterServiceImpl dogShelterService;
    private final ShelterInfoTakeAnimalImlp shelterInfoTakeAnimal;

    private final UpdateTextHandlerImpl updateTextHandler;
    private boolean isCat = false;

    public ButtonReactionService(MenuService menuService, TakeAnimalServiceImlp takeAnimalService, MessageSender messageSender, CatShelterServiceImpl catShelterService, AnimalAdopterServiceImlp animalAdopterService, DogShelterServiceImpl dogShelterService, ShelterInfoTakeAnimalImlp shelterInfoTakeAnimal, UpdateTextHandlerImpl updateTextHandler) {
        this.menuService = menuService;
        this.takeAnimalService = takeAnimalService;
        this.messageSender = messageSender;
        this.catShelterService = catShelterService;
        this.animalAdopterService = animalAdopterService;
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
            case REPORT_ANIMAL:
                return messageSender.sendMessage(chatId, "Чтобы бот принял ваш отчет нужно прислать фотографию питомца, и в описании написать рацион животного, общее самочувствие и привыкание к новому месту, а также изменение в поведении. Напишите всё одним сообщением.");
            case TAKE_CAT:
                if (isCat) {
                    takeAnimalService.getInfoAboutAllCats(chatId);
                    return menuService.CatNamesMenu(chatId);
                }
            case TAKE_DOG:
                if (!isCat) {
                    takeAnimalService.getInfoAboutAllDogs(chatId);
                    return menuService.DogNamesMenu(chatId);
                }
            case GARFIELD, OSCAR, VASYA, TOM, BARSIK, SAMMY:
                if (animalAdopterService.existsById(chatId)) {
                    takeAnimalService.addTookAnimalField(chatId);
                    return messageSender.sendMessage(chatId, "Спасибо за ответ. Наш волонтёр свяжется с вами в ближайшее время, чтобы обсудить, когда вы сможете забрать питомца и заполнить документы");
                } else
                    return messageSender.sendMessage(chatId, "Извините, у нас ещё нет ваших данных, чтобы наш волонтёр смог с вами связаться и уточнить информацию. Введите пожалуйста ваш номер, имя и электронную почту и наш волонтёр свяжется с вами в ближайшее время. Порядок написания данных не важен.");
            case NO:
                return messageSender.sendMessage(chatId, "Спасибо за ответ. Будем вас ждать позже");





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

