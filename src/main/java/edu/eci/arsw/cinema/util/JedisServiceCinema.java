package edu.eci.arsw.cinema.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.eci.arsw.cinema.model.Cinema;
import edu.eci.arsw.cinema.model.CinemaFunction;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Response;
import redis.clients.jedis.Transaction;

public class JedisServiceCinema {

    public static void saveToREDIS(String key, String data) {
        Jedis jedis = JedisUtil.getPool().getResource();
        jedis.watch(key);
        Transaction t1 = jedis.multi();
        t1.set(key, data);
        t1.exec();
        jedis.close();
    }

    public static String getFromREDIS(String key) {
        boolean intentar = true;
        String content = "";
        while (intentar) {
            Jedis jedis = JedisUtil.getPool().getResource();
            jedis.watch(key);
            Transaction t = jedis.multi();
            Response<String> data = t.get(key);
            List<Object> result = t.exec();
            if (result.size() > 0) {
                intentar = false;
                content = data.get();
                jedis.close();
            }
        }
        return content;
    }

    public static boolean[][] buyTicketRedis(Cinema cinema, CinemaFunction funcion, int col, int row) {

        ObjectMapper mapper = new ObjectMapper();
        String value = getFromREDIS(cinema.getName() + "," + funcion.getDate() + "," + funcion.getMovie().getName());
        boolean[][] seats = null;
        try {
            seats = mapper.readValue(value, boolean[][].class);
            seats[col][row]=false;
        } catch (IOException e) {            
            e.printStackTrace();
        }
        return seats;
    }

    public static boolean[][] getSeatsRedis(Cinema cinema, CinemaFunction funcion) {

        ObjectMapper mapper = new ObjectMapper();
        String value = getFromREDIS(cinema.getName() + "," + funcion.getDate() + "," + funcion.getMovie().getName());
        boolean[][] seats = null;
        try {
            seats = mapper.readValue(value, boolean[][].class);
        } catch (IOException e) {            
            e.printStackTrace();
        }
        return seats;
    }
    

}