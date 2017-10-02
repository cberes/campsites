---
title: "Vue.js Testing"
date: 2017-10-01T22:21:48-04:00
---
Since my last update, I added new mock services to the Spring backend and new components to the Vue.js frontend. I added mock campsite, area, and availability services, and I added to the mock campground service. I added (real, not mock) controllers for those services. In the frontend, I added the campsite, campsite-list, availabilty-calendar, and quick-availability components. 

The Java changes were very straightforward. It's trivial to keep fake data in memory. And there was nothing exceptional about the new controllers.

The Vue.js changes were much more interesting. While working on frontend changes, I learned how to

- use the Vue.js router
- invoke functions from a template
- add static functions to classes and use them
- get around some roadblocks testing Vue.js components

The biggest complication has been writing unit tests for Vue.js components. Angular has built-in dependency injection, but I haven't found such a feature in Vue.js. They offer [provide/inject](https://vuejs.org/v2/api/#provide-inject), but that feature comes with the disclaimer that it's "NOT recommended to use them in generic application code." Furthermore, it doesn't seem to be the dependency-injection feature I'm looking for.

One workaround is [inject-loader](https://vue-loader.vuejs.org/en/workflow/testing-with-mocks.html), which comes with its own disclaimer: "inject-loader@3.x is currently unstable." However, new and past versions do not seem to work for my use cases. Its usage caused my unit tests to fail intermittently after seemingly innocuous changes.

My workaround was to create a very simple dependency-injection module. I could have used an off-the-shelf solution, but the only one I found was [vue-inject](https://github.com/jpex-js/vue-inject). It seems complicated compared to the simplicity of Spring's `@Component` and `@Autowired` system. My solution is far from robust (I'm not certain it will work with complicated dependency scenarios), but it meets my needs.

The second problem I faced with unit tests is an inability to inject router params. It just doesn't seem to be possible yet. The helpful [avoriaz](https://github.com/eddyerburgh/avoriaz) library seems to offer a solution, but it failed to set router params with my version of Vue.js. I tried to implement avoriaz's relevant "globals" feature on my own, but I'm unable to touch the `$route` object on a Vue prototype. So I've disabled the single assertion that depends on router params.

I'm now questioning my decision to use Vue.js. It doesn't seem to be ready for primetime. Angular may be heavyweight, but at least it's mature. In Vue.js, testing seems to be an afterthought.

If I decide to keep Vue.js, then for my next tasks I would like to flesh out the frontend components. I'd like to add a more complete design to the pages that I've created. I also need to give more thought to my arrangement of the data (campsite info, availability, etc.). Finally, I want to add real DAOs that get data from a database. Of course, the  database would contain mock data at this point. Once this is done, I can move onto the campsite reservation process.

If I decide to scrap Vue.js, then I can first change the backend to read from a databse. Then I think it would make sense to replace Vue.js before making any further changes to the frontend.
