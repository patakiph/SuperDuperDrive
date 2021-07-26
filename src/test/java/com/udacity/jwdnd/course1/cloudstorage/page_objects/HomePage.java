package com.udacity.jwdnd.course1.cloudstorage.page_objects;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    @FindBy(id = "logout-button")
    private WebElement logoutButton;

    @FindBy(id = "edit_note_btn")
    private WebElement editNoteButton;
    @FindBy(id = "delete_note_btn")
    private WebElement deleteNoteButton;
    @FindBy(id = "create_note_button")
    private WebElement createNoteButton;

    @FindBy(id = "note-title")
    private WebElement noteTitle;
    @FindBy(id = "note-description")
    private WebElement noteDescription;
    @FindBy(id = "SaveNoteBtn")
    private WebElement noteSubmitButton;

    @FindBy(id = "nav_notes_tab")
    private WebElement noteNavigateLink;

    @FindBy(id = "nav_credentials_tab")
    private WebElement credentialNavigateLink;

    @FindBy(id = "noteTitleShow")
    private WebElement noteTitleShow;
    @FindBy(id = "noteDescriptionShow")
    private WebElement noteDescriptionShow;

    @FindBy(id = "credentialURLShow")
    private WebElement credentialURLShow;
    @FindBy(id = "credentialUsernameShow")
    private WebElement credentialUsernameShow;

    @FindBy(id = "edit_credential_btn")
    private WebElement editCredentialButton;
    @FindBy(id = "delete_credential_btn")
    private WebElement deleteCredentialButton;
    @FindBy(id = "createCredentialButton")
    private WebElement createCredentialButton;

    @FindBy(id = "credential-url")
    private WebElement credentialURL;
    @FindBy(id = "credential-username")
    private WebElement credentialUsername;
    @FindBy(id = "credential-password")
    private WebElement credentialPassword;

    @FindBy(id = "credentialSaveBtn")
    private WebElement credentialSubmitButton;


    public HomePage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }


    public void navigateToNotes(){
        noteNavigateLink.click();
    }

    public void createNote(String noteTitle, String noteDescription, WebDriver driver){
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(createNoteButton))
                .click();
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(this.noteTitle))
                .clear();
        this.noteTitle.sendKeys(noteTitle);
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(this.noteDescription))
                .clear();
        this.noteDescription.sendKeys(noteDescription);
        noteSubmitButton.click();
    }

    public void deleteNote(){
        deleteNoteButton.click();
    }

    public void editNote(String noteTitle, String noteDescription, WebDriver driver){
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(editNoteButton))
                .click();
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(this.noteTitle))
                .clear();
        this.noteTitle.sendKeys(noteTitle);
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(this.noteDescription))
                .clear();
        this.noteDescription.sendKeys(noteDescription);
        noteSubmitButton.click();
    }
    public Note getFirstNote(WebDriver driver){
        try {
            new WebDriverWait(driver, 10)
                    .until(ExpectedConditions.visibilityOfElementLocated(By.id("noteTitleShow")));
            return new Note(null, noteTitleShow.getText(), noteDescriptionShow.getText(), null);
        } catch (org.openqa.selenium.TimeoutException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void navigateToCredentials(){
        credentialNavigateLink.click();
    }

    public void createCredential(String credentialURL, String credentialUsename, String credentialPassword, WebDriver driver){
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(createCredentialButton))
                .click();
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(this.credentialURL))
                .clear();
        this.credentialURL.sendKeys(credentialURL);
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(this.credentialUsername))
                .clear();
        this.credentialUsername.sendKeys(credentialUsename);
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(this.credentialPassword))
                .clear();
        this.credentialPassword.sendKeys(credentialPassword);
        credentialSubmitButton.click();
    }

    public void editCredential(String credentialURL, String credentialUsename, String credentialPassword, WebDriver driver){
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(editCredentialButton))
                .click();
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(this.credentialURL))
                .clear();
        this.credentialURL.sendKeys(credentialURL);
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(this.credentialUsername))
                .clear();
        this.credentialUsername.sendKeys(credentialUsename);
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(this.credentialPassword))
                .clear();
        this.credentialPassword.sendKeys(credentialPassword);
        credentialSubmitButton.click();
    }

    public Credential getFirstCredential(WebDriver driver){
        try {
            new WebDriverWait(driver, 10)
                    .until(ExpectedConditions.visibilityOfElementLocated(By.id("credentialUsernameShow")));
            return new Credential(null, credentialURLShow.getText(), credentialUsernameShow.getText(), null, null, null);
        } catch (org.openqa.selenium.TimeoutException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void deleteCredential(){
        deleteCredentialButton.click();
    }

    public void logout(){
        logoutButton.click();
    }


}
