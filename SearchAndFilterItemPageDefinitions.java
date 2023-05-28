package com.testapp.ebay.eBay_Automation;


import org.openqa.selenium.support.ui.Select;
/**
 * Unit test for simple App.
 */
import org.openqa.selenium.Keys;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.openqa.selenium.JavascriptExecutor;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchAndFilterItemPageDefinitions { 
     
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
    
    @Given("User is on ebay homepage {string}")
    public void homepageTest(String url) {
         
        driver.get(url);
    }
    
    
    @When("User click on Electronics")
    public void user_click_on_Electronics()
	{
    	driver.findElement(By.linkText("Electronics")).click();
	}
    
    @And("click on CellPhones and Accessories under ShopBy category")
	public void click_on_CellPhones_and_Accessories_under_ShopBy_category()
	{
    	driver.findElement(By.xpath("//span[contains(text(),'Cell Phones & Accessories')]")).click();
	
		//to perform Scroll on application using Selenium Jse
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,350)", "");
		
	}
    @Then("click on CellPhones and Smartphones")
	public void click_on_CellPhones_and_Smartphones()
	{
    	driver.findElement(By.xpath("//a[contains(text(),'Cell Phones & Smartphones')]")).click();
	}
  
    @And("click on See All at Shop By Brand")
	public void click_on_SeeAll_at_Shop_By_Brand()
	{
    	driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div[1]/section[1]/div[1]/div[2]/button/span[1]")).click();
			
	}
    
    @Then("User enters specified filters such as Screen Size and Item location")
   	public void user_enters_filter_values()throws IOException, InterruptedException
   	{
    	
        	driver.findElement(By.xpath("//*[@id=\"c3-mainPanel-Screen%20Size\"]")).click();
        	WebElement check_right_fieldset = driver.findElement(By.className("x-overlay-sub-panel__aspect-fieldset"));
        	
        	System.out.println(check_right_fieldset.getText()); //printing screen size checklist items
        	
        	
        	
        	Actions action = new Actions(driver);
     //   	(//*[@id="c3-subPanel"]//span[@class='checkbox__icon'])[i]
        	WebElement checkItem = driver.findElement(By.xpath("(//*[@id=\"c3-subPanel\"]//span[@class='checkbox__icon'])[2]"));

        	action.click(checkItem).build().perform();
        	action.release(checkItem).build().perform();
        	

        	WebElement popup_left_panel=driver.findElement(By.xpath("//div[@id='c3-mainPanel']"));
     	      
     	      ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(false);", popup_left_panel);
           
                 //selecting Item loc filter in menu dropdown on popup
     	      WebElement selectMenuItemloc=driver.findElement(By.id("c3-mainPanel-location"));
     	      selectMenuItemloc.click();
     	     
     	    
     	 Thread.sleep(2000);
     	    
     	 //TEST INPUT: single selection of radio button select verified for Item Location Filter 
     	/*  
     	JavascriptExecutor js = (JavascriptExecutor) driver;

     	// Scroll inside web element vertically (e.g. 100 pixel)
     	js.executeScript("arguments[0].scrollTop = arguments[1];",driver.findElement(By.id("c3-subPanel")), 100); 
     	     

     	
     	WebElement check_radio_option = driver.findElement(By.xpath("//input[@value='North America']"));
     	check_radio_option.click();
     	
     	System.out.println("Selected Item location option :::: "+check_radio_option.getText());
     	
*/     	
     
   //ITEM LOC size static known to user, Used array to hold limited #no. of radio button values of item location
     	String[] array={"Default","US Only","North America","Worldwide"};
     	
       	for(int i=0;i<array.length;i++)
       	{
       		
            // Printing the values
            System.out.print(array[i]+"\n");
       		
            //select random values 
       		WebElement checkradio_option = driver.findElement(By.xpath("//input[@value="+"'"+array[i]+"'"+"]"));
         	checkradio_option.click();
         	
         	//break;
         	
         	List<String> lst=Arrays.asList(array);
            for(int j=0;j<lst.size();j++)
            {
            	
            	boolean selectState = checkradio_option.isSelected();
    			
   	        	//checking whether radio button state based on element if selected or not selected
   	        	if(selectState==false) {
   	        		System.out.println("radio button Item location  selected");
   	        		
   	        	}else
   	        	{
   	        		System.out.println("ITEM LOCATION -> RADIO BUTTON STATE ::"+selectState+"radio button Item location  deselected");
            }
       	}
     
           	}  	

     	 Thread.sleep(2000);      	
   	}
  @And("User enters range for price filter Price_from, Price_to") 
  public void user_enters_price_filter_range(DataTable range)
  {
	  
	   WebElement selectPrice=driver.findElement(By.id("c3-mainPanel-price"));
       selectPrice.click();

     //passing test data values for filter Price range using datatable
	  List<List<String>> data=range.asLists(String.class);
	  WebElement x=driver.findElement(By.xpath("//input[@class=\"x-textrange__input x-textrange__input--from\"]"));
	  x.sendKeys(data.get(0).get(0));
	  WebElement y= driver.findElement(By.xpath("//input[@class=\"x-textrange__input x-textrange__input--to\"]"));
	  y.sendKeys(data.get(0).get(1));
	  
  }
    
  
  @And("hit on Apply button")
  public void hitApply()
  {
	  driver.findElement(By.xpath("//button[@class='x-overlay-footer__apply-btn btn btn--primary']")).click();
  }
  
  @Then("verify User selected filters are applied")
  public void verifyFiltersAsSelected()throws InterruptedException
  {

	  //verifying current URL state of webpage complete 
	  WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));  
	  wait.until(webDriver -> ((JavascriptExecutor)webDriver).executeScript("return document.readyState").equals("complete"));

	  driver.get("https://www.ebay.com/b/4-5-4-9-Inch-Cell-Phones-Smartphones/9355/bn_600664?rt=nc&mag=1&_udlo=10&_udhi=100&LH_PrefLoc=2");
	 // wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='s0-41']")))).click();
	
	  WebElement selectedFilters=driver.findElement(By.xpath("//div[@id='s0-41']"));
	  Thread.sleep(TIMEOUT);
	  Actions actions = new Actions(driver);
	  actions.moveByOffset(0, 100).click(selectedFilters).build().perform();
	  selectedFilters.getText();
	  
      //xpath of "applied filters" button on category  	  
	  WebElement button=driver.findElement(By.xpath("(//button[@class='x-flyout__button'])[1]"));
 	  button.click();
	
 	String actual= button.getText();
	System.out.println("checking text of \"applied filters\" button");
	System.out.println("ACTUAL ::"+actual);
	
	
	
	//div[@class='x-flyout__content flyout-notice flyout-notice--information flyout-notice--top-right']/ul
//	(//div[@class='x-flyout__content flyout-notice flyout-notice--information flyout-notice--top-right'])[4]
	
	Thread.sleep(TIMEOUT);
	WebElement selected_Filters= driver.findElement(By.xpath("(//div[@class='x-flyout__content flyout-notice flyout-notice--information flyout-notice--top-right'])[1]/ul"));
	
	//xpath for selected or applied filters sub_list present inside the applied filters button Dropdown widget(^)
	List<WebElement> selectedFiltersSubList=driver.findElements(By.tagName("//li[@class='brm__aspect-item brm__aspect-item--applied']//span[@class='brm__item-label']"));
	
	
	 	for(int i=0;i<selectedFiltersSubList.size();i++)
	{
	if ((selectedFiltersSubList.get(i).getText().equals("Screen Size: 4.5 - 4.9 in")&&(selectedFiltersSubList.get(i).getText().equals("Price: $10.00 to $100.00"))&&selectedFiltersSubList.get(i).getText().equals("Item Location: Worldwide"))) {
		System.out.println("list elements::" +selectedFiltersSubList);
		System.out.println("Screen Size filter applied");
	    System.out.println("Price filter applied");
	    System.out.println("Item Location filter applied");
	   }
	}
	
//getting SIZE of the applied filters button "selectedFiltersSubList" or its inner dropdown list(or widget^) holding user applied filters @UI in a list
	List<WebElement> elelist=driver.findElements(By.xpath("//li[@class='brm__aspect-item brm__aspect-item--applied']//span[@class='brm__item-label']"));
	System.out.println("size of selectedFiltersSubList :: "+elelist.size());
	for(WebElement e:elelist)
	System.out.println(elelist);  //printing SIZE of Applied filters button sublist
	
	//type cast int to string
	 String expected=String.valueOf(elelist.size());
	 
	 System.out.println("EXPECTED::"+expected);
	 
	 //verifying values of ACTUAL versus EXPECTED :: presence of count(or size) of applied filters
	  if(actual.contains(expected))
	  {
		  System.out.println("Applied fiters count is same, Expected::"+expected +", Actual::" +actual);
	  }
	// Assert.assertEquals(actual, expected);
	 
	  
  }
  @And("User able to access product via category with the applied filters")
  public void accessSelectedProductcategory()
  {
	  //print product search results of filtered category and display list, doing validations on result data against occurrence/presence of filters for an item/product
    
	  System.out.println("Displaying & accessing products list by category based on applied filters");
	  System.out.println("===================================================================");
	
	  List<WebElement> resultsList=driver.findElements(By.xpath("//body[1]/div[4]/div[4]/div[3]/section[1]/ul[1]/li"));
      for(WebElement el:resultsList)
      {
    	  System.out.println(el.getText()); //accessing each product information or detail in the list
      System.out.println(resultsList);
      }
  }
  
@When("User enter search_keyword as {string} in searchbar")
public void input_search_keyword(String search_keyword)
{
	driver.findElement(By.xpath("//input[@id='gh-ac']")).sendKeys("MacBook");
}

  @When("User select the search category to {string}")
  public void user_select_the_search_category_to(String category) {
	  
	 Select category_Drpdown = new Select(driver.findElement(By.name("_sacat")));
	  category_Drpdown.selectByVisibleText("Computers/Tablets & Networking");
  }
  
  @Then("Hit on Search button")
  public void hit_on_search_button() {
     driver.findElement(By.xpath("//*[@value=\"Search\"]")).click();
  }
  @When("verify page load completely")
  public void verify_pageLoad() {
 
	  String url = "https://www.ebay.com/sch/i.html?_from=R40&_trksid=p2380057.m570.l1313&_nkw=MacBook&_sacat=58058";
      driver.get(url);
      
      JavascriptExecutor j = (JavascriptExecutor) driver; //jse for returning value
      j.executeScript("return document.readyState")
      .toString().equals("complete");
      // get the current URL
      String s = driver.getCurrentUrl();
      // checking condition if the URL is loaded
      if (s.equals(url)) {
         System.out.println("Page Loaded Success");
         System.out.println("Current Url: " + s);
      }
      else {
         System.out.println("Page did not load");
      }
   //   driver.quit();
     
  }
  
  @Then("verify first result name matches with the given search string")
  public static void checkAbsentSearchResult() throws IOException{   
	System.out.println("===================================================================================================");
	System.out.println("Display of Products having \"MacBook\" match based on selected category ::");
  
  //loop for pagination
for(int i=0;i<=9;i++)
{
	if(i>0)
	{
		//typecase variable, click on pagination
		WebElement pages=driver.findElement(By.linkText(String.valueOf(i+1)));
		pages.click();
	
	}
	
	/*
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(40));	  
	  wait.until((ExpectedConditions.visibilityOfElementLocated(By.xpath("(//li[@class='s-item s-item__pl-on-bottom']//div[@class='s-item__title'])[1]"))));
*/
	  
	
	//printing entire search items as list having search keyword matches the given string
	 List<WebElement> allSearchResults = driver
             .findElements(By
                          .xpath("//li[@class='s-item s-item__pl-on-bottom']//div[@class='s-item__title']"));

	 //print Products list
	 for(WebElement el:allSearchResults)
		 System.out.println(el.getText());
	 
	

System.out.println("===================================================================================================");
	 

	 for(int k=0;k<allSearchResults.size();k++) {
		 int index=0;
		 String products=allSearchResults.get(k).getText();
		// String product_1=allSearchResults.get(0).getText();
		 System.out.println(products+" :: presnt at index :: "+k +"have a match at pagination index "+i);
		 
	//	 System.out.println("item at index1 ::"+product_1);
		 
	 //validate list 1st index content having a search string in match 
		 String searchWord="macbook";
		 
	boolean resultsFound = false;
	 if(allSearchResults.get(0).getText().toLowerCase().contains(searchWord))

//	 if(allSearchResults.get(0).getText().toLowerCase().contains("macbook"))
	 {
		 System.out.println("Item at 0 index :: ");	
		 System.out.println(allSearchResults.get(0).getText());	 
		 
		 System.out.println("given search string present at 1st Index");
		 resultsFound = true;
         break;
		 
	 }
	 }
     
}
  }
  @After
  public void teardown() {

    driver.quit();
  	System.out.println("Ending tests...");
  } 
}