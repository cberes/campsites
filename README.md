# Campsite Reservation System

## Pages

0. Campgrounds intro
0. Day selection (for reservations)
0. Campsite detail
   0. Show when reserved and available
   0. Can select days and reserve them
0. Campsite list
   0. Show if available/unavailable
0. Map of campsites
   0. Show if available/unavailable
0. Cart
   0. Show pending reservations
   0. Can remove reservations
   0. Proceed to checkout
0. Checkout: data collection
0. Checkout: payment
0. Checkout: confirmation
0. Checkout: thank you

### Other features

Send email reminders about upcoming reservations

## Data

0. Campgrounds
   0. id
   0. active
   0. name
   0. description
0. Campsite
   0. id
   0. active
   0. campground_id
   0. name
   0. description
   0. electric/power
   0. size
   0. max_occupancy
   0. max_vehicles
0. Customer
   0. id
   0. active
   0. name
   0. email
   0. phone
   0. data (dependent on campgrounds)
0. Reservation
   0. id
   0. campsite_id
   0. customer_id
   0. payment_id
   0. start_date
   0. end_date
0. Payment
   0. id
   0. transaction_id
   0. amount
   0. tax
   0. fee
0. Transaction?
   0. id
   0. processor_id
   0. cardholder_name
   0. amount
   0. tax
   0. fee
   0. status
   0. response_code
   0. masked_pan
   0. card_brand
   0. approval_code
   0. external_id
   0. created
   0. updated
0. Processor
   0. id
   0. active
   0. name

## Services

0. Campground
0. Payments
0. Campsites
0. Reservations 
0. Cart
0. Customer
   * To ask for customer info during checkout?
0. Map
   * Probably?
0. Orders
   * For customers to view/modify past orders?
0. Reminders
   * Sends email reminders about reservations

## Open questions

0. How do I store shopping cart contents?
0. How to implement maps? Plus what technology to use?
0. Ensure that campsite is not reserved by person B while person A started reserving it first and is still reserving it.
   * Probably pretty easy: just make a row with a timestamp. Ignore if older than *x* minutes.

