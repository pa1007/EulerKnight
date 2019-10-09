public class CavalierALL {
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
     * Nombre de solutions incrémenté lors de l'appel
     * de la fonction
     */
    private static int nbSolutions = 0;

    /**
     * Fonction récursive permettant de trouver toutes les solutions
     * (elle incrémente le nombre de solution trouvées et ne les affiche pas)
     *
     * @param x position du cavalier en X
     * @param y position du cavalier en Y
     * @param n numéro de l'étape
     */
    private static void rechercheSolution(int x, int y, int n) {
        if (n == taille * taille) {
            nbSolutions++;
        } else {
            for (int i = 0; i < 8; i++) {
                int nextX = x + mvtsX[i];
                int nextY = y + mvtsY[i];
                if (nextX >= 0 && nextX < taille && nextY >= 0 && nextY < taille && echiquier[nextX][nextY] == -1) {
                    echiquier[nextX][nextY] = n;
                    rechercheSolution(nextX, nextY, n + 1);
                    echiquier[nextX][nextY] = -1; // Retour en arrière
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 3) {
            throw new Exception("Comme indiqué dans la doc, il faut 3 paramètres afin de lancer le programme.");
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
        rechercheSolution(posXD, posYD, 1);
        System.out.println(nbSolutions);
    }
}
