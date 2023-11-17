package com.shelter.animalshelter.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.request.SendMessage;
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

    private boolean isCat = false;

    public ButtonReactionService(MenuService menuService, MessageSender messageSender) {//, CallbackDataRequest callbackDataRequest) {
        this.menuService = menuService;
        //this.callbackDataRequest = callbackDataRequest;

        this.messageSender = messageSender;
    }
/*  public ButtonReactionService(MenuService menuService, MessageSender messageSender, ShelterInfoRepository shelterInfoRepository, ReportService reportService, TakeAnimal takeAnimal) {
        this.menuService = menuService;*/

       /* this.messageSender = messageSender;
        this.shelterInfoRepository = shelterInfoRepository;
        this.reportService = reportService;*/
    // this.takeAnimal = takeAnimal;


    //  @Override

    public SendMessage buttonReaction(CallbackQuery callbackQuery) {

        Long chatId = callbackQuery.message().chat().id();
        String data = callbackQuery.data();
        CallbackDataRequest constantByRequest = CallbackDataRequest.getConstantByRequest(data);
        //     Optional<ShelterInfo> shelterInfo;
        menuService.getFirstStartMenuShelter(chatId);
        // menuService.getCatMenu() or menuService.getDogMenu()
        if (isCat) {

     /*       shelterInfo = shelterInfoRepository.findById(1L);
        } else {
            shelterInfo = shelterInfoRepository.findById(2L);*/
        }
       /* System.out.println(constantByRequest + " constByRequest ");
        System.out.println(constantByRequest.getText() + " get Text constByRequest ");
        System.out.println(constantByRequest.toString() + " to String constByRequest ");
        System.out.println(constantByRequest.getCallbackData() + " data constByRequest ");*/
        switch (constantByRequest) {

            case CAT:
                isCat = true;
                return menuService.getCatMenu(chatId);
            case DOG:
                isCat = false;
                return menuService.getDogMenu(chatId);
            case GENERAL_SHELTER_INFO:
                return menuService.getInfoAboutShelter(chatId);
           /* case ABOUT_SHELTER:
                if (shelterInfo.isPresent()) {
                    return messageSender.sendMessage(chatId, shelterInfo.get().getAboutShelter());
                }
            case CONTACT_SHELTER:
                if (shelterInfo.isPresent()) {
                    return messageSender.sendMessage(chatId, shelterInfo.get().getAddressAndSchedule());
                }
            case SAFETY_CONTACT_FOR_CAR_PASS:
                if (shelterInfo.isPresent()) {
                    return messageSender.sendMessage(chatId, shelterInfo.get().getSafetyContactForCarPass());
                }
            case SAFETY_IN_SHELTER_TERRITORY:
                if (shelterInfo.isPresent()) {
                    return messageSender.sendMessage(chatId, shelterInfo.get().getSafetyOnTerritory());
                }*/
            case GIVE_MY_CONTACT:
                return messageSender.sendMessage(chatId, "Введите ваши данные в формате \"Имя Фамилия номер телефона с кодом +7\" ");
            case CALL_VOLUNTEER:
                return messageSender.sendMessage(chatId, "Позвать волонтера");
            case ROLLBACK:
                return menuService.getStartMenuShelter(chatId);
            case HOW_TO_TAKE_ANIMAL:
                return menuService.getInfoAboutTakeAnimal(chatId, isCat);

          /*  case SHELTER_RULES_BEFORE_MEETING_ANIMAL:
                if (shelterInfo.isPresent()) {
                    return messageSender.sendMessage(chatId, shelterInfo.get().getFirstMeetRecommendation());
                }
            case DOCUMENTS_TO_TAKE_ANIMAL:
                if (shelterInfo.isPresent()) {
                    return messageSender.sendMessage(chatId, shelterInfo.get().getDocuments());
                }
            case TRANSPORTATION_ADVICE:
                if (shelterInfo.isPresent()) {
                    return messageSender.sendMessage(chatId, shelterInfo.get().getTransportationAdvice());
                }
            case HOUSE_RULES_FOR_SMALL_ANIMAL:
                if (shelterInfo.isPresent()) {
                    return messageSender.sendMessage(chatId, shelterInfo.get().getHouseRulesForSmallAnimal());
                }
            case HOUSE_RULES_FOR_ADULT_ANIMAL:
                if (shelterInfo.isPresent()) {
                    return messageSender.sendMessage(chatId, shelterInfo.get().getHouseRulesForAdultAnimal());
                }
            case HOUSE_RULES_FOR_ANIMAL_WITH_DISABILITY:
                if (shelterInfo.isPresent()) {
                    return messageSender.sendMessage(chatId, shelterInfo.get().getRulesForAnimalWithDisability());
                }
            case CYNOLOGIST_ADVICE:
                if (shelterInfo.isPresent()) {
                    return messageSender.sendMessage(chatId, shelterInfo.get().getCynologistAdvice());
                }
            case CYNOLOGISTS:
                if (shelterInfo.isPresent()) {
                    return messageSender.sendMessage(chatId, shelterInfo.get().getCynologists());
                }
            case REFUSE_REASONS:
                if (shelterInfo.isPresent()) {
                    return messageSender.sendMessage(chatId, shelterInfo.get().getRefuseReasons());
                }
            case TAKE_CAT:
            case TAKE_DOG:
                return takeAnimal.takeAnimal(chatId, isCat);
            case REPORT_ANIMAL:
                if (reportService.checkIsFullReportPostToday()) {
                    return messageSender.sendMessage(chatId, "Сегодня Вы уже отправили отчет о своем питомце. Наши волонтеры " +
                            "посмотрят его в ближайшее время");
                }
                return messageSender.sendMessage(chatId, "Отправьте фото животного и текст с указанием следующей информации:\n * Рацион животного. \n" +
                        " * Общее самочувствие и привыкание к новому месту.*\n" +
                        " * Изменения в поведении: отказ от старых привычек, приобретение новых и т.д.*");

            default:
                return messageSender.sendMessage(chatId, "Обратитесь к волонтеру по телефону: 89111111111");*/
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

