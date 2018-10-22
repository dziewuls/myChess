package com.pl.mychess;

import com.pl.mychess.api.ConsoleUserInterface;
import com.pl.mychess.domain.classicchess.manager.ClassicChessGameManager;
import com.pl.mychess.domain.port.api.UserInterface;
import com.pl.mychess.domain.port.game.GameManager;

public class Application {
    public static void main(String[] args) {
        GameManager gameManager = new ClassicChessGameManager();
        UserInterface userInterface = new ConsoleUserInterface();


    }
}
