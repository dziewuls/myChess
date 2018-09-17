package com.pl.mychess.domain.logic;

import com.pl.mychess.domain.chessboard.ChessboardFactory;
import com.pl.mychess.domain.model.chessboard.Chessboard;
import com.pl.mychess.domain.model.chessboard.ColorOfFigure;
import com.pl.mychess.domain.model.chessboard.Figure;
import com.pl.mychess.domain.model.chessboard.TypeOfFigure;
import org.junit.Test;
import org.assertj.core.api.Condition;

import static org.assertj.core.api.Assertions.assertThat;

public class GameResultValidatorTest {
    @Test
    public void shouldCheckDetectMethodReturnTrueWhenTheCheckExist(){
        Chessboard chessboardForTest = ChessboardFactory.createEmptyChessboard();
        chessboardForTest.setFigureInPlace('d', 8, new Figure(TypeOfFigure.KING, ColorOfFigure.BLACK));
        chessboardForTest.setFigureInPlace('d', 5, new Figure(TypeOfFigure.KING, ColorOfFigure.WHITE));
        chessboardForTest.setFigureInPlace('d', 6, new Figure(TypeOfFigure.QUEEN, ColorOfFigure.WHITE));

        boolean result = GameResultValidator.isCheck(chessboardForTest, ColorOfFigure.BLACK);

        assertThat(result).isTrue();
    }

    @Test
    public void shouldCheckDetectMethodReturnFalseWhenTheCheckExistButForOtherPlayerColor(){
        Chessboard chessboardForTest = ChessboardFactory.createEmptyChessboard();
        chessboardForTest.setFigureInPlace('d', 8, new Figure(TypeOfFigure.KING, ColorOfFigure.BLACK));
        chessboardForTest.setFigureInPlace('d', 5, new Figure(TypeOfFigure.KING, ColorOfFigure.WHITE));
        chessboardForTest.setFigureInPlace('d', 6, new Figure(TypeOfFigure.QUEEN, ColorOfFigure.WHITE));

        boolean result = GameResultValidator.isCheck(chessboardForTest, ColorOfFigure.WHITE);

        assertThat(result).isFalse();
    }

    @Test
    public void shouldCheckDetectMethodReturnFalseWhenTheCheckNotExist(){
        Chessboard chessboardForTest = ChessboardFactory.createEmptyChessboard();
        chessboardForTest.setFigureInPlace('d', 8, new Figure(TypeOfFigure.KING, ColorOfFigure.BLACK));
        chessboardForTest.setFigureInPlace('d', 5, new Figure(TypeOfFigure.KING, ColorOfFigure.WHITE));
        chessboardForTest.setFigureInPlace('d', 6, new Figure(TypeOfFigure.QUEEN, ColorOfFigure.WHITE));
        chessboardForTest.setFigureInPlace('d', 7, new Figure(TypeOfFigure.ROOK, ColorOfFigure.BLACK));

        boolean result = GameResultValidator.isCheck(chessboardForTest, ColorOfFigure.BLACK);

        assertThat(result).isFalse();
    }


    @Test
    public void shouldMateDetectMethodReturnTrueWhenTheMateExist(){
        Chessboard chessboardForTest = ChessboardFactory.createEmptyChessboard();
        chessboardForTest.setFigureInPlace('d', 8, new Figure(TypeOfFigure.KING, ColorOfFigure.BLACK));
        chessboardForTest.setFigureInPlace('d', 6, new Figure(TypeOfFigure.KING, ColorOfFigure.WHITE));
        chessboardForTest.setFigureInPlace('d', 7, new Figure(TypeOfFigure.QUEEN, ColorOfFigure.WHITE));

        boolean result = GameResultValidator.isCheckmate(chessboardForTest, ColorOfFigure.BLACK);

        assertThat(result).isTrue();
    }

    @Test
    public void shouldMateDetectMethodReturnFalseWhenTheMateExistButForOtherPlayerColor(){
        Chessboard chessboardForTest = ChessboardFactory.createEmptyChessboard();
        chessboardForTest.setFigureInPlace('d', 8, new Figure(TypeOfFigure.KING, ColorOfFigure.BLACK));
        chessboardForTest.setFigureInPlace('d', 6, new Figure(TypeOfFigure.KING, ColorOfFigure.WHITE));
        chessboardForTest.setFigureInPlace('d', 7, new Figure(TypeOfFigure.QUEEN, ColorOfFigure.WHITE));

        boolean result = GameResultValidator.isCheckmate(chessboardForTest, ColorOfFigure.WHITE);

        assertThat(result).isFalse();
    }

    @Test
    public void shouldMateDetectMethodReturnFalseWhenTheMateNotExist(){
        Chessboard chessboardForTest = ChessboardFactory.createEmptyChessboard();
        chessboardForTest.setFigureInPlace('d', 8, new Figure(TypeOfFigure.KING, ColorOfFigure.BLACK));
        chessboardForTest.setFigureInPlace('d', 6, new Figure(TypeOfFigure.KING, ColorOfFigure.WHITE));
        chessboardForTest.setFigureInPlace('d', 7, new Figure(TypeOfFigure.QUEEN, ColorOfFigure.WHITE));
        chessboardForTest.setFigureInPlace('a', 7, new Figure(TypeOfFigure.ROOK, ColorOfFigure.BLACK));

        boolean result = GameResultValidator.isCheckmate(chessboardForTest, ColorOfFigure.BLACK);

        assertThat(result).isTrue();
    }

    @Test
    public void shouldStalemateDetectMethodReturnTrueWhenTheStalemateExist(){
        Chessboard chessboardForTest = ChessboardFactory.createEmptyChessboard();
        chessboardForTest.setFigureInPlace('d', 8, new Figure(TypeOfFigure.KING, ColorOfFigure.BLACK));
        chessboardForTest.setFigureInPlace('d', 6, new Figure(TypeOfFigure.KING, ColorOfFigure.WHITE));
        chessboardForTest.setFigureInPlace('d', 7, new Figure(TypeOfFigure.PAWN, ColorOfFigure.WHITE));

        boolean result = GameResultValidator.isStalemate(chessboardForTest, ColorOfFigure.BLACK);

        assertThat(result).isTrue();
    }

    @Test
    public void shouldStalemateDetectMethodReturnFalseWhenTheMateExistButForOtherPlayerColor(){
        Chessboard chessboardForTest = ChessboardFactory.createEmptyChessboard();
        chessboardForTest.setFigureInPlace('d', 8, new Figure(TypeOfFigure.KING, ColorOfFigure.BLACK));
        chessboardForTest.setFigureInPlace('d', 6, new Figure(TypeOfFigure.KING, ColorOfFigure.WHITE));
        chessboardForTest.setFigureInPlace('d', 7, new Figure(TypeOfFigure.PAWN, ColorOfFigure.WHITE));

        boolean result = GameResultValidator.isStalemate(chessboardForTest, ColorOfFigure.WHITE);

        assertThat(result).isFalse();
    }

    @Test
    public void shouldStalemateDetectMethodReturnFalseWhenTheStalemateNotExist(){
        Chessboard chessboardForTest = ChessboardFactory.createEmptyChessboard();
        chessboardForTest.setFigureInPlace('d', 8, new Figure(TypeOfFigure.KING, ColorOfFigure.BLACK));
        chessboardForTest.setFigureInPlace('d', 6, new Figure(TypeOfFigure.KING, ColorOfFigure.WHITE));
        chessboardForTest.setFigureInPlace('d', 7, new Figure(TypeOfFigure.PAWN, ColorOfFigure.WHITE));
        chessboardForTest.setFigureInPlace('h', 5, new Figure(TypeOfFigure.PAWN, ColorOfFigure.BLACK));

        boolean result = GameResultValidator.isStalemate(chessboardForTest, ColorOfFigure.BLACK);

        assertThat(result).isFalse();
    }

    @Test
    public void shouldDrawDetectMethodReturnTrueWhenInChessboardIsInsufficientMaterialForMate(){
        Chessboard chessboardForTest = ChessboardFactory.createEmptyChessboard();
        chessboardForTest.setFigureInPlace('d', 8, new Figure(TypeOfFigure.KING, ColorOfFigure.BLACK));
        chessboardForTest.setFigureInPlace('d', 6, new Figure(TypeOfFigure.KING, ColorOfFigure.WHITE));
        chessboardForTest.setFigureInPlace('d', 7, new Figure(TypeOfFigure.KNIGHT, ColorOfFigure.WHITE));

        boolean result = GameResultValidator.isDraw(chessboardForTest);

        assertThat(result).isTrue();
    }
}
