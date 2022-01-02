Feature:  whats App feature validations
  As a user I should be able to register and send file to new or existing user.

@whatsAppChat
  Scenario: Register and send random file to contact on whats app
    Given I register with a user on whatsapp
    When I navigate to whatsapp chat screen
    And I tap on new chat icon
    And I select a contact from the list
    When I send a random file to that contact
    Then I should see the random file sent to that contact successfully.