// https://www.acmicpc.net/problem/13460 구슬 탈출 2

package Apr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Apr1723_BFS {
  static boolean[][][][] visit = new boolean[10][10][10][10];
  static int n, m, holeX, holeY;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    n = Integer.parseInt(st.nextToken());
    m = Integer.parseInt(st.nextToken());

    char[][] initialBoard = new char[n][m];

    int startRedX = 0;
    int startRedY = 0;

    int startBlueX = 0;
    int startBlueY = 0;

    holeX = 0;
    holeY = 0;

    for (int i = 0; i < n; i++) {
      String line = br.readLine();

      for (int j = 0; j < m; j++) {
        initialBoard[i][j] = line.charAt(j);

        if (initialBoard[i][j] == 'R') {
          startRedX = j;
          startRedY = i;
        } else if (initialBoard[i][j] == 'B') {
          startBlueX = j;
          startBlueY = i;
        } else if (initialBoard[i][j] == 'O') {
          holeX = j;
          holeY = i;
        }
      }
    }

    int result = 11;

    Queue<Node> queue = new LinkedList<>();
    // System.out.printf("%d %d %d %d", startRedX, startRedY, startBlueX,
    // startBlueY);
    queue.add(new Node(startRedX, startRedY, startBlueX, startBlueY, initialBoard, 0));

    while (!queue.isEmpty()) {
      Node current = queue.poll();

      int redX = current.redX;
      int redY = current.redY;

      int blueX = current.blueX;
      int blueY = current.blueY;

      for (int i = 0; i < 4; i++) {
        switch (i) {
          case 0:
            // System.out.println("up");
            current = up(current);
            break;
          case 1:
            // System.out.println("down");
            current = down(current);
            break;
          case 2:
            // System.out.println("left");
            current = left(current);
            break;
          case 3:
            // System.out.println("right");
            current = right(current);
            break;
        }

        // printBoard(current.board);

        if (!visit[current.redY][current.redX][current.blueY][current.blueX]) {
          int gameResult = getResult(current);
          if (gameResult == 1) {
            result = Math.min(result, current.move + 1);
            queue.clear();
            break;
          } else if (gameResult == 0) {
            visit[current.redY][current.redX][current.blueY][current.blueX] = true;
            queue
                .add(new Node(current.redX, current.redY, current.blueX, current.blueY, current.board,
                    current.move + 1));
          }
        }
        current = restore(redX, redY, blueX, blueY, current);
      }

    }

    System.out.println(result > 10 ? -1 : result);
  }

  static void printBoard(char[][] board) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        sb.append(board[i][j]);
      }
      sb.append("\n");
    }
    System.out.println(sb);
  }

  static int getResult(Node current) {
    boolean redEnd = current.redX == holeX && current.redY == holeY;
    boolean blueEnd = current.blueX == holeX && current.blueY == holeY;

    if (blueEnd) {
      // 블루가 끝났으면 무조건 fail
      return -1;
    }

    if (redEnd) {
      // 레드만 끝났다면 Success
      return 1;
    }

    // 둘 다 아닐 경우 계속 진행
    return 0;
  }

  static Node restore(int redX, int redY, int blueX, int blueY, Node current) {
    current.board[current.redY][current.redX] = '.';
    current.board[current.blueY][current.blueX] = '.';

    current.redX = redX;
    current.redY = redY;

    current.blueX = blueX;
    current.blueY = blueY;

    current.board[redY][redX] = 'R';
    current.board[blueY][blueX] = 'B';

    current.board[holeY][holeX] = 'O';
    return current;
  }

  static Node up(Node current) {
    int redX = current.redX;
    int redY = current.redY;

    int blueX = current.blueX;
    int blueY = current.blueY;

    current.board[redY][redX] = '.';
    current.board[blueY][blueX] = '.';

    if (redX == blueX) {
      boolean redFirst = redY < blueY;
      int firstY = redFirst ? redY : blueY;

      while (true) {
        // System.out.println(firstY);
        char position = current.board[firstY - 1][redX];
        if (position == '.') {
          firstY--;
        } else if (position == 'O') {
          firstY--;
          break;
        } else {
          break;
        }
      }

      if (redFirst) {
        redY = firstY;
        if (current.board[redY][redX] != 'O') {
          current.board[redY][redX] = 'R';
        }
      } else {
        blueY = firstY;
        if (current.board[blueY][blueX] != 'O') {
          current.board[blueY][blueX] = 'B';
        }
      }

      int secondY = redFirst ? blueY : redY;

      while (true) {
        char position = current.board[secondY - 1][redX];
        if (position == '.') {
          secondY--;
        } else if (position == 'O') {
          secondY--;
          break;
        } else {
          break;
        }
      }

      if (redFirst) {
        blueY = secondY;
        if (current.board[blueY][blueX] != 'O') {
          current.board[blueY][blueX] = 'B';
        }
      } else {
        redY = secondY;
        if (current.board[redY][redX] != 'O') {
          current.board[redY][redX] = 'R';
        }
      }
    } else {
      while (true) {
        char position = current.board[redY - 1][redX];
        if (position == '.') {
          redY--;
        } else if (position == 'O') {
          redY--;
          break;
        } else {
          break;
        }
      }

      if (current.board[redY][redX] != 'O') {
        current.board[redY][redX] = 'R';
      }

      while (true) {
        char position = current.board[blueY - 1][blueX];
        if (position == '.') {
          blueY--;
        } else if (position == 'O') {
          blueY--;
          break;
        } else {
          break;
        }
      }

      if (current.board[blueY][blueX] != 'O') {
        current.board[blueY][blueX] = 'B';
      }
    }

    current.redX = redX;
    current.redY = redY;
    current.blueX = blueX;
    current.blueY = blueY;

    return current;

  }

  static Node down(Node current) {
    int redX = current.redX;
    int redY = current.redY;

    int blueX = current.blueX;
    int blueY = current.blueY;

    current.board[redY][redX] = '.';
    current.board[blueY][blueX] = '.';

    if (redX == blueX) {
      boolean redFirst = redY > blueY;
      int firstY = redFirst ? redY : blueY;

      while (true) {
        char position = current.board[firstY + 1][redX];
        if (position == '.') {
          firstY++;
        } else if (position == 'O') {
          firstY++;
          break;
        } else {
          break;
        }
      }

      if (redFirst) {
        redY = firstY;
        if (current.board[redY][redX] != 'O') {
          current.board[redY][redX] = 'R';
        }
      } else {
        blueY = firstY;
        if (current.board[blueY][blueX] != 'O') {
          current.board[blueY][blueX] = 'B';
        }
      }

      int secondY = redFirst ? blueY : redY;

      while (true) {
        char position = current.board[secondY + 1][redX];
        if (position == '.') {
          secondY++;
        } else if (position == 'O') {
          secondY++;
          break;
        } else {
          break;
        }
      }

      if (redFirst) {
        blueY = secondY;
        if (current.board[blueY][blueX] != 'O') {
          current.board[blueY][blueX] = 'B';
        }
      } else {
        redY = secondY;
        if (current.board[redY][redX] != 'O') {
          current.board[redY][redX] = 'R';
        }
      }
    } else {
      while (true) {
        char position = current.board[redY + 1][redX];
        if (position == '.') {
          redY++;
        } else if (position == 'O') {
          redY++;
          break;
        } else {
          break;
        }
      }

      if (current.board[redY][redX] != 'O') {
        current.board[redY][redX] = 'R';
      }

      while (true) {
        char position = current.board[blueY + 1][blueX];
        if (position == '.') {
          blueY++;
        } else if (position == 'O') {
          blueY++;
          break;
        } else {
          break;
        }
      }

      if (current.board[blueY][blueX] != 'O') {
        current.board[blueY][blueX] = 'B';
      }
    }

    current.redX = redX;
    current.redY = redY;
    current.blueX = blueX;
    current.blueY = blueY;

    return current;

  }

  static Node left(Node current) {
    int redX = current.redX;
    int redY = current.redY;

    int blueX = current.blueX;
    int blueY = current.blueY;

    current.board[redY][redX] = '.';
    current.board[blueY][blueX] = '.';

    if (redY == blueY) {
      boolean redFirst = redX < blueX;
      int firstX = redFirst ? redX : blueX;

      while (true) {
        char position = current.board[redY][firstX - 1];
        if (position == '.') {
          firstX--;
        } else if (position == 'O') {
          firstX--;
          break;
        } else {
          break;
        }
      }

      if (redFirst) {
        redX = firstX;
        if (current.board[redY][redX] != 'O') {
          current.board[redY][redX] = 'R';
        }
      } else {
        blueX = firstX;
        if (current.board[blueY][blueX] != 'O') {
          current.board[blueY][blueX] = 'B';
        }
      }

      int secondX = redFirst ? blueX : redX;

      while (true) {
        char position = current.board[redY][secondX - 1];
        if (position == '.') {
          secondX--;
        } else if (position == 'O') {
          secondX--;
          break;
        } else {
          break;
        }
      }

      if (redFirst) {
        blueX = secondX;
        if (current.board[blueY][blueX] != 'O') {
          current.board[blueY][blueX] = 'B';
        }
      } else {
        redX = secondX;
        if (current.board[redY][redX] != 'O') {
          current.board[redY][redX] = 'R';
        }
      }
    } else {
      while (true) {
        char position = current.board[redY][redX - 1];
        if (position == '.') {
          redX--;
        } else if (position == 'O') {
          redX--;
          break;
        } else {
          break;
        }
      }

      if (current.board[redY][redX] != 'O') {
        current.board[redY][redX] = 'R';
      }

      while (true) {
        char position = current.board[blueY][blueX - 1];
        if (position == '.') {
          blueX--;
        } else if (position == 'O') {
          blueX--;
          break;
        } else {
          break;
        }
      }

      if (current.board[blueY][blueX] != 'O') {
        current.board[blueY][blueX] = 'B';
      }
    }

    current.redX = redX;
    current.redY = redY;
    current.blueX = blueX;
    current.blueY = blueY;

    return current;
  }

  static Node right(Node current) {
    int redX = current.redX;
    int redY = current.redY;

    int blueX = current.blueX;
    int blueY = current.blueY;

    // System.out.println("Before First");
    // System.out.printf("%d %d %d %d %d %d\n", current.redX, current.redY,
    // current.blueX, current.blueY, holeX, holeY);
    // printBoard(current.board);

    current.board[redY][redX] = '.';
    current.board[blueY][blueX] = '.';

    if (redY == blueY) {
      boolean redFirst = redX > blueX;
      int firstX = redFirst ? redX : blueX;

      while (true) {
        char position = current.board[redY][firstX + 1];
        if (position == '.') {
          firstX++;
        } else if (position == 'O') {
          firstX++;
          break;
        } else {
          break;
        }
      }

      if (redFirst) {
        redX = firstX;
        if (current.board[redY][redX] != 'O') {
          current.board[redY][redX] = 'R';
        }
      } else {
        blueX = firstX;
        if (current.board[blueY][blueX] != 'O') {
          current.board[blueY][blueX] = 'B';
        }
      }

      // System.out.println("After First");
      // printBoard(current.board);
      int secondX = redFirst ? blueX : redX;

      while (true) {
        char position = current.board[redY][secondX + 1];

        if (position == '.') {
          secondX++;
        } else if (position == 'O') {
          secondX++;
          break;
        } else {
          break;
        }
      }
      // System.out.println("SECOND X is " + secondX);
      if (redFirst) {
        blueX = secondX;
        if (current.board[blueY][blueX] != 'O') {
          current.board[blueY][blueX] = 'B';
        }
      } else {
        redX = secondX;
        if (current.board[redY][redX] != 'O') {
          current.board[redY][redX] = 'R';
        }
      }
    } else {
      while (true) {
        char position = current.board[redY][redX + 1];
        if (position == '.') {
          redX++;
        } else if (position == 'O') {
          redX++;
          break;
        } else {
          break;
        }
      }

      if (current.board[redY][redX] != 'O') {
        current.board[redY][redX] = 'R';
      }

      while (true) {
        char position = current.board[blueY][blueX + 1];
        if (position == '.') {
          blueX++;
        } else if (position == 'O') {
          blueX++;
          break;
        } else {
          break;
        }
      }

      if (current.board[blueY][blueX] != 'O') {
        current.board[blueY][blueX] = 'B';
      }
    }

    current.redX = redX;
    current.redY = redY;
    current.blueX = blueX;
    current.blueY = blueY;

    return current;
  }

  static class Node {
    int move;

    int redX;
    int redY;

    int blueX;
    int blueY;

    char[][] board = new char[n][m];

    Node(int redX, int redY, int blueX, int blueY, char[][] board, int move) {
      this.move = move;

      this.redX = redX;
      this.redY = redY;

      this.blueX = blueX;
      this.blueY = blueY;

      for (int i = 0; i < n; i++) {
        this.board[i] = board[i].clone();
      }
    }
  }
}
