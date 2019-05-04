/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.cinema.persistence;

import edu.eci.arsw.cinema.model.Cinema;
import edu.eci.arsw.cinema.model.CinemaFunction;
import edu.eci.arsw.cinema.model.Movie;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

/**
 *
 * @author cristian
 */
@Component
public interface CinemaPersitence {
		
    
    /**
     * 
     * @param row the row of the seat
     * @param col the column of the seat
     * @param cinema the cinema's name
     * @param date the date of the function
     * @param movieName the name of the movie
     * 
     * @throws CinemaException if the seat is occupied,
     *    or any other low-level persistence error occurs.
     */
    public void buyTicket(int row, int col, String cinema, String date, String movieName) throws CinemaException;
    
    /**
     * 
     * @param cinema cinema's name
     * @param date date
     * @return the list of the functions of the cinema in the given date
     * @throws CinemaException 
     */
    public List<CinemaFunction> getFunctionsbyCinemaAndDate(String cinema, String date) throws CinemaException;
    
    /**
     * 
     * @param cinema new cinema
     * @throws  CinemaPersistenceException n if a cinema with the same name already exists
     */
    public void saveCinema(Cinema cinema) throws CinemaPersistenceException;
    
    /**
     * 
     * @param name name of the cinema
     * @return Cinema of the given name
     * @throws  CinemaPersistenceException if there is no such cinema
     */
    public Cinema getCinemaByName(String name) throws CinemaPersistenceException;
    
    /**
     * 
     * @return cinemas 
     */
    public Map<String,Cinema> getCinemas ();
    
    /**
     * 
     * @param cinema
     * @param fecha
     * @param filter
     * @return
     * @throws CinemaException 
     */
    public List<Movie> filterMovies(String cinema, String fecha, String filter) throws CinemaException;

	CinemaFunction getFunctionsbyCinemaDateName(String cinema, String date, String name) throws CinemaException;
    
}
