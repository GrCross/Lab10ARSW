package edu.eci.arsw.cinema.main;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.eci.arsw.cinema.model.Cinema;
import edu.eci.arsw.cinema.model.CinemaFunction;
import edu.eci.arsw.cinema.model.Movie;
import edu.eci.arsw.cinema.persistence.CinemaException;
import edu.eci.arsw.cinema.services.CinemaServices;

public class MainCinema {
	
	public static CinemaServices cinemaService;
	
	/*
	
	public static void main(String[] args) {
		
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		cinemaService = ac.getBean(CinemaServices.class);
		
		
		System.out.println("---------------agregar nuevo cinema-----------------");
		List<CinemaFunction> funciones = new ArrayList();
        
        Movie pelicula1 = new Movie("La favorita","Drama");
        CinemaFunction funcion = new CinemaFunction(pelicula1,"07/05/1999");
        
        Movie pelicula2 = new Movie("La momia","Accion");
        CinemaFunction funcion1 = new CinemaFunction(pelicula2,"07/05/1999");
        
        funciones.add(funcion);
        funciones.add(funcion1);
        
        Cinema c = new Cinema("CineColombia",funciones);
        
        cinemaService.addNewCinema(c);
        
        System.out.println("---------------consultar cinema (nombre)-----------------");
        
        Cinema cinemaConsultado = null;
        try {
        	cinemaConsultado = cinemaService.getCinemaByName("CineColombia");
		} catch (CinemaException e) {
			e.printStackTrace();
		}
        
        System.out.println(cinemaConsultado.getName());
        
        
        System.out.println("--------------- Obtener funciones de un cinema-----------------");
        
        for(CinemaFunction cf: cinemaConsultado.getFunctions()) {
        	System.out.println("nombre pelicula: "+ cf.getMovie().getName() + "fecha: "+cf.getDate()+"Genero: "+cf.getMovie().getGenre());
        }
        
        System.out.println("--------------- Comprar tiquetes--------------");
        
        cinemaService.buyTicket(1, 1, "CineColombia", "07/05/1999", "La favorita");
        cinemaService.buyTicket(1, 2, "CineColombia", "07/05/1999", "La favorita");
        cinemaService.buyTicket(1, 3, "CineColombia", "07/05/1999", "La favorita");
        cinemaService.buyTicket(1, 4, "CineColombia", "07/05/1999", "La favorita");
        
        System.out.println("-*-------------------- Filtra por disponibilidad--------------------");
        List<Movie>movies = null;
        try {
			movies  = cinemaService.filterMovies("CineColombia", "07/05/1999", "82");
		} catch (CinemaException e) {
			e.printStackTrace();
		}
        
        for (Movie m : movies) {
			System.out.println(m.getName()+" "+m.getGenre());
		}
   
        
	}
	
	public void registerCinema() {
		
        
	}
	*/

}
