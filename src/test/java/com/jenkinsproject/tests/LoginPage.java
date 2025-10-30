package com.jenkinsproject.tests;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    WebDriver driver;
    By username = By.id("username");
    By password = By.id("password");
    By loginBtn = By.cssSelector("button.radius");
    By message = By.id("flash");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigate() {
        driver.get("https://the-internet.herokuapp.com/login");
    }

    public void login(String user, String pass) {
        driver.findElement(username).sendKeys(user);
        driver.findElement(password).sendKeys(pass);
        driver.findElement(loginBtn).click();
    }

    public boolean isLoginSuccessful() {
        return driver.getPageSource().contains("You logged into a secure area!");
    }

    public boolean isErrorDisplayed() {
        return driver.findElement(message).isDisplayed();
    }
}
