package test;
import io.appium.java_client.AppiumDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.URL;
import java.util.List;

public class ContactTest {
	private AppiumDriver driver;

	@Before
	public void setUp() throws Exception {
		// Setup path
		File classpathRoot = new File(System.getProperty("user.dir"));
		File appDir = new File(classpathRoot, "apps");
		File app = new File(appDir, "ContactManager.apk");

		// Setup capabilities
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("deviceName", "Android Emulator");

		// Setup Android platform version
		capabilities.setCapability("platformVersion", "4.3.1");
		// Setup apk path
		capabilities.setCapability("app", app.getAbsolutePath());

		// Setup app package and class name
		capabilities.setCapability("appPackage",
				"com.example.android.contactmanager");
		capabilities.setCapability("appActivity", ".ContactManager");

		// Initialization
		driver = new AppiumDriver(new URL("http://127.0.0.1:4723/wd/hub"),
				capabilities);
	}

	@Test
	public void addContact() {
		WebElement el = driver.findElement(By.name("Add Contact"));
		el.click();
		List<WebElement> textFieldsList = driver
				.findElementsByClassName("android.widget.EditText");
		textFieldsList.get(0).sendKeys("Example Name");
		textFieldsList.get(2).sendKeys("ExampleMail@example.com");
		driver.swipe(100, 500, 100, 100, 2);
		driver.findElementByName("Save").click();
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}
}