Feature: As a user, I can add, remove and update shopping cart

    Scenario: Add item to shopping cart
    Given Open page for all product
    When I click on Add To Cart and Go to Checkout for an item
    Then This item should be listed in shopping cart

    Scenario: Remove item from shopping cart
    Given Shopping cart is open and present
    When I click on Remove for an item
    Then This item shoule be removed from shopping cart

    Scenario: Update item from shopping cart with positive integers
    Given Shopping cart is open with at least one item
    When I update the quantity of an item with valid numbers
    Then The quantity of this item should be updated

    Scenario: Update item in shopping cart with negtive numbers
    Given Shopping cart is open and present
    When I update the quantity of an item with negtive numbers
    Then The quantity of this item should not be negtive

    Scenario: Sub-Total price changes in shopping cart
    Given Shopping cart is open with at least one item
    When I update the quantity of an item
    Then The total price should change