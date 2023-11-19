package com.shelter.animalshelter.controller;

import com.shelter.animalshelter.model.AnimalAdopter;
import com.shelter.animalshelter.model.User;
import com.shelter.animalshelter.service.implement.AnimalAdopterServiceImlp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/adopter")
public class AnimalAdopterController {

    private AnimalAdopterServiceImlp animalAdopterServiceImlp;

    public AnimalAdopterController(AnimalAdopterServiceImlp animalAdopterServiceImlp) {
        this.animalAdopterServiceImlp = animalAdopterServiceImlp;
    }

    @Operation(
            requestBody = @RequestBody,
            summary = "Записывает данные усыновителя в БД",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Возвращает объект созданного усыновителя",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = User.class))
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Ошибка на стороне сервера"
                    )
            },
            tags = "Adopters"
    )
    @PostMapping
    public ResponseEntity<AnimalAdopter> createAnimalAdopter(@Parameter(name = "Объект пользователя") @RequestBody AnimalAdopter animalAdopter) {
        return ResponseEntity.ok(animalAdopterServiceImlp.createAnimalAdopter(animalAdopter));
    }

    @Operation(
            requestBody = @RequestBody,
            summary = "Редактирует данные усыновителя в БД",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Возвращает объект редактируемого усыновителя",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = User.class))
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Ошибка на стороне сервера"
                    )
            },
            tags = "Adopters"
    )
    @PutMapping
    public ResponseEntity<AnimalAdopter> editAnimalAdopter(@Parameter(name = "Объект пользователя") @org.springframework.web.bind.annotation.RequestBody AnimalAdopter animalAdopter) {
        return ResponseEntity.ok(animalAdopterServiceImlp.editAnimalAdopter(animalAdopter));
    }

    @Operation(
            summary = "Поиск усыновителя в БД по id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Возвращает найденного усыновителя",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = User.class))
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Не удалось найти усыновителя"
                    )
            },
            tags = "Adopters"
    )
    @GetMapping("/{id}")
    public ResponseEntity<AnimalAdopter> findAnimalAdopter(@PathVariable Long id) {
        try {
            animalAdopterServiceImlp.findAnimalAdopter(id);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(animalAdopterServiceImlp.findAnimalAdopter(id));
    }

    @Operation(
            summary = "Удаляет усыновителя из БД по id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Ничего не возвращает"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Не удалось найти усыновителя"
                    )
            },
            tags = "Adopters"
    )
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteAnimalAdopter(@PathVariable Long id) {
        try {
            animalAdopterServiceImlp.deleteAnimalAdopter(id);
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("all")
    public ResponseEntity<List<AnimalAdopter>> getAll() {
        return ResponseEntity.ok(animalAdopterServiceImlp.getAllAdopters());
    }

}
