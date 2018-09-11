package com.pl.mychess.domain.model.chessboard;

import com.pl.mychess.domain.chessboard.StateOfChessboard;

import java.util.ArrayList;
import java.util.List;

public class Chessboard implements Cloneable{
    //TODO implementacja Clonable
    private Place[][] chessboard;
    private List<Figure> figures;
    private StateOfChessboard stateOfChessboard;

    public Chessboard(){
        this.chessboard = new Place[8][8];
        this.figures = new ArrayList<>();
        this.stateOfChessboard = new StateOfChessboard();
    }

    public Chessboard(Chessboard chessboard){
        this.chessboard = chessboard.getChessboard().clone();
        this.figures = new ArrayList<>(chessboard.getFigures());
        this.stateOfChessboard = new StateOfChessboard();
    }



    public Place[][] getChessboard() {
        return chessboard;
    }

    public List<Figure> getFigures() {
        return figures;
    }

    public StateOfChessboard getStateOfChessboard() {
        return stateOfChessboard;
    }

    public Place getPlaceByCoordinates(char x, int y){

        return null;
    }

    public Figure getFigureByCoordinates(char x, int y){

        return null;
    }
}
