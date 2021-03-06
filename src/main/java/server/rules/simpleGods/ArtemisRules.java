package server.rules.simpleGods;

import model.Cell;
import model.Game;
import model.Player;
import model.Worker;
import server.rules.Rules;
import util.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Artemis Card
 */
public class ArtemisRules extends Rules {

    /**
     * Gets a RoundActions object containing all the possible actions of the specified player according to the Rules
     *
     * @param player The player whose possible actions are to be analyzed
     * @param game   The game to which the player belongs
     * @return A RoundActions object containing all the possible actions
     */
    public RoundActions nextPossibleActions(Player player, Game game) {
        RoundActions roundActions = player.getRoundActions();
        RoundActions possibleActions = new RoundActions();

        // Has not moved?
        if (roundActions.hasMoved() == 0) {
            possibleActions.add(getPossibleMoves(player.getWorker(Genre.MALE), game));
            possibleActions.add(getPossibleMoves(player.getWorker(Genre.FEMALE), game));
            //Can't move?
            if (possibleActions.isEmpty())
                possibleActions.add(new Action(ActionType.LOSE));
        } else {
            Genre movedWorkerGenre = roundActions.
                    getActionList().
                    stream().
                    filter(x -> x.getActionType() == ActionType.MOVE).
                    collect(Collectors.toList()).
                    get(0).
                    getGenre();

            // Has not build?
            if (roundActions.hasBuildAnything() == 0) {
                // Has moved once?
                if (roundActions.hasMoved() == 1) {
                    possibleActions.add(getPossibleMoves(player.getWorker(movedWorkerGenre), game));
                }
                possibleActions.add(getPossibleBuilds(player.getWorker(movedWorkerGenre), game));
                possibleActions.add(getPossibleDomes(player.getWorker(movedWorkerGenre), game));
                //Can't do anything?
                if (possibleActions.isEmpty())
                    possibleActions.add(new Action(ActionType.LOSE));
            } else {
                possibleActions.add(new Action(ActionType.END));
            }
        }
        return possibleActions;
    }

    /**
     * Gets a RoundActions object containing all the move-actions of the specified worker according to the Rules
     *
     * @param worker The worker whose possible moves are to be analyzed
     * @param game   The game to which the player belongs
     * @return A RoundActions object containing all the possible move-actions
     */
    protected RoundActions getPossibleMoves(Worker worker, Game game) {
        RoundActions roundMoves = new RoundActions();
        Cell workerCell = worker.getPosition();
        List<Cell> cells = calculateStandardMoves(workerCell, game);

        // Remove the cell occupied by the previous move, if any
        Player player = game.getPlayerByColor(worker.getPlayerColor());
        RoundActions roundActions = player.getRoundActions();
        if (!roundActions.isEmpty()) {
            Direction prevMoveDirection = roundActions.getActionList().get(0).getDirection();
            cells.remove(game.getBoard().getPrevCell(workerCell, prevMoveDirection));
        }

        roundMoves.addMoves(cells, workerCell, worker.getGenre());
        return roundMoves;
    }
}
