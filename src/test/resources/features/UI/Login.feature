Feature: Login to Report Portal

  Scenario Outline: User successfully logins to Report Portal
    Given User with "<login>" and "<password>" authenticate to report portal
    Then User "<login>" navigated to dashboards page
    Examples:
      | login      | password |
      | superadmin | erebus   |
      | default    | 1q2w3e   |

  Scenario: Verify not shared dashboard not present for default user
    Given User with "default" and "1q2w3e" authenticate to report portal
    And User "default" navigated to dashboards page
    Then "DEMO DASHBOARD" not present on dashboard