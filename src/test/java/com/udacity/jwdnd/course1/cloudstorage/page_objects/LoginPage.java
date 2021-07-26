package com.udacity.jwdnd.course1.cloudstorage.page_objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    @FindBy(id = "inputUsername")
    private WebElement username;
    @FindBy(id = "inputPassword")
    private WebElement password;
    @FindBy(id = "submit-button")
    private WebElement submitButton;
    @FindBy(id = "signup-link")
    private WebElement signupLink;

    public LoginPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    public void setUsername(String username){
        this.username.clear();
        this.username.sendKeys(username);
    }
    public void setPassword(String password){
        this.password.clear();
        this.password.sendKeys(password);
    }
    public void submit(){
        submitButton.click();
    }

    public void login(String username, String password){
        setUsername(username);
        setPassword(password);
        submit();
    }
    public void signup(){
        signupLink.click();
    }
}
