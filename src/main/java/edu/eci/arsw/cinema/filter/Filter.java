package edu.eci.arsw.cinema.filter;

import java.util.List;

import org.springframework.stereotype.Component;

import edu.eci.arsw.cinema.model.Cinema;
import edu.eci.arsw.cinema.model.Movie;
import edu.eci.arsw.cinema.persistence.CinemaException;



public interface Filter {	
	
	public List<Movie>  filter(Cinema cinema, String fecha, String filter) throws CinemaException;
	
}
