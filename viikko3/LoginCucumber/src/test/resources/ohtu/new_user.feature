Feature: A new user account can be created if a proper unused username and password are given

    Scenario: creation is successful with valid username and password
        Given command new is selected
        When username "pekkinen" and password "1234abcd" are entered
        Then system will respond with "new user registered"

    Scenario: creation fails with already takin username and valid password
        Given command new is selected
        When username "pekkinen" and password "12345abcd" are entered
        And command new is selected
        And username "pekkinen" and password "abcd12345" are entered
        Then system will respond with "new user not registered"

    Scenario: creation fails with too short username and valid password
        Given command new is selected
        When username "ab" and password "12345abcd" are entered
        Then system will respond with "new user not registered"

    Scenario: creation fails with valid username and too short password
        Given command new is selected
        When username "abcd" and password "12345a" are entered
        Then system will respond with "new user not registered"

    Scenario: creation fails with valid username and password long enough but consisting of only letters 
        Given command new is selected
        When username "yeet" and password "qwertyqwerty" are entered
        Then system will respond with "new user not registered"

    Scenario: can login with successfully generated account
        Given user "eero" with password "salainen1" is created
        And command login is selected
        And username "eero" and password "salainen1" are entered
        Then system will respond with "logged in"
        