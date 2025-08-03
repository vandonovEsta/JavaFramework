package api;

import api.models.trello.actions.ActionResponseDto;
import api.models.trello.cards.CardResponseDto;
import api.services.CardService;
import com.github.javafaker.Faker;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Optional;

import static junit.framework.Assert.assertEquals;

public class CardWorkflowTests extends BoardSetupTests {

    protected String cardId;

    @BeforeTest
    public void setUp() {
        boardWorkflowTest();
    }

    @Test
    public void cardWorkflowTest() {
        String cardName = "Prepare Interview Task";

        //Create card
        CardService cardService = new CardService();
        CardResponseDto card = cardService.createCard(cardName, toDoListId);
        cardId = card.getId();
        card = cardService.getCard(cardId);
        assertEquals("Card id is not the same as todoList", toDoListId, card.getIdList());

        //Move cart trough lists and validate position
        card = cardService.mobeCard(cardId, inProgressListId);
        card = cardService.getCard(cardId);
        assertEquals("Card id is not the same as inProgressList", inProgressListId, card.getIdList());
        card = cardService.mobeCard(cardId, doneListid);
        card = cardService.getCard(cardId);
        assertEquals("Card id is not the same as doneList", doneListid, card.getIdList());

        //Add comment and assert actions
        String comment = Faker.instance().lorem().sentence();
        cardService.addCommentToCard(cardId, comment);
        Optional<ActionResponseDto> actionResponseDto = cardService.getActionsForCard(cardId).stream().filter(a -> a.getType().equals("commentCard")).findFirst();
        actionResponseDto.ifPresent(action ->
                assertEquals(comment, action.getData().getText()));
    }
}
