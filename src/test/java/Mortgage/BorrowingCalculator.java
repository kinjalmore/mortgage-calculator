package Mortgage;


import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BorrowingCalculator extends base{

	@Test
	public void calculateBorrow() throws IOException {
		
		// Find the element that's xpath attribute and click on Borrowing calculator link
		driver.findElement(By.xpath(".//*[@id='leftnav']/li[11]/ul/li[2]/a[2]")).click();
		
		//Click on Joint checkbox from 'Your application type'
		driver.findElement(By.id("simpleJointType")).click();
		Assert.assertTrue(driver.findElement(By.id("simpleJointType")).isDisplayed());
		
	    // Find the element and enter amount in Annual household income field.
		driver.findElement(By.id("Income_AnnualHousehold")).sendKeys("70000");
		
    	// Find the element and click on plus button to add one dependent children under 18 years of age:*
		driver.findElement(By.cssSelector("[class='js-btnPlusMinus numberToggle js-add'][data-btnplusminfor='LoanDetails_Dependants']")).click();
		
		// Find the element and click on plus button to add one Vehicles
		driver.findElement(By.cssSelector("[class='js-btnPlusMinus numberToggle js-add'][data-btnplusminfor='LoanDetails_Vehicles']")).click();

		//Clicking on Calculator button
        driver.findElement(By.id("baseSubmit")).click();
		
		//Wait for element to be visible 
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".calculatedResults")));
				
		//Verifying that Calculated result panel is displayed after entering all details 
	    boolean actualString = driver.findElement(By.className("calculatedResults")).isDisplayed();
	    Assert.assertTrue(actualString, "Calculated result panel is displayed"); 
	}

}
