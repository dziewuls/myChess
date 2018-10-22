package com.pl.mychess.domain.classicchess.manager;

import com.pl.mychess.domain.model.chessboard.*;
import com.pl.mychess.domain.model.state.MatchResult;
import com.pl.mychess.domain.model.state.TypeOfCustomMove;
import com.pl.mychess.domain.port.game.GameManager;
import org.junit.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class GameManagerTest {
    @Test
    public void shouldGetCorrectPlacesReturnEmptyMapWhenGivenPlaceHasNotFigure() {
        GameManager gameManager = new ClassicChessGameManager();

        Map<Place, TypeOfCustomMove> possiblePlaces = gameManager.getCorrectMoveOptions(new Place('e', 4));

        assertThat(possiblePlaces).isEmpty();
    }

    @Test
    public void shouldGetCorrectPlacesReturnEmptyMapWhenFigureOnGivenPlaceHasWrongColor() {
        GameManager gameManager = new ClassicChessGameManager();

        Map<Place, TypeOfCustomMove> possiblePlaces = gameManager.getCorrectMoveOptions(new Place('e', 7));

        assertThat(possiblePlaces).isEmpty();
    }

    @Test
    public void shouldGetCorrectPlacesReturnCorrectMapWhenFigureOnGivenPlaceHasCorrectColor() {
        GameManager gameManager = new ClassicChessGameManager();

        Map<Place, TypeOfCustomMove> possiblePlacesMap = gameManager.getCorrectMoveOptions(new Place('e', 2));
        List<Place> possiblePlacesList = new ArrayList<>(possiblePlacesMap.keySet());

        List<Place> expectedPlaces = new ArrayList<>(
                Arrays.asList(
                        new Place('e', 3),
                        new Place('e', 4)
                )
        );

        assertThat(possiblePlacesList).containsExactlyInAnyOrderElementsOf(expectedPlaces);
    }

    @Test
    public void shouldGetCorrectPlacesReturnEmptyMapWhenFigureHasNotCorrectMoves() {
        GameManager gameManager = new ClassicChessGameManager();

        Map<Place, TypeOfCustomMove> possiblePlaces = gameManager.getCorrectMoveOptions(new Place('e', 1));

        assertThat(possiblePlaces).isEmpty();
    }

    @Test
    public void shouldMakeMoveUpdateTheChessboard() {
        GameManager gameManager = new ClassicChessGameManager();

        gameManager.getCorrectMoveOptions(new Place('e', 2));
        gameManager.makeMove(new Place('e', 4), TypeOfCustomMove.NORMAL, null);

        Chessboard chessboard = gameManager.getCurrentChessboard();
        Figure expectedPawn = new Figure(TypeOfFigure.PAWN, Color.WHITE);

        assertThat(chessboard.getFigureByCoordinates('e', 2)).isNull();
        assertThat(chessboard.getFigureByCoordinates('e', 4)).isEqualTo(expectedPawn);
    }

    @Test
    public void shouldMakeMoveAddTheMoveToStateOfChessboard() {
        GameManager gameManager = new ClassicChessGameManager();

        gameManager.getCorrectMoveOptions(new Place('e', 2));
        gameManager.makeMove(new Place('e', 4), TypeOfCustomMove.NORMAL, null);
        gameManager.getCorrectMoveOptions(new Place('e', 7));
        gameManager.makeMove(new Place('e', 5), TypeOfCustomMove.NORMAL, null);

        List<String> moves = gameManager.getMovesHistory();

        List<String> expectedMoves = new ArrayList<>(Arrays.asList("e2-e4", "e7-e5"));

        assertThat(moves).containsExactlyInAnyOrderElementsOf(expectedMoves);
    }

    @Test
    public void shouldGameManagerReturnCorrectMatchResult() {
        GameManager gameManager = new ClassicChessGameManager();

        gameManager.getCorrectMoveOptions(new Place('e', 2));
        gameManager.makeMove(new Place('e', 4), TypeOfCustomMove.NORMAL, null);
        gameManager.getCorrectMoveOptions(new Place('e', 7));
        gameManager.makeMove(new Place('e', 5), TypeOfCustomMove.NORMAL, null);

        MatchResult matchResult = gameManager.getGameResult();

        assertThat(matchResult).isEqualTo(MatchResult.GAME_IS_NOT_COMPLETED);
    }

    @Test
    public void shouldBackMoveUpdateTheChessboard() {
        GameManager gameManager = new ClassicChessGameManager();

        gameManager.getCorrectMoveOptions(new Place('e', 2));
        gameManager.makeMove(new Place('e', 4), TypeOfCustomMove.NORMAL, null);
        gameManager.getCorrectMoveOptions(new Place('e', 7));
        gameManager.makeMove(new Place('e', 5), TypeOfCustomMove.NORMAL, null);

        gameManager.backMove();

        Chessboard chessboard = gameManager.getCurrentChessboard();
        Figure expectedPawn = new Figure(TypeOfFigure.PAWN, Color.BLACK);

        assertThat(chessboard.getFigureByCoordinates('e', 5)).isNull();
        assertThat(chessboard.getFigureByCoordinates('e', 7)).isEqualTo(expectedPawn);
    }

    @Test
    public void shouldBackMoveRemoveTheMoveFromStateOfChessboard() {
        GameManager gameManager = new ClassicChessGameManager();

        gameManager.getCorrectMoveOptions(new Place('e', 2));
        gameManager.makeMove(new Place('e', 4), TypeOfCustomMove.NORMAL, null);
        gameManager.getCorrectMoveOptions(new Place('e', 7));
        gameManager.makeMove(new Place('e', 5), TypeOfCustomMove.NORMAL, null);

        gameManager.backMove();

        List<String> moves = gameManager.getMovesHistory();

        List<String> expectedMoves = new ArrayList<>(Collections.singletonList("e2-e4"));

        assertThat(moves).containsExactlyInAnyOrderElementsOf(expectedMoves);
    }
}
