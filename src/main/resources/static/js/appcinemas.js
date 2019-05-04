var appcinemas = (function () {

    var cinemas = [];

    var mostrarCinemas = function (param) {
        cinemas = param;
        for (var i = 0; i < cinemas.length; i++) {
            if (i == 0) {
                $("#CarruselCinemas").find("ul").append('<li data-target="#CarruselCinemas" data-slide-to="' + i + '"></li>');
                $("#CarruselCinemas").find("div:first").append('<div class="carousel-item active"><img src="../images/' + cinemas[i].name + '.png" alt="' + cinemas[i].name + '" style="width: auto;"><div class="tituloImgCarrusel">' + cinemas[i].name + '</div></div>');
            } else {
                $("#CarruselCinemas").find("ul").append('<li data-target="#CarruselCinemas" data-slide-to="' + i + '"></li>');
                $("#CarruselCinemas").find("div:first").append('<div class="carousel-item"><img src="../images/' + cinemas[i].name + '.png" alt="' + cinemas[i].name + '" style="width: auto;"><div class="tituloImgCarrusel">' + cinemas[i].name + '</div></div>');
            }
        }
    }

    return {
        getAllCinemas: function () {
            apiclient.getAllCinemas(mostrarCinemas);
        }
    }
})();