package com.pl.mychess.domain.model.chessboard;

public enum TypeOfFigure {
    PAWN(" "),
    KNIGHT("N"),
    BISHOP("B"),
    ROOK("R"),
    QUEEN("Q"),
    KING("K");

    private final String signature;

    TypeOfFigure(String signature) {
        this.signature = signature;
    }

    public String getSignature() {
        return signature;
    }
}
