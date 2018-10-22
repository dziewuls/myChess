package com.pl.mychess.domain.classicchess.logic;

import com.pl.mychess.domain.classicchess.chessboard.ClassicChessChessboardFactory;
import com.pl.mychess.domain.model.chessboard.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class KnightMovesValidatorTest {
    private ClassicChessChessboardFactory chessboardFactory = new ClassicChessChessboardFactory();

    @Test
    public void shouldReturnAllPossiblePlacesForTheKnightWhenChessboardIsEmpty() {
        Chessboard chessboard = chessboardFactory.createEmptyChessboard();
        Figure testedKnight = new Figure(TypeOfFigure.KNIGHT, Color.WHITE);
        chessboard.setFigureInPlace('e', 4, testedKnight);
        List<Place> possiblePlaces = MovesValidator.getAllPossiblePlacesForTheFigure(chessboard, testedKnight);

        List<Place> expectedPlaces = new ArrayList<>();
        expectedPlaces.add(new Place('f', 2));
        expectedPlaces.add(new Place('f', 6));
        expectedPlaces.add(new Place('g', 3));
        expectedPlaces.add(new Place('g', 5));
        expectedPlaces.add(new Place('d', 2));
        expectedPlaces.add(new Place('d', 6));
        expectedPlaces.add(new Place('c', 3));
        expectedPlaces.add(new Place('c', 5));

        assertThat(possiblePlaces).containsExactlyInAnyOrderElementsOf(expectedPlaces);
    }

    @Test
    public void shouldReturnAllPossiblePlacesForTheKnightWhenSomeOpponentFigureCouldBeBeaten() {
        Chessboard chessboard = chessboardFactory.createEmptyChessboard();
        Figure testedKnight = new Figure(TypeOfFigure.KNIGHT, Color.WHITE);
        Figure opponentFigure1 = new Figure(TypeOfFigure.ROOK, Color.BLACK);
        Figure opponentFigure2 = new Figure(TypeOfFigure.KING, Color.BLACK);
        Figure opponentFigure3 = new Figure(TypeOfFigure.BISHOP, Color.BLACK);
        chessboard.setFigureInPlace('e', 4, testedKnight);
        chessboard.setFigureInPlace('f', 2, opponentFigure1);
        chessboard.setFigureInPlace('d', 6, opponentFigure2);
        chessboard.setFigureInPlace('c', 3, opponentFigure3);
        List<Place> possiblePlaces = MovesValidator.getAllPossiblePlacesForTheFigure(chessboard, testedKnight);

        List<Place> expectedPlaces = new ArrayList<>();
        expectedPlaces.add(new Place('f', 2));
        expectedPlaces.add(new Place('f', 6));
        expectedPlaces.add(new Place('g', 3));
        expectedPlaces.add(new Place('g', 5));
        expectedPlaces.add(new Place('d', 2));
        expectedPlaces.add(new Place('d', 6));
        expectedPlaces.add(new Place('c', 3));
        expectedPlaces.add(new Place('c', 5));

        assertThat(possiblePlaces).containsExactlyInAnyOrderElementsOf(expectedPlaces);
    }

    @Test
    public void shouldReturnAllPossiblePlacesForTheKnightWhenSomeOurFigureStayInPossiblePlace() {
        Chessboard chessboard = chessboardFactory.createEmptyChessboard();
        Figure testedKnight = new Figure(TypeOfFigure.KNIGHT, Color.WHITE);
        Figure figure1 = new Figure(TypeOfFigure.ROOK, Color.WHITE);
        Figure figure2 = new Figure(TypeOfFigure.KING, Color.WHITE);
        Figure figure3 = new Figure(TypeOfFigure.BISHOP, Color.WHITE);
        chessboard.setFigureInPlace('e', 4, testedKnight);
        chessboard.setFigureInPlace('f', 2, figure1);
        chessboard.setFigureInPlace('d', 6, figure2);
        chessboard.setFigureInPlace('c', 3, figure3);
        List<Place> possiblePlaces = MovesValidator.getAllPossiblePlacesForTheFigure(chessboard, testedKnight);

        List<Place> expectedPlaces = new ArrayList<>();
        expectedPlaces.add(new Place('f', 6));
        expectedPlaces.add(new Place('g', 3));
        expectedPlaces.add(new Place('g', 5));
        expectedPlaces.add(new Place('d', 2));
        expectedPlaces.add(new Place('c', 5));

        assertThat(possiblePlaces).containsExactlyInAnyOrderElementsOf(expectedPlaces);
    }

    @Test
    public void shouldReturnAllPossiblePlacesForTheKnightWhenItStayInTheCorner() {
        Chessboard chessboard = chessboardFactory.createEmptyChessboard();
        Figure testedKnight = new Figure(TypeOfFigure.KNIGHT, Color.WHITE);
        chessboard.setFigureInPlace('a', 1, testedKnight);
        List<Place> possiblePlaces = MovesValidator.getAllPossiblePlacesForTheFigure(chessboard, testedKnight);

        List<Place> expectedPlaces = new ArrayList<>();
        expectedPlaces.add(new Place('b', 3));
        expectedPlaces.add(new Place('c', 2));

        assertThat(possiblePlaces).containsExactlyInAnyOrderElementsOf(expectedPlaces);
    }

    @Test
    public void shouldReturnEmptyArrayWhenTheKnightHasNotPossiblePlacesToMove(){
        Chessboard chessboard = chessboardFactory.createEmptyChessboard();
        Figure testedKnight = new Figure(TypeOfFigure.KNIGHT, Color.WHITE);
        Figure figure1 = new Figure(TypeOfFigure.ROOK, Color.WHITE);
        Figure figure2 = new Figure(TypeOfFigure.KING, Color.WHITE);
        chessboard.setFigureInPlace('a', 1, testedKnight);
        chessboard.setFigureInPlace('b', 3, figure1);
        chessboard.setFigureInPlace('c', 2, figure2);

        List<Place> possiblePlaces = MovesValidator.getAllPossiblePlacesForTheFigure(chessboard, testedKnight);

        assertThat(possiblePlaces.isEmpty()).isTrue();
    }

}
