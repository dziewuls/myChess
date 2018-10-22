package com.pl.mychess.domain.classicchess.logic;

import com.pl.mychess.domain.classicchess.chessboard.ClassicChessChessboardFactory;
import com.pl.mychess.domain.model.chessboard.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class RookMovesValidatorTest {
    private ClassicChessChessboardFactory chessboardFactory = new ClassicChessChessboardFactory();

    @Test
    public void shouldReturnAllPossiblePlacesForTheRookWhenChessboardIsEmpty() {
        Chessboard chessboard = chessboardFactory.createEmptyChessboard();
        Figure testedRook = new Figure(TypeOfFigure.ROOK, Color.WHITE);
        chessboard.setFigureInPlace('e', 4, testedRook);
        List<Place> possiblePlaces = MovesValidator.getAllPossiblePlacesForTheFigure(chessboard, testedRook);

        List<Place> expectedPlaces = new ArrayList<>();
        expectedPlaces.add(new Place('e', 1));
        expectedPlaces.add(new Place('e', 2));
        expectedPlaces.add(new Place('e', 3));
        expectedPlaces.add(new Place('e', 5));
        expectedPlaces.add(new Place('e', 6));
        expectedPlaces.add(new Place('e', 7));
        expectedPlaces.add(new Place('e', 8));
        expectedPlaces.add(new Place('a', 4));
        expectedPlaces.add(new Place('b', 4));
        expectedPlaces.add(new Place('c', 4));
        expectedPlaces.add(new Place('d', 4));
        expectedPlaces.add(new Place('f', 4));
        expectedPlaces.add(new Place('g', 4));
        expectedPlaces.add(new Place('h', 4));

        assertThat(possiblePlaces).containsExactlyInAnyOrderElementsOf(expectedPlaces);
    }

    @Test
    public void shouldReturnAllPossiblePlacesForTheRookWhenSomeOpponentFigureCouldBeBeaten() {
        Chessboard chessboard = chessboardFactory.createEmptyChessboard();
        Figure testedRook = new Figure(TypeOfFigure.ROOK, Color.WHITE);
        Figure opponentFigure1 = new Figure(TypeOfFigure.PAWN, Color.BLACK);
        Figure opponentFigure2 = new Figure(TypeOfFigure.KING, Color.BLACK);
        chessboard.setFigureInPlace('e', 4, testedRook);
        chessboard.setFigureInPlace('e', 5, opponentFigure1);
        chessboard.setFigureInPlace('g', 4, opponentFigure2);
        List<Place> possiblePlaces = MovesValidator.getAllPossiblePlacesForTheFigure(chessboard, testedRook);

        List<Place> expectedPlaces = new ArrayList<>();
        expectedPlaces.add(new Place('e', 1));
        expectedPlaces.add(new Place('e', 2));
        expectedPlaces.add(new Place('e', 3));
        expectedPlaces.add(new Place('e', 5));
        expectedPlaces.add(new Place('a', 4));
        expectedPlaces.add(new Place('b', 4));
        expectedPlaces.add(new Place('c', 4));
        expectedPlaces.add(new Place('d', 4));
        expectedPlaces.add(new Place('f', 4));
        expectedPlaces.add(new Place('g', 4));

        assertThat(possiblePlaces).containsExactlyInAnyOrderElementsOf(expectedPlaces);
    }

    @Test
    public void shouldReturnAllPossiblePlacesForTheRookWhenSomeOurFigureStayInPossiblePlace() {
        Chessboard chessboard = chessboardFactory.createEmptyChessboard();
        Figure testedRook = new Figure(TypeOfFigure.ROOK, Color.WHITE);
        Figure opponentFigure1 = new Figure(TypeOfFigure.PAWN, Color.WHITE);
        Figure opponentFigure2 = new Figure(TypeOfFigure.KING, Color.WHITE);
        chessboard.setFigureInPlace('e', 4, testedRook);
        chessboard.setFigureInPlace('e', 5, opponentFigure1);
        chessboard.setFigureInPlace('g', 4, opponentFigure2);
        List<Place> possiblePlaces = MovesValidator.getAllPossiblePlacesForTheFigure(chessboard, testedRook);

        List<Place> expectedPlaces = new ArrayList<>();
        expectedPlaces.add(new Place('e', 1));
        expectedPlaces.add(new Place('e', 2));
        expectedPlaces.add(new Place('e', 3));
        expectedPlaces.add(new Place('a', 4));
        expectedPlaces.add(new Place('b', 4));
        expectedPlaces.add(new Place('c', 4));
        expectedPlaces.add(new Place('d', 4));
        expectedPlaces.add(new Place('f', 4));

        assertThat(possiblePlaces).containsExactlyInAnyOrderElementsOf(expectedPlaces);
    }

    @Test
    public void shouldReturnAllPossiblePlacesForTheRookWhenItStayInTheCorner() {
        Chessboard chessboard = chessboardFactory.createEmptyChessboard();
        Figure testedRook = new Figure(TypeOfFigure.ROOK, Color.WHITE);
        chessboard.setFigureInPlace('a', 1, testedRook);
        List<Place> possiblePlaces = MovesValidator.getAllPossiblePlacesForTheFigure(chessboard, testedRook);

        List<Place> expectedPlaces = new ArrayList<>();
        expectedPlaces.add(new Place('a', 2));
        expectedPlaces.add(new Place('a', 3));
        expectedPlaces.add(new Place('a', 4));
        expectedPlaces.add(new Place('a', 5));
        expectedPlaces.add(new Place('a', 6));
        expectedPlaces.add(new Place('a', 7));
        expectedPlaces.add(new Place('a', 8));
        expectedPlaces.add(new Place('b', 1));
        expectedPlaces.add(new Place('c', 1));
        expectedPlaces.add(new Place('d', 1));
        expectedPlaces.add(new Place('e', 1));
        expectedPlaces.add(new Place('f', 1));
        expectedPlaces.add(new Place('g', 1));
        expectedPlaces.add(new Place('h', 1));

        assertThat(possiblePlaces).containsExactlyInAnyOrderElementsOf(expectedPlaces);
    }

    @Test
    public void shouldReturnEmptyArrayWhenTheRookHasNotPossiblePlacesToMove(){
        Chessboard chessboard = chessboardFactory.createEmptyChessboard();
        Figure testedRook = new Figure(TypeOfFigure.ROOK, Color.WHITE);
        Figure figure1 = new Figure(TypeOfFigure.PAWN, Color.WHITE);
        Figure figure2 = new Figure(TypeOfFigure.KING, Color.WHITE);
        Figure figure3 = new Figure(TypeOfFigure.ROOK, Color.WHITE);
        Figure figure4 = new Figure(TypeOfFigure.KNIGHT, Color.WHITE);
        chessboard.setFigureInPlace('e', 4, testedRook);
        chessboard.setFigureInPlace('e', 5, figure1);
        chessboard.setFigureInPlace('e', 3, figure2);
        chessboard.setFigureInPlace('d', 4, figure3);
        chessboard.setFigureInPlace('f', 4, figure4);

        List<Place> possiblePlaces = MovesValidator.getAllPossiblePlacesForTheFigure(chessboard, testedRook);

        assertThat(possiblePlaces.isEmpty()).isTrue();
    }
}
