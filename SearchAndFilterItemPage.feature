Feature: EBay Search  

Scenario:  Access a Product via category after applying multiple filters
   Given User is on ebay homepage "https://www.ebay.com/"
   When User click on Electronics
   And click on CellPhones and Accessories under ShopBy category
   Then click on CellPhones and Smartphones
   And click on See All at Shop By Brand
   And User enters range for price filter Price_from, Price_to 
      |    10   |  100   |
   Then User enters specified filters such as Screen Size and Item location
   And hit on Apply button
   Then verify User selected filters are applied 
   And User able to access product via category with the applied filters 
    

Scenario: Access a Product via Search
     Given User is on ebay homepage "https://www.ebay.com/" 
     When User enter search_keyword as "MacBook" in searchbar
     And User select the search category to "Computers/Tablets & Networking"
     Then Hit on Search button
     When verify page load completely
     Then verify first result name matches with the given search string
     






