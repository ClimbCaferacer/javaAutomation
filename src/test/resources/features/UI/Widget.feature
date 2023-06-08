Feature: Widget tests

  Background:
    Given User with "superadmin" and "erebus" authenticate to report portal
    When User opens dashboard "DEMO DASHBOARD"

  @deleteWidget
  Scenario: Verify Component health check widget present on dashboard
    And Creates new widget "Component health check" with name "Component health check NEW WIDGET"
    Then "Component health check NEW WIDGET" present on dashboard

  @deleteWidget
  Scenario: Verify Launches duration chart widget present on dashboard
    And Creates new widget "Launches duration chart" with name "Launches duration chart NEW WIDGET"
    Then "Launches duration chart NEW WIDGET" present on dashboard
