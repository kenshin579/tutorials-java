package kr.pe.advenoh;

import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ScreenshotRuleTest {
	static WebDriver driver = new FirefoxDriver();

	@Rule
	public ScreenshotRule screenshotRule = new ScreenshotRule();

	@Test
	public void testScreenShot() {
		driver.get("http://www.teknosa.com");
		driver.findElement(By.id("asdasda"));
	}
}
