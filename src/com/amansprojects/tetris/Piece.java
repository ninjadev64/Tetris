package com.amansprojects.tetris;

import java.util.Arrays;

public class Piece {
    public final PieceType type;
    public int[] origin;
    public int[][] bits;

    public Piece(PieceType t) {
        type = t;
        origin = Arrays.copyOf(t.spawn, 2);
        bits = Arrays.stream(t.bits).map(int[]::clone).toArray(int[][]::new);
    }
}
