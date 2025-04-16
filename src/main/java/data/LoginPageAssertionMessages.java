package data;

public interface LoginPageAssertionMessages {
    String WRONGCREDINTIALS = "Check Login With Wrong UserName And Password";
    String USERNAMEANDONLYSPACEINPASSWORD = "Check Login With UserName And Only 'Space' Password";
    String USERNAMEANDWRONGPASSWORD = "Check Login With UserName And Wrong Password";


    String EMPTYUSERNAMEANDSPACEINSPASSWORD = "Login Button Should Be Disabled When Login With Empty UserName And Space In Password";
    String EMPTYUSERNAMEANDRADOMPASSWORD = "Login Button Should Be Disabled When Login With Empty And Random Password";
    String EMPTYUSERNAMEANDCORRECTINSPASSWORD = "Login Button Should Be Disabled When Login With Empty UserName And Correct Password";
    String GEORGIANUSERNAMEANDSPACEINSPASSWORD = "Login Button Should Be Disabled When Login With Georgian UserName And Password";

    String RANDOMUSERNAMEANDEMPTYPASSWORD = "Login Button Should Be Disabled When Login With Random UserName And Empty Password";
    String USERNAMEANDEMPTYPASSWORD = "Login Button Should Be Disabled When Login With UserName Empty Password";
    String SPACEINUSERNAMEANDEMPTYPASSWORD = "Login Button Should Be Disabled When Login With Space In UserName And Empty Password";

    String INVALIDOTPCODE = "Appropriate Error Text Should Be Visible When Entering Invalid OTP Code";

    String BLOCKEDUSER = "Login With Blocked User Should Not Be Possible";

    String BLOCKUSER = "Appropriate Error Text Should Appear When User Is Blocked";
}
