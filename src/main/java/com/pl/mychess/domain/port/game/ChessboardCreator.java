package com.pl.mychess.domain.port.game;

import com.pl.mychess.domain.model.chessboard.Chessboard;
import com.pl.mychess.domain.model.state.Move;

public interface ChessboardCreator {
    Chessboard createInitialChessboard();
    Chessboard createEmptyChessboard();
    Chessboard createUpdatedChessboardByMove(Chessboard chessboard, Move move);
    Chessboard createUpdatedChessboardByBackMove(Chessboard chessboard, Move move);
}
