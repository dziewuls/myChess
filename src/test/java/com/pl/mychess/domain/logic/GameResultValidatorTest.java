package com.pl.mychess.domain.logic;

import com.pl.mychess.domain.chessboard.ClassicChessChessboardFactory;
import com.pl.mychess.domain.model.chessboard.*;
import com.pl.mychess.domain.model.state.Move;
import com.pl.mychess.domain.model.state.StateOfMatch;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class GameResultValidatorTest {
    private ClassicChessChessboardFactory chessCreator = new ClassicChessChessboardFactory();
    private ClassicChessGameValidator gameValidator = new ClassicChessGameValidator();

    @Test
    public void shouldGameResultValidatorReturnCheckWhenThePawnAttacks(){
        Chessboard chessboardForTest = chessCreator.createEmptyChessboard();
        chessboardForTest.setFigureInPlace('d', 8, new Figure(TypeOfFigure.KING, ColorOfFigure.BLACK));
        chessboardForTest.setFigureInPlace('d', 5, new Figure(TypeOfFigure.KING, ColorOfFigure.WHITE));
        chessboardForTest.setFigureInPlace('e', 7, new Figure(TypeOfFigure.PAWN, ColorOfFigure.WHITE));

        StateOfMatch result = gameValidator.getTheGameResult(chessboardForTest, ColorOfFigure.BLACK);

        assertThat(result).isEqualTo(StateOfMatch.CHECK);
    }

    @Test
    public void shouldGameResultValidatorReturnCheckWhenTheKnightAttacks(){
        Chessboard chessboardForTest = chessCreator.createEmptyChessboard();
        chessboardForTest.setFigureInPlace('d', 8, new Figure(TypeOfFigure.KING, ColorOfFigure.BLACK));
        chessboardForTest.setFigureInPlace('d', 5, new Figure(TypeOfFigure.KING, ColorOfFigure.WHITE));
        chessboardForTest.setFigureInPlace('e', 6, new Figure(TypeOfFigure.KNIGHT, ColorOfFigure.WHITE));

        StateOfMatch result = gameValidator.getTheGameResult(chessboardForTest, ColorOfFigure.BLACK);

        assertThat(result).isEqualTo(StateOfMatch.CHECK);
    }

    @Test
    public void shouldGameResultValidatorReturnCheckWhenTheBishopAttacks(){
        Chessboard chessboardForTest = chessCreator.createEmptyChessboard();
        chessboardForTest.setFigureInPlace('d', 8, new Figure(TypeOfFigure.KING, ColorOfFigure.BLACK));
        chessboardForTest.setFigureInPlace('d', 5, new Figure(TypeOfFigure.KING, ColorOfFigure.WHITE));
        chessboardForTest.setFigureInPlace('f', 6, new Figure(TypeOfFigure.BISHOP, ColorOfFigure.WHITE));

        StateOfMatch result = gameValidator.getTheGameResult(chessboardForTest, ColorOfFigure.BLACK);

        assertThat(result).isEqualTo(StateOfMatch.CHECK);
    }

    @Test
    public void shouldGameResultValidatorReturnCheckWhenTheRookAttacks(){
        Chessboard chessboardForTest = chessCreator.createEmptyChessboard();
        chessboardForTest.setFigureInPlace('d', 8, new Figure(TypeOfFigure.KING, ColorOfFigure.BLACK));
        chessboardForTest.setFigureInPlace('d', 5, new Figure(TypeOfFigure.KING, ColorOfFigure.WHITE));
        chessboardForTest.setFigureInPlace('d', 6, new Figure(TypeOfFigure.QUEEN, ColorOfFigure.WHITE));

        StateOfMatch result = gameValidator.getTheGameResult(chessboardForTest, ColorOfFigure.BLACK);

        assertThat(result).isEqualTo(StateOfMatch.CHECK);
    }

    @Test
    public void shouldGameResultValidatorReturnCheckWhenTheQueenAttacks(){
        Chessboard chessboardForTest = chessCreator.createEmptyChessboard();
        chessboardForTest.setFigureInPlace('d', 8, new Figure(TypeOfFigure.KING, ColorOfFigure.BLACK));
        chessboardForTest.setFigureInPlace('d', 5, new Figure(TypeOfFigure.KING, ColorOfFigure.WHITE));
        chessboardForTest.setFigureInPlace('d', 6, new Figure(TypeOfFigure.QUEEN, ColorOfFigure.WHITE));

        StateOfMatch result = gameValidator.getTheGameResult(chessboardForTest, ColorOfFigure.BLACK);

        assertThat(result).isEqualTo(StateOfMatch.CHECK);
    }

    @Test
    public void shouldGameResultValidatorReturnGameIsNotCompleteWhenTheCheckCheckmateOrDrawNotExist1(){
        Chessboard chessboardForTest = chessCreator.createEmptyChessboard();
        chessboardForTest.setFigureInPlace('d', 8, new Figure(TypeOfFigure.KING, ColorOfFigure.BLACK));
        chessboardForTest.setFigureInPlace('d', 5, new Figure(TypeOfFigure.KING, ColorOfFigure.WHITE));
        chessboardForTest.setFigureInPlace('d', 6, new Figure(TypeOfFigure.QUEEN, ColorOfFigure.WHITE));
        chessboardForTest.setFigureInPlace('d', 7, new Figure(TypeOfFigure.ROOK, ColorOfFigure.BLACK));

        StateOfMatch result = gameValidator.getTheGameResult(chessboardForTest, ColorOfFigure.BLACK);

        assertThat(result).isEqualTo(StateOfMatch.GAME_IS_NOT_COMPLETED);
    }

    @Test
    public void shouldGameResultValidatorReturnGameIsNotCompleteWhenTheCheckCheckmateOrDrawNotExist2(){
        Chessboard chessboardForTest = chessCreator.createInitialChessboard();
        Move move = Move.getMoveBuilder()
                .previousPlace(chessboardForTest.getPlaceByCoordinates('e', 2))
                .nextPlace(chessboardForTest.getPlaceByCoordinates('e', 4))
                .movedFigure(chessboardForTest.getFigureByCoordinates('e', 2))
                .currentPlayerColor(ColorOfFigure.WHITE)
                .build();

        chessboardForTest = chessCreator.createUpdatedChessboard(chessboardForTest, move);

        StateOfMatch result = gameValidator.getTheGameResult(chessboardForTest, ColorOfFigure.BLACK);

        assertThat(result).isEqualTo(StateOfMatch.GAME_IS_NOT_COMPLETED);
    }

    @Test
    public void shouldGameResultValidatorReturnWhiteIsWinnerWhenTheBlackIsCheckmated1(){
        Chessboard chessboardForTest = chessCreator.createEmptyChessboard();
        chessboardForTest.setFigureInPlace('d', 8, new Figure(TypeOfFigure.KING, ColorOfFigure.BLACK));
        chessboardForTest.setFigureInPlace('d', 6, new Figure(TypeOfFigure.KING, ColorOfFigure.WHITE));
        chessboardForTest.setFigureInPlace('d', 7, new Figure(TypeOfFigure.QUEEN, ColorOfFigure.WHITE));

        StateOfMatch result = gameValidator.getTheGameResult(chessboardForTest, ColorOfFigure.BLACK);

        assertThat(result).isEqualTo(StateOfMatch.WHITE_IS_A_WINNER);
    }

    @Test
    public void shouldGameResultValidatorReturnBlackIsWinnerWhenTheWhiteIsCheckmated2(){
        Chessboard chessboardForTest = chessCreator.createEmptyChessboard();
        chessboardForTest.setFigureInPlace('d', 8, new Figure(TypeOfFigure.KING, ColorOfFigure.WHITE));
        chessboardForTest.setFigureInPlace('d', 6, new Figure(TypeOfFigure.KING, ColorOfFigure.BLACK));
        chessboardForTest.setFigureInPlace('d', 7, new Figure(TypeOfFigure.PAWN, ColorOfFigure.WHITE));
        chessboardForTest.setFigureInPlace('f', 5, new Figure(TypeOfFigure.QUEEN, ColorOfFigure.BLACK));

        Move move = Move.getMoveBuilder()
                .previousPlace(chessboardForTest.getPlaceByCoordinates('f', 5))
                .nextPlace(chessboardForTest.getPlaceByCoordinates('d', 7))
                .movedFigure(chessboardForTest.getFigureByCoordinates('f', 5))
                .beatenFigure(chessboardForTest.getFigureByCoordinates('d', 7))
                .currentPlayerColor(ColorOfFigure.BLACK)
                .build();

        chessboardForTest = chessCreator.createUpdatedChessboard(chessboardForTest, move);

        StateOfMatch result = gameValidator.getTheGameResult(chessboardForTest, ColorOfFigure.WHITE);

        assertThat(result).isEqualTo(StateOfMatch.BLACK_IS_A_WINNER);
    }

    @Test
    public void shouldGameResultValidatorNotReturnCheckmateWhenTheMateNotExist(){
        Chessboard chessboardForTest = chessCreator.createEmptyChessboard();
        chessboardForTest.setFigureInPlace('d', 8, new Figure(TypeOfFigure.KING, ColorOfFigure.BLACK));
        chessboardForTest.setFigureInPlace('d', 6, new Figure(TypeOfFigure.KING, ColorOfFigure.WHITE));
        chessboardForTest.setFigureInPlace('d', 7, new Figure(TypeOfFigure.QUEEN, ColorOfFigure.WHITE));
        chessboardForTest.setFigureInPlace('a', 7, new Figure(TypeOfFigure.ROOK, ColorOfFigure.BLACK));

        StateOfMatch result = gameValidator.getTheGameResult(chessboardForTest, ColorOfFigure.BLACK);

        assertThat(result).isNotEqualTo(StateOfMatch.WHITE_IS_A_WINNER);
    }

    @Test
    public void shouldGameResultValidatorReturnDrawWhenTheStalemateExist1(){
        Chessboard chessboardForTest = chessCreator.createEmptyChessboard();
        chessboardForTest.setFigureInPlace('d', 8, new Figure(TypeOfFigure.KING, ColorOfFigure.BLACK));
        chessboardForTest.setFigureInPlace('d', 6, new Figure(TypeOfFigure.KING, ColorOfFigure.WHITE));
        chessboardForTest.setFigureInPlace('d', 7, new Figure(TypeOfFigure.PAWN, ColorOfFigure.WHITE));

        StateOfMatch result = gameValidator.getTheGameResult(chessboardForTest, ColorOfFigure.BLACK);

        assertThat(result).isEqualTo(StateOfMatch.DRAW);
    }

    @Test
    public void shouldGameResultValidatorReturnDrawWhenTheStalemateExist2(){
        Chessboard chessboardForTest = chessCreator.createEmptyChessboard();
        chessboardForTest.setFigureInPlace('a', 8, new Figure(TypeOfFigure.KING, ColorOfFigure.BLACK));
        chessboardForTest.setFigureInPlace('g', 6, new Figure(TypeOfFigure.KING, ColorOfFigure.WHITE));
        chessboardForTest.setFigureInPlace('b', 1, new Figure(TypeOfFigure.QUEEN, ColorOfFigure.WHITE));

        Move move = Move.getMoveBuilder()
                .previousPlace(chessboardForTest.getPlaceByCoordinates('b', 1))
                .nextPlace(chessboardForTest.getPlaceByCoordinates('b', 6))
                .movedFigure(chessboardForTest.getFigureByCoordinates('b', 1))
                .beatenFigure(chessboardForTest.getFigureByCoordinates('b', 6))
                .currentPlayerColor(ColorOfFigure.BLACK)
                .build();

        chessboardForTest = chessCreator.createUpdatedChessboard(chessboardForTest, move);

        StateOfMatch result = gameValidator.getTheGameResult(chessboardForTest, ColorOfFigure.BLACK);

        assertThat(result).isEqualTo(StateOfMatch.DRAW);
    }

    @Test
    public void shouldGameResultValidatorNotReturnDrawWhenTheStalemateExistButForOtherPlayerColor(){
        Chessboard chessboardForTest = chessCreator.createEmptyChessboard();
        chessboardForTest.setFigureInPlace('d', 8, new Figure(TypeOfFigure.KING, ColorOfFigure.BLACK));
        chessboardForTest.setFigureInPlace('d', 6, new Figure(TypeOfFigure.KING, ColorOfFigure.WHITE));
        chessboardForTest.setFigureInPlace('d', 7, new Figure(TypeOfFigure.PAWN, ColorOfFigure.WHITE));

        StateOfMatch result = gameValidator.getTheGameResult(chessboardForTest, ColorOfFigure.WHITE);

        assertThat(result).isNotEqualTo(StateOfMatch.DRAW);
    }

    @Test
    public void shouldGameResultValidatorNotReturnDrawWhenTheStalemateNotExist(){
        Chessboard chessboardForTest = chessCreator.createEmptyChessboard();
        chessboardForTest.setFigureInPlace('d', 8, new Figure(TypeOfFigure.KING, ColorOfFigure.BLACK));
        chessboardForTest.setFigureInPlace('d', 6, new Figure(TypeOfFigure.KING, ColorOfFigure.WHITE));
        chessboardForTest.setFigureInPlace('d', 7, new Figure(TypeOfFigure.PAWN, ColorOfFigure.WHITE));
        chessboardForTest.setFigureInPlace('h', 5, new Figure(TypeOfFigure.PAWN, ColorOfFigure.BLACK));

        StateOfMatch result = gameValidator.getTheGameResult(chessboardForTest, ColorOfFigure.BLACK);

        assertThat(result).isNotEqualTo(StateOfMatch.DRAW);
    }

    @Test
    public void shouldGameResultValidatorReturnDrawWhenInChessboardIsInsufficientMaterialForMate(){
        Chessboard chessboardForTest = chessCreator.createEmptyChessboard();
        chessboardForTest.setFigureInPlace('d', 8, new Figure(TypeOfFigure.KING, ColorOfFigure.BLACK));
        chessboardForTest.setFigureInPlace('d', 6, new Figure(TypeOfFigure.KING, ColorOfFigure.WHITE));
        chessboardForTest.setFigureInPlace('d', 7, new Figure(TypeOfFigure.KNIGHT, ColorOfFigure.WHITE));

        StateOfMatch result = gameValidator.getTheGameResult(chessboardForTest, ColorOfFigure.WHITE);

        assertThat(result).isEqualTo(StateOfMatch.DRAW);
    }

    @Test
    public void shouldCorrectMovesValidatorReturnPlacesToWhichTheMoveWouldNotDiscoverTheCheck() {
        Chessboard chessboard = chessCreator.createEmptyChessboard();
        Figure testedWhiteKing = new Figure(TypeOfFigure.KING, ColorOfFigure.WHITE);
        Figure testedWhiteRook = new Figure(TypeOfFigure.ROOK, ColorOfFigure.WHITE);
        Figure testedBlackQueen = new Figure(TypeOfFigure.QUEEN, ColorOfFigure.BLACK);
        chessboard.setFigureInPlace('e', 2, testedWhiteKing);
        chessboard.setFigureInPlace('e', 4, testedWhiteRook);
        chessboard.setFigureInPlace('e', 8, testedBlackQueen);
        List<Place> possiblePlaces = gameValidator.getCorrectPlacesForFigure(chessboard, testedWhiteRook, null);

        List<Place> expectedPlaces = new ArrayList<>();
        expectedPlaces.add(new Place('e', 3));
        expectedPlaces.add(new Place('e', 5));
        expectedPlaces.add(new Place('e', 6));
        expectedPlaces.add(new Place('e', 7));
        expectedPlaces.add(new Place('e', 8, chessboard.getFigureByCoordinates('e', 8)));

        assertThat(possiblePlaces).containsExactlyInAnyOrderElementsOf(expectedPlaces);
    }

    @Test
    public void shouldCorrectMovesValidatorNotReturnAnyPlacesWhenTheKingIsChecked() {
        Chessboard chessboard = chessCreator.createEmptyChessboard();
        Figure testedWhiteKing = new Figure(TypeOfFigure.KING, ColorOfFigure.WHITE);
        Figure testedWhiteRook = new Figure(TypeOfFigure.ROOK, ColorOfFigure.WHITE);
        Figure testedBlackQueen = new Figure(TypeOfFigure.QUEEN, ColorOfFigure.BLACK);
        chessboard.setFigureInPlace('e', 4, testedWhiteKing);
        chessboard.setFigureInPlace('e', 2, testedWhiteRook);
        chessboard.setFigureInPlace('e', 8, testedBlackQueen);
        List<Place> possiblePlaces = gameValidator.getCorrectPlacesForFigure(chessboard, testedWhiteRook, null);

        assertThat(possiblePlaces.isEmpty()).isTrue();
    }

    @Test
    public void shouldCorrectMovesValidatorReturnThePlacesForWhichTheKingDoesNotFallUnderTheCheck() {
        Chessboard chessboard = chessCreator.createEmptyChessboard();
        Figure testedWhiteKing = new Figure(TypeOfFigure.KING, ColorOfFigure.WHITE);
        Figure testedBlackPawn = new Figure(TypeOfFigure.PAWN, ColorOfFigure.BLACK);
        chessboard.setFigureInPlace('e', 4, testedWhiteKing);
        chessboard.setFigureInPlace('e', 6, testedBlackPawn);
        List<Place> possiblePlaces = gameValidator.getCorrectPlacesForFigure(chessboard, testedWhiteKing, null);

        List<Place> expectedPlaces = new ArrayList<>();
        expectedPlaces.add(new Place('f', 3));
        expectedPlaces.add(new Place('f', 4));
        expectedPlaces.add(new Place('d', 3));
        expectedPlaces.add(new Place('d', 4));
        expectedPlaces.add(new Place('e', 3));
        expectedPlaces.add(new Place('e', 5));

        assertThat(possiblePlaces).containsExactlyInAnyOrderElementsOf(expectedPlaces);
    }
}
