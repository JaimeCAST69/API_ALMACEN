package utez.edu.mx.unidad3.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

public class ClaveGenerator {
    public static String generateCedeClave(Long id){
             //generar un formato para fechas: el patron que ddMMyyyy, adicional a que le inidcamos que la fecha va estaar en españo-Mexico
                SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy", new Locale("es-MX"));
                //utlizamos el sdf para formatear un objeto de tipo date
                String fecha = sdf.format(new Date());
                //generamos un numero de 4 caracteres de longitud (%04d)
                String random = String.format("%04d", ThreadLocalRandom.current().nextInt(1,10000));
                return "C" + id + "-" + fecha + "-" + random;
    }

    public static String generateWarehouseClave(String cedeClave, Long id){
        return null;
    }
}
