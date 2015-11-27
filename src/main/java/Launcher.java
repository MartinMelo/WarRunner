import org.apache.catalina.startup.Tomcat;

/**
 * Created by Martin Alejandro Melo
 * on 27/11/2015.
 */
public class Launcher {

    public static void main(final String[] args) throws Exception {
        final Tomcat server = new Tomcat();
        server.setPort(8080);
        server.setBaseDir(".");

        server.getHost().setAppBase(".");
        server.getHost().setAutoDeploy(true);
        server.getHost().setDeployOnStartup(true);

        server.addWebapp("/", "app.war");
        server.start();
    }
}
