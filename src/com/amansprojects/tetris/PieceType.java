package com.amansprojects.tetris;

public enum PieceType {
    I(new int[][]{ { 0, 0 }, { 1, 0 }, { 2, 0 }, { 3, 0 } }, new int[]{ 4, -1 }),
    J(new int[][]{ { 0, 0 }, { 0, 1 }, { 1, 1 }, { 2, 1 } }, new int[]{ 4, -2 }),
    L(new int[][]{ { 2, 0 }, { 0, 1 }, { 1, 1 }, { 2, 1 } }, new int[]{ 4, -2 }),
    O(new int[][]{ { 0, 0 }, { 1, 0 }, { 0, 1 }, { 1, 1 } }, new int[]{ 5, -2 }),
    S(new int[][]{ { 1, 0 }, { 2, 0 }, { 0, 1 }, { 1, 1 } }, new int[]{ 4, -2 }),
    T(new int[][]{ { 1, 0 }, { 0, 1 }, { 1, 1 }, { 2, 1 } }, new int[]{ 4, -2 }),
    Z(new int[][]{ { 0, 0 }, { 1, 0 }, { 1, 1 }, { 2, 1 } }, new int[]{ 4, -2 });

    public final int[][] bits;
    public final int[] spawn;

    PieceType(int[][] bits, int[] spawn) {
        this.bits = bits;
        this.spawn = spawn;
    }
}
