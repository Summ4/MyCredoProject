package LoginPage;

import data.Language;
import org.testng.annotations.Factory;

public class LoginPageTest {

    @Factory
    public Object[] createInstances() {
        return new Object[]{
                new LoginTest(Language.GEO),
                new LoginTest(Language.ENG)
        };
    }
}

