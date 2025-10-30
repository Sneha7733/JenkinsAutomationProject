package com.jenkinsproject.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import java.time.Duration;

public class LoginTests {
    BaseTest base;
    WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        base = new BaseTest();
        base.setUp(); // open Chrome and maximize
        wait = new WebDriverWait(base.driver, Duration.ofSeconds(5));
    }

    @Test
    public void validLoginTest() {
        base.driver.get("https://the-internet.herokuapp.com/login");
        base.driver.findElement(By.id("username")).sendKeys("tomsmith");
        base.driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
        base.driver.findElement(By.cssSelector("button[type='submit']")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h2")));
        String heading = base.driver.findElement(By.tagName("h2")).getText();
        Assert.assertTrue(heading.contains("Secure Area"), "User not redirected to Secure Area!");
        base.driver.quit();
    }

    @Test
    public void invalidLoginTest() {
        base.driver.get("https://the-internet.herokuapp.com/login");
        base.driver.findElement(By.id("username")).sendKeys("wrongUser");
        base.driver.findElement(By.id("password")).sendKeys("wrongPass");
        base.driver.findElement(By.cssSelector("button[type='submit']")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("flash")));
        String actualMessage = base.driver.findElement(By.id("flash")).getText();
        Assert.assertTrue(actualMessage.contains("Your username is invalid!"),
                "Error message mismatch for invalid credentials!");
        base.driver.quit();
    }

    @Test
    public void emptyFieldsTest() {
        base.driver.get("https://the-internet.herokuapp.com/login");
        base.driver.findElement(By.id("username")).sendKeys("");
        base.driver.findElement(By.id("password")).sendKeys("");
        base.driver.findElement(By.cssSelector("button[type='submit']")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("flash")));
        String actualMessage = base.driver.findElement(By.id("flash")).getText();
        Assert.assertTrue(actualMessage.contains("Your username is invalid!"),
                "Expected validation message for empty fields!");
        base.driver.quit();
    }
}
