/**
 * Cette classe permet d'afficher
 * l'éqchiquier pour UNE solution.
 * L'affichage de cette solution se
 * fait en indiquant le numéro de
 * l'étape pour chaque case de
 * l'échiquier.
 * <p>
 * Il est nécessaire de passer en
 * argument de cette classe dans cet ordre:
 * X0, Y0 et N. Où la paire (X0,Y0) correspond
 * à la position de départ du cavalier et N
 * l'échiquier de N x N
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
     * @return Si la fonction a trouvé une solution complète ou non
     */
    private static boolean rechercheSolution(int x, int y, int n) {
        boolean res = false;
        if (n == (taille * taille)) {
            res = true;
        } else {
            for (int i = 0; i < 8; i++) {
                int nextX = x + mvtsX[i];
                int nextY = y + mvtsY[i];
                if (nextX >= 0 && nextX < taille && nextY >= 0 && nextY < taille && echiquier[nextX][nextY] == -1) {
                    echiquier[nextX][nextY] = n;
                    if (rechercheSolution(nextX, nextY, n + 1)) {
                        res = true;
                    } else {
                        echiquier[nextX][nextY] = -1; // Retour en arrière
                    }
                }
            }
        }
        return res;
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 3) {
            throw new Exception("Comme indiqué dans la doc, il faut 3 arguments afin de lancer le programme.");
        }
        taille = Integer.parseInt(args[2]);
        int posXD = Integer.parseInt(args[0]);
        int posYD = Integer.parseInt(args[1]);
        echiquier = new int[taille][taille];
        for (int x = 0; x < taille; x++) {
            for (int y = 0; y < taille; y++) {
                echiquier[x][y] = -1;
            }
        }
        echiquier[posXD][posYD] = 0;
        if (!rechercheSolution(posXD, posYD, 1)) {
            System.err.println("Il n'existe aucune solution.");
        } else {
            for (int x = 0; x < taille; x++) {
                for (int y = 0; y < taille; y++) {
                    System.out.print(echiquier[x][y] + 1 + " ");
                }
                System.out.println();
            }
        }
    }
}