Feature: Widget tests

  @CreateWidget
  Scenario: Verify newly created widget present on dashboard
    Given User with "superadmin" and "erebus" authenticate to report portal
    When User opens dashboard "DEMO DASHBOARD"
    And Creates new widget "NEW WIDGET"
    Then "NEW WIDGET" present on dashboard

