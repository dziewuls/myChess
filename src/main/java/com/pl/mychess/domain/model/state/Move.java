package com.pl.mychess.domain.model.state;

import com.pl.mychess.domain.model.chessboard.*;

public class Move {
    private final Color currentPlayerColor;
    private final Figure movedFigure;
    private final Figure beatenFigure;
    private final Place previousPlace;
    private final Place nextPlace;
    private final TypeOfCustomMove typeOfCustomMove;
    private final TypeOfFigure pawnTransformNewFigure;

    public static MoveBuilder getMoveBuilder() {
        return new MoveBuilder();
    }

    private Move(Color currentPlayerColor, Figure movedFigure, Figure beatenFigure, Place previousPlace, Place nextPlace, TypeOfCustomMove typeOfCustomMove, TypeOfFigure pawnTransformNewFigure) {
        this.currentPlayerColor = currentPlayerColor;
        this.movedFigure = movedFigure;
        this.beatenFigure = beatenFigure;
        this.previousPlace = previousPlace;
        this.nextPlace = nextPlace;
        this.typeOfCustomMove = typeOfCustomMove;
        this.pawnTransformNewFigure = pawnTransformNewFigure;
    }

    public Color getCurrentPlayerColor() {
        return currentPlayerColor;
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
        //TODO toString for Move
        return "";
    }

    public static class MoveBuilder {
        private Color currentPlayerColor;
        private Figure movedFigure;
        private Figure beatenFigure;
        private Place previousPlace;
        private Place nextPlace;
        private TypeOfCustomMove typeOfCustomMove;
        private TypeOfFigure pawnTransformNewFigure;

        public MoveBuilder() {
            this.beatenFigure = null;
            this.typeOfCustomMove = TypeOfCustomMove.NORMAL;
            this.pawnTransformNewFigure = null;
        }

        public MoveBuilder currentPlayerColor(Color currentPlayerColor) {
            this.currentPlayerColor = currentPlayerColor;
            return this;
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
            return new Move(currentPlayerColor, movedFigure, beatenFigure, previousPlace, nextPlace, typeOfCustomMove, pawnTransformNewFigure);
        }
    }
}
