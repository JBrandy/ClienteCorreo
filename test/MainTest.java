import Brandy.Launcher;
import javafx.stage.Stage;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import static org.testfx.api.FxAssert.verifyThat;

public class MainTest extends ApplicationTest {


    @Before
    public void setup() throws Exception {
        ApplicationTest.launch(Launcher.class);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.show();
    }
    @Test
    public void puslarInicio() {

        clickOn("#tvCorreo");
        write("damdijb@gmail.com");
        clickOn("#tvContrasena");
        write("123456A@");
        clickOn("#btInicio");
    }
    @Test
    public void pulsarMenu() {
        clickOn("#miInicioSesion");
    }



}
