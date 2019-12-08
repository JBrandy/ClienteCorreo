import Brandy.Launcher;
import Brandy.logica.Logica;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.After;
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
    public void confcuenta() {

        clickOn("#mInicio");
        type(KeyCode.DOWN);
        type(KeyCode.DOWN);
        type(KeyCode.DOWN);
        clickOn("#mConfigurarCuentas");
        clickOn("#btAnadirr");
        clickOn("#tvCorreo");
        //write("damdijb@gmail.com");
        //clickOn("#tvContrasena");
        //write("123456A@");
        clickOn("#btInicio");
    }

    @After
    public void finalizar()
    {
        Logica.getInstance().finalizar();
    }




}
