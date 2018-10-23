package com.pl.mychess.domain.model.state;

import com.pl.mychess.domain.model.chessboard.*;

public class Move {
    private final Figure movedFigure;
    private final Figure beatenFigure;
    private final Place previousPlace;
    private final Place nextPlace;
    private final TypeOfCustomMove typeOfCustomMove;
    private final TypeOfFigure pawnTransformNewFigure;

    public static MoveBuilder getMoveBuilder() {
        return new MoveBuilder();
    }

    private Move(Figure movedFigure, Figure beatenFigure, Place previousPlace, Place nextPlace, TypeOfCustomMove typeOfCustomMove, TypeOfFigure pawnTransformNewFigure) {
        this.movedFigure = movedFigure;
        this.beatenFigure = beatenFigure;
        this.previousPlace = previousPlace;
        this.nextPlace = nextPlace;
        this.typeOfCustomMove = typeOfCustomMove;
        this.pawnTransformNewFigure = pawnTransformNewFigure;
    }

    public Figure getMovedFigure() {
        return movedFigure;
    }

    public Figure getBeatenFigure() {
        return beatenFigure;
    }

    public Place getPreviousPlace() {
        return previousPlace;
    }

    public Place getNextPlace() {
        return nextPlace;
    }

    public TypeOfFigure getPawnTransformNewFigure() {
        return pawnTransformNewFigure;
    }

    public TypeOfCustomMove getTypeOfCustomMove() {
        return typeOfCustomMove;
    }

    @Override
    public String toString() {
        if (typeOfCustomMove == TypeOfCustomMove.SHORT_CASTLE) return  "O-O";
        if (typeOfCustomMove == TypeOfCustomMove.LONG_CASTLE) return "O-O-O";

        String result = movedFigure.toString() + previousPlace.toString();
        result += (beatenFigure == null) ? "-" : ":";
        result += nextPlace.toString();

        if (typeOfCustomMove == TypeOfCustomMove.PAWN_TRANSFORM)
            result += pawnTransformNewFigure.getSignature();

        return result;
    }

    public static class MoveBuilder {
        private Figure movedFigure;
        private Figure beatenFigure;
        private Place previousPlace;
        private Place nextPlace;
        private TypeOfCustomMove typeOfCustomMove;
        private TypeOfFigure pawnTransformNewFigure;

        public Figure getMovedFigure() {
            return movedFigure;
        }

        public Figure getBeatenFigure() {
            return beatenFigure;
        }

        public Place getPreviousPlace() {
            return previousPlace;
        }

        public Place getNextPlace() {
            return nextPlace;
        }

        public MoveBuilder() {
            this.beatenFigure = null;
            this.typeOfCustomMove = TypeOfCustomMove.NORMAL;
            this.pawnTransformNewFigure = null;
        }

        public MoveBuilder movedFigure(Figure movedFigure) {
            this.movedFigure = movedFigure;
            return this;
        }

        public MoveBuilder beatenFigure(Figure beatenFigure) {
            this.beatenFigure = beatenFigure;
            return this;
        }

        public MoveBuilder previousPlace(Place previousPlace) {
            this.previousPlace = previousPlace;
            return this;
        }

        public MoveBuilder nextPlace(Place nextPlace) {
            this.nextPlace = nextPlace;
            return this;
        }

        public MoveBuilder typeOfCustomMove(TypeOfCustomMove typeOfCustomMove) {
            this.typeOfCustomMove = typeOfCustomMove;
            return this;
        }

        public MoveBuilder pawnTransformNewFigure(TypeOfFigure pawnTransformNewFigure) {
            this.pawnTransformNewFigure = pawnTransformNewFigure;
            return this;
        }

        public Move build() {
            return new Move(movedFigure, beatenFigure, previousPlace, nextPlace, typeOfCustomMove, pawnTransformNewFigure);
        }
    }
}
