package ru.yandex.practicum.filmorate.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.validation.FilmDataChecker;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE) // Add this line
public class Film {
    int id;

    @NotBlank(message = "Название фильма не может быть пустым")
    String name;

    @Size(min = 1, max = 200, message = "Максимальная длина описания — 200 символов")
    String description;

    @NotNull(message = "Дата релиза не может быть null")
    @PastOrPresent(message = "Дата релиза не может быть в будущем") // Добавьте эту аннотацию
    @FilmDataChecker
    LocalDate releaseDate;

    @Min(value = 1, message = "Продолжительность фильма должна быть положительным числом")
    int duration;

    public Film() {
        if (name == null || name.isBlank()) {
            throw new ValidationException("название фильма не может быть пустым.");
        }
        if (description != null && description.length() > 200) {
            throw new ValidationException("максимальная длина описания — 200 символов.");
        }
        if (duration < 0) {
            throw new ValidationException("продолжительность фильма должна быть положительным числом.");
        }
    }
}