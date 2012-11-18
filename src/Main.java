import com.ternovsky.gui.GameJFrame;

import java.awt.*;
import java.io.FileNotFoundException;

/**
 * Created with IntelliJ IDEA.
 * User: ternovsky
 * Date: 15.11.12
 * Time: 22:30
 * To change this template use File | Settings | File Templates.
 */
public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GameJFrame();
            }
        });
    }
}
