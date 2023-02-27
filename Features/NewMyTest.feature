Feature: Test Facebook

  Scenario Outline: Test login with valid Credential
    Given Open chrome and start application
    When I enter vaild "<username>" and "<password>"
    Then user should able to login successfully
    Then close the application

    Examples: 
      | username               | password         |
      | selvamrajesh87@gamil   | amuthaselvam87   |
      | selvamrajesh87@gamil11 | amuthaselvam8711 |
      | selvamrajesh87@gamil111 | amuthaselvam87111 |
