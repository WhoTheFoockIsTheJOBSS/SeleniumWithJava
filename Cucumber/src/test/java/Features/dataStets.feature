Feature: Login
  As a User I want to be able to login successfully

  Scenario Outline: Login successfully
    Given I have navigated to the page
    When User types "<username>" and "<password>"
    And My credentials are "<credentials>"
    Then The "<message>" message should be displayed

    Examples:
      | username   | password       | credentials | message               |
      | testuser_1 | password1234   | valid       | Login successful      |
      | testuser_2 | 989das12.a1!_  | invalid     | Incorrect credentials |
      | testuser_3 | 989das12.a1!_  | valid       | Login successful      |
      | testuser_4 | passwordStrong | invalid     | Incorrect credentials |
      | testuser_5 | Fernando38972  | invalid     | Incorrect credentials |
      | testuser_6 | 939das12.a1!_  | valid       | Login successful      |

  @smoke
  Scenario: Doc Strings
    Given I have the following text:
  """
  ======================
  Hola Amigo que tal amigo, espero que estes muy bien.
  Salúdame a la familia, la próxima vez que te vea espero vayamos a comer pollo.
  Me tengo que despedir no sin antes desearte feliz Halloween!!!.
  ======================
  """

  Scenario: Data tables
    Given I login with the following info
      | username | password     |
      | admin    | admin1234    |
      | admin21  | adminFer1234 |