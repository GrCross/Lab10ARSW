package edu.eci.arsw.cinema.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

public class JedisService{

    public void saveToREDIS(String key, String data){
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
            // Inicializar jedis y obtener recursos
            // Hacer watch de la llave 
            // Crear la transacción t
            //Response<String> data = t.get(key);
            //List<Object> result = t.exec();
            /*if (result.size() > 0) {
               intentar = false;
               content = data.get();
               //Cerrar recurso jedis	
            }*/
        }
       return content;
    }

}