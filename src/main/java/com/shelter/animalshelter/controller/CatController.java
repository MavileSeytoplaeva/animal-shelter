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

import java.util.List;

@RestController
@RequestMapping("cats")
@Tag(name = "Кошки", description = "CRUD-методы для работы с кошками")
@RequiredArgsConstructor
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Всё хорошо, запрос выполнился."),
        @ApiResponse(responseCode = "400", description = "Есть ошибка в параметрах запроса."),
        @ApiResponse(responseCode = "404", description = "URL неверный или такого действия нет в веб-приложении."),
        @ApiResponse(responseCode = "500", description = "Во время выполнения запроса произошла ошибка на сервере.")
})
public class CatController {

    private final CatService catService;

    @GetMapping("/id")
    @Operation(summary = "Получение кота по ID")
    public Cat getById(@RequestParam @Parameter(description = "ID кота") Long id) {
        return catService.getById(id);
    }

 //   @PostMapping
 //   @Operation(summary = "Добавить кота в приют")
 //   public Cat create(
 //           @RequestParam @Parameter(description = "Имя кота") String name,
 //           @RequestParam @Parameter(description = "Возраст") int age,
 //           @RequestParam @Parameter(description = "Здоров?") boolean isHealthy,
 //           @RequestParam @Parameter(description = "Привит?") boolean vaccinated)
 //            {
 //       return catService.create(new Cat(name, age, isHealthy, vaccinated));
 //   }

 //   @GetMapping()
 //   @Operation(summary = "Получение всех котов")
 //   public List<Cat> getAll() {
 //       return catService.getAll();
 //   }

  //  @GetMapping("/ownerID")
  //  @Operation(summary = "Получение списка котов по ID хозяина")
  //  public List<Cat> getOwnerById(@RequestParam @Parameter(description = "ID хозяина") Long id) {
  //      return catService.getAllByUserId(id);
  //  }


    @PutMapping
    @Operation(summary = "Изменить информацию о коте")
    public Cat update(
            @RequestParam @Parameter(description = "ID кота") Long id,
            @RequestParam(required = false) @Parameter(description = "Имя кота") String name,
            @RequestParam(required = false) @Parameter(description = "Возраст кота") Integer age,
            @RequestParam(required = false) @Parameter(description = "Здоров?") Boolean isHealthy,
            @RequestParam(required = false) @Parameter(description = "Привит?") Boolean vaccinated,
            @RequestParam(required = false) @Parameter(description = "ID кошачьего приюта") Long shelterId) {
        return catService.update(new Cat(id, name, age, isHealthy, vaccinated, shelterId));
    }

    @DeleteMapping("/id")
    @Operation(summary = "Удаление кота")
    public String deleteById(@RequestParam Long id) {
        catService.remove(id);
        return "Кота выбросили на улицу";
    }

}