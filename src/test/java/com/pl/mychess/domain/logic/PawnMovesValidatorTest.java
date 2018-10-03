package com.pl.mychess.domain.logic;

import com.pl.mychess.domain.chessboard.ClassicChessChessboardFactory;
import com.pl.mychess.domain.model.chessboard.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PawnMovesValidatorTest {
    private static ClassicChessChessboardFactory chessboardFactory = new ClassicChessChessboardFactory();
    @Test
    public void shouldReturnTwoPlacesStraightAheadForPawnInStartingPosition() {
        Chessboard chessboard = chessboardFactory.createEmptyChessboard();
        Figure testedPawn = new Figure(TypeOfFigure.PAWN, ColorOfFigure.WHITE);
        chessboard.setFigureInPlace('e', 2, testedPawn);
        List<Place> possiblePlaces = MovesValidator.getAllPossiblePlacesForTheFigure(chessboard, testedPawn);

        List<Place> expectedPlaces = new ArrayList<>();
        expectedPlaces.add(new Place('e', 3));
        expectedPlaces.add(new Place('e', 4));

        assertThat(possiblePlaces).containsExactlyInAnyOrderElementsOf(expectedPlaces);
    }

    @Test
    public void shouldReturnOnePlaceAslantAndOnePlaceStraightForPawnWithBeating() {
        Chessboard chessboard = chessboardFactory.createEmptyChessboard();
        Figure testedPawn = new Figure(TypeOfFigure.PAWN, ColorOfFigure.WHITE);
        testedPawn.setMoved(true);
        chessboard.setFigureInPlace('e', 3, testedPawn);
        chessboard.setFigureInPlace('f', 4, new Figure(TypeOfFigure.ROOK, ColorOfFigure.BLACK));
        List<Place> possiblePlaces = MovesValidator.getAllPossiblePlacesForTheFigure(chessboard, testedPawn);

        List<Place> expectedPlaces = new ArrayList<>();
        expectedPlaces.add(new Place('f', 4, chessboard.getFigureByCoordinates('f', 4)));
        expectedPlaces.add(new Place('e', 4));

        assertThat(possiblePlaces).containsExactlyInAnyOrderElementsOf(expectedPlaces);
    }

    @Test
    public void shouldReturnOnePlaceStraightForPawnWhoHasAlreadyMadeAMove() {
        Chessboard chessboard = chessboardFactory.createEmptyChessboard();
        Figure testedPawn = new Figure(TypeOfFigure.PAWN, ColorOfFigure.WHITE);
        testedPawn.setMoved(true);
        chessboard.setFigureInPlace('e', 3, testedPawn);
        List<Place> possiblePlaces = MovesValidator.getAllPossiblePlacesForTheFigure(chessboard, testedPawn);

        List<Place> expectedPlaces = new ArrayList<>();
        expectedPlaces.add(new Place('e', 4));

        assertThat(possiblePlaces).containsExactlyInAnyOrderElementsOf(expectedPlaces);
    }

    @Test
    public void shouldReturnTwoPlacesStraightAndOneAslantWhenThePawnStayInStartedPositionAndCouldBeatOpponent(){
        Chessboard chessboard = chessboardFactory.createEmptyChessboard();
        Figure testedPawn = new Figure(TypeOfFigure.PAWN, ColorOfFigure.WHITE);
        Figure opponentPawn = new Figure(TypeOfFigure.PAWN, ColorOfFigure.BLACK);
        chessboard.setFigureInPlace('e', 2, testedPawn);
        chessboard.setFigureInPlace('f', 3, opponentPawn);
        List<Place> possiblePlaces = MovesValidator.getAllPossiblePlacesForTheFigure(chessboard, testedPawn);

        List<Place> expectedPlaces = new ArrayList<>();
        expectedPlaces.add(new Place('e', 3));
        expectedPlaces.add(new Place('e', 4));
        expectedPlaces.add(new Place('f', 3));

        assertThat(possiblePlaces).containsExactlyInAnyOrderElementsOf(expectedPlaces);
    }

    @Test
    public void shouldReturnEmptyArrayWhenThePawnHasOpponentInFront(){
        Chessboard chessboard = chessboardFactory.createEmptyChessboard();
        Figure testedPawn = new Figure(TypeOfFigure.PAWN, ColorOfFigure.WHITE);
        Figure opponentFigure = new Figure(TypeOfFigure.PAWN, ColorOfFigure.BLACK);
        chessboard.setFigureInPlace('e', 2, testedPawn);
        chessboard.setFigureInPlace('e', 3, opponentFigure);

        List<Place> possiblePlaces = MovesValidator.getAllPossiblePlacesForTheFigure(chessboard, testedPawn);

        assertThat(possiblePlaces.isEmpty()).isTrue();
    }
}
