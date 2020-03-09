Feature: Add Preferred Types
  As an existing user, I want to be able to add my 3 preferred types of restaurants to my profile

  Background:
    Given The following users are already registered
      |full_name    |_username     |_password    |
      |Steve Jobs   |steve99       |iphoneX89    |
      |John Doe     |johnDoe02     |abcd123      |
      |Imad Dodin   |idodin        |abcd123      |

  Scenario Outline: Add 3 Preferred Types
    When User "<userName>" wishes to set their first preference to "<preference1>"
    And User "<userName>" wishes to set their second preference to "<preference2>"
    And User "<userName>" wishes to set their third preference to "<preference3>"
    Then User "<userName>" should receive a message confiriming their preferences are "<preference1>", "<preference2>" and "<preference3>"
    Examples:
    | userName | preference1 | preference2 | preference3 |
    | idodin   | lebanese    | burger      | portugese   |

  Scenario Outline: Add Single Preferred Type
    When User "<userName>" wishes to set their first preference to "<preference1>"
    Then User "<userName>" should receive a message confiriming their preference is "<preference1>"
    Examples:
      | userName | preference1 |
      | idodin   | lebanese    |

  Scenario Outline: Add Invalid Preferred Type
    When User "<userName>" wishes to set their first preference to "<preference1>"
    Then User "<userName>" should receive a message that "<preference1>" is not a valid type
    Examples:
      | userName | preference1 |
      | idodin   | fdsa         |
