package data;

public class Messages {
    public static final String INCORRECTDATAGEO = "მონაცემები არასწორია";
    public static final String INCORRECTDATAENG = "Incorrect data";

    public static final String BLOCKEDUSERENG = "Concumer was blocked due to wrong attempts. For removing block contact the call center or use reset of details. Unblocking will automatically occur within the next 30 minutes.";
    public static final String BLOCKEDUSERGEO = "მომხმარებელი დაიბლოკა არასწორ მცდელობათა გამო. ბლოკის მოსახსნელად დაუკავშირდით ქოლ ცენტრს ან გამოიყენეთ მონაცემების აღდგენა. განბლოკვა ავტომატურად მოხდება მომდევნო 30 წუთში.";

    public static String getIncorrectDataText(Language language){
        return language == Language.GEO ? INCORRECTDATAGEO : INCORRECTDATAENG;
    }

    public static String getBlockedUserText(Language language){
        return language == Language.GEO ? BLOCKEDUSERGEO : BLOCKEDUSERENG;
    }

}
