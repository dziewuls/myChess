package com.pl.mychess.domain.classicchess.logic;

import com.pl.mychess.domain.classicchess.chessboard.ClassicChessChessboardFactory;
import com.pl.mychess.domain.model.chessboard.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class QueenMovesValidatorTest {
    private ClassicChessChessboardFactory chessboardFactory = new ClassicChessChessboardFactory();

    @Test
    public void shouldReturnAllPossiblePlacesForTheQueenWhenChessboardIsEmpty() {
        Chessboard chessboard = chessboardFactory.createEmptyChessboard();
        Figure testedQueen = new Figure(TypeOfFigure.QUEEN, Color.WHITE);
        chessboard.setFigureInPlace('e', 4, testedQueen);
        List<Place> possiblePlaces = MovesValidator.getAllPossiblePlacesForTheFigure(chessboard, testedQueen);

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
        expectedPlaces.add(new Place('f', 3));
        expectedPlaces.add(new Place('f', 5));
        expectedPlaces.add(new Place('g', 2));
        expectedPlaces.add(new Place('g', 6));
        expectedPlaces.add(new Place('h', 1));
        expectedPlaces.add(new Place('h', 7));
        expectedPlaces.add(new Place('d', 3));
        expectedPlaces.add(new Place('d', 5));
        expectedPlaces.add(new Place('c', 2));
        expectedPlaces.add(new Place('c', 6));
        expectedPlaces.add(new Place('b', 1));
        expectedPlaces.add(new Place('b', 7));
        expectedPlaces.add(new Place('a', 8));

        assertThat(possiblePlaces).containsExactlyInAnyOrderElementsOf(expectedPlaces);
    }

    @Test
    public void shouldReturnAllPossiblePlacesForTheQueenWhenSomeOpponentFigureCouldBeBeaten() {
        Chessboard chessboard = chessboardFactory.createEmptyChessboard();
        Figure testedQueen = new Figure(TypeOfFigure.QUEEN, Color.WHITE);
        Figure opponentFigure1 = new Figure(TypeOfFigure.KNIGHT, Color.BLACK);
        Figure opponentFigure2 = new Figure(TypeOfFigure.PAWN, Color.BLACK);
        Figure opponentFigure3 = new Figure(TypeOfFigure.ROOK, Color.BLACK);
        Figure opponentFigure4 = new Figure(TypeOfFigure.KING, Color.BLACK);
        chessboard.setFigureInPlace('e', 4, testedQueen);
        chessboard.setFigureInPlace('f', 4, opponentFigure1);
        chessboard.setFigureInPlace('f', 5, opponentFigure2);
        chessboard.setFigureInPlace('e', 3, opponentFigure3);
        chessboard.setFigureInPlace('d', 5, opponentFigure4);
        List<Place> possiblePlaces = MovesValidator.getAllPossiblePlacesForTheFigure(chessboard, testedQueen);

        List<Place> expectedPlaces = new ArrayList<>();
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
        expectedPlaces.add(new Place('f', 3));
        expectedPlaces.add(new Place('f', 5));
        expectedPlaces.add(new Place('g', 2));
        expectedPlaces.add(new Place('h', 1));
        expectedPlaces.add(new Place('d', 3));
        expectedPlaces.add(new Place('d', 5));
        expectedPlaces.add(new Place('c', 2));
        expectedPlaces.add(new Place('b', 1));

        assertThat(possiblePlaces).containsExactlyInAnyOrderElementsOf(expectedPlaces);
    }

    @Test
    public void shouldReturnAllPossiblePlacesForTheQueenWhenSomeOurFigureStayInPossiblePlace() {
        Chessboard chessboard = chessboardFactory.createEmptyChessboard();
        Figure testedQueen = new Figure(TypeOfFigure.QUEEN, Color.WHITE);
        Figure figure1 = new Figure(TypeOfFigure.KNIGHT, Color.WHITE);
        Figure figure2 = new Figure(TypeOfFigure.PAWN, Color.WHITE);
        Figure figure3 = new Figure(TypeOfFigure.ROOK, Color.WHITE);
        Figure figure4 = new Figure(TypeOfFigure.KING, Color.WHITE);
        chessboard.setFigureInPlace('e', 4, testedQueen);
        chessboard.setFigureInPlace('f', 4, figure1);
        chessboard.setFigureInPlace('f', 5, figure2);
        chessboard.setFigureInPlace('e', 3, figure3);
        chessboard.setFigureInPlace('d', 5, figure4);
        List<Place> possiblePlaces = MovesValidator.getAllPossiblePlacesForTheFigure(chessboard, testedQueen);

        List<Place> expectedPlaces = new ArrayList<>();
        expectedPlaces.add(new Place('e', 5));
        expectedPlaces.add(new Place('e', 6));
        expectedPlaces.add(new Place('e', 7));
        expectedPlaces.add(new Place('e', 8));
        expectedPlaces.add(new Place('a', 4));
        expectedPlaces.add(new Place('b', 4));
        expectedPlaces.add(new Place('c', 4));
        expectedPlaces.add(new Place('d', 4));
        expectedPlaces.add(new Place('f', 3));
        expectedPlaces.add(new Place('g', 2));
        expectedPlaces.add(new Place('h', 1));
        expectedPlaces.add(new Place('d', 3));
        expectedPlaces.add(new Place('c', 2));
        expectedPlaces.add(new Place('b', 1));

        assertThat(possiblePlaces).containsExactlyInAnyOrderElementsOf(expectedPlaces);
    }

    @Test
    public void shouldReturnAllPossiblePlacesForTheQueenWhenItStayInTheCorner() {
        Chessboard chessboard = chessboardFactory.createEmptyChessboard();
        Figure testedQueen = new Figure(TypeOfFigure.QUEEN, Color.WHITE);
        chessboard.setFigureInPlace('a', 1, testedQueen);
        List<Place> possiblePlaces = MovesValidator.getAllPossiblePlacesForTheFigure(chessboard, testedQueen);

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
        expectedPlaces.add(new Place('b', 2));
        expectedPlaces.add(new Place('c', 3));
        expectedPlaces.add(new Place('d', 4));
        expectedPlaces.add(new Place('e', 5));
        expectedPlaces.add(new Place('f', 6));
        expectedPlaces.add(new Place('g', 7));
        expectedPlaces.add(new Place('h', 8));

        assertThat(possiblePlaces).containsExactlyInAnyOrderElementsOf(expectedPlaces);
    }

    @Test
    public void shouldReturnEmptyArrayWhenTheQueenHasNotPossiblePlacesToMove(){
        Chessboard chessboard = chessboardFactory.createEmptyChessboard();
        Figure testedQueen = new Figure(TypeOfFigure.QUEEN, Color.WHITE);
        Figure figure1 = new Figure(TypeOfFigure.KNIGHT, Color.WHITE);
        Figure figure2 = new Figure(TypeOfFigure.PAWN, Color.WHITE);
        Figure figure3 = new Figure(TypeOfFigure.ROOK, Color.WHITE);
        chessboard.setFigureInPlace('a', 1, testedQueen);
        chessboard.setFigureInPlace('a', 2, figure1);
        chessboard.setFigureInPlace('b', 2, figure2);
        chessboard.setFigureInPlace('b', 1, figure3);

        List<Place> possiblePlaces = MovesValidator.getAllPossiblePlacesForTheFigure(chessboard, testedQueen);

        assertThat(possiblePlaces.isEmpty()).isTrue();
    }
}
