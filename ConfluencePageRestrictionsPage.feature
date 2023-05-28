Feature: Confluence page restrictions feature

  Scenario: Verify User able to modify existing restriction of the Confluence page
    
  Given User login at Atlassian Confluence page "https://sbhagi.atlassian.net/" 
  And search for existing page “Testing Document” in the confluence searchbar
  When User click on Lock icon and check for existing applied restriction on the page
  Then User choose to modify to other restriction “Only specific people can view or edit”, And Hit Apply
  And choose Individual or Default user or Group to ADD and control restriction for “view/edit” on his page  
  When Hit Apply
  Then Verify whether User able to modify exist restriction to new restriction on the page successfully
  And User able to reset to default restriction as applied for the page
  
