package com.pl.mychess.domain.logic;

import com.pl.mychess.domain.chessboard.ChessboardFactory;
import com.pl.mychess.domain.model.chessboard.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MovesValidatorTest {
    @Test
    public void shouldReturnTwoPlacesStraightAheadForPawnInStartingPosition(){
        Chessboard chessboard = ChessboardFactory.createEmptyChessboard();
        Figure testedPawn = new Figure(TypeOfFigure.PAWN, ColorOfFigure.WHITE);
        chessboard.setFigureInPlace('e', 2, testedPawn);
        List<Place> possiblePlaces = CustomMovesValidator.getAllPossiblePlacesForTheFigure(chessboard, testedPawn);

        List<Place> expectedPlaces = new ArrayList<>();
        expectedPlaces.add(new Place('e', 3));
        expectedPlaces.add(new Place('e', 4));

        assertThat(possiblePlaces).isEqualTo(expectedPlaces);
    }

    @Test
    public void shouldReturnOnePlaceAslantAndOnePlaceStraightForPawnWithBeating(){
        Chessboard chessboard = ChessboardFactory.createEmptyChessboard();
        Figure testedPawn = new Figure(TypeOfFigure.PAWN, ColorOfFigure.WHITE);
        testedPawn.setMoved(true);
        chessboard.setFigureInPlace('e', 3, testedPawn);
        chessboard.setFigureInPlace('f', 4, new Figure(TypeOfFigure.ROOK, ColorOfFigure.BLACK));
        List<Place> possiblePlaces = CustomMovesValidator.getAllPossiblePlacesForTheFigure(chessboard, testedPawn);

        List<Place> expectedPlaces = new ArrayList<>();
        expectedPlaces.add(new Place('f', 4));
        expectedPlaces.add(new Place('e', 4));

        assertThat(possiblePlaces).isEqualTo(expectedPlaces);
    }

    @Test
    public void shouldReturnOnePlaceStraightForPawnWhoHasAlreadyMadeAMove(){
        Chessboard chessboard = ChessboardFactory.createEmptyChessboard();
        Figure testedPawn = new Figure(TypeOfFigure.PAWN, ColorOfFigure.WHITE);
        testedPawn.setMoved(true);
        chessboard.setFigureInPlace('e', 3, testedPawn);
        List<Place> possiblePlaces = CustomMovesValidator.getAllPossiblePlacesForTheFigure(chessboard, testedPawn);

        List<Place> expectedPlaces = new ArrayList<>();
        expectedPlaces.add(new Place('e', 4));

        assertThat(possiblePlaces).isEqualTo(expectedPlaces);
    }

    @Test
    public void shouldReturnAllPossiblePlacesForTheKnight(){
        Chessboard chessboard = ChessboardFactory.createEmptyChessboard();
        Figure testedKnight = new Figure(TypeOfFigure.KNIGHT, ColorOfFigure.WHITE);
        chessboard.setFigureInPlace('e', 4, testedKnight);
        List<Place> possiblePlaces = CustomMovesValidator.getAllPossiblePlacesForTheFigure(chessboard, testedKnight);

        List<Place> expectedPlaces = new ArrayList<>();
        expectedPlaces.add(new Place('f', 2));
        expectedPlaces.add(new Place('f', 6));
        expectedPlaces.add(new Place('g', 3));
        expectedPlaces.add(new Place('g', 5));
        expectedPlaces.add(new Place('d', 2));
        expectedPlaces.add(new Place('d', 6));
        expectedPlaces.add(new Place('c', 3));
        expectedPlaces.add(new Place('c', 5));

        assertThat(possiblePlaces).isEqualTo(expectedPlaces);
    }

    @Test
    public void shouldReturnAllPossiblePlacesForTheBishop(){
        Chessboard chessboard = ChessboardFactory.createEmptyChessboard();
        Figure testedBishop = new Figure(TypeOfFigure.BISHOP, ColorOfFigure.WHITE);
        chessboard.setFigureInPlace('e', 4, testedBishop);
        List<Place> possiblePlaces = CustomMovesValidator.getAllPossiblePlacesForTheFigure(chessboard, testedBishop);

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

        assertThat(possiblePlaces).isEqualTo(expectedPlaces);
    }

    @Test
    public void shouldReturnAllPossiblePlacesForTheRook(){
        Chessboard chessboard = ChessboardFactory.createEmptyChessboard();
        Figure testedRook = new Figure(TypeOfFigure.ROOK, ColorOfFigure.WHITE);
        chessboard.setFigureInPlace('e', 4, testedRook);
        List<Place> possiblePlaces = CustomMovesValidator.getAllPossiblePlacesForTheFigure(chessboard, testedRook);

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

        assertThat(possiblePlaces).isEqualTo(expectedPlaces);
    }

    @Test
    public void shouldReturnAllPossiblePlacesForTheQueen(){
        Chessboard chessboard = ChessboardFactory.createEmptyChessboard();
        Figure testedQueen = new Figure(TypeOfFigure.QUEEN, ColorOfFigure.WHITE);
        chessboard.setFigureInPlace('e', 4, testedQueen);
        List<Place> possiblePlaces = CustomMovesValidator.getAllPossiblePlacesForTheFigure(chessboard, testedQueen);

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

        assertThat(possiblePlaces).isEqualTo(expectedPlaces);
    }

    @Test
    public void shouldReturnAllPossiblePlacesForTheKing(){
        Chessboard chessboard = ChessboardFactory.createEmptyChessboard();
        Figure testedKing = new Figure(TypeOfFigure.KING, ColorOfFigure.WHITE);
        chessboard.setFigureInPlace('e', 4, testedKing);
        List<Place> possiblePlaces = CustomMovesValidator.getAllPossiblePlacesForTheFigure(chessboard, testedKing);

        List<Place> expectedPlaces = new ArrayList<>();
        expectedPlaces.add(new Place('f', 3));
        expectedPlaces.add(new Place('f', 4));
        expectedPlaces.add(new Place('f', 5));
        expectedPlaces.add(new Place('d', 3));
        expectedPlaces.add(new Place('d', 4));
        expectedPlaces.add(new Place('d', 5));
        expectedPlaces.add(new Place('e', 3));
        expectedPlaces.add(new Place('e', 5));

        assertThat(possiblePlaces).isEqualTo(expectedPlaces);
    }

    @Test
    public void shouldNotReturnPlacesToWhichTheMoveWouldDiscoverTheCheck(){

    }

    @Test
    public void shouldReturnPlacesOccupiedByFigureAnotherColor(){

    }

    @Test
    public void shouldNotReturnPlacesOccupiedByFigureThisSameColor(){

    }



}
