package com.test.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
/*
@CucumberOptions(tags = "", features = {"src/test/resources/features/SearchAndFilterItemPage.feature"}, glue = {"com.testapp.ebay.eBay_Automation"},
                 plugin = {})

*/
@CucumberOptions(tags = "", features = {"src/test/resources/features/ConfluencePageRestrictionsPage.feature"}, glue = {"com.testapp.jira.Atlassian_Automation"},
plugin = {}, dryRun=false)

//implemented Inheritance feature - OOP
public class CucumberRunnerTests extends AbstractTestNGCucumberTests {
    
}