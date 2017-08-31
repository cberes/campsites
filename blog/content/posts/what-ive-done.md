---
title: "What I've Done So Far"
date: 2017-08-30T22:05:00-04:00
---
As I said in my last post, I've already started working on my campsites project. I had already recorded two live programming sessions, but those videos are gone forever.

So far, I built a _very_ simple static website. When I build RESTful services (that return mock data initially), I'll use this website to display the data. The website is very simple and ugly right now. I'll worry about the aesthetics later.

First, there is a campgrounds overview page. This page will introduce the campgrounds. 

Next there is a list of campsites. Users will be able to see information about the campsites, when they're available in the coming days, and links to reserve the campsites.

There will also be a map of campsites. I think this will be one of the trickiest features to implement. But I'll leave the actual map implementation for _much_ later.

From the campsites list, users can navigate to the campsite detail page. This will show in-depth information about a campsite. Pictures, availability for the next month, and links to reserve will be shown.

Users can select available days for a campsite. This will add the reservation to a shopping cart. The user can edit or remove pending reservations. From the cart, the user can empty the cart or proceed to checkout.

## Checkout

First, the website will ask for relevant customer data. Next the website will ask for payment information. Then there will be a page that allows the user to review the order and confirm it. Finally, a thank-you page will be shown when the user confirms the order.

## Order management

I've added a placeholder page that shows a review of a completed order.

I plan to allow users to manage their reservations. They may need to cancel a reservation to get a refund. Also, users should be able to see a list of upcoming orders. I have yet to create pages for these features.

## What's next

Next I plan to stub out the Spring project. Then I'll create a controller with mock data. I'll go back to the frontend and display that mock data. I'll continue with this until every page renders mock data.


