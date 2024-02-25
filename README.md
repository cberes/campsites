# Campsite Reservation System

An open-source reservation system for campgrounds

[![Build Status](https://travis-ci.org/cberes/campsites.svg?branch=main)](https://travis-ci.org/cberes/campsites)

## History

I've tried to complete this project twice. This is my most recent attempt in the `main` branch. My first attempt from 2018 is in the `campsites-2018` branch.

## Pages

1. Campgrounds intro
1. Day selection (for reservations)
1. Campsite detail
   1. Show when reserved and available
   1. Can select days and reserve them
1. Campsite list
   1. Show if available/unavailable
1. Map of campsites
   1. Show if available/unavailable
1. Cart
   1. Show pending reservations
   1. Can remove reservations
   1. Proceed to checkout
1. Checkout: data collection
1. Checkout: payment
1. Checkout: confirmation
1. Checkout: thank you

### Other features

Send email reminders about upcoming reservations

## Data

1. Campgrounds
   1. id
   1. active
   1. name
   1. description
1. Campsite
   1. id
   1. active
   1. campground_id
   1. name
   1. description
   1. electric/power
   1. size
   1. max_occupancy
   1. max_vehicles
1. Customer
   1. id
   1. active
   1. name
   1. email
   1. phone
   1. data (dependent on campgrounds)
1. Reservation
   1. id
   1. campsite_id
   1. customer_id
   1. payment_id
   1. start_date
   1. end_date
1. Payment
   1. id
   1. transaction_id
   1. amount
   1. tax
   1. fee
1. Transaction?
   1. id
   1. processor_id
   1. cardholder_name
   1. amount
   1. tax
   1. fee
   1. status
   1. response_code
   1. masked_pan
   1. card_brand
   1. approval_code
   1. external_id
   1. created
   1. updated
1. Processor
   1. id
   1. active
   1. name

## Services

1. Campground
1. Payments
1. Campsites
1. Reservations 
1. Cart
1. Customer
   * To ask for customer info during checkout?
1. Map
   * Probably?
1. Orders
   * For customers to view/modify past orders?
1. Reminders
   * Sends email reminders about reservations

## Open questions

1. How do I store shopping cart contents?
1. How to implement maps? Plus what technology to use?
1. Ensure that campsite is not reserved by person B while person A started reserving it first and is still reserving it.
   * Probably pretty easy: just make a row with a timestamp. Ignore if older than *x* minutes.

