package Utils;


import java.util.Map;

/**
 * Created by Martin Alejandro Melo
 * on 20/07/2015.
 */
public class Configuracion {

    //Mapa con la configuracion.
    private Map<String, Map> configuracion;

    public Configuracion() {
        try {
            this.configuracion = ConfigLoader.cargarConfiguracion("config");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Object get(String key){
        return this.configuracion.get(key);
    }
}
