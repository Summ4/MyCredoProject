package utils;

import page.LoginPage;

public class CommonSteps<T> {
    protected LoginPage loginPage;

    public CommonSteps(LoginPage loginPage) {
        this.loginPage = loginPage;
    }

}