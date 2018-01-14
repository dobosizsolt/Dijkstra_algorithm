import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        int numberOfNodes;
        int[][] matrix;    //szomszédsági mátrix
        int[] distance;    //csúcsok közti költség tárolására szolgáló tömb
        int[] previous;    //előző csúcs tárolására szolgáló tömb
        int[] visited;     //meglátogatott csúcs tárolására szolgáló tömb
        int inf = 99999;
        int nextNode = 0;

        Scanner scan = new Scanner(System.in);

        System.out.println("Add meg a gráf csúcsainak mennyiségét: ");
        numberOfNodes = scan.nextInt();

        matrix = new int[numberOfNodes][numberOfNodes];
        previous = new int[numberOfNodes];
        visited = new int[numberOfNodes];

        System.out.println("Add meg a gráf éleinek költségét: ");

        for (int i = 0; i < numberOfNodes; i++) {          //szomszédsági mátrix feltöltése
            previous[i] = 0;
            visited [i] = 0;
            for (int j = 0; j < numberOfNodes; j++) {
                matrix[i][j] = scan.nextInt();

                if (matrix[i][j] == 0) matrix[i][j] = inf; //a "0" költségeket végtelenre cseréli
            }
        }

        visited[0] = 1;        //kezdő csúcs beállítása látogatottra
        distance = matrix[0];  //távolság beállítása a szomszédsági mátrix 0. elemére
        distance[0] = 0;

        for (int i = 0; i < numberOfNodes; i++) {
            inf = 99999;
            for (int j = 0; j < numberOfNodes; j++) {

                if (inf > distance[j] && visited[j] != 1) {
                    nextNode = j;
                    inf = distance[j];
                }
            }

            visited[nextNode] = 1;

            for (int c = 0; c < numberOfNodes; c++) {
                if (visited[c] != 1) {
                    if (inf + matrix[nextNode][c] < distance[c]) {
                        distance[c] = inf + matrix[nextNode][c];
                        previous[c] = nextNode;
                    }
                }
            }
        }

        for (int i = 0; i < numberOfNodes; i++) {

            if (i != 0) {
                String chars = numToChar(i);
                System.out.print("Út = " + chars);

                int j = i;

                do {
                    j = previous[j];
                    chars = numToChar(j);
                    System.out.print(" <- " + chars);
                } while (j != 0);
                System.out.println("\t  Költség: " + distance[i]);
            }
        }
    }

    public static String numToChar(int i) {
        if (i < 25 && i > -1) {
            return String.valueOf((char) (i + 65));
        } else {
            return null;
        }
    }
}
