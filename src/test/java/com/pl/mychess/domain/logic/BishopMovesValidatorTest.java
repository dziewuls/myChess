package com.pl.mychess.domain.logic;

import com.pl.mychess.domain.chessboard.ClassicChessChessboardFactory;
import com.pl.mychess.domain.model.chessboard.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BishopMovesValidatorTest {
    private ClassicChessChessboardFactory chessboardFactory = new ClassicChessChessboardFactory();

    @Test
    public void shouldReturnAllPossiblePlacesForTheBishopWhenChessboardIdEmpty() {
        Chessboard chessboard = chessboardFactory.createEmptyChessboard();
        Figure testedBishop = new Figure(TypeOfFigure.BISHOP, Color.WHITE);
        chessboard.setFigureInPlace('e', 4, testedBishop);
        List<Place> possiblePlaces = MovesValidator.getAllPossiblePlacesForTheFigure(chessboard, testedBishop);

        List<Place> expectedPlaces = new ArrayList<>();
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
    public void shouldReturnAllPossiblePlacesForTheBishopWhenSomeOpponentFigureCouldBeBeaten() {
        Chessboard chessboard = chessboardFactory.createEmptyChessboard();
        Figure testedBishop = new Figure(TypeOfFigure.BISHOP, Color.WHITE);
        Figure opponentFigure1 = new Figure(TypeOfFigure.ROOK, Color.BLACK);
        Figure opponentFigure2 = new Figure(TypeOfFigure.PAWN, Color.BLACK);
        chessboard.setFigureInPlace('e', 4, testedBishop);
        chessboard.setFigureInPlace('f', 5, opponentFigure1);
        chessboard.setFigureInPlace('c', 2, opponentFigure2);
        List<Place> possiblePlaces = MovesValidator.getAllPossiblePlacesForTheFigure(chessboard, testedBishop);

        List<Place> expectedPlaces = new ArrayList<>();
        expectedPlaces.add(new Place('f', 3));
        expectedPlaces.add(new Place('f', 5));
        expectedPlaces.add(new Place('g', 2));
        expectedPlaces.add(new Place('h', 1));
        expectedPlaces.add(new Place('d', 3));
        expectedPlaces.add(new Place('d', 5));
        expectedPlaces.add(new Place('c', 2));
        expectedPlaces.add(new Place('c', 6));
        expectedPlaces.add(new Place('b', 7));
        expectedPlaces.add(new Place('a', 8));

        assertThat(possiblePlaces).containsExactlyInAnyOrderElementsOf(expectedPlaces);
    }

    @Test
    public void shouldReturnAllPossiblePlacesForTheBishopWhenSomeOurFigureStayInPossiblePlace() {
        Chessboard chessboard = chessboardFactory.createEmptyChessboard();
        Figure testedBishop = new Figure(TypeOfFigure.BISHOP, Color.WHITE);
        Figure figure1 = new Figure(TypeOfFigure.ROOK, Color.WHITE);
        Figure figure2 = new Figure(TypeOfFigure.PAWN, Color.WHITE);
        chessboard.setFigureInPlace('e', 4, testedBishop);
        chessboard.setFigureInPlace('f', 5, figure1);
        chessboard.setFigureInPlace('c', 2, figure2);
        List<Place> possiblePlaces = MovesValidator.getAllPossiblePlacesForTheFigure(chessboard, testedBishop);

        List<Place> expectedPlaces = new ArrayList<>();
        expectedPlaces.add(new Place('f', 3));
        expectedPlaces.add(new Place('g', 2));
        expectedPlaces.add(new Place('h', 1));
        expectedPlaces.add(new Place('d', 3));
        expectedPlaces.add(new Place('d', 5));
        expectedPlaces.add(new Place('c', 6));
        expectedPlaces.add(new Place('b', 7));
        expectedPlaces.add(new Place('a', 8));

        assertThat(possiblePlaces).containsExactlyInAnyOrderElementsOf(expectedPlaces);
    }

    @Test
    public void shouldReturnAllPossiblePlacesForTheBishopWhenItStayInTheCorner() {
        Chessboard chessboard = chessboardFactory.createEmptyChessboard();
        Figure testedBishop = new Figure(TypeOfFigure.BISHOP, Color.WHITE);
        chessboard.setFigureInPlace('a', 1, testedBishop);
        List<Place> possiblePlaces = MovesValidator.getAllPossiblePlacesForTheFigure(chessboard, testedBishop);

        List<Place> expectedPlaces = new ArrayList<>();
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
    public void shouldReturnEmptyArrayWhenTheBishopHasNotPossiblePlacesToMove(){
        Chessboard chessboard = chessboardFactory.createEmptyChessboard();
        Figure testedBishop = new Figure(TypeOfFigure.BISHOP, Color.WHITE);
        Figure figure1 = new Figure(TypeOfFigure.ROOK, Color.WHITE);
        Figure figure2 = new Figure(TypeOfFigure.PAWN, Color.WHITE);
        Figure figure3 = new Figure(TypeOfFigure.KING, Color.WHITE);
        Figure figure4 = new Figure(TypeOfFigure.KNIGHT, Color.WHITE);
        chessboard.setFigureInPlace('e', 4, testedBishop);
        chessboard.setFigureInPlace('f', 5, figure1);
        chessboard.setFigureInPlace('f', 3, figure2);
        chessboard.setFigureInPlace('d', 3, figure3);
        chessboard.setFigureInPlace('d', 5, figure4);

        List<Place> possiblePlaces = MovesValidator.getAllPossiblePlacesForTheFigure(chessboard, testedBishop);

        assertThat(possiblePlaces.isEmpty()).isTrue();
    }
}
