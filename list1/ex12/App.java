import java.util.Scanner;

public class App {

  public static char[][] board = new char[3][3];

  public static int[] bestPlay(char player, char opponent) {
    // Jogada para vencer

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        if (board[i][j] == ' ') {
          board[i][j] = player;
          if (verifyVictory(player)) {
            board[i][j] = ' '; // Remove a jogada feita
            return new int[] { i, j }; // Retornando as posicoes que vao conceder a vitoria
          }
          board[i][j] = ' ';
        }
      }
    }

    // Jogada se nao houver a possibilidade de vencer ou bloquear
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        if (board[i][j] == ' ') {
          return new int[] { i, j }; // Retornando as posicoes que vao conceder a melhor jogada
        }
      }
    }

    // Jogada para bloquear
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        if (board[i][j] == ' ') {
          board[i][j] = opponent;
          if (verifyVictory(opponent)) {
            board[i][j] = ' '; // Remove a jogada feita
            return new int[] { i, j }; // Retornando as posicoes que vao conceder o bloqueio
          }
          board[i][j] = ' ';
        }
      }
    }

    return null;
  }

  public static boolean verifyVictory(char player) {
    for (int i = 0; i < 3; i++) {
      if ((board[i][0] == player && board[i][1] == player && board[i][2] == player)
          || ((board[0][i] == player && board[1][i] == player && board[2][i] == player))) {
        return true;
      }

    }

    if ((board[0][0] == player && board[1][1] == player && board[2][2] == player)
        || ((board[0][2] == player && board[1][1] == player && board[2][0] == player))) {
      return true;
    }

    return false;

  }

  public static void main(String[] args) {
    Scanner keyboard = new Scanner(System.in);

    System.out.printf("Monte o tabuleiro ficticio (Utilize: 'X', 'O' e ' ' para montar o tabuleiro): \n");


    for (int i = 0; i < 3; i++) {
      String row = keyboard.nextLine();
      for (int j = 0; j < 3; j++) {
        board[i][j] = row.charAt(j);
      }
    }

    char player = 'X';
    char opponent = 'O';

    int[] play = bestPlay(player, opponent);

    if (play != null) {
      System.out.println("Melhor jogada na Linha: " + (play[0] + 1) + ", na Coluna: " + (play[1] + 1));
    } else {
      System.out.println("Nao vai dar nao!");
    }

    keyboard.close();
  }
}
