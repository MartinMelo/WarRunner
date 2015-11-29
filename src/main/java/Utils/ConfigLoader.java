package Utils;

import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.Map;

/**
 * Created by Martin Alejandro Melo
 * on 21/07/2015.
 */
public class ConfigLoader {

    /**
     * Carga la configuracion para el nombre de archivo recibido.
     * @param nombreArchivo
     * @return Map
     * @throws java.net.URISyntaxException
     * @throws java.io.FileNotFoundException
     */
    public static Map cargarConfiguracion(String nombreArchivo) throws URISyntaxException, FileNotFoundException {
        File archivo = new File("./config.yml");
        InputStream configuracion = new FileInputStream(archivo);
        Yaml yaml = new Yaml();
        return (Map) yaml.load(configuracion);
    }
}
