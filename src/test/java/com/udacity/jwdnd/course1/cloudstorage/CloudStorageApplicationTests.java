package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.page_objects.HomePage;
import com.udacity.jwdnd.course1.cloudstorage.page_objects.LoginPage;
import com.udacity.jwdnd.course1.cloudstorage.page_objects.SignupPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;



@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CloudStorageApplicationTests {

	String firstName = "testFirstName";
	String lastName = "testLastName";
	String username = "testUsername";
	String password = "testPassword";
	String noteTitle = "testNoteTitle";
	String noteDescription = "testNoteDescription";
	String noteTitleEdited = "testNoteTitleEdited";
	String noteDescriptionEdited = "testNoteDescriptionEdited";
	String credentialUsername = "credentialUsername";
	String credentialPassword = "credentialPassword";
	String credentialURL = "credentialURL";
	String credentialUsernameEdited = "credentialUsernameEdited";


	@LocalServerPort
	private int port;

	private WebDriver driver;

	@BeforeAll
	static void beforeAll() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	public void beforeEach() {
		this.driver = new ChromeDriver();
	}

	@AfterEach
	public void afterEach() {
		if (this.driver != null) {
			driver.quit();
		}
	}

	@Test
	@Order(1)
	public void getLoginPage() {
		driver.get("http://localhost:" + this.port + "/login");
		Assertions.assertEquals("Login", driver.getTitle());
	}
	@Test
	@Order(2)
	public void getHomePageUnauthorized() {
		driver.get("http://localhost:" + this.port + "/home");
		Assertions.assertEquals("Login", driver.getTitle());
	}

	@Test
	@Order(3)
	public void signinTest() {
		driver.get("http://localhost:" + this.port + "/signup");
		SignupPage signupPage = new SignupPage(driver);
		signupPage.signup(firstName, lastName, username, password);
		signupPage.submit();
		signupPage.login();
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(username, password);
		HomePage homePage = new HomePage(driver);
		Assertions.assertEquals("Home", driver.getTitle());
		homePage.logout();
		driver.get("http://localhost:" + this.port + "/home");
		Assertions.assertEquals("Login", driver.getTitle());
	}

	@Test
	@Order(4)
	public void crudNote() {
		driver.get("http://localhost:" + this.port + "/login");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(username, password);
		HomePage homePage = new HomePage(driver);
		homePage.navigateToNotes();
		//	sleep(5000);

		homePage.createNote(noteTitle,noteDescription, driver);
		Note testNote = homePage.getFirstNote(driver);

		Assertions.assertEquals(noteTitle, testNote.getNotetitle());
		Assertions.assertEquals(noteDescription, testNote.getNotedescription());

		homePage.editNote(noteTitleEdited, noteDescriptionEdited, driver);
		testNote = homePage.getFirstNote(driver);

		Assertions.assertEquals(noteTitleEdited, testNote.getNotetitle());
		Assertions.assertEquals(noteDescriptionEdited, testNote.getNotedescription());

		homePage.deleteNote();
		testNote = homePage.getFirstNote(driver);
		Assertions.assertEquals(null, testNote);
	}

	@Test
	@Order(5)
	public void crudCredential() throws InterruptedException {
		driver.get("http://localhost:" + this.port + "/login");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(username, password);
		HomePage homePage = new HomePage(driver);
		homePage.navigateToCredentials();

		homePage.createCredential(credentialURL,credentialUsername, credentialPassword, driver);
		Credential testCredential = homePage.getFirstCredential(driver);

		Assertions.assertEquals(credentialURL, testCredential.getUrl());
		Assertions.assertEquals(credentialUsername, testCredential.getUsername());

		homePage.editCredential(credentialURL,credentialUsernameEdited, credentialPassword, driver);
		testCredential = homePage.getFirstCredential(driver);

		Assertions.assertEquals(credentialUsernameEdited, testCredential.getUsername());

		homePage.deleteCredential();
		testCredential = homePage.getFirstCredential(driver);
		Assertions.assertEquals(null, testCredential);
	}

}
