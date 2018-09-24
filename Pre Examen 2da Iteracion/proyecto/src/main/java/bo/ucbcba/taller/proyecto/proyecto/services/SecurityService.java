package bo.ucbcba.taller.proyecto.proyecto.services;

/**
 * Created by Juan on 11/05/2017.
 */
public interface SecurityService {
    String findLoggedInUsername();

    void autologin(String username, String password);
}
