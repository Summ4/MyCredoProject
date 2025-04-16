package LoginPage;

import data.Language;
import org.testng.annotations.Factory;

public class LanguageTestFactory {

    @Factory
    public Object[] createInstances() {
        return new Object[]{
                new LoginPageTest(Language.GEO),
                new LoginPageTest(Language.ENG)
        };
    }
}

