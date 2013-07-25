package functional.com.trailblazers.freewheelers.helpers;

import org.openqa.selenium.By;

public class HomeTable {
    public static By nameFieldFor(String name) {
        return By.cssSelector("h3[data-name='" + name + "']");

    }

    public static By reserveButtonFor(String name) {
        return By.cssSelector("button[data-name='" + name + "']");
    }
}
