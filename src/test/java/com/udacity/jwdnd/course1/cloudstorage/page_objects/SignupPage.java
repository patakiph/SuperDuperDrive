package com.udacity.jwdnd.course1.cloudstorage.page_objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignupPage {
    @FindBy(id = "inputFirstName")
    private WebElement firstName;
    @FindBy(id = "inputLastName")
    private WebElement lastName;
    @FindBy(id = "inputUsername")
    private WebElement username;
    @FindBy(id = "inputPassword")
    private WebElement password;
    @FindBy(id = "submit-button")
    private WebElement submitButton;

    @FindBy(id = "login-link")
    private WebElement loginLink;

    public SignupPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }
    public void setFirstName(String firstName){
        this.firstName.clear();
        this.firstName.sendKeys(firstName);
    }
    public void setLastName(String lastName){
        this.lastName.clear();
        this.lastName.sendKeys(lastName);
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

    public void signup(String firstName, String lastName, String username, String password){
        setFirstName(firstName);
        setLastName(lastName);
        setUsername(username);
        setPassword(password);
        submit();
    }

    public void login(){
        loginLink.click();
    }
}
