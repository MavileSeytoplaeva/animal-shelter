package com.shelter.animalshelter.controller;

import com.shelter.animalshelter.model.User;
import com.shelter.animalshelter.service.implement.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Operation(
            requestBody = @RequestBody,
            summary = "Записывает данные пользователя в БД",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Возвращает объект созданного пользователя",
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
            tags = "Users"
    )
    @PostMapping
//    public ResponseEntity<User> createUser(@Parameter(name = "Объект пользователя") @org.springframework.web.bind.annotation.RequestBody User user) {
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.create(user));
    }

    @Operation(
            requestBody = @RequestBody,
            summary = "Редактирует данные пользователя в БД",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Возвращает объект редактируемого пользователя",
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
            tags = "Users"
    )
    @PutMapping
    public ResponseEntity<User> updateUser(@Parameter(name = "Объект пользователя") @RequestBody User user) {
        return ResponseEntity.ok(userService.update(user));
    }

    @Operation(
            summary = "Поиск пользователя в БД по id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Возвращает найденного пользователя",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = User.class))
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Не удалось найти пользователя"
                    )
            },
            tags = "Users"
    )
    @GetMapping("/{id}")
    public ResponseEntity<User> findUser(@PathVariable long id) {
        try {
            userService.getById(id);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userService.getById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }

    @Operation(
            summary = "Удаляет пользователя из БД по id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Ничего не возвращает"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Не удалось найти пользователя"
                    )
            },
            tags = "Users"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable long id) {
        try {
            userService.deleteUser(id);
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }
}

