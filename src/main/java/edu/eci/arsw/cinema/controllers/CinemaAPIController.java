/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.cinema.controllers;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.eci.arsw.cinema.model.CinemaFunction;
import edu.eci.arsw.cinema.services.CinemaServices;
/**
 *
 * @author cristian
 */
@RestController
@RequestMapping(value = "/cinemas")
public class CinemaAPIController {
 	
	static ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
	public static CinemaServices cinemaService = ac.getBean(CinemaServices.class);
    
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> recursoGetAllCinemas(){
		
	    try {
	        //obtener datos que se enviarán a través del API
	    	
	        Object data = cinemaService.getAllCinemas();
			return new ResponseEntity<>(data,HttpStatus.ACCEPTED);
	    } catch (Exception ex) {
	        Logger.getLogger(CinemaAPIController.class.getName()).log(Level.SEVERE, null, ex);
	        return new ResponseEntity<>("Error Cinemas no encontrados",HttpStatus.NOT_FOUND);
	    }        
	}
	
	@RequestMapping("/{name}")
    public HttpEntity getAddressName(@PathVariable String name) {
		try {
	        //obtener datos que se enviarán a través del API
	    	
	        Object data = cinemaService.getCinemaByName(name);
	        
			return new ResponseEntity<>(data,HttpStatus.ACCEPTED);
	    } catch (Exception ex) {
	        Logger.getLogger(CinemaAPIController.class.getName()).log(Level.SEVERE, null, ex);
	        return new ResponseEntity<>("Error Cinemas no encontrados",HttpStatus.NOT_FOUND);
	    }  
	}
	
	@RequestMapping("/{name}/{date}")
    public HttpEntity getAddressNameAndDate(@PathVariable String name, @PathVariable String date) {
		try {
	        //obtener datos que se enviarán a través del API
	    	
	        Object data = cinemaService.getFunctionsbyCinemaAndDate(name, date);
	        if(cinemaService.getFunctionsbyCinemaAndDate(name, date).isEmpty())return new ResponseEntity<>("No existen funciones para esa fecha",HttpStatus.NOT_FOUND);
	        else return new ResponseEntity<>(data,HttpStatus.ACCEPTED);
	        
	    } catch (Exception ex) {
	        Logger.getLogger(CinemaAPIController.class.getName()).log(Level.SEVERE, null, ex);
	        return new ResponseEntity<>("Error 404, Cinema y fecha no encontrado",HttpStatus.NOT_FOUND);
	    }      	 
	}

	@RequestMapping("/{cinema}/{date}/{moviename}")
    public HttpEntity getAddressCinemaDateAndName(@PathVariable String cinema, @PathVariable String date,@PathVariable String moviename) {
		try {
	    	
	        Object data = cinemaService.getFunctionsbyCinemaDateAndName(cinema, date, moviename);
	        
	        if(cinemaService.getFunctionsbyCinemaDateAndName(cinema, date, moviename) == null)return new ResponseEntity<>("No existen funciones para esta pelicula en esta fecha",HttpStatus.NOT_FOUND);
	        else return new ResponseEntity<>(data,HttpStatus.ACCEPTED);
	        
	    } catch (Exception ex) {
	        Logger.getLogger(CinemaAPIController.class.getName()).log(Level.SEVERE, null, ex);
	        return new ResponseEntity<>("Error 404, Cinema y fecha no encontrado",HttpStatus.NOT_FOUND);
	    }      
	}
	
	
	
	@RequestMapping(method = RequestMethod.POST,value = "/{cinema}")	
	public ResponseEntity<?> manejadorPostRecursoXX(@RequestBody CinemaFunction function, @PathVariable String cinema){
	    try {
	        cinemaService.addNewFunction(cinema,function);
	        return new ResponseEntity<>(HttpStatus.CREATED);
	    } catch (Exception ex) {
	        Logger.getLogger(CinemaAPIController.class.getName()).log(Level.SEVERE, null, ex);
	        return new ResponseEntity<>("No fue posible crear el recurso",HttpStatus.FORBIDDEN);            
	    }        

	}
	
	@RequestMapping(method = RequestMethod.PUT,value = "/{name}")	
	public ResponseEntity<?> manejadorPut(@RequestBody CinemaFunction function, @PathVariable String name){
	    try {
	    	System.out.println("llego aqui1");
	        CinemaFunction cf = cinemaService.getFunctionsbyCinemaDateAndName(name, function.getDate(), function.getMovie().getName());
	        System.out.println("llego aqui");
	        if(cf == null) {
	        	cinemaService.addNewFunction(name,function);
	        }else {
	        	cf.updateAtributes(function.getMovie(),function.getDate(),function.getSeats());
	        }
	        
	        return new ResponseEntity<>(HttpStatus.CREATED);
	    } catch (Exception ex) {
	        Logger.getLogger(CinemaAPIController.class.getName()).log(Level.SEVERE, null, ex);
	        return new ResponseEntity<>("No es posible actualizar ni crear el recurso",HttpStatus.FORBIDDEN);            
	    }        
	}
	
	
	
}
