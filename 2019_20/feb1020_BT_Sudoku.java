//https://www.acmicpc.net/problem/2580
//내가 생각한 검사 조건 : 행,열,작은사각형의 모든 숫자들의 합이 55이다
//틀린 이유 : 다른 칸에도 0이 있기 때문
//개선 방법 : 세 조건 안에 지금 보고 있는 숫자가 "없다" 를 검사 >> 숫자가 없다 == 반드시 넣어야 한다
//boolean 배열 사용하여 위 세 조건 검사
//작은 삼각형 검사하는 방법 알아둘 것
//System.exit(0); >> 실행 중인 자바 프로그램 강제 종료

import java.util.Scanner;

public class feb1020_BT_Sudoku {
    static boolean[][] rowCheck = new boolean[9][10];
    static boolean[][] colCheck = new boolean[9][10];
    static boolean[][] squareCheck = new boolean[9][10];

    static int square(int x, int y) { // 몇번째 작은 사각형인지 계산
        return (x / 3) * 3 + y / 3;
    }

    public static void go(int[][] a, int cnt) {
        if (cnt == 81) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    System.out.print(a[i][j] + " ");
                }
                System.out.println();
            }
            System.exit(0); // 더 이상 재귀가 돌지 않도록 프로그램 종료, 없으면 시간초과
        }
        int x = cnt / 9; // 행 구하기
        int y = cnt % 9; // 열 구하기

        if (a[x][y] != 0) {
            go(a, cnt + 1);
        } else {
            for (int k = 1; k <= 9; k++) {
                if (!rowCheck[x][k] && !colCheck[y][k] && !squareCheck[square(x, y)][k]) { // 3가지 경우 모두 k가 없을 경우
                    rowCheck[x][k] = true; // 다시 방문하지 않기위한
                    colCheck[y][k] = true;
                    squareCheck[square(x, y)][k] = true;
                    a[x][y] = k; // 0을 k로 바꿈
                    go(a, cnt + 1); // 다음 단계
                    // 백트래킹을 위한
                    a[x][y] = 0;
                    rowCheck[x][k] = false;
                    colCheck[y][k] = false;
                    squareCheck[square(x, y)][k] = false;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int[][] a = new int[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                a[i][j] = scan.nextInt();
                if (a[i][j] != 0) {
                    rowCheck[i][a[i][j]] = true;
                    colCheck[j][a[i][j]] = true;
                    squareCheck[square(i, j)][a[i][j]] = true;
                }
            }
        }
        go(a, 0);
        scan.close();
    }

}
