import java.util.Arrays;

/**
 * Cette classe permet d'afficher l'échiquier
 * pour UNE solution. L'affichage de cette
 * solution se fait en indiquant le numéro de
 * l'étape pour chaque case de l'échiquier.
 *
 * Comme indiqué dans le sujet il est nécessaire
 * de passer en argument X0, Y0 et N.
 *
 * @author Jules Sayer
 * @version 1.0
 */
public class Cavalier {
    /**
     * Echiquier qui enregistre les solutions
     */
    private static int[][] echiquier;

    /**
     * Taille de l'échiquier
     */
    private static int taille;

    /**
     * Mouvements possible d'un cavalier en X
     */
    private static int[] mvtsX = {-2, -2, -1, -1, 1, 1, 2, 2};

    /**
     * Mouvements possible d'un cavalier en Y
     */
    private static int[] mvtsY = {-1, 1, -2, 2, -2, 2, -1, 1};

    /**
     * Fonction récursive permettant de trouver UNE solution
     *
     * @param x position du cavalier en X
     * @param y position du cavalier en Y
     * @param n numéro de l'étape
     * @return boolean Si la fonction a trouvé une solution complète ou non
     */
    private static boolean rechercheSolution(int x, int y, int n) {
        boolean res = false;
        if (n > (taille * taille)) {
            res = true;
        } else {
            for (int i = 0; i < 8; i++) {
                int nextX = x + mvtsX[i];
                int nextY = y + mvtsY[i];
                if (nextX >= 0 && nextX < taille && nextY >= 0 && nextY < taille && echiquier[nextX][nextY] == 0) {
                    echiquier[nextX][nextY] = n;
                    if (rechercheSolution(nextX, nextY, n + 1)) {
                        res = true;
                    } else {
                        echiquier[nextX][nextY] = 0;
                    }
                }
            }
        }
        return res;
    }

    public static void main(String[] args) throws Exception {
        /* Test arguments */
        if (args.length != 3) {
            throw new Exception("Comme indiqué dans la doc, il faut 3 arguments afin de lancer le programme.");
        }

        /* Initialisation de l'échiquier et placement du pion. */
        taille = Integer.parseInt(args[2]);
        int posXD = Integer.parseInt(args[0]);
        int posYD = Integer.parseInt(args[1]);
        echiquier = new int[taille][taille];
        for (int[] row : echiquier) {
            Arrays.fill(row, 0);
        }
        echiquier[posXD][posYD] = 1;

        /* Appel de la fonction récursive */
        if (rechercheSolution(posXD, posYD, 2)) {
            for (int[] ligne : echiquier) {
                for (int n : ligne) {
                    System.out.print("\t" + n);
                }
                System.out.println();
            }
        } else {
            System.err.println("Il n'existe aucune solution.");
        }
    }
}