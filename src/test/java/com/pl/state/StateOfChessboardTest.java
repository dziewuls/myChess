package com.pl.state;

import com.pl.chessboard.ColorOfFigure;
import com.pl.chessboard.Figure;
import com.pl.chessboard.Place;
import com.pl.chessboard.TypeOfFigure;
import org.assertj.core.api.Condition;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class StateOfChessboardTest {
    @Test
    public void shouldCreateNewMoveWithExpectedParameters() {
        StateOfChessboard stateOfChessboard = new StateOfChessboard();
        Figure movedFigure = new Figure(TypeOfFigure.KING, ColorOfFigure.WHITE);
        Figure beatenFigure = new Figure(TypeOfFigure.PAWN, ColorOfFigure.BLACK);
        Place previousPlace = new Place('a', 4, movedFigure);
        Place nextPlace = new Place('b', 5, beatenFigure);
        Move move = stateOfChessboard.getMoveBuilder()
                .currentPlayerColor(ColorOfFigure.WHITE)
                .movedFigure(movedFigure)
                .beatenFigure(beatenFigure)
                .previousPlace(previousPlace)
                .nextPlace(nextPlace)
                .typeOfMove(TypeOfCustomMove.NORMAL)
                .build();

        assertThat(move)
                .isNotNull()
                .is(new Condition<>(m -> m.getCurrentPlayerColor().equals(ColorOfFigure.WHITE),
                        "Current color is white"))
                .is(new Condition<>(m -> m.getMovedFigure().equals(new Figure(TypeOfFigure.KING,
                        ColorOfFigure.WHITE)), "Moved figure is white King"))
                .is(new Condition<>(m -> m.getBeatenFigure().equals(new Figure(TypeOfFigure.PAWN,
                        ColorOfFigure.BLACK)), "Beaten figure is black Pawn"))
                .is(new Condition<>(m -> m.getPreviousPlace().equals(new Place('a', 4)),
                        "Previous place is a4"))
                .is(new Condition<>(m -> m.getNextPlace().equals(new Place('b', 5)),
                        "Next place is b5"))
                .is(new Condition<>(m -> m.getTypeOfMove().equals(TypeOfCustomMove.NORMAL),
                        "Type of move is normal"));
    }

    @Test //TODO params
    public void shouldCreateInitialArrangementForStartGame() {
        StateOfChessboard stateOfChessboard = new StateOfChessboard();
        Arrangement figuresArrangement = stateOfChessboard.getStartedArrangement();

        assertThat(figuresArrangement)
                .isNotNull()
                .is(new Condition<>(a -> a.getFigure("a1").equals(new Figure(TypeOfFigure.ROOK,
                        ColorOfFigure.WHITE)), "White Rook in a1"))
                .is(new Condition<>(a -> a.getFigure("d1").equals(new Figure(TypeOfFigure.QUEEN,
                        ColorOfFigure.WHITE)), "White Queen in d1"))
                .is(new Condition<>(a -> a.getFigure("e2").equals(new Figure(TypeOfFigure.PAWN,
                        ColorOfFigure.WHITE)), "White Pawn in e2"))
                .is(new Condition<>(a -> a.getFigure("e8").equals(new Figure(TypeOfFigure.KING,
                        ColorOfFigure.BLACK)), "Black King in e8"))
                .is(new Condition<>(a -> a.getFigure("g8").equals(new Figure(TypeOfFigure.KNIGHT,
                        ColorOfFigure.BLACK)), "Black knight in g8"));
    }

    @Test
    public void shouldCreateArrangementByStartedArrangementAndTheListOfMoves() {

    }

    @Test
    public void shouldCreateArrangementByLastOfArrangementAndTheNewMove() {

    }

    @Test
    public void shouldAddTheNewMoveToTheListOfMoves() {

    }

    @Test
    public void shouldAddTheNewArrangementToTheListOfArrangementsAndReturnIt() {

    }

    @Test
    public void shouldRemoveTheLastMoveFromTheListOfMoves() {

    }

    @Test
    public void shouldRemoveTheLastArrangementAndReturnPenultimate() {

    }


}