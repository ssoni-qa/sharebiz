package crossBrowserTesting;
/*
 * Run from the xml suit file
 */



import org.testng.annotations.Test;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class CrossBrowserTest {

  private String username = "dev%40aramisinteractive.com";
  private String api_key = "ube96e1b5c957d36";  
  
  public  WebDriver driver;  

  @BeforeClass
  @org.testng.annotations.Parameters(value={"os", "browser"})
  public void setUp(String os,String browser) throws Exception {
    DesiredCapabilities capability = new DesiredCapabilities();
    capability.setCapability("os_api_name", os);
    capability.setCapability("browser_api_name", browser);
    capability.setCapability("name", "TestNG-Parallel");
    capability.setCapability("record_video", "true");
    capability.setCapability("record_network", "true");
    driver = new RemoteWebDriver(
      new URL("http://" + username + ":" + api_key + "@hub.crossbrowsertesting.com:80/wd/hub"),
      capability);
  }  
  public String takeSnapshot(String seleniumTestId) throws UnirestException {
      /*
       * Takes a snapshot of the screen for the specified test.
       * The output of this function can be used as a parameter for setDescription()
       */
      HttpResponse<JsonNode> response = Unirest.post("http://crossbrowsertesting.com/api/v3/selenium/{seleniumTestId}/snapshots")
              .basicAuth(username, api_key)
              .routeParam("seleniumTestId", seleniumTestId)
              .asJson(); 
      // grab out the snapshot "hash" from the response
      String snapshotHash = (String) response.getBody().getObject().get("hash");
      
      return snapshotHash;
  }
  
  @AfterClass  
  public void tearDown() throws Exception {  
    driver.quit();  
  }
}