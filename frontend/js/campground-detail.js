(function (w, d) {
    var service = new CampgroundService('http://localhost:8080', w.jQuery);

    function renderCampground(campground) {
        new Vue({
            el: '#campground',
            data: campground
        });
    }

    service.getCampground(0, renderCampground);
}(window, document));
