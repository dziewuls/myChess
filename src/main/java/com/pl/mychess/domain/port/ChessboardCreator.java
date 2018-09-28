package com.pl.mychess.domain.port;

import com.pl.mychess.domain.model.chessboard.Chessboard;
import com.pl.mychess.domain.model.state.Move;

public interface ChessboardCreator {
    Chessboard createInitialChessboard();
    Chessboard createEmptyChessboard();
    Chessboard createUpdatedChessboard(Chessboard chessboard, Move move);
}
