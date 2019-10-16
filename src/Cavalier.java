import java.util.Arrays;
/**
 * @author Jules Sayer
 * @version 1.0
 */
public class Cavalier {
    private static int[][] chessboard;
    private static int size;
    private static int[] mvtsX = {-2, -2, -1, -1, 1, 1, 2, 2};
    private static int[] mvtsY = {-1, 1, -2, 2, -2, 2, -1, 1};

    private static boolean findSolutions(int x, int y, int n) {
        boolean res = false;
        if (n > (size * size)) {
            res = true;
        } else {
            for (int i = 0; i < 8; i++) {
                int nextX = x + mvtsX[i];
                int nextY = y + mvtsY[i];
                if (nextX >= 0 && nextX < size && nextY >= 0 && nextY < size && chessboard[nextX][nextY] == 0) {
                    chessboard[nextX][nextY] = n;
                    if (findSolutions(nextX, nextY, n + 1)) {
                        res = true;
                    } else {
                        chessboard[nextX][nextY] = 0;
                    }
                }
            }
        }
        return res;
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 3) {
            throw new Exception("You need to use args like: StartPosX StartPosY Size");
        }

        size = Integer.parseInt(args[2]);
        int posXD = Integer.parseInt(args[0]);
        int posYD = Integer.parseInt(args[1]);
        chessboard = new int[size][size];
        for (int[] row : chessboard) {
            Arrays.fill(row, 0);
        }
        chessboard[posXD][posYD] = 1;

        if (findSolutions(posXD, posYD, 2)) {
            for (int[] ligne : chessboard) {
                for (int n : ligne) {
                    System.out.print("\t" + n);
                }
                System.out.println();
            }
        } else {
            System.err.println("There is no solutions.");
        }
    }
}