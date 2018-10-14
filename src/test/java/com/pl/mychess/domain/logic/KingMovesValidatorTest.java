package com.pl.mychess.domain.logic;

import com.pl.mychess.domain.chessboard.ClassicChessChessboardFactory;
import com.pl.mychess.domain.model.chessboard.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class KingMovesValidatorTest {
    private ClassicChessChessboardFactory chessboardFactory = new ClassicChessChessboardFactory();

    @Test
    public void shouldReturnAllPossiblePlacesForTheKingWhenChessboardIsEmpty() {
        Chessboard chessboard = chessboardFactory.createEmptyChessboard();
        Figure testedKing = new Figure(TypeOfFigure.KING, Color.WHITE);
        chessboard.setFigureInPlace('e', 4, testedKing);
        List<Place> possiblePlaces = MovesValidator.getAllPossiblePlacesForTheFigure(chessboard, testedKing);

        List<Place> expectedPlaces = new ArrayList<>();
        expectedPlaces.add(new Place('f', 3));
        expectedPlaces.add(new Place('f', 4));
        expectedPlaces.add(new Place('f', 5));
        expectedPlaces.add(new Place('d', 3));
        expectedPlaces.add(new Place('d', 4));
        expectedPlaces.add(new Place('d', 5));
        expectedPlaces.add(new Place('e', 3));
        expectedPlaces.add(new Place('e', 5));

        assertThat(possiblePlaces).containsExactlyInAnyOrderElementsOf(expectedPlaces);
    }

    @Test
    public void shouldReturnAllPossiblePlacesForTheKingWhenSomeOpponentFigureCouldBeBeaten() {
        Chessboard chessboard = chessboardFactory.createEmptyChessboard();
        Figure testedKing = new Figure(TypeOfFigure.KING, Color.WHITE);
        Figure opponentFigure1 = new Figure(TypeOfFigure.ROOK, Color.BLACK);
        Figure opponentFigure2 = new Figure(TypeOfFigure.PAWN, Color.BLACK);
        chessboard.setFigureInPlace('e', 4, testedKing);
        chessboard.setFigureInPlace('e', 3, opponentFigure1);
        chessboard.setFigureInPlace('f', 5, opponentFigure2);
        List<Place> possiblePlaces = MovesValidator.getAllPossiblePlacesForTheFigure(chessboard, testedKing);

        List<Place> expectedPlaces = new ArrayList<>();
        expectedPlaces.add(new Place('f', 3));
        expectedPlaces.add(new Place('f', 4));
        expectedPlaces.add(new Place('f', 5));
        expectedPlaces.add(new Place('d', 3));
        expectedPlaces.add(new Place('d', 4));
        expectedPlaces.add(new Place('d', 5));
        expectedPlaces.add(new Place('e', 3));
        expectedPlaces.add(new Place('e', 5));

        assertThat(possiblePlaces).containsExactlyInAnyOrderElementsOf(expectedPlaces);
    }

    @Test
    public void shouldReturnAllPossiblePlacesForTheKingWhenSomeOurFigureStayInPossiblePlace() {
        Chessboard chessboard = chessboardFactory.createEmptyChessboard();
        Figure testedKing = new Figure(TypeOfFigure.KING, Color.WHITE);
        Figure figure1 = new Figure(TypeOfFigure.ROOK, Color.WHITE);
        Figure figure2 = new Figure(TypeOfFigure.PAWN, Color.WHITE);
        chessboard.setFigureInPlace('e', 4, testedKing);
        chessboard.setFigureInPlace('e', 3, figure1);
        chessboard.setFigureInPlace('f', 5, figure2);
        List<Place> possiblePlaces = MovesValidator.getAllPossiblePlacesForTheFigure(chessboard, testedKing);

        List<Place> expectedPlaces = new ArrayList<>();
        expectedPlaces.add(new Place('f', 3));
        expectedPlaces.add(new Place('f', 4));
        expectedPlaces.add(new Place('d', 3));
        expectedPlaces.add(new Place('d', 4));
        expectedPlaces.add(new Place('d', 5));
        expectedPlaces.add(new Place('e', 5));

        assertThat(possiblePlaces).containsExactlyInAnyOrderElementsOf(expectedPlaces);
    }

    @Test
    public void shouldReturnAllPossiblePlacesForTheKingWhenItStayInTheCorner() {
        Chessboard chessboard = chessboardFactory.createEmptyChessboard();
        Figure testedKing = new Figure(TypeOfFigure.KING, Color.WHITE);
        chessboard.setFigureInPlace('a', 1, testedKing);
        List<Place> possiblePlaces = MovesValidator.getAllPossiblePlacesForTheFigure(chessboard, testedKing);

        List<Place> expectedPlaces = new ArrayList<>();
        expectedPlaces.add(new Place('a', 2));
        expectedPlaces.add(new Place('b', 2));
        expectedPlaces.add(new Place('b', 1));

        assertThat(possiblePlaces).containsExactlyInAnyOrderElementsOf(expectedPlaces);
    }

    @Test
    public void shouldReturnEmptyArrayWhenTheBishopHasNotPossiblePlacesToMove() {
        Chessboard chessboard = chessboardFactory.createEmptyChessboard();
        Figure testedKing = new Figure(TypeOfFigure.KING, Color.WHITE);
        Figure figure1 = new Figure(TypeOfFigure.ROOK, Color.WHITE);
        Figure figure2 = new Figure(TypeOfFigure.ROOK, Color.WHITE);
        Figure figure3 = new Figure(TypeOfFigure.PAWN, Color.WHITE);
        chessboard.setFigureInPlace('a', 1, testedKing);
        chessboard.setFigureInPlace('a', 2, figure1);
        chessboard.setFigureInPlace('b', 1, figure2);
        chessboard.setFigureInPlace('b', 2, figure3);

        List<Place> possiblePlaces = MovesValidator.getAllPossiblePlacesForTheFigure(chessboard, testedKing);

        assertThat(possiblePlaces.isEmpty()).isTrue();
    }
}
