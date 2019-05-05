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
import edu.eci.arsw.cinema.util.JedisServiceCinema;

public class MainJedis {
	
	public static CinemaServices cinemaService;
	
	
	
	public static void main(String[] args) {
        String functionDate1 = "2018-12-18 15:30";
        List<CinemaFunction> functions1 = new ArrayList<>();
        CinemaFunction funct1 = new CinemaFunction(new Movie("SuperHeroes Movie", "Action"), functionDate1);
        functions1.add(funct1);
        Cinema c1 = new Cinema("CinemaX", functions1);

        JedisServiceCinema js = new JedisServiceCinema();
        String key = c1.getName() + "," + funct1.getDate() + "," + funct1.getMovie().getName();
        String valueInput = "[[true,true,true,true,true,true,true,true,true,true,true,true],[true,true,true,true,true,true,true,true,true,true,true,true],[true,true,true,true,true,true,true,true,true,true,true,true],[true,true,true,true,true,true,true,true,true,true,true,true],[true,true,true,true,true,true,true,true,true,true,true,true],[true,true,true,true,true,true,true,true,true,true,true,true],[true,true,true,true,true,true,true,true,true,true,true,true]]";
        js.saveToREDIS(key, valueInput);
        boolean[][] value = js.buyTicketRedis(c1,funct1,0,0);
        for(int i = 0;i < value.length;i++){
            for(int j = 0;j < value[i].length;j++){
                System.out.print(value[i][j]+" ");
            }                
            System.out.println();
        }
        //String value = js.getFromREDIS("1");
        System.out.println("este es el valor solicitado::: " + valueInput);
    }	
	

}
