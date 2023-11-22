package com.shelter.animalshelter.controller;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.shelter.animalshelter.model.animals.Cat;
import com.shelter.animalshelter.service.CatService;

@RestController
@RequestMapping("/cats")
@Tag(name = "Кошки", description = "CRUD-методы для работы с кошками")
@RequiredArgsConstructor
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Всё хорошо, запрос выполнился."),
        @ApiResponse(responseCode = "400", description = "Есть ошибка в параметрах запроса."),
        @ApiResponse(responseCode = "404", description = "URL неверный или такого действия нет в веб-приложении."),
        @ApiResponse(responseCode = "500", description = "Во время выполнения запроса произошла ошибка на сервере.")
})
public class CatController {
//    @PostMapping
//    @Operation(
//            requestBody = @RequestBody,
//            summary = "Записывает кота в БД",
//            responses = {
//                    @ApiResponse(
//                            responseCode = "200",
//                            description = "Возвращает объект созданного кота",
//                            content = @Content(
//                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
//                                    array = @ArraySchema(schema = @Schema(implementation = Cat.class))
//                            )
//                    ),
//                    @ApiResponse(
//                            responseCode = "500",
//                            description = "Ошибка на стороне сервера"
//                    )
//            },
//            tags = "Кошки"
//    )
//    public ResponseEntity<Cat> createCat(@RequestBody Cat cat) {
//        return ResponseEntity.ok(catService.create(cat));
//    }



       @PostMapping
       @Operation(summary = "Добавить кота в приют")
       public Cat create(
               @RequestParam @Parameter(description = "Имя кота") String name,
               @RequestParam @Parameter(description = "Возраст") int age,
               @RequestParam @Parameter(description = "Здоров?") boolean isHealthy,
               @RequestParam @Parameter(description = "Привит?") boolean vaccinated)
                {
           return catService.create(new Cat(name, age, isHealthy, vaccinated));
       }

    @PutMapping
    @Operation(summary = "Изменить информацию о коте")
    public Cat update(
            @RequestParam @Parameter(description = "ID кота") Long cat_id,
            @RequestParam(required = false) @Parameter(description = "Имя кота") String name,
            @RequestParam(required = false) @Parameter(description = "Возраст кота") Integer age,
            @RequestParam(required = false) @Parameter(description = "Здоров?") Boolean isHealthy,
            @RequestParam(required = false) @Parameter(description = "Привит?") Boolean vaccinated)
    {
        return catService.update(new Cat(name, age, isHealthy, vaccinated));
    }


    private final CatService catService;

    @GetMapping("/id")
    @Operation(summary = "Получение кота по ID")
    public Cat getByCatId(@RequestParam @Parameter(description = "ID кота") Long cat_id) {
        return catService.getById(cat_id);
    }

    @DeleteMapping("/id")
    @Operation(summary = "Удаление кота")
    public String deleteById(@RequestParam Long cat_id) {
        catService.remove(cat_id);
        return "Кота выбросили на улицу";
    }

}