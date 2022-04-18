package codingStudy.backJunProduct.gold;

import java.util.Scanner;

public class DiceExample {

    private int a = 1;
    private int b = 1;

    public static void main(String[] args) {
        int N, M, x, y, k;

        Scanner scan = new Scanner(System.in);

        // 초기값 지정
        N = scan.nextInt();
        M = scan.nextInt();
        x = scan.nextInt();
        y = scan.nextInt();
        k = scan.nextInt();


        int[][] _map = new int[N][M];

        // 주사위 시작위치 {1,1}
        int[][] _dice = {{0,0},{0,0,0,0},{0,0}};
        // 객체생성
        Dice dice = new Dice();

        try {
            if(N>0 && N<=20 && M > 0 && M <= 20) {
                if(x>=0 && x < N && y >=0 && y < M){
                    _map = dice.mapItem(N, M);
                }
            }
/*
            for(int i =0; i < _map.length; i++){
                for(int j = 0; j < _map[i].length;j++){
                    System.out.print(_map[i][j]+" ");
                }
                System.out.println();
            }
*/


            if(k > 0 && k <= 1000) {
                for(int i = 0; i < k;i++){
                    int way = scan.nextInt();
                    dice.diceController(dice, way);
                    int col = dice.getA();
                    int row = dice.getB();
                    switch(way){
                        case 1:
                            y++;
                            if(_map[x][y] == 0){
                                _map[x][y] = _dice[col][row];
                            } else {
                                _dice[col][row] = _map[x][y];
                                _map[x][y] = 0;
                            }

                            dice.arrayPrint(dice, _dice);
                            break;
                        case 2:
                            y--;
                            if(_map[x][y] == 0){
                                _map[x][y] = _dice[col][row];
                            } else {
                                _dice[col][row] = _map[x][y];
                                _map[x][y] = 0;
                            }

                            dice.arrayPrint(dice, _dice);
                            break;
                        case 3:
                            x--;
                            if(_map[x][y] == 0){
                                _map[x][y] = _dice[col][row];
                            } else {
                                _dice[col][row] = _map[x][y];
                                _map[x][y] = 0;
                            }

                            dice.arrayPrint(dice, _dice);
                            break;
                        case 4:
                            x++;
                            if(_map[x][y] == 0){
                                _map[x][y] = _dice[col][row];
                            } else {
                                _dice[col][row] = _map[x][y];
                                _map[x][y] = 0;
                            }

                            dice.arrayPrint(dice, _dice);
                            break;
                    }

                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class Dice{

    private int a = 1;
    private int b = 1;

    public void arrayPrint(Dice dice,int[][] _dice) {


        int col = dice.getA();
        int row = dice.getB();

        if(col == 1){
            if(row == 1 || row == 0){
                System.out.println(_dice[col][row +2]);
            } else if(row == 2 || row == 3){
                System.out.println(_dice[col][row -2]);
            }
        } else if(col == 2){
            System.out.println(_dice[col - 2][row]);
        } else if(col == 0){
            System.out.println(_dice[col + 2][row]);
        }
    }

    // 지도에 숫자지정 함수
    public int[][] mapItem(int N, int M) {

        Scanner scan = new Scanner(System.in);
        int[][] result = new int[N][M];

        for(int i = 0; i < result.length; i++){
            for(int j = 0; j < result[i].length; j++){
                result[i][j] = scan.nextInt();
            }
        }

        return result;
    }

    // 주사위 위치조절 함수
    public void diceController(Dice dice, int way){

        int col = dice.getA();
        int row = dice.getB();

        if(col == 0){
            if(row == 1){
                switch(way){
                    case 1:
                        dice.setA(1);
                        break;
                    case 2:
                        dice.setA(2);
                        break;
                    case 3:
                        dice.setA(1);
                        dice.setB(0);
                        break;
                    case 4:
                        dice.setA(1);
                        dice.setB(2);
                        break;
                }
            }
        } else if(col == 2){
            if(row == 1) {
                dice.setA(1);
                switch (way) {
                    case 1:
                        dice.setB(3);
                        break;
                    case 2:
                        dice.setB(1);
                        break;
                    case 3:
                        dice.setB(0);
                        break;
                    case 4:
                        dice.setB(2);
                        break;
                }
            }
        } else if(col == 1){
            if(row == 0) {
                switch(way){
                    case 1:
                        dice.setA(2);
                        dice.setB(1);
                        break;
                    case 2:
                        dice.setA(0);
                        dice.setB(1);
                        break;
                    case 3:
                        dice.setB(3);
                        break;
                    case 4:
                        dice.setB(1);
                        break;
                }
            } else if(row == 1){
                switch(way){
                    case 1:
                        dice.setA(2);
                        break;
                    case 2:
                        dice.setA(0);
                        break;
                    case 3:
                        dice.setB(0);
                        break;
                    case 4:
                        dice.setB(2);
                        break;
                }
            } else if(row == 2){
                switch(way){
                    case 1:
                        dice.setA(2);
                        dice.setB(1);
                        break;
                    case 2:
                        dice.setA(0);
                        dice.setB(1);
                        break;
                    case 3:
                        dice.setB(1);
                        break;
                    case 4:
                        dice.setB(3);
                        break;
                }
            } else{
                switch(way){
                    case 1:
                        dice.setA(2);
                        dice.setB(1);
                        break;
                    case 2:
                        dice.setA(0);
                        dice.setB(1);
                        break;
                    case 3:
                        dice.setB(2);
                        break;
                    case 4:
                        dice.setB(0);
                        break;
                }
            }
        }
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

}

