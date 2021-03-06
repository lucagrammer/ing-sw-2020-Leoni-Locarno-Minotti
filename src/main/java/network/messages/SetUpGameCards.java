package network.messages;

import client.View;
import model.Card;
import network.CVMessage;
import network.VCMessage;
import server.VirtualView;
import util.MessageType;

import java.io.Serializable;
import java.util.List;

/**
 * Message for the choice of the game cards by the challenger
 */
public class SetUpGameCards implements CVMessage, VCMessage, Serializable {
    private final MessageType messageType;
    private int numCards;
    private List<Card> chosenCards;

    /**
     * Server-side constructor: build a request message
     *
     * @param numCards The number of cards to be selected
     */
    public SetUpGameCards(int numCards) {
        messageType = MessageType.CV;
        this.numCards = numCards;
    }

    /**
     * Client-side constructor: build a response message
     *
     * @param chosenCards The chosen game cards
     */
    public SetUpGameCards(List<Card> chosenCards) {
        messageType = MessageType.VC;
        this.chosenCards = chosenCards;
    }

    /**
     * Gets the message type
     *
     * @return The message type
     */
    public MessageType getType() {
        return messageType;
    }

    /**
     * Execute the request client-side
     *
     * @param view The recipient component
     */
    public void execute(View view) {
        view.askGameCards(numCards);
    }

    /**
     * Execute the request server-side
     *
     * @param virtualView The recipient component
     */
    public void execute(VirtualView virtualView) {
        virtualView.setGameCards(chosenCards);
    }
}
