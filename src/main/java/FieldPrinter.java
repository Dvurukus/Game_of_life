import java.lang.Math;

import com.googlecode.lanterna.graphics.TextGraphics;

public class FieldPrinter{

 
    public static void initField(int[][] field1){
        for(int i = 0; i < field1.length; i++){
            for(int j = 0; j < field1[i].length; j++){
                double rand = Math.random() * 100;
                if(rand <= 75.0) {
                    field1[i][j] = 0;
                } else {
                    field1[i][j] = 1;
                }
            }
        }
    }

    public static void printField(int[][] field){
        for(int i = 0; i < field.length; i++){
            for(int j = 0; j < field[i].length; j++){
                System.out.print(field[i][j]);
            }
            System.out.println();
        }
    }

    public static void updateField(int[][] field1, int[][] field2){
        
        TextGraphics tg = screen.newTextGraphics();

        for(int i = 0; i < field1.length; i++){
            for(int j = 0; j < field1[i].length; j++){
                field2[i][j] = desicion(countNeighbor(field1, i, j), field1[i][j]);
                if(field2[i][j] == 1){
                    tg.setCharacter(i, j, '@');
                } else {
                    tg.setCharacter(i, j, '.');
                }
            }
            System.out.println();
        }
    }

    public static int countNeighbor(int[][] field1, int i, int j) {
        int iMinus = i - 1, iPlus = i + 1;
        int jMinus = j - 1, jPlus = j + 1;

        if(iMinus < 0) iMinus = field1.length - 1;
        if(iPlus >= field1.length) iPlus = 0;
        if(jMinus < 0) jMinus = field1[i].length - 1;
        if(jPlus >= field1[i].length) jPlus = 0;

        int sum = 0;

        sum += field1[iMinus][jMinus];
        sum += field1[iMinus][j];
        sum += field1[iMinus][jPlus];
        sum += field1[i][jMinus];
        sum += field1[i][jPlus];
        sum += field1[iPlus][jMinus];
        sum += field1[iPlus][j];
        sum += field1[iPlus][jPlus];

        return sum;

    }

    public static int desicion(int neighbor, int status){
        int desicion = -1;
        if((neighbor == 2 || neighbor == 3)&& status == 1){
            desicion = 1;
        } if (neighbor == 3 && status == 0) {
            desicion = 1;
        } else {
            desicion = 0;
        }
        return desicion;
    }

    public static void copyField(int[][] field2, int[][] field1){
        for(int i = 0; i < field1.length; i++){
            for(int j = 0; j < field1[i].length; j++){
                field1[i][j] = field2[i][j];
            }
        }
    }
}