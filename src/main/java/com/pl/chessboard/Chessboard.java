package com.pl.chessboard;

import com.pl.state.Move;
import com.pl.state.StateOfChessboard;

import java.util.ArrayList;
import java.util.List;

public class Chessboard {
    private Place[][] chessboard;
    private List<Figure> figures;
    private StateOfChessboard stateOfChessboard;

    public Chessboard(){
        this.chessboard = new Place[8][8];
        this.figures = new ArrayList<>();
        stateOfChessboard = new StateOfChessboard();
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

    public void updateMove(Move move){

    }

    public void updateBackMove(){

    }

    private void setChessboardByArrangement(){

    }


}
