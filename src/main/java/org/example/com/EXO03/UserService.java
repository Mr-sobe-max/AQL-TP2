package org.example.com.EXO03;

public class UserService {
    private final UtilisateurApi utilisateurApi;

    public UserService(UtilisateurApi utilisateurApi) {
        this.utilisateurApi = utilisateurApi;
    }

    public void creerUtilisateur(Utilisateur utilisateur) throws ServiceException {
        if (utilisateur.getEmail() == null || utilisateur.getEmail().isEmpty()) {
            return; // Validation simple
        }

        int id = utilisateurApi.creerUtilisateur(utilisateur);
        utilisateur.setId(id);
    }
}
