import java.lang.Math;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.graphics.TextGraphics;

import javax.swing.*;
import java.io.IOException;

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

    public static void updateField(int[][] field1, int[][] field2, Screen screen){

        TextGraphics tg = screen.newTextGraphics();

        for(int i = 0; i < field1.length; i++){
            for(int j = 0; j < field1[i].length; j++){
                field2[i][j] = desicion(countNeighbor(field1, i, j), field1[i][j]);
                if(field2[i][j] == 1){
                    double rand = Math.random() * 100;
                    if(rand <= 50){
                        tg.setBackgroundColor(TextColor.ANSI.MAGENTA_BRIGHT);
                        tg.setCharacter(j, i, ' ');
                    } else if (rand > 50 && rand <= 80){
                        tg.setBackgroundColor(TextColor.ANSI.CYAN_BRIGHT);
                        tg.setCharacter(j, i, ' ');
                    } else if (rand == 99) {
                        tg.setBackgroundColor(TextColor.ANSI.WHITE_BRIGHT);
                        tg.setCharacter(j, i, ' ');
                    } else if (rand > 93) {
                        tg.setBackgroundColor(TextColor.ANSI.BLUE_BRIGHT);
                        tg.setCharacter(j, i, ' ');
                    }
                } else {
                    tg.setBackgroundColor(TextColor.ANSI.BLACK);
                    tg.setCharacter(j, i, ' ');
                }
            }
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
        } else if (neighbor == 3 && status == 0) {
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

    public static int coutAllive(int[][] field1){
        int count = 0;
        for (int i = 0; i < field1.length; i++){
            for (int j = 0; j < field1[i].length; j++){
                count += field1[i][j];
            }
        }
        return count;
    }

    public static int readKey(Screen screen, int milliSec, GameField game) throws IOException {
        KeyStroke stroke = screen.pollInput();

        if(stroke != null){
            if(stroke.getKeyType() == KeyType.ArrowUp){
                if(milliSec - 10 > 0){
                    milliSec -= 10;
                }
            } else if (stroke.getKeyType() == KeyType.ArrowDown){
                milliSec += 10;
            } else if (stroke.getKeyType() == KeyType.Escape) {
                game.setFlag(true);
            }
        }
        return milliSec;
    }

}