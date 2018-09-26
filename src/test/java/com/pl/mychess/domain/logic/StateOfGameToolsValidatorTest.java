package com.pl.mychess.domain.logic;

import com.pl.mychess.domain.chessboard.ChessboardFactory;
import com.pl.mychess.domain.model.chessboard.Chessboard;
import com.pl.mychess.domain.model.chessboard.ColorOfFigure;
import com.pl.mychess.domain.model.chessboard.Figure;
import com.pl.mychess.domain.model.chessboard.TypeOfFigure;
import com.pl.mychess.domain.model.match.StateOfMatch;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StateOfGameToolsValidatorTest {
    @Test
    public void shouldGameResultValidatorReturnCheckWhenTheCheckExist(){
        Chessboard chessboardForTest = ChessboardFactory.createEmptyChessboard();
        chessboardForTest.setFigureInPlace('d', 8, new Figure(TypeOfFigure.KING, ColorOfFigure.BLACK));
        chessboardForTest.setFigureInPlace('d', 5, new Figure(TypeOfFigure.KING, ColorOfFigure.WHITE));
        chessboardForTest.setFigureInPlace('d', 6, new Figure(TypeOfFigure.QUEEN, ColorOfFigure.WHITE));

        StateOfMatch result = StateOfGameToolsValidator.getTheGameResult(chessboardForTest, ColorOfFigure.BLACK);

        assertThat(result).isEqualTo(StateOfMatch.CHECK);
    }

    @Test
    public void shouldGameResultValidatorReturnGameIsNotCompleteWhenTheCheckNotExist(){
        Chessboard chessboardForTest = ChessboardFactory.createEmptyChessboard();
        chessboardForTest.setFigureInPlace('d', 8, new Figure(TypeOfFigure.KING, ColorOfFigure.BLACK));
        chessboardForTest.setFigureInPlace('d', 5, new Figure(TypeOfFigure.KING, ColorOfFigure.WHITE));
        chessboardForTest.setFigureInPlace('d', 6, new Figure(TypeOfFigure.QUEEN, ColorOfFigure.WHITE));
        chessboardForTest.setFigureInPlace('d', 7, new Figure(TypeOfFigure.ROOK, ColorOfFigure.BLACK));

        StateOfMatch result = StateOfGameToolsValidator.getTheGameResult(chessboardForTest, ColorOfFigure.BLACK);

        assertThat(result).isEqualTo(StateOfMatch.GAME_IS_NOT_COMPLETED);
    }


    @Test
    public void shouldGameResultValidatorReturnCheckmateWhenTheMateExist(){
        Chessboard chessboardForTest = ChessboardFactory.createEmptyChessboard();
        chessboardForTest.setFigureInPlace('d', 8, new Figure(TypeOfFigure.KING, ColorOfFigure.BLACK));
        chessboardForTest.setFigureInPlace('d', 6, new Figure(TypeOfFigure.KING, ColorOfFigure.WHITE));
        chessboardForTest.setFigureInPlace('d', 7, new Figure(TypeOfFigure.QUEEN, ColorOfFigure.WHITE));

        StateOfMatch result = StateOfGameToolsValidator.getTheGameResult(chessboardForTest, ColorOfFigure.BLACK);

        assertThat(result).isEqualTo(StateOfMatch.CHECKMATE);
    }

    @Test
    public void shouldGameResultValidatorNotReturnCheckmateWhenTheMateNotExist(){
        Chessboard chessboardForTest = ChessboardFactory.createEmptyChessboard();
        chessboardForTest.setFigureInPlace('d', 8, new Figure(TypeOfFigure.KING, ColorOfFigure.BLACK));
        chessboardForTest.setFigureInPlace('d', 6, new Figure(TypeOfFigure.KING, ColorOfFigure.WHITE));
        chessboardForTest.setFigureInPlace('d', 7, new Figure(TypeOfFigure.QUEEN, ColorOfFigure.WHITE));
        chessboardForTest.setFigureInPlace('a', 7, new Figure(TypeOfFigure.ROOK, ColorOfFigure.BLACK));

        StateOfMatch result = StateOfGameToolsValidator.getTheGameResult(chessboardForTest, ColorOfFigure.BLACK);

        assertThat(result).isNotEqualTo(StateOfMatch.CHECKMATE);
    }

    @Test
    public void shouldGameResultValidatorReturnDrawWhenTheStalemateExist(){
        Chessboard chessboardForTest = ChessboardFactory.createEmptyChessboard();
        chessboardForTest.setFigureInPlace('d', 8, new Figure(TypeOfFigure.KING, ColorOfFigure.BLACK));
        chessboardForTest.setFigureInPlace('d', 6, new Figure(TypeOfFigure.KING, ColorOfFigure.WHITE));
        chessboardForTest.setFigureInPlace('d', 7, new Figure(TypeOfFigure.PAWN, ColorOfFigure.WHITE));

        StateOfMatch result = StateOfGameToolsValidator.getTheGameResult(chessboardForTest, ColorOfFigure.BLACK);

        assertThat(result).isEqualTo(StateOfMatch.DRAW);
    }

    @Test
    public void shouldGameResultValidatorNotReturnDrawWhenTheStalemateExistButForOtherPlayerColor(){
        Chessboard chessboardForTest = ChessboardFactory.createEmptyChessboard();
        chessboardForTest.setFigureInPlace('d', 8, new Figure(TypeOfFigure.KING, ColorOfFigure.BLACK));
        chessboardForTest.setFigureInPlace('d', 6, new Figure(TypeOfFigure.KING, ColorOfFigure.WHITE));
        chessboardForTest.setFigureInPlace('d', 7, new Figure(TypeOfFigure.PAWN, ColorOfFigure.WHITE));

        StateOfMatch result = StateOfGameToolsValidator.getTheGameResult(chessboardForTest, ColorOfFigure.WHITE);

        assertThat(result).isNotEqualTo(StateOfMatch.DRAW);
    }

    @Test
    public void shouldGameResultValidatorNotReturnDrawWhenTheStalemateNotExist(){
        Chessboard chessboardForTest = ChessboardFactory.createEmptyChessboard();
        chessboardForTest.setFigureInPlace('d', 8, new Figure(TypeOfFigure.KING, ColorOfFigure.BLACK));
        chessboardForTest.setFigureInPlace('d', 6, new Figure(TypeOfFigure.KING, ColorOfFigure.WHITE));
        chessboardForTest.setFigureInPlace('d', 7, new Figure(TypeOfFigure.PAWN, ColorOfFigure.WHITE));
        chessboardForTest.setFigureInPlace('h', 5, new Figure(TypeOfFigure.PAWN, ColorOfFigure.BLACK));

        StateOfMatch result = StateOfGameToolsValidator.getTheGameResult(chessboardForTest, ColorOfFigure.BLACK);

        assertThat(result).isNotEqualTo(StateOfMatch.DRAW);
    }

    @Test
    public void shouldGameResultValidatorReturnDrawWhenInChessboardIsInsufficientMaterialForMate(){
        Chessboard chessboardForTest = ChessboardFactory.createEmptyChessboard();
        chessboardForTest.setFigureInPlace('d', 8, new Figure(TypeOfFigure.KING, ColorOfFigure.BLACK));
        chessboardForTest.setFigureInPlace('d', 6, new Figure(TypeOfFigure.KING, ColorOfFigure.WHITE));
        chessboardForTest.setFigureInPlace('d', 7, new Figure(TypeOfFigure.KNIGHT, ColorOfFigure.WHITE));

        StateOfMatch result = StateOfGameToolsValidator.getTheGameResult(chessboardForTest, ColorOfFigure.WHITE);

        assertThat(result).isEqualTo(StateOfMatch.DRAW);
    }
}
