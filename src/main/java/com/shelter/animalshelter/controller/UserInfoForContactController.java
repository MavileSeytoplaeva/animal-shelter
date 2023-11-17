package com.shelter.animalshelter.controller;

import com.shelter.animalshelter.model.UserInfoForContact;
import com.shelter.animalshelter.model.User;
import com.shelter.animalshelter.service.UserInfoForContactServiceImlp;
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
public class UserInfoForContactController {

    private UserInfoForContactServiceImlp userInfoForContactServiceImlp;

    public UserInfoForContactController(UserInfoForContactServiceImlp userInfoForContactServiceImlp) {
        this.userInfoForContactServiceImlp = userInfoForContactServiceImlp;
    }

    @Operation(
            requestBody = @RequestBody,
            summary = "Записывает данные пользователя для контакта с ним в БД",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Возвращает объект созданного пользователя для контакта с ним",
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
    public ResponseEntity<UserInfoForContact> createUserInfoForContact(@Parameter(name = "Объект пользователя для контакта с ним") @RequestBody UserInfoForContact userInfoForContact) {
        return ResponseEntity.ok(userInfoForContactServiceImlp.createUserInfoForContact(userInfoForContact));
    }

    @Operation(
            requestBody = @RequestBody,
            summary = "Редактирует данные пользователя для контакта с ним в БД",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Возвращает объект редактируемого пользователя для контакта с ним",
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
    public ResponseEntity<UserInfoForContact> editUserInfoForContact(@Parameter(name = "Объект пользователя для контакта с ним") @org.springframework.web.bind.annotation.RequestBody UserInfoForContact userInfoForContact) {
        return ResponseEntity.ok(userInfoForContactServiceImlp.editUserInfoForContact(userInfoForContact));
    }

    @Operation(
            summary = "Поиск пользователя для контакта с ним в БД по id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Возвращает найденного пользователя для контакта с ним",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = User.class))
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Не удалось найти пользователя для контакта с ним"
                    )
            },
            tags = "Adopters"
    )
    @GetMapping("/{id}")
    public ResponseEntity<UserInfoForContact> findUserInfoForContact(@PathVariable Long id) {
        try {
            userInfoForContactServiceImlp.findUserInfoForContact(id);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userInfoForContactServiceImlp.findUserInfoForContact(id));
    }

    @Operation(
            summary = "Удаляет пользователя для контакта с ним из БД по id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Ничего не возвращает"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Не удалось найти пользователя для контакта с ним"
                    )
            },
            tags = "Adopters"
    )
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteAnimalAdopter(@PathVariable Long id) {
        try {
            userInfoForContactServiceImlp.deleteUserInfoForContact(id);
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("all")
    public ResponseEntity<List<UserInfoForContact>> getAll() {
        return ResponseEntity.ok(userInfoForContactServiceImlp.getAllUserInfoForContact());
    }

}
