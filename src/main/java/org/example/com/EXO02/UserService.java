package org.example.com.EXO02;

public class UserService {
    private final UtilisateurApi utilisateurApi;
    public UserService(UtilisateurApi utilisateurApi) {
        this.utilisateurApi = utilisateurApi;
    }
    public void creerUtilisateur(Utilisateur utilisateur) {
        utilisateurApi.creerUtilisateur(utilisateur);
    }
}