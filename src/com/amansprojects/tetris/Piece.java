package com.amansprojects.tetris;

public class Piece {
    public final PieceType type;
    public int[] origin;
    public int[][] bits;

    public Piece(PieceType t) {
        type = t;
        origin = new int[] { t.spawn[0], t.spawn[1] };
        bits = t.bits;
    }
}
