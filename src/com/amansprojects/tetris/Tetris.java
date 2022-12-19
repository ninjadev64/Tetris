package com.amansprojects.tetris;

import java.util.Arrays;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Tetris {
    private static Piece current;
    private static final PieceType[][] board = new PieceType[20][10];

    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        current = new Piece(PieceType.values()[random.nextInt(6)]);

        new Thread(() -> {
            while (true) {
                try {
                    switch (scanner.nextLine().substring(0, 1).toLowerCase().trim()) {
                        case "z" -> {
                            if (canMove(current, -1, 0)) current.origin[0] -= 1;
                        }
                        case "x" -> {
                            if (canMove(current, 1, 0)) current.origin[0] += 1;
                        }
                        case "a" -> current.rotate(-1);
                        case "d" -> current.rotate(1);
                    }
                } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException ignored) { }
            }
        }).start();

        while (true) {
            PieceType[][] boardWithCurrent = Arrays.stream(board).map(PieceType[]::clone).toArray(PieceType[][]::new);
            StringBuilder toPrint = new StringBuilder();

            for (int[] bit : current.bits) {
                int x = current.origin[0] + bit[0];
                int y = current.origin[1] + bit[1];
                if (y < 0) continue;
                boardWithCurrent[y][x] = current.type;
            }
            for (PieceType[] row : boardWithCurrent) {
                for (PieceType cell : row) {
                    String s = "  ";
                    if (cell != null) {
                        switch (cell) {
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
            for (int i = 0; i < 20; i++) {
                if (Arrays.stream(board[i]).allMatch(Objects::nonNull)) {
                    board[i] = new PieceType[10];
                    for (int x = i; x >= 0; x--) {
                        board[x + 1] = Arrays.copyOf(board[x], 10);
                    }
                }
            }
            System.out.println(toPrint);
            TimeUnit.MILLISECONDS.sleep(500);
            System.out.print("\u001B[2J");

            if (canMove(current, 0, 1)) {
                current.origin[1] += 1;
            } else {
                for (int[] bit : current.bits) {
                    board[current.origin[1] + bit[1]][current.origin[0] + bit[0]] = current.type;
                }
                current = new Piece(PieceType.values()[random.nextInt(6)]);
            }
        }
    }

    static boolean canMove(Piece p, int xOffset, int yOffset) {
        for (int[] bit : p.bits) {
            int x = p.origin[0] + xOffset + bit[0];
            int y = p.origin[1] + yOffset + bit[1];
            if (y < 0) continue;
            if (y >= 19) return false;
            if (board[y][x] != null) {
                return false;
            }
        }
        return true;
    }
}
