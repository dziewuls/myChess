package com.pl.mychess.domain.model.chessboard;

public enum TypeOfFigure {
    PAWN(""),
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

    public static TypeOfFigure getTypeOfFigure(String signature){
        if(signature.equals(PAWN.getSignature())) return PAWN;
        else if(signature.equals(KNIGHT.getSignature())) return KNIGHT;
        else if(signature.equals(BISHOP.getSignature())) return BISHOP;
        else if(signature.equals(ROOK.getSignature())) return ROOK;
        else if(signature.equals(QUEEN.getSignature())) return QUEEN;
        else if(signature.equals(KING.getSignature())) return KING;
        else return null;
    }
}
