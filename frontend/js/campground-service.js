function CampgroundService(host, jquery) {
    var baseUrl = host + '/api/campground';

    this.getCampground = function (id, callback) {
        var url = baseUrl + '/' + id;
        jquery.get(url, function (data) {
            callback(data || {});
        });
    };

    this.getCampgrounds = function (callback) {
        jquery.get(baseUrl, function (data) {
            callback(data || []);
        });
    };
}
