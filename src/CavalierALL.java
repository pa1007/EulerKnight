import java.util.Arrays;
/**
 * @author Jules Sayer
 * @version 1.0
 */
public class CavalierALL {
    private static int[][] chessboard;
    private static int size;
    private static int[] mvtsX = {-2, -2, -1, -1, 1, 1, 2, 2};
    private static int[] mvtsY = {-1, 1, -2, 2, -2, 2, -1, 1};
    private static int nbSolutions = 0;

    private static void findSolutions(int x, int y, int n) {
        if (n > size * size) {
            nbSolutions++;
        } else {
            for (int i = 0; i < 8; i++) {
                int nextX = x + mvtsX[i];
                int nextY = y + mvtsY[i];
                if (nextX >= 0 && nextX < size && nextY >= 0 && nextY < size && chessboard[nextX][nextY] == 0) {
                    chessboard[nextX][nextY] = n;
                    findSolutions(nextX, nextY, n + 1);
                    chessboard[nextX][nextY] = 0; // Retour en arrière
                }
            }
        }
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

        findSolutions(posXD, posYD, 2);
        System.out.println(nbSolutions);
    }
}