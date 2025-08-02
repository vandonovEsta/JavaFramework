package api.faker.trello.boards;

import api.models.trello.boards.BoardRequestDto;
import com.github.javafaker.Faker;

public class BoardRequestDtoFaker {
    private static final Faker faker = new Faker();

    public static BoardRequestDto generateRequiredOnly() {
        String name = faker.company().name(); // or use .book().title() or .funnyName().name()
        return new BoardRequestDto(name, false, false, null, null, null, null, null, null, null);
    }

    public static BoardRequestDto generateFull() {
        return new BoardRequestDto(
                faker.company().name(),
                true,
                true,
                faker.lorem().sentence(),
                faker.idNumber().valid(),
                faker.idNumber().valid(),
                "all",
                "calendar",
                "public",
                "members"
        );
    }
}
