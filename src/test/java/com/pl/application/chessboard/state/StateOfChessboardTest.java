package com.pl.application.chessboard.state;

import com.pl.application.chessboard.ColorOfFigure;
import com.pl.application.chessboard.Figure;
import com.pl.application.chessboard.Place;
import com.pl.application.chessboard.TypeOfFigure;
import org.assertj.core.api.Condition;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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
        StateOfChessboard stateOfChessboard = new StateOfChessboard();
        List<Move> moveList = new ArrayList<>();
        Arrangement startedArrangement = (new Arrangement()).createArrangement();

        Move firstMove = stateOfChessboard.getMoveBuilder()
                .currentPlayerColor(ColorOfFigure.WHITE)
                .movedFigure(new Figure(TypeOfFigure.PAWN, ColorOfFigure.WHITE))
                .beatenFigure(null)
                .previousPlace(new Place('e', 2))
                .nextPlace(new Place('e', 4))
                .typeOfMove(TypeOfCustomMove.NORMAL)
                .build();
        Move secondMove = stateOfChessboard.getMoveBuilder()
                .currentPlayerColor(ColorOfFigure.BLACK)
                .movedFigure(new Figure(TypeOfFigure.PAWN, ColorOfFigure.BLACK))
                .beatenFigure(null)
                .previousPlace(new Place('e', 7))
                .nextPlace(new Place('e', 5))
                .typeOfMove(TypeOfCustomMove.NORMAL)
                .build();

        moveList.add(firstMove);
        moveList.add(secondMove);

        Arrangement finishedArrangement = (new Arrangement()).createArrangement(startedArrangement, moveList);

        assertThat(finishedArrangement)
                .isNotNull()
                .is(new Condition<>(a -> a.getFigure("e4").equals(new Figure(TypeOfFigure.PAWN,
                        ColorOfFigure.WHITE)), "White Rook in e4"))
                .is(new Condition<>(a -> a.getFigure("e5").equals(new Figure(TypeOfFigure.PAWN,
                        ColorOfFigure.BLACK)), "Black Rook in e5"))
                .is(new Condition<>(a -> a.getFigure("e2").equals(null), "no figure in e2"))
                .is(new Condition<>(a -> a.getFigure("e7").equals(null), "no figure in e7"));
    }

    @Test
    public void shouldCreateArrangementByLastOfArrangementAndTheNewMove() {
        StateOfChessboard stateOfChessboard = new StateOfChessboard();
        List<Move> moveList = new ArrayList<>();
        Arrangement startedArrangement = (new Arrangement()).createArrangement();

        Move firstMove = stateOfChessboard.getMoveBuilder()
                .currentPlayerColor(ColorOfFigure.WHITE)
                .movedFigure(new Figure(TypeOfFigure.PAWN, ColorOfFigure.WHITE))
                .beatenFigure(null)
                .previousPlace(new Place('e', 2))
                .nextPlace(new Place('e', 4))
                .typeOfMove(TypeOfCustomMove.NORMAL)
                .build();
        Move secondMove = stateOfChessboard.getMoveBuilder()
                .currentPlayerColor(ColorOfFigure.BLACK)
                .movedFigure(new Figure(TypeOfFigure.PAWN, ColorOfFigure.BLACK))
                .beatenFigure(null)
                .previousPlace(new Place('e', 7))
                .nextPlace(new Place('e', 5))
                .typeOfMove(TypeOfCustomMove.NORMAL)
                .build();

        moveList.add(firstMove);

        Arrangement secondArrangement = (new Arrangement()).createArrangement(startedArrangement, moveList);
        Arrangement finishedArrangement = (new Arrangement()).createArrangement(secondArrangement, secondMove);

        assertThat(finishedArrangement)
                .isNotNull()
                .is(new Condition<>(a -> a.getFigure("e4").equals(new Figure(TypeOfFigure.PAWN,
                        ColorOfFigure.WHITE)), "White Rook in e4"))
                .is(new Condition<>(a -> a.getFigure("e5").equals(new Figure(TypeOfFigure.PAWN,
                        ColorOfFigure.BLACK)), "Black Rook in e5"))
                .is(new Condition<>(a -> a.getFigure("e2").equals(null), "no figure in e2"))
                .is(new Condition<>(a -> a.getFigure("e7").equals(null), "no figure in e7"));
    }

    @Test
    public void shouldAddTheNewMoveToTheListOfMoves() {
        StateOfChessboard stateOfChessboard = new StateOfChessboard();
        Move firstMove = stateOfChessboard.getMoveBuilder()
                .currentPlayerColor(ColorOfFigure.WHITE)
                .movedFigure(new Figure(TypeOfFigure.PAWN, ColorOfFigure.WHITE))
                .beatenFigure(null)
                .previousPlace(new Place('e', 2))
                .nextPlace(new Place('e', 4))
                .typeOfMove(TypeOfCustomMove.NORMAL)
                .build();

        stateOfChessboard.addNewMove(firstMove);
        List<Move> moveList = stateOfChessboard.getHistoryOfMoves();

        assertThat(moveList).contains(firstMove);
    }

    @Test
    public void shouldAddTheNewArrangementToTheListOfArrangementsAndReturnIt() {
        StateOfChessboard stateOfChessboard = new StateOfChessboard();
        Move firstMove = stateOfChessboard.getMoveBuilder()
                .currentPlayerColor(ColorOfFigure.WHITE)
                .movedFigure(new Figure(TypeOfFigure.PAWN, ColorOfFigure.WHITE))
                .beatenFigure(null)
                .previousPlace(new Place('e', 2))
                .nextPlace(new Place('e', 4))
                .typeOfMove(TypeOfCustomMove.NORMAL)
                .build();

        Arrangement currentArrangement = stateOfChessboard.addNewMove(firstMove);
        List<Arrangement> arrangementList = stateOfChessboard.getHistoryOfArrangement();

        assertThat(arrangementList).contains(currentArrangement);
    }

    @Test
    public void shouldRemoveTheLastMoveFromTheListOfMoves() {
        StateOfChessboard stateOfChessboard = new StateOfChessboard();
        Move firstMove = stateOfChessboard.getMoveBuilder()
                .currentPlayerColor(ColorOfFigure.WHITE)
                .movedFigure(new Figure(TypeOfFigure.PAWN, ColorOfFigure.WHITE))
                .beatenFigure(null)
                .previousPlace(new Place('e', 2))
                .nextPlace(new Place('e', 4))
                .typeOfMove(TypeOfCustomMove.NORMAL)
                .build();

        stateOfChessboard.addNewMove(firstMove);
        stateOfChessboard.backMove();
        List<Move> moveList = stateOfChessboard.getHistoryOfMoves();

        assertThat(moveList).doesNotContain(firstMove);
    }

    @Test
    public void shouldRemoveTheLastArrangementAndReturnPenultimate() {
        StateOfChessboard stateOfChessboard = new StateOfChessboard();
        Move firstMove = stateOfChessboard.getMoveBuilder()
                .currentPlayerColor(ColorOfFigure.WHITE)
                .movedFigure(new Figure(TypeOfFigure.PAWN, ColorOfFigure.WHITE))
                .beatenFigure(null)
                .previousPlace(new Place('e', 2))
                .nextPlace(new Place('e', 4))
                .typeOfMove(TypeOfCustomMove.NORMAL)
                .build();

        Arrangement currentArrangement = stateOfChessboard.addNewMove(firstMove);
        Arrangement penultimateArrangement = stateOfChessboard.backMove();
        List<Arrangement> arrangementList = stateOfChessboard.getHistoryOfArrangement();

        assertThat(arrangementList)
                .doesNotContain(currentArrangement)
                .endsWith(penultimateArrangement);
    }
}
