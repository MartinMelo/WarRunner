package Main;

import Utils.Configuracion;
import org.apache.catalina.startup.Tomcat;

import java.util.Map;
import java.util.TimeZone;

/**
 * Created by Martin Alejandro Melo
 * on 27/11/2015.
 */
public class Launcher {

    private final Integer puerto;
    private final String warName;
    private final String nameSpace;
    private final String baseDir;
    private final String appBase;
    private final Map<String, String> systemProperties;
    private Configuracion configuracion;

    public Launcher() {
        this.configuracion = new Configuracion();
        this.puerto = (Integer) this.configuracion.get("port");
        this.warName = (String) this.configuracion.get("warName");
        this.nameSpace = (String) this.configuracion.get("nameSpace");
        this.baseDir = (String) this.configuracion.get("baseDir");
        this.appBase = (String) this.configuracion.get("appBase");
        this.systemProperties = (Map<String, String>) this.configuracion.get("systemProperties");
    }

    /**
     * Carga las properties del enviroment.
     */
    private void configurarProperties() {
        System.setProperty("nombreDeSala", this.systemProperties.get("config.nombreDeSala"));
        System.setProperty("user.language", this.systemProperties.get("user.language"));
        System.setProperty("user.language.format", this.systemProperties.get("user.language.format"));
        System.setProperty("user.country", this.systemProperties.get("user.country"));
        System.setProperty("user.country.format", this.systemProperties.get("user.country.format"));
        TimeZone.setDefault(TimeZone.getTimeZone( this.systemProperties.get("user.timezone")));
        this.imprimirConfiguracionCargada();
    }

    /**
     * Imprime la configuracion modificada desde el archivo de configuracion.
     */
    private void imprimirConfiguracionCargada() {
        System.out.println("Using User Timezone: " + TimeZone.getDefault().getDisplayName());
        System.out.println("Using User Language: " + System.getProperty("user.language"));
        System.out.println("Using User Language Format: " + System.getProperty("user.language.format"));
        System.out.println("Using User Country: " + System.getProperty("user.country"));
        System.out.println("Using User Country Format: " + System.getProperty("user.country.format"));
    }

    public void launch() throws Exception {
        this.configurarProperties();
        final Tomcat server = new Tomcat();
        server.setPort(this.puerto);
        server.setBaseDir(this.baseDir);

        server.getHost().setAppBase(this.appBase);
        server.getHost().setAutoDeploy(true);
        server.getHost().setDeployOnStartup(true);
        server.addWebapp(this.nameSpace, this.warName + ".war");
        server.start();
    }
    public static void main(final String[] args) throws Exception {
        Launcher launcher = new Launcher();
        launcher.launch();
    }
}
