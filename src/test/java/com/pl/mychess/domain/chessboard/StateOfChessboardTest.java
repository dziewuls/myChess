package com.pl.mychess.domain.chessboard;

import com.pl.mychess.domain.model.chessboard.*;
import com.pl.mychess.domain.model.state.Move;
import com.pl.mychess.domain.model.state.StateOfChessboard;
import com.pl.mychess.domain.model.state.TypeOfCustomMove;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.Condition;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class StateOfChessboardTest {
    @Test
    public void shouldCreateNewMoveWithExpectedParameters() {
        Figure movedFigure = new Figure(TypeOfFigure.KING, ColorOfFigure.WHITE);
        Figure beatenFigure = new Figure(TypeOfFigure.PAWN, ColorOfFigure.BLACK);
        Place previousPlace = new Place('a', 4, movedFigure);
        Place nextPlace = new Place('b', 5, beatenFigure);
        Move move = Move.getMoveBuilder()
                .currentPlayerColor(ColorOfFigure.WHITE)
                .movedFigure(movedFigure)
                .beatenFigure(beatenFigure)
                .previousPlace(previousPlace)
                .nextPlace(nextPlace)
                .typeOfCustomMove(TypeOfCustomMove.NORMAL)
                .build();

        assertThat(move)
                .isNotNull()
                .is(new Condition<>(m -> m.getCurrentPlayerColor().equals(ColorOfFigure.WHITE),
                        "Current color is white"))
                .is(new Condition<>(m -> m.getMovedFigure().equals(new Figure(TypeOfFigure.KING,
                        ColorOfFigure.WHITE)), "Moved figure is white King"))
                .is(new Condition<>(m -> m.getBeatenFigure().equals(new Figure(TypeOfFigure.PAWN,
                        ColorOfFigure.BLACK)), "Beaten figure is black Pawn"))
                .is(new Condition<>(m -> m.getPreviousPlace().equalsWithFigure(new Place('a', 4, movedFigure)),
                        "Previous place is a4"))
                .is(new Condition<>(m -> m.getNextPlace().equalsWithFigure(new Place('b', 5, beatenFigure)),
                        "Next place is b5"))
                .is(new Condition<>(m -> m.getTypeOfCustomMove().equals(TypeOfCustomMove.NORMAL),
                        "Type of move is normal"));
    }

    @Test
    public void shouldAddTheNewMoveToTheListOfMoves() {
        StateOfChessboard stateOfChessboard = new StateOfChessboard();
        Move firstMove = Move.getMoveBuilder()
                .currentPlayerColor(ColorOfFigure.WHITE)
                .movedFigure(new Figure(TypeOfFigure.PAWN, ColorOfFigure.WHITE))
                .beatenFigure(null)
                .previousPlace(new Place('e', 2))
                .nextPlace(new Place('e', 4))
                .typeOfCustomMove(TypeOfCustomMove.NORMAL)
                .build();

        stateOfChessboard.addNewMove(firstMove);
        List<Move> moveList = stateOfChessboard.getHistoryOfMoves();

        Assertions.assertThat(moveList).contains(firstMove);
    }

    @Test
    public void shouldAddTheNewChessboardToTheListOfArrangement() {
        ClassicChessChessboardFactory classicChessChessboardFactory = new ClassicChessChessboardFactory();
        StateOfChessboard stateOfChessboard = new StateOfChessboard();
        Move firstMove = Move.getMoveBuilder()
                .currentPlayerColor(ColorOfFigure.WHITE)
                .movedFigure(new Figure(TypeOfFigure.PAWN, ColorOfFigure.WHITE))
                .beatenFigure(null)
                .previousPlace(new Place('e', 2))
                .nextPlace(new Place('e', 4))
                .typeOfCustomMove(TypeOfCustomMove.NORMAL)
                .build();

        Chessboard newChessboard = classicChessChessboardFactory.createUpdatedChessboard(
                classicChessChessboardFactory.createInitialChessboard(), firstMove);

        stateOfChessboard.addNewArrangement(newChessboard);
        List<Chessboard> arrangementList = stateOfChessboard.getHistoryOfArrangement();

        Assertions.assertThat(arrangementList).contains(newChessboard);
    }

    @Test
    public void shouldRemoveTheLastMoveFromTheListOfMoves() {
        ClassicChessChessboardFactory classicChessChessboardFactory = new ClassicChessChessboardFactory();
        StateOfChessboard stateOfChessboard = new StateOfChessboard();
        Move firstMove = Move.getMoveBuilder()
                .currentPlayerColor(ColorOfFigure.WHITE)
                .movedFigure(new Figure(TypeOfFigure.PAWN, ColorOfFigure.WHITE))
                .beatenFigure(null)
                .previousPlace(new Place('e', 2))
                .nextPlace(new Place('e', 4))
                .typeOfCustomMove(TypeOfCustomMove.NORMAL)
                .build();

        stateOfChessboard.addNewMove(firstMove);
        Chessboard newChessboard = classicChessChessboardFactory.createUpdatedChessboard(
                classicChessChessboardFactory.createInitialChessboard(), firstMove);
        stateOfChessboard.addNewArrangement(newChessboard);

        stateOfChessboard.backMove();
        List<Move> moveList = stateOfChessboard.getHistoryOfMoves();

        Assertions.assertThat(moveList).doesNotContain(firstMove);
    }

    @Test
    public void shouldRemoveTheLastChessboardFromTheHistoryOfArrangement() {
        ClassicChessChessboardFactory classicChessChessboardFactory = new ClassicChessChessboardFactory();
        StateOfChessboard stateOfChessboard = new StateOfChessboard();
        Move firstMove = Move.getMoveBuilder()
                .currentPlayerColor(ColorOfFigure.WHITE)
                .movedFigure(new Figure(TypeOfFigure.PAWN, ColorOfFigure.WHITE))
                .beatenFigure(null)
                .previousPlace(new Place('e', 2))
                .nextPlace(new Place('e', 4))
                .typeOfCustomMove(TypeOfCustomMove.NORMAL)
                .build();

        stateOfChessboard.addNewMove(firstMove);
        Chessboard newChessboard = classicChessChessboardFactory.createUpdatedChessboard(
                classicChessChessboardFactory.createInitialChessboard(), firstMove);
        stateOfChessboard.addNewArrangement(newChessboard);

        stateOfChessboard.backMove();
        List<Chessboard> arrangementList = stateOfChessboard.getHistoryOfArrangement();

        Assertions.assertThat(arrangementList).doesNotContain(newChessboard);
    }
}
