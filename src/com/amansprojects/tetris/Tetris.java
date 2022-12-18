package com.amansprojects.tetris;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Tetris {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        Piece current = new Piece(PieceType.values()[random.nextInt(6)]);
        ArrayList<Piece> pieces = new ArrayList<>();
        pieces.add(current);

        while (true) {
            Piece[][] board = new Piece[20][10];
            StringBuilder toPrint = new StringBuilder();

            for (Piece piece : pieces) {
                for (int[] bit : piece.bits) {
                    int x = piece.origin[0] + bit[0];
                    if (x < 0) continue;
                    int y = piece.origin[1] + bit[1];
                    if (y < 0) continue;
                    board[y][x] = piece;
                }
            }
            for (Piece[] row : board) {
                for (Piece cell : row) {
                    String s = "  ";
                    if (cell != null) {
                        switch (cell.type) {
                            case I -> s = "\u001b[46m  ";
                            case J -> s = "\u001b[44m  ";
                            case L -> s = "\u001b[47m  ";
                            case O -> s = "\u001b[43m  ";
                            case S -> s = "\u001b[42m  ";
                            case T -> s = "\u001b[45m  ";
                            case Z -> s = "\u001b[41m  ";
                        }
                    }
                    s = s + "\u001b[0m";
                    toPrint.append(s);
                }
                toPrint.append("\n");
            }
            System.out.println(toPrint);
            try {
                switch (scanner.nextLine().substring(0, 1).toLowerCase().trim()) {
                    case "z" -> current.origin[0] -= 1;
                    case "x", "c" -> current.origin[0] += 1;
                }
                System.out.println(current.origin[0] + ", " + current.origin[1]);
            } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException ignored) { }
            System.out.print("\u001B[2J");

            boolean c = true;
            for (int[] bit : current.bits) {
                int x = current.origin[0] + bit[0];
                if (x < 0) continue;
                int y = current.origin[1] + bit[1];
                if (y < 0) continue;
                if (y >= 19 || (board[y + 1][x] != null && board[y + 1][x] != current)) {
                    c = false;
                    break;
                }
            }
            if (c) {
                current.origin[1] += 1;
            } else {
                current = new Piece(PieceType.values()[random.nextInt(6)]);
                pieces.add(current);
            }
        }
    }
}
