package testSuite;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import crossBrowserTesting.CrossBrowserTest;

public class NewTest extends CrossBrowserTest {
	@Test
	public void testSimple() throws Exception {

		System.out.println("Running  Test ");
		driver.get("http://www.google.com");
		System.out.println("Page title is: " + driver.getTitle());
		Assert.assertEquals("Google", driver.getTitle());
		WebElement element = driver.findElement(By.name("q"));
		element.sendKeys("CrossBrowserTesting.com");
		element.submit();

	}
}
