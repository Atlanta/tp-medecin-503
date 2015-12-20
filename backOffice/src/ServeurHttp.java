import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * Serveur back-office
 * Gère la communication entre le site web et le RMI
 * @author Julien Hubert
 * @version 17/12/2015
 */
public class ServeurHttp {

    public static void main(String[] args) {
        HttpServer serveur = null;
        try {
            serveur = HttpServer.create(new InetSocketAddress(8181), 0);
        } catch(IOException e) {
            System.err.println("Erreur lors de la création du serveur " + e);
            System.exit(-1);
        }

        serveur.createContext("/login.php", new LoginHandler());
        serveur.createContext("/ajax.php", new AjaxHandler());
        serveur.setExecutor(null);
        serveur.start();

        System.out.println("Serveur démarré.");
    }
}