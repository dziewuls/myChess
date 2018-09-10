package com.pl.mychess.domain.model.chessboard;

import java.util.HashMap;
import java.util.Map;

public class Arrangement {
    private Map<String, Figure> figuresArrangement;

    public Arrangement(Map<String, Figure> figuresArrangement) {
        this.figuresArrangement = figuresArrangement;
    }

    public Arrangement() {
        this.figuresArrangement = new HashMap<>();
    }

    public Map<String, Figure> getFiguresArrangement() {
        return figuresArrangement;
    }
}