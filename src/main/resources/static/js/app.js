var app = (function () {

    var cinema = ""; //Tiene el nombre del ciema seelccionado
    var totalFunctions = []; //Va a tener todas las funciones
    var funciones = []; //Va a tener los json a mostrar en la tabla
    var contMetodo = 0;
    var funcionActual = 0;

    var cambiarCinema = function (param) {
        var movieName;
        var movieSeatsCols;
        var movieSeatsRows;
        var movieDate;
        var singleFunction;
        var functions = param.functions;
        totalFunctions = functions;
        funciones = [];
        for (var i = 0; i < functions.length; i++) {
            movieName = functions[i].movie.name;
            movieSeatsCols = functions[i].seats[0].length;
            movieSeatsRows = functions[i].seats.length;
            movieDate = functions[i].date;
            singleFunction = { "cinema": cinema, "funcion": movieName, "seats": movieSeatsCols * movieSeatsRows, "fecha": movieDate };
            funciones.push(singleFunction);
        }
        actualizarTabla();
    };

    var actualizarTabla = function () {
        $("#tablaCines").find('tbody').empty();
        $("#tablaSeats").find("tbody").empty();
        for (var i = 0; i < funciones.length; i++) {
            var funcion = funciones[i];
            $("#tablaCines").find('tbody').append('<tr class="clickable-row"><th scope="row" data-funcion="' + funcion.funcion + '">' + (i + 1) + '</th><td data-funcion="' + funcion.funcion + '">' + funcion.cinema + '</td><td data-funcion="' + funcion.funcion + '">' + funcion.funcion + '</td><td data-funcion="' + funcion.funcion + '">' + funcion.seats + '</td><td data-funcion="' + funcion.funcion + '">' + funcion.fecha + '</td></tr>');
        }
        mostrarSillas();
    }

    var mostrarSillas = function () {
        var movieSeatsCols;
        var movieSeatsRows;
        $('#tablaCines').on('click', 'tbody tr', function (event) {
            $(this).addClass('highlight').siblings().removeClass('highlight');
            $("#tablaSeats").find("thead").empty();
            $("#tablaSeats").find("tbody").empty();
            $("#pantallaSeats").empty();
            var rowSelected = false;
            for (var i = 0; i < totalFunctions.length && !rowSelected; i++) {
                if (totalFunctions[i].movie.name == event.target.dataset.funcion) {
                    movieSeatsCols = totalFunctions[i].seats[0].length;
                    movieSeatsRows = totalFunctions[i].seats.length;
                    funcionActual = i;
                    generarTablaSillas(movieSeatsCols, movieSeatsRows);
                    rowSelected = true;
                }
            }
        });
    }

    var generarTablaSillas = function (movieSeatsCols, movieSeatsRows) {
        $("#tablaSeats").find("thead").append('<tr>');
        for (var col = 0; col < movieSeatsCols; col++) {
            $("#tablaSeats").find("thead tr").append('<th scope="col">' + (col + 1) + '</th>');
            if (col == 2 || col == 8) {
                $("#tablaSeats").find("thead tr").append('<th scope="col"></th>');
            }
        }
        $("#tablaSeats").find("thead").append('</tr>');
        for (var row = 0; row < movieSeatsRows; row++) {
            $("#tablaSeats").find("tbody").append('<tr>');
            for (var col = 0; col < movieSeatsCols; col++) {
                $("#tablaSeats").find("tbody tr:last").append('<td>');
                if (totalFunctions[funcionActual].seats[row][col]) {
                    $("#tablaSeats").find("tbody tr:last td:last").append('<button data-row="' + row + '" data-col="' + col + '" class="btn-seat" data-toggle="tooltip" data-placement="right" title="F:'+row+', C:'+col+'"><img data-row="' + row + '" data-col="' + col + '" src="../images/sillaOnMini.png"/></button>');
                } else {
                    $("#tablaSeats").find("tbody tr:last td:last").append('<button data-row="' + row + '" data-col="' + col + '" class="btn-seat" data-toggle="tooltip" data-placement="right" title="F:'+row+', C:'+col+'"><img data-row="' + row + '" data-col="' + col + '" src="../images/sillaOffMini.png"/></button>');
                }
                $("#tablaSeats").find("tbody tr:last").append('</td>');
                if (col == 2 || col == 8) {
                    $("#tablaSeats").find("tbody tr:last").append('<td></td>');
                }
            }
            $("#tablaSeats").find("tbody").append('</tr>');
            if (row == 1) {
                $("#tablaSeats").find("tbody").append('<tr><td></td></tr>');
            }
        }
        $("#pantallaSeats").append('<img src="../images/pantalla.png">');
        if (contMetodo < 1) {
            cambiarSillas();
            contMetodo = contMetodo + 1;
        }
    }

    var cambiarSillas = function () {
        $('#tablaSeats').on('click', 'tbody button', function (event) {
            var row = event.target.dataset.row;
            var col = event.target.dataset.col;
            if (totalFunctions[funcionActual].seats[row][col]) {
                $(this).find('img').attr('src', '../images/sillaOffMini.png');
                totalFunctions[funcionActual].seats[row][col] = false;
                alert("Se reservo correctamente el asiento");
            } else {
                $(this).find('img').attr('src', '../images/sillaOnMini.png');
                totalFunctions[funcionActual].seats[row][col] = true;
                alert("Se cancelo la reserva correctamente");
            }
        });
    }

    return {
        buscarCinemas: function () {
            cinema = $("#inputBuscar").val();
            if (cinema != "") {
                //apimock.getCinemaByName(cinema, cambiarCinema);
                apiclient.getCinemaByName(cinema, cambiarCinema);
            }
        },
        eliminarTexto: function () {
            $("#inputBuscar").val("");
        }
    };

})();