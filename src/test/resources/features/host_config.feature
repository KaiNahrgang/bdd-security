@host_config
Feature: Host Configuration
  Verify that the configuration of the host and network are as expected

  @iriusrisk-open_ports
  Scenario Outline: Only the required ports should be open
    Given the target host name <host>
    When TCP ports from <startPort> to <endPort> are scanned using <threads> threads and a timeout of <timeout> milliseconds
    And the <state> ports are selected
    Then the ports should be <ports>
    Examples:
      | host      | startPort | endPort | threads | timeout | state | ports  |
      | sectestlab | 1         | 65535   | 500     | 100     | open  | 22,80,1521,2377,5000,7946,8080,8887,8888,8889,49160 |
