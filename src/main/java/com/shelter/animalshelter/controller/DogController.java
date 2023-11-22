package com.shelter.animalshelter.controller;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.shelter.animalshelter.model.animals.Dog;
import com.shelter.animalshelter.service.DogService;

@RestController
@RequestMapping("/dogs")
@Tag(name = "Собаки", description = "CRUD-методы для работы с собаками")
@RequiredArgsConstructor
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Всё хорошо, запрос выполнился."),
        @ApiResponse(responseCode = "400", description = "Есть ошибка в параметрах запроса."),
        @ApiResponse(responseCode = "404", description = "URL неверный или такого действия нет в веб-приложении."),
        @ApiResponse(responseCode = "500", description = "Во время выполнения запроса произошла ошибка на сервере.")
})
public class DogController {

    private final DogService dogService;

    @GetMapping("/id")
    @Operation(summary = "Получение собаки по ID")
    public Dog getByDogId(@RequestParam @Parameter(description = "ID собаки") Long dog_id) {
        return dogService.getById(dog_id);
    }

    @PostMapping
    @Operation(summary = "Добавить собаку в приют")
    public Dog create(
            @RequestParam @Parameter(description = "Имя собаки") String name,
            @RequestParam @Parameter(description = "Возраст") int age,
            @RequestParam @Parameter(description = "Здоров?") boolean isHealthy,
            @RequestParam @Parameter(description = "Привит?") boolean vaccinated)
    {
        return dogService.create(new Dog(name, age, isHealthy, vaccinated));
    }



    @PutMapping
    @Operation(summary = "Изменить информацию о собаке")
    public Dog update(
            @RequestParam @Parameter(description = "ID собаки") Long dog_id,
            @RequestParam(required = false) @Parameter(description = "Имя собаки") String name,
            @RequestParam(required = false) @Parameter(description = "Возраст собаки") Integer age,
            @RequestParam(required = false) @Parameter(description = "Здоров?") Boolean isHealthy,
            @RequestParam(required = false) @Parameter(description = "Привит?") Boolean vaccinated)
    {
        return dogService.update(new Dog(name, age, isHealthy, vaccinated));
    }

    @DeleteMapping("/id")
    @Operation(summary = "Удаление собаки")
    public String deleteById(@RequestParam Long dog_id) {
        dogService.remove(dog_id);
        return "Собаку исключили из приюта";
    }

}