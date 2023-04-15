// https://www.acmicpc.net/problem/12100 2048 (Easy)
package Apr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Apr1523_2048 {
  static int n;
  static int[][] board;
  static int result = 0;

  public static void main(String[] args) throws NumberFormatException, IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    n = Integer.parseInt(br.readLine());

    board = new int[n][n];
    for (int i = 0; i < n; i++) {

      StringTokenizer st = new StringTokenizer(br.readLine());
      for (int j = 0; j < n; j++) {
        board[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    dfs(0);
    System.out.println(result);
  }

  static void dfs(int depth) {
    if (depth == 5) {
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
          if (board[i][j] > result) {
            result = board[i][j];
          }
        }
      }
      return;
    }

    int[][] backup = new int[n][n];
    for (int i = 0; i < n; i++) {
      backup[i] = board[i].clone();
    }

    for (int i = 0; i < 4; i++) {
      moveBoard(i);
      dfs(depth + 1);

      // Board 복구
      for (int j = 0; j < n; j++) {
        board[j] = backup[j].clone();
      }
    }
  }

  static void moveBoard(int dir) {
    // 상, 하, 좌, 우 보드 이동
    switch (dir) {
      case 0:
        up();
        break;

      case 1:
        down();
        break;

      case 2:
        left();
        break;

      case 3:
        right();
        break;
    }
  }

  static void left() {
    for (int i = 0; i < n; i++) {
      int index = 0;
      int prevBlock = 0;
      for (int j = 0; j < n; j++) {
        if (board[i][j] != 0) {
          if (prevBlock == board[i][j]) {
            board[i][index - 1] = prevBlock * 2;
            prevBlock = 0;
            board[i][j] = 0;
          } else {
            prevBlock = board[i][j];
            board[i][j] = 0;
            board[i][index] = prevBlock;
            index++;
          }
        }
      }
    }
  }

  static void right() {
    for (int i = 0; i < n; i++) {
      int index = n - 1;
      int prevBlock = 0;
      for (int j = n - 1; j >= 0; j--) {
        if (board[i][j] != 0) {
          if (prevBlock == board[i][j]) {
            board[i][index + 1] *= 2;
            prevBlock = 0;
            board[i][j] = 0;
          } else {
            prevBlock = board[i][j];
            board[i][j] = 0;
            board[i][index] = prevBlock;
            index--;
          }
        }
      }
    }
  }

  static void down() {
    for (int j = 0; j < n; j++) {
      int index = n - 1;
      int prevBlock = 0;
      for (int i = n - 1; i >= 0; i--) {
        if (board[i][j] != 0) {
          if (prevBlock == board[i][j]) {
            board[index + 1][j] = prevBlock * 2;
            prevBlock = 0;
            board[i][j] = 0;
          } else {
            prevBlock = board[i][j];
            board[i][j] = 0;
            board[index][j] = prevBlock;
            index--;
          }
        }
      }
    }
  }

  static void up() {
    for (int j = 0; j < n; j++) {
      int index = 0;
      int prevBlock = 0;
      for (int i = 0; i < n; i++) {
        if (board[i][j] != 0) {
          if (prevBlock == board[i][j]) {
            board[index - 1][j] = prevBlock * 2;
            prevBlock = 0;
            board[i][j] = 0;
          } else {
            prevBlock = board[i][j];
            board[i][j] = 0;
            board[index][j] = prevBlock;
            index++;
          }
        }
      }
    }
  }
}