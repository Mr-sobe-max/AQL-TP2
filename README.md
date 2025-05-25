# TP2 : Tests Unitaires avec JUnit 5 et Mockito - Mockups

Ce projet implémente le TP2 sur les tests unitaires avec JUnit 5 et Mockito, en utilisant des mockups pour isoler les composants.

## Objectifs
- Comprendre l'importance des tests unitaires.
- Utiliser des mockups pour l'isolation.
- Maîtriser JUnit 5 et Mockito.
- Différencier les tests d'état et d'interaction.

## Structure du Projet
- **src/main/java/org.example.com/EXO{numeroExo}**:
  - EXO1/ `Calculatrice.java`
  - EXO2/ et EXO3/  `Utilisateur.java`, `UtilisateurApi.java`, `ServiceException.java`, `UserService.java`
  - EXO4/ `De.java`, `Joueur.java`, `Banque.java`, `BanqueImpl.java`, `DebitImpossibleException.java`, `JeuFermeException.java`, `Jeu.java`
- **src/test/java/EXO0{numeroExo}/**:
  - EXO01/ `CalculatriceTest.java`
  - EXO02/ `UserServiceTest.java`
  - EXO03/ `UserServiceScenariosTest.java`
  - EXO04/ `JeuTest.java`, `JeuAvecBanqueReelleTest.java`
- **pom.xml**: Configuration Maven.
- **README.md**: Ce Fichier

## Exercices

- **Exercice 1 : Calculatrice**
  - Classe : Calculatrice (mockée)
  - Tests : Interaction (vérifie l'appel à additionner).
  - Fichier : EXO01/CalculatriceTest.java

- **Exercice 2 : UserService**
  - Classe : UserService
  - Tests : Interaction (mocke UtilisateurApi).
  - Fichier : EXO01/UserServiceTest.java

- **Exercice 3 : Scénarios UserService**
  - Classe : UserService
  - Tests :
    - Gestion des exceptions.
    - Erreur de validation.
    - Attribution d'ID.
    - Capture d'arguments.
  - Fichier : EXO01/UserServiceScenariosTest.java
  - Réponses aux Questions :
    - Scénario 1 (Lever une exception) : Le test testCreerUtilisateurThrowsServiceException configure le mock UtilisateurApi pour lever une ServiceException lors de l'appel à creerUtilisateur. Il vérifie que l'exception est bien levée et que la méthode a été appelée.
    - Scénario 2 (Erreur de validation) : Le test testCreerUtilisateurValidationError crée un utilisateur avec des champs vides et vérifie que creerUtilisateur n'est jamais appelé sur le mock, en utilisant verify(..., never()).
    - Scénario 3 (Attribution d'ID) : Le test testCreerUtilisateurSetsId configure le mock pour assigner un ID (123) à l'utilisateur via doAnswer. Il vérifie que l'utilisateur a bien cet ID après l'appel à creerUtilisateur.
    - Scénario 4 (Capture d'arguments) : Le test testCreerUtilisateurArgumentCapture utilise ArgumentCaptor pour capturer l'utilisateur passé à creerUtilisateur. Il vérifie que les attributs (prénom, nom, email) correspondent à ceux de l'utilisateur créé.

- **Exercice 4 : Jeu de Dés** 
  - Classe : Jeu
  - Réponses aux Questions :
    - Objets Mockés : Les objets Banque, Joueur, et De (de1, de2) sont mockés. Raison : Ce sont des dépendances externes de la classe Jeu. Les mocker permet d'isoler la méthode jouer pour tester son comportement sans dépendre des implémentations réelles, qui peuvent être complexes ou non déterministes (par exemple, le lancer de dés).
    - Scénarios (Classes d'Équivalence) :
    - Jeu fermé : Lancer une JeuFermeException si le jeu est fermé avant de jouer.
    - Joueur insolvable : Lancer une DebitImpossibleException si le joueur ne peut pas être débité de sa mise.
    - Somme des dés ≠ 7 : Le joueur est débité, la banque est créditée, et le jeu s'arrête sans gain.
    - Somme des dés = 7, banque solvable : Le joueur gagne (crédité de 2 × mise), la banque est débitée, et le jeu reste ouvert.
    - Somme des dés = 7, banque insolvable : Le joueur gagne, la banque est débitée, et le jeu se ferme car la banque n'est plus solvable.
  - Tests: 
    - Test Jeu Fermé : Testé dans testJouerJeuFerme. C'est un test d'état, car il vérifie l'état du jeu (fermé) en s'assurant que jouer lève une JeuFermeException.
    - Test Joueur Insolvable : Testé dans testJouerJoueurInsolvable. C'est un test d'interaction, car il vérifie que les dés ne sont pas lancés (verifyNoInteractions(de1, de2)) lorsque le joueur est insolvable. Il utilise verify pour confirmer l'absence d'interaction avec les dés.
    - Tests mockés (JeuTest) et test d'intégration (JeuAvecBanqueReelleTest).
  - Fichiers : EXO04/JeuTest.java, EXO04/JeuAvecBanqueReelleTest.java
  - Différence entre Mocké vs Réel :
    - Mocké : Rapide, contrôlé, idéal pour tester la logique de Jeu en isolation.
    - Réel : Réaliste, teste l'intégration avec BanqueImpl, mais plus lent et dépendant de l'implémentation.

## Auteurs
[BOUKHIBAR Mounir]
