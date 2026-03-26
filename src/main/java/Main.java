import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.screen.Screen;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            // Инициализация Lanterna
            Screen screen = new DefaultTerminalFactory().createScreen();
            screen.startScreen();

            GameField game = new GameField(40, 80);
            FieldPrinter.initField(game.getField1());

            // Ваш цикл игры
            for (int i = 0; i < 100; i++) {
                
                FieldPrinter.updateField(game.getField1(), game.getField2());

                screen.refresh();
                
                FieldPrinter.copyField(game.getField2(), game.getField1());

                Thread.sleep(100);
            }

            screen.stopScreen();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
