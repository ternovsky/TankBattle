import com.ternovsky.Scene;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: ternovsky
 * Date: 15.11.12
 * Time: 22:30
 * To change this template use File | Settings | File Templates.
 */
public class Main {

    public static final Logger LOGGER = Logger.getLogger(Main.class.getSimpleName());
    public static final String FILENAME = "C:\\Users\\ternovsky\\Documents\\GitHub\\TankBattle\\src\\scene.txt";
    public static final File SCENE_FILE = new File(FILENAME);

    public static void main(String[] args) throws FileNotFoundException {
        LOGGER.info("Hello World!");
        Scene scene = new Scene();
        scene.readScene(SCENE_FILE);
        LOGGER.info(scene.toString());
    }
}
