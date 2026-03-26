import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.screen.Screen;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {

            Screen screen = new DefaultTerminalFactory().createScreen();
            screen.startScreen();

            GameField game = new GameField(70, 240);
            FieldPrinter.initField(game.getField1());

            int milliSec = 201;


            while (!game.getFlag()) {

                milliSec = FieldPrinter.readKey(screen, milliSec, game);

                FieldPrinter.updateField(game.getField1(), game.getField2(), screen);

                screen.refresh();
                
                FieldPrinter.copyField(game.getField2(), game.getField1());

                Thread.sleep(milliSec);

                screen.doResizeIfNecessary();
                if (screen.doResizeIfNecessary() != null) {
                    screen.clear();
                }

            }

            screen.stopScreen();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
