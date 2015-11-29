package Main;

import Utils.Configuracion;
import org.apache.catalina.startup.Tomcat;

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
    private Configuracion configuracion;

    public Launcher() {
        this.configuracion = new Configuracion();
        this.puerto = (Integer) this.configuracion.get("port");
        this.warName = (String) this.configuracion.get("warName");
        this.nameSpace = (String) this.configuracion.get("nameSpace");
        this.baseDir = (String) this.configuracion.get("baseDir");
        this.appBase = (String) this.configuracion.get("appBase");
    }

    public void launch() throws Exception {
        final Tomcat server = new Tomcat();
        server.setPort(this.puerto);
        server.setBaseDir(this.baseDir);

        server.getHost().setAppBase(this.appBase);
        server.getHost().setAutoDeploy(true);
        server.getHost().setDeployOnStartup(true);

        server.addWebapp(this.nameSpace, this.warName+".war");
        server.start();
    }
    public static void main(final String[] args) throws Exception {
        Launcher launcher = new Launcher();
        launcher.launch();
    }
}
