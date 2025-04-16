package app;

import utils.Browser;

public abstract class AppElement {
    protected Browser browser;

    protected AppElement(Browser browser) {
        this.browser = browser;
    }


}