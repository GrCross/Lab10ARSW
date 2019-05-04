var apiclient = (function () {

    return {
        getCinemaByName: function (name, callback) {
            $.get("cinemas/" + name, function (data) {
                callback(data);
            });
        },
        getAllCinemas: function (callback) {
            $.get("cinemas", function (data) {
                callback(data);
            });
        }
    };
})();