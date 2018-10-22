package com.pl.mychess.domain.classicchess.chessboard;

import com.pl.mychess.domain.model.chessboard.*;
import com.pl.mychess.domain.model.state.MatchStatus;
import com.pl.mychess.domain.model.state.Move;
import com.pl.mychess.domain.model.state.TypeOfCustomMove;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.Condition;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class MatchStatusTest {
    @Test
    public void shouldMoveBuilderCreateNewMoveWithExpectedParameters() {
        Figure movedFigure = new Figure(TypeOfFigure.KING, Color.WHITE);
        Figure beatenFigure = new Figure(TypeOfFigure.PAWN, Color.BLACK);
        Place previousPlace = new Place('a', 4, movedFigure);
        Place nextPlace = new Place('b', 5, beatenFigure);
        Move move = Move.getMoveBuilder()
                .movedFigure(movedFigure)
                .beatenFigure(beatenFigure)
                .previousPlace(previousPlace)
                .nextPlace(nextPlace)
                .typeOfCustomMove(TypeOfCustomMove.NORMAL)
                .build();

        assertThat(move)
                .isNotNull()
                .is(new Condition<>(m -> m.getMovedFigure().equals(new Figure(TypeOfFigure.KING,
                        Color.WHITE)), "Moved figure is white King"))
                .is(new Condition<>(m -> m.getBeatenFigure().equals(new Figure(TypeOfFigure.PAWN,
                        Color.BLACK)), "Beaten figure is black Pawn"))
                .is(new Condition<>(m -> m.getPreviousPlace().equalsWithFigure(new Place('a', 4, movedFigure)),
                        "Previous place is a4"))
                .is(new Condition<>(m -> m.getNextPlace().equalsWithFigure(new Place('b', 5, beatenFigure)),
                        "Next place is b5"))
                .is(new Condition<>(m -> m.getTypeOfCustomMove().equals(TypeOfCustomMove.NORMAL),
                        "Type of move is normal"));
    }

    @Test
    public void shouldAddTheNewMoveToTheListOfMoves() {
        MatchStatus matchStatus = new MatchStatus();
        Move firstMove = Move.getMoveBuilder()
                .movedFigure(new Figure(TypeOfFigure.PAWN, Color.WHITE))
                .beatenFigure(null)
                .previousPlace(new Place('e', 2))
                .nextPlace(new Place('e', 4))
                .typeOfCustomMove(TypeOfCustomMove.NORMAL)
                .build();

        matchStatus.newMove(firstMove);
        List<Move> moveList = matchStatus.getHistoryOfMoves();

        Assertions.assertThat(moveList).contains(firstMove);
    }

    @Test
    public void shouldRemoveTheLastMoveFromTheListOfMoves() {
        MatchStatus matchStatus = new MatchStatus();
        Move firstMove = Move.getMoveBuilder()
                .movedFigure(new Figure(TypeOfFigure.PAWN, Color.WHITE))
                .beatenFigure(null)
                .previousPlace(new Place('e', 2))
                .nextPlace(new Place('e', 4))
                .typeOfCustomMove(TypeOfCustomMove.NORMAL)
                .build();

        matchStatus.newMove(firstMove);

        matchStatus.backMove();
        List<Move> moveList = matchStatus.getHistoryOfMoves();

        Assertions.assertThat(moveList).doesNotContain(firstMove);
    }
}
