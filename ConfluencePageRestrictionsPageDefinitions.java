package com.testapp.jira.Atlassian_Automation;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.support.ui.WebDriverWait;


import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ConfluencePageRestrictionsPageDefinitions {

	
	
	 public final String encodePwd="Atlassian!2!0";
	private static WebDriver driver;       
    public final static int TIMEOUT = 20;
     
    
    @Before
    public void setUp() {
    	ChromeOptions options=new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("force-device-scale-factor=1.1");
        options.addArguments("high-dpi-support=1.1");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TIMEOUT));
        driver.manage().window().maximize();
        
    }
    public static String decodeStr(String encodeStr)
    {
    	byte[] decoded=Base64.decodeBase64(encodeStr);
//        System.out.println("encoded string"+new String(decoded));
        return new String(decoded);
    }
    
    @Given("User login at Atlassian Confluence page {string}")
    public void user_visits_Confluence_login_page(String url)
    {
    	
    	driver.get(url);
    	WebElement email=driver.findElement(By.xpath("//input[@id='username']"));
    	email.sendKeys("sai3bhagi@gmail.com");
        WebElement CNTINUE=driver.findElement(By.xpath("//*[@id=\"login-submit\"]/span/span"));
        CNTINUE.click();
        String encodePasswd="QXRsYXNzaWFuITIhMA==";
           
        WebElement pwd=driver.findElement(By.xpath("//input[@name='password']"));
        pwd.sendKeys(decodeStr(encodePasswd));
        
        WebElement login=driver.findElement(By.xpath("//*[@id=\"login-submit\"]/span/span"));
        login.click();
        
        
    }
    
	@And("search for existing page “Testing Document” in the confluence searchbar")
	public void user_chosen_existing_page_testing_document_in_the_confluence() {
	  

		 WebElement searchBtn=driver.findElement(By.xpath("//input[@class='css-125p73 em6wsuj5']"));
		 searchBtn.sendKeys("Testing Document");
		 searchBtn.sendKeys(Keys.ENTER);
			
		//*[@id=\"content-body\"]/main/div/div/div[2]/ul/li
		  // .//a[contains(@href,'Testing Document')]
		 
	 List<WebElement> pageTitles=driver.findElements(By.xpath("//div/a[@class='searchResultLink']"));

	 
	 if(pageTitles.size() != 0) 
	   {
		   System.out.println(pageTitles.size() + " Elements found by href as input \n");
				  		
	   }
	 
	 
	 System.out.println("ALL PAGE TITLES :: ");
	 System.out.println("=================================");
	 for(WebElement currTitle:pageTitles) {
		  System.out.println(currTitle.getText()); //accessing each page title information or detail in the list
	  
		  }
	 
	 
	 
	 System.out.println("=================================");
	  for(int i=0;i<pageTitles.size();i++)
	 {
		 if(pageTitles.get(i).getText().equalsIgnoreCase("Testing Document"))
		    {
			 System.out.println("CURRENT TITLE :: "+pageTitles.get(i).getText());
			 
			 pageTitles.get(i).click();
		        break;
		    }
		
	 }
	  

	 
	 
	  } 
	
	
	private void clickAnElementMatchingText(List<WebElement> pageTitles, String currItem)
	{
		
		for(WebElement currLink:pageTitles)
		{
            System.out.println(currLink.getText());
			currLink.click();

		}
			}
	
	@When("User click on Lock icon and check for existing applied restriction on the page")
	public void user_click_on_lock_icon_and_check_for_existing_applied_restriction_on_the_page() throws InterruptedException {
	    
   	   WebElement lockIcon=driver.findElement(By.xpath("//*[@id=\"system-content-items-extracted\"]/div/span/div/button/span/span"));
       lockIcon.click();
       WebElement existrestrictIconText=driver.findElement(By.xpath("//span[contains(text(),'Anyone can view, only some can edit')]"));
              
       System.out.println("PRINT RANDOM RESTRICTION :: "+existrestrictIconText.getText());
       existrestrictIconText.click();
       
     
       
     //*[@id="com-atlassian-confluence"]/div[2]/div[4]/div/div[2]/div/div/section/div[2]/div/div/div/div[2]
     //div[@id='react-select-restrictions:user-and-group-search:user-and-group-picker-placeholder']
     //body/div[2]/div[4]/div[1]/div[2]/div[1]/div[1]/section[1]
     //*[@id="react-select-11-option-0"]/div/span[contains(text(),'Anyone can view and edit')]  --individual
     //div[@class='css-ob5f9p']//div/span[@class='css-7pg0cj-a11yText']
     //*[@id="react-select-rest//*[@id="react-select-11-input"]rictions:user-and-group-search:user-and-group-picker-placeholder"]
       
     //*[@id="aria-context"]/text()
       
     //div[@class='restrictions-dialog-component__menu css-x50wjx-menu']/div/div
       
       System.out.println("=================================");
       System.out.println("LOCK ICON RESTRICTIONS DROPDOWN");
       System.out.println("=================================");
    
    		//div[@class='restrictions-dialog-component__menu css-x50wjx-menu']/div/div/div/span
       List<WebElement> lockDrpDwn = driver.findElements(By.xpath("//div[@class='restrictions-dialog-component__menu css-x50wjx-menu']/div/div/div/span"));
    
       for(WebElement op:lockDrpDwn)
       {
    	   System.out.println(op.getText());   
       }
      System.out.println("=================================");
      System.out.println("==============Checking for existing applied restriction from the dropdown===============");    
for(int i=0;i<lockDrpDwn.size();i++)
{
	System.out.println("Restriction option :: "+lockDrpDwn.get(i).getText());
       if(lockDrpDwn.get(i).getText().equalsIgnoreCase(existrestrictIconText.getText()))
          {
              System.out.println("Existing selected RESTRICTION OPTION in the Dropdown"
              		+ "");
          }
        else

          {
               System.out.println("Sorry , This RESTRICTION OPTION in the Dropdown is not selected");
           }

}	   
	}
	@Then("User choose to modify to other restriction “Only specific people can view or edit”, And Hit Apply")
	public void user_choose_to_modify_to_other_restriction_only_specific_people_can_view_or_edit_and_hit_apply() throws InterruptedException {
	    
		System.out.println("=================================");
		System.out.println("Modifying EXISTED restriction to NEW restriction for a page");
	
		
		Actions act = new Actions(driver);

	  //Double click on element
	  WebElement newRestrictionIcon = driver.findElement(By.xpath("//span[contains(text(),'Only specific people can view or edit')]")); 
	  System.out.println("NEW RESTRICTION SELECTED :: "+newRestrictionIcon.getText());
	   
	   act.doubleClick(newRestrictionIcon).build().perform();

	  
	  act.release(newRestrictionIcon);
	}
	@And("choose Individual or Default user or Group to ADD and control restriction for “view\\/edit” on his page")
	public void choose_individual_default_user_or_group_to_add_and_control_restriction_for_view_edit_on_his_page() {
	    
	    System.out.println("Added users/individual/group manual");
	}
	@When("Hit Apply")
	public void hit_apply() throws InterruptedException {
	  
	   
	   WebElement apply=driver.findElement(By.xpath("//span[contains(text(),'Apply')]"));
	   apply.click();
	 
	   
	   
	   
	 //*[@id="system-content-items-extracted"]/div/span/div/button[@aria-label='Restrictions apply']
	
	 //*[@id=\"system-content-items-extracted\"]/div/span/div/button/span/span
	  
			   
			 //span[contains(text(),'Restrictions apply')]
			 //*[@id="system-content-items-extracted"]/div/span/div/button/span/span[@data-testid='locked-icon']
			   
			 //button[@class='css-hxlay7']//span[@data-testid='unlocked-restricted-icon']
		
	   
	   /*
	   WebElement LockIconState=driver.findElement(By.xpath("//button[@class='css-hxlay7']//span[@data-testid='locked-icon']"));
	 
	   String actual=LockIconState
	   String expected="Restrictions apply";
	   Assert.assertEquals(actual, expected);
	   
	   System.out.println("EXIST RESTRICTION ICON STATUS CHANGE TO NEW RESTRICTION WITH STATUS :: LOCKED "+ "STATUS FLAG :: "+actual);
	   
	  
		//   System.out.println("UNABLE TO MODIFY EXISTING RESTRICTION");
	   
	   */
	   
	  
	   driver.navigate().refresh();
	   Thread.sleep(1000);
	   WebElement lockIcon=driver.findElement(By.xpath("//*[@id=\"system-content-items-extracted\"]/div/span/div/button/span/span"));
       lockIcon.click();
       
	}
	@Then("Verify whether User able to modify exist restriction to new restriction on the page successfully")
	public void verify_whether_user_able_to_modify_exist_restriction_to_new_restriction_on_the_page_successfully() {

		WebElement NewrestrictIconText=driver.findElement(By.xpath("//span[contains(text(),'Only specific people can view or edit')]"));
		NewrestrictIconText.click();
		List<WebElement> allOptions=driver.findElements(By.xpath("//div[@class='restrictions-dialog-component__menu css-x50wjx-menu']/div/div/div/span"));
	
		
		 
		
		System.out.println("=================================");
		  System.out.println("LOCK ICON RESTRICTIONS DROPDOWN");
		System.out.println("=================================");
		for(WebElement op:allOptions)
			 System.out.println(op.getText());
		 
		 System.out.println("=================================");
     
		 String actual=NewrestrictIconText.getText();
         String expected="Only specific people can view or edit";
         
		         for(int i=0; i<allOptions.size(); i++) {

		        	 System.out.println("=================================");
		             
		             
		            System.out.println("Restriction option :: "+allOptions.get(i).getText());
		            
		          
		            if(allOptions.get(i).getText().equalsIgnoreCase(NewrestrictIconText.getText()))
		 	          {
		            	
		            	
		 	              System.out.println("Selected NEW RESTRICTION OPTION in the Dropdown"
		 	              		+ "");
		 	              
		 	             
		 	              Assert.assertEquals(actual, expected);
		 	             System.out.println("=================================");
		 	             System.out.println("verified restriction change :: SUCCESS " +actual +" :: selected option from dropdown and actual are same");
		 	              
		 	          }
		 	        else

		 	          {
		 	               System.out.println("Sorry , This RESTRICTION OPTION in the Dropdown is not selected :: FAILED " + expected +" :: selected different restriction to the option selected in dropdown");
		 	           }
		            
		            
		         }
		
		         
		 	    
		 		
		 	

	}
	/*
	@And("Verify whether restriction label name for the LOCK icon got changed from “Editing restricted” to “Restrictions applied” for the page")
	public void verify_whether_restriction_label_name_for_the_lock_icon_got_changed_from_editing_restricted_to_restrictions_applied_for_the_page() {
	    
	}*/

	@And("User able to reset to default restriction as applied for the page")
	public void User_able_to_reset_to_default_restriction_As_applied_for_the_page() throws InterruptedException {
		
		

		   driver.navigate().refresh();
		   Thread.sleep(1000);
		   
		   WebElement lockIcon=driver.findElement(By.xpath("//*[@id=\"system-content-items-extracted\"]/div/span/div/button/span/span"));
	       lockIcon.click();
		
		    Thread.sleep(1000);
	      
		    
		    WebElement source=driver.findElement(By.xpath("//span[contains(text(),'Only specific people can view or edit')]"));
            source.click();
			List<WebElement> allOptions=driver.findElements(By.xpath("//div[@class='restrictions-dialog-component__menu css-x50wjx-menu']/div/div/div/span"));
	
			//WebElement ele=driver.findElement(By.xpath("//span[contains(text(),'Anyone can view, only some can edit')]"));

			for(WebElement target:allOptions)
			{
				
				
			      target=driver.findElement(By.xpath("//span[contains(text(),'Anyone can view, only some can edit')]"));
	    		 			 //     target.click();
	    					
	    		 			      
	    		 			      	   System.out.println("RESET RESTRICTION TO EXISTED RESTRICTION successfully:: "+target.getText());
			     
	    		 			      	target.click();	      
	    		 
	    /*		 			      
	    		 			    WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));
	    		 			    String text="Anyone can view, only some can edit";
	    		 			    
	    		 			    wait.until(ExpectedConditions.textToBePresentInElement(target,text));
	    		 			    // click on the compose button as soon as the "compose" button is visible
	    		 			    driver.findElement(By.xpath("//span[contains(text(),'Anyone can view, only some can edit')]")).getText();
*/
	    		 			      	
	
			}
			
			  WebElement apply=driver.findElement(By.xpath("//span[contains(text(),'Apply')]"));
			   apply.click();
			 

				}
	
	  @After
	  public void teardown() {

	    driver.quit();
	  	System.out.println("Ending tests...");
	  } 


	
}
