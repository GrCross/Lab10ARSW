package edu.eci.arsw.cinema.filter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import edu.eci.arsw.cinema.model.Cinema;
import edu.eci.arsw.cinema.model.CinemaFunction;
import edu.eci.arsw.cinema.model.Movie;
import edu.eci.arsw.cinema.persistence.CinemaException;
import edu.eci.arsw.cinema.persistence.CinemaPersistenceException;

//@Service
public class filterByGenre implements Filter {
	
	public  filterByGenre() {
		
	}
	
	@Override
	public List<Movie> filter(Cinema cinema, String fecha, String filter) throws CinemaException{
		System.out.println("holaaa");
		List<Movie> movies = new ArrayList<>();
		
		try {
			
			List<CinemaFunction> functions = cinema.getFunctions();
			for (CinemaFunction cf : functions) {
				Movie movie = cf.getMovie();
				if(cf.getDate().equals(fecha)) {
					if(movie.getGenre().equals(filter)) movies.add(movie);
				} 
			}
			return movies;
		}catch(NullPointerException e) {
			e.printStackTrace();
			throw new CinemaException("Cinema not found"); 
		}
		
		
		
	}


}
