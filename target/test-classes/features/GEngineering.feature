Feature: Growth Engineering Test


Scenario: Check all menu links are working in top navigation
Given navigating to growth engineering
When getting all the links present in top navigation
Then all links should work

Scenario: form validation of contact page
Given navigating to growth engineering
When user fill all the detils in contact form required fields
Then check that the form validation is working


