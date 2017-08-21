package Mortgage;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;


public class RepaymentCalculator extends base{

	@Test
	public void homeloancalculator() throws IOException {
		
		// Find the element that's xpath attribute and click on Repayments calculator link
		driver.findElement(By.xpath(".//*[@id='leftnav']/li[11]/ul/li[1]/a[2]")).click();
		
	    // Find the element and enter amount in Loan Amount field.
	    driver.findElement(By.id("LoanAmount")).sendKeys("500,000");
	    

		//Mouse over and click on View ANZ rate and selecting current ANZ rate
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.cssSelector(".selector.rates"))).click().build().perform();
		driver.findElement(By.xpath(".//*[@class='dropdown']/ul/li[4]")).click();
		
		//Select numbers of year to pay loan from loan length dropdown
		Select s = new Select(driver.findElement(By.id("LoanLength")));
		s.selectByValue("15");
		
		
		//Clicking on Calculator button
		driver.findElement(By.cssSelector(".btn.btnPrimary.large.spinning")).submit();
	   
		
		//Getting value of entered loan amount to compare with actual result 		
		WebElement  EnterAmountText = driver.findElement(By.id("LoanAmount"));
		String expectedText = EnterAmountText.getAttribute("value");

		//Wait for element to be visible 
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("js-adjustPrincipalAmountResult")));
				
				
		//Getting printed value of Loan amount text and compare it with entered amount
		WebElement  text = driver.findElement(By.id("js-adjustPrincipalAmountResult"));
		String Actualtext= text.getText().replaceAll("[$,]*","");
		Assert.assertEquals(Actualtext, expectedText);
	}
}
