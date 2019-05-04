package edu.eci.arsw.cinema.persistence.impl;

import edu.eci.arsw.cinema.filter.Filter;
import edu.eci.arsw.cinema.model.Cinema;
import edu.eci.arsw.cinema.model.CinemaFunction;
import edu.eci.arsw.cinema.model.Movie;
import edu.eci.arsw.cinema.persistence.CinemaException;
import edu.eci.arsw.cinema.persistence.CinemaPersistenceException;
import edu.eci.arsw.cinema.persistence.CinemaPersitence;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author cristian
 */

@Service
public class InMemoryCinemaPersistence implements CinemaPersitence {

    @Autowired
    private Filter filter = null;

    private final ConcurrentHashMap<String, Cinema> cinemas = new ConcurrentHashMap<>();

    public InMemoryCinemaPersistence() {

        // load stub data
        String functionDate1 = "2018-12-18 15:30";
        String functionDate2 = "2018-12-18 15:30";
        String functionDate3 = "2018-12-18 15:30";
        String functionDate4 = "2018-12-18 15:31";
        String functionDate5 = "2019-03-17 10:50";
        String functionDate6 = "2019-03-09 20:30";
        String functionDate7 = "2019-01-15 24:00";
        String functionDate8 = "2019-03-29 11:00";
        List<CinemaFunction> functions1 = new ArrayList<>();
        List<CinemaFunction> functions2 = new ArrayList<>();
        List<CinemaFunction> functions3 = new ArrayList<>();
        List<CinemaFunction> functions4 = new ArrayList<>();
        List<CinemaFunction> functions5 = new ArrayList<>();
        List<CinemaFunction> functions6 = new ArrayList<>();
        List<CinemaFunction> functions7 = new ArrayList<>();
        List<CinemaFunction> functions8 = new ArrayList<>();
        CinemaFunction funct1 = new CinemaFunction(new Movie("SuperHeroes Movie", "Action"), functionDate1);
        CinemaFunction funct2 = new CinemaFunction(new Movie("The Night", "Horror"), functionDate1);
        CinemaFunction funct3 = new CinemaFunction(new Movie("Green Book", "Mystery"), functionDate1);
        CinemaFunction funct4 = new CinemaFunction(new Movie("Harry Potter", "Fantasy"), functionDate1);
        CinemaFunction funct5 = new CinemaFunction(new Movie("A star is born", "Romance"), functionDate1);
        CinemaFunction funct6 = new CinemaFunction(new Movie("The Favorite", "Drama"), functionDate1);
        CinemaFunction funct7 = new CinemaFunction(new Movie("Titanic Movie", "Action"), functionDate2);
        CinemaFunction funct8 = new CinemaFunction(new Movie("The Purge", "Horror"), functionDate3);
        CinemaFunction funct9 = new CinemaFunction(new Movie("Mision Imposible", "Fiction"), functionDate4);
        CinemaFunction funct10 = new CinemaFunction(new Movie("Shrek", "Animado"), functionDate5);
        CinemaFunction funct11 = new CinemaFunction(new Movie("Capitan Marvel", "Action"), functionDate6);
        CinemaFunction funct12 = new CinemaFunction(new Movie("The Aro Resurrection", "Terror"), functionDate7);
        CinemaFunction funct13 = new CinemaFunction(new Movie("Harry Potter & The Secret Chamber", "Fantasia"), functionDate8);
        functions1.add(funct1);
        functions1.add(funct2);
        functions2.add(funct3);
        functions2.add(funct4);
        functions3.add(funct5);
        functions3.add(funct6);
        functions4.add(funct7);
        functions4.add(funct8);
        functions5.add(funct9);
        functions6.add(funct10);
        functions7.add(funct11);
        functions8.add(funct12);
        functions8.add(funct13);
        Cinema c1 = new Cinema("CinemaX", functions1);
        Cinema c2 = new Cinema("CinePolis", functions2);
        Cinema c3 = new Cinema("CineMania", functions3);
        Cinema c4 = new Cinema("CineMark", functions4);
        Cinema c5 = new Cinema("CineProcinal", functions5);
        Cinema c6 = new Cinema("CineStereo", functions6);
        Cinema c7 = new Cinema("CineColombia", functions7);
        Cinema c8 = new Cinema("CriptoCine", functions8);
        cinemas.put("CinemaX", c1);
        cinemas.put("CinePolis", c2);
        cinemas.put("CineMania", c3);
        cinemas.put("CineMark", c4);
        cinemas.put("CineProcinal", c5);
        cinemas.put("CineStereo", c6);
        cinemas.put("CineColombia", c7);
        cinemas.put("CriptoCine", c8);
    }

    @Override
    public void buyTicket(int row, int col, String cinema, String date, String movieName) throws CinemaException {
        try {
            Cinema cine = cinemas.get(cinema);
            for (CinemaFunction funcion : cine.getFunctions()) {
                if (funcion.getDate().equals(date) && funcion.getMovie().getName().equals(movieName)) {
                    funcion.buyTicket(row, col);
                }
            }
        } catch (Exception e) {
            throw new CinemaException("Seat booked");
        }

    }

    @Override
    public List<CinemaFunction> getFunctionsbyCinemaAndDate(String cinema, String date) throws CinemaException {
        try {
            Cinema cine = cinemas.get(cinema);
            List<CinemaFunction> funciones = new ArrayList<CinemaFunction>();
            for (CinemaFunction funcion : cine.getFunctions()) {
                if (funcion.getDate().equals(date)) {
                    funciones.add(funcion);
                }
            }

            return funciones;
        } catch (Exception e) {
            throw new CinemaException("Cinema not found");
        }

    }

    @Override
    public CinemaFunction getFunctionsbyCinemaDateName(String cinema, String date, String name) throws CinemaException {
        CinemaFunction cinemaFunction = null;
        try {
            Cinema cine = cinemas.get(cinema);

            for (CinemaFunction funcion : cine.getFunctions()) {
                if (funcion.getMovie().getName().equals(name)) {
                    cinemaFunction = funcion;
                }
            }
        } catch (Exception e) {
            throw new CinemaException("Cinema not found");
        }

        return cinemaFunction;

    }

    @Override
    public void saveCinema(Cinema c) throws CinemaPersistenceException {

        if (cinemas.containsKey(c.getName())) {

            throw new CinemaPersistenceException("The given cinema already exists: " + c.getName());
        } else {
            cinemas.put(c.getName(), c);
        }
    }

    @Override
    public Cinema getCinemaByName(String name) throws CinemaPersistenceException {
        if (cinemas.get(name) == null)
            throw new CinemaPersistenceException("cine no encontrado");
        return cinemas.get(name);
    }

    @Override
    public Map<String, Cinema> getCinemas() {
        return this.cinemas;
    }

    @Override
    public List<Movie> filterMovies(String cinema, String fecha, String filter) throws CinemaException {
        Cinema c = cinemas.get(cinema);
        List<Movie> movies = this.filter.filter(c, fecha, filter);
        return movies;
    }

    public void setFilter(Filter filter) {
        this.filter = filter;
    }

}
