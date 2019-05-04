package edu.eci.arsw.cinema.filter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import edu.eci.arsw.cinema.model.Cinema;
import edu.eci.arsw.cinema.model.CinemaFunction;
import edu.eci.arsw.cinema.model.Movie;

@Service
public class filterByAvailability implements Filter{

	@Override
	public List<Movie> filter(Cinema cinema, String fecha, String filter) {
		int seats = Integer.parseInt(filter);
		
		List<Movie> movies = new ArrayList<>();
		try {
			List<CinemaFunction> functions = cinema.getFunctions();
			for (CinemaFunction cf : functions) {
				if(cf.freeSeats() >= seats) movies.add(cf.getMovie());
			}
			
		}catch (Exception e) {
		
		}
		
		
		return movies;
	}

}
