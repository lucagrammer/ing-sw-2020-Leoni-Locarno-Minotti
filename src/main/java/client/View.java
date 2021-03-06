package client;

import model.Card;
import model.Cell;
import model.Player;
import util.Genre;
import util.MapInfo;
import util.RoundActions;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages the interface to the user
 */
public interface View {

    /**
     * Sets the serverHandler
     *
     * @param serverHandler The serverHandler
     */
    void setServerHandler(ServerHandler serverHandler);

    /**
     * Interface launcher. Asks the server IP to connect to and notify it to the serverHandler
     */
    void launch();

    /**
     * Shows a message to say to the user that is connected to
     * the server and will be added to the next available game
     */
    void showQueuedMessage();

    /**
     * Shows a specified message to the user
     *
     * @param message   The message to be shown
     * @param newScreen True if it's necessary to clean the interface
     */
    void showMessage(String message, boolean newScreen);

    /**
     * Asks a new nickname to the user and notify the choice to the serverHandler
     */
    void askNewNickname();

    /**
     * Asks nickname, birth date and if it's a new game the number of players
     * for the game and notify the information to the serverHandler
     *
     * @param newGame True if the it is a new game, otherwise false
     */
    void setUpGame(boolean newGame);

    /**
     * Asks the game cards
     *
     * @param numCards Number of cards to be selected
     */
    void askGameCards(int numCards);

    /**
     * Asks the card the player whats to use during the game
     *
     * @param possibleChoices All the possible cards
     */
    void askPlayerCard(List<Card> possibleChoices);

    /**
     * Asks the nickname of the first player
     *
     * @param playersNicknames All the nicknames
     */
    void askFirstPlayer(List<String> playersNicknames);

    /**
     * Show all the cards of the game
     *
     * @param cards All the cards of the game
     */
    void showGameCards(List<Card> cards);

    /**
     * Shows the cards assignment of the game
     *
     * @param playerList The list of players of the game
     */
    void showCardAssignmentMessage(List<Player> playerList);

    /**
     * Asks the color the player whats to choose
     *
     * @param availableColors All the available colors
     */
    void askPlayerColor(ArrayList<String> availableColors);

    /**
     * Asks the first position for the male and female worker
     *
     * @param genre          The genre of the worker
     * @param forbiddenCells The forbidden cells
     * @param mapInfo        The map info
     */
    void askPlayerPosition(Genre genre, List<Cell> forbiddenCells, MapInfo mapInfo);

    /**
     * Shows the board of the game
     *
     * @param mapInfo   The map info
     * @param newScreen True if it's necessary to clean the interface
     */
    void showMap(MapInfo mapInfo, boolean newScreen);

    /**
     * Asks the action the player wants to perform
     *
     * @param roundActions  All the possible actions
     * @param mapInfo       The map info
     * @param loserNickname The nickname of the looser or null value
     */
    void askAction(RoundActions roundActions, MapInfo mapInfo, String loserNickname);

    /**
     * Notify the players that the game has ended and notify the winner
     *
     * @param winnerNickname The nickname of the winner
     * @param youWin         True if the player has win
     */
    void showGameEndMessage(String winnerNickname, boolean youWin);

    /**
     * Notify the players that a player has disconnected and the game has ended
     *
     * @param disconnectedNickname The nickname of the disconnected player
     */
    void showDisconnectionMessage(String disconnectedNickname);

    /**
     * Shows an error message to the user
     *
     * @param errorMessage The message to be shown
     * @param newScreen    True if it's necessary to clean the interface
     */
    void showErrorMessage(String errorMessage, boolean newScreen);

    /**
     * Notify the players that there is a loser
     *
     * @param loserNickname The nickname of the loser
     */
    void showLoser(String loserNickname);

    /**
     * Shows the user who is taking his turn
     *
     * @param currentNickname The nickname of the user who is taking his turn
     * @param hasLoser        True if during the turn a player has lost
     */
    void showTurn(String currentNickname, boolean hasLoser);
}
