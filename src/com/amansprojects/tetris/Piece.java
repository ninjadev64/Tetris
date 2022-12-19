package com.amansprojects.tetris;

import java.util.Arrays;

public class Piece {
    public final PieceType type;
    public int[] origin;
    public int[][] bits;
    private int orientation = 0;

    public Piece(PieceType t) {
        type = t;
        origin = Arrays.copyOf(type.spawn, 2);
        bits = Arrays.stream(type.bits[orientation]).map(int[]::clone).toArray(int[][]::new);
    }

    public void rotate(int direction) {
        int backup = orientation;
        if (direction < 0) {
            switch (orientation) {
                case 0 -> orientation = 3;
                case 1 -> orientation = 0;
                case 2 -> orientation = 1;
                case 3 -> orientation = 2;
            }
        }
        else if (direction > 0) {
            switch (orientation) {
                case 0 -> orientation = 1;
                case 1 -> orientation = 2;
                case 2 -> orientation = 3;
                case 3 -> orientation = 0;
            }
        }
        bits = Arrays.stream(type.bits[orientation]).map(int[]::clone).toArray(int[][]::new);
        if (!Tetris.canMove(this, 0, 0)) {
            orientation = backup;
            bits = Arrays.stream(type.bits[orientation]).map(int[]::clone).toArray(int[][]::new);
        }
    }
}
