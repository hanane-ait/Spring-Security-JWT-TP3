# TP2 --- Authentification sécurisée avec Spring Boot et JWT

## 1. Présentation du projet

Ce projet est une application backend développée avec **Java et Spring
Boot**. Elle met en place un mécanisme d'inscription et de connexion des
utilisateurs, avec une authentification basée sur des **JSON Web Tokens
(JWT)**.

L'objectif est de permettre à un utilisateur de créer un compte, de se
connecter avec ses identifiants et d'utiliser un jeton pour accéder aux
ressources protégées de l'application.

> **Note :** vérifiez les chemins exacts des endpoints dans
> `AuthController.java` et `UserController.java` avant de lancer les
> tests. Les chemins indiqués dans les exemples ci-dessous sont à
> adapter si votre code utilise d'autres mappings.

## 2. Fonctionnalités principales

-   **Inscription (Register)** : réception des informations d'un nouvel
    utilisateur et création de son compte.
-   **Connexion (Login)** : vérification des identifiants de
    l'utilisateur.
-   **Authentification JWT** : génération d'un token après une
    authentification réussie.
-   **Validation du token** : lecture et validation du JWT dans les
    requêtes protégées.
-   **Protection des routes** : configuration des règles de sécurité à
    l'aide de Spring Security.
-   **Accès aux données** : utilisation d'un repository pour rechercher
    les utilisateurs.
-   **Gestion des utilisateurs** : présence d'un contrôleur dédié aux
    opérations liées aux utilisateurs.

## 3. Technologies utilisées

-   Java
-   Spring Boot
-   Spring Web
-   Spring Security
-   Spring Data JPA
-   JWT (JSON Web Token)
-   Maven
-   Base de données configurée dans `application.properties`
-   Postman ou un outil équivalent pour tester l'API REST

## 4. Architecture du projet

``` text
tp2/
├── pom.xml
├── mvnw
├── mvnw.cmd
└── src/
    ├── main/
    │   ├── java/org/example/tp2/
    │   │   ├── Tp2Application.java
    │   │   ├── SecurityConfig.java
    │   │   ├── controller/
    │   │   │   ├── AuthController.java
    │   │   │   └── UserController.java
    │   │   ├── dto/
    │   │   │   ├── AuthResponse.java
    │   │   │   ├── LoginRequest.java
    │   │   │   └── RegisterRequest.java
    │   │   ├── entities/
    │   │   │   └── AppUser.java
    │   │   ├── repository/
    │   │   │   └── UserRepository.java
    │   │   ├── security/
    │   │   │   └── JwtFilter.java
    │   │   └── service/
    │   │       ├── CustomUserDetailsService.java
    │   │       └── JwtService.java
    │   └── resources/
    │       └── application.properties
    └── test/
        └── java/org/example/tp2/
            └── Tp2ApplicationTests.java
```

### Rôle des principaux composants

  -----------------------------------------------------------------------
  Composant                           Responsabilité
  ----------------------------------- -----------------------------------
  `AuthController`                    Expose les endpoints d'inscription
                                      et de connexion.

  `UserController`                    Expose les opérations liées aux
                                      utilisateurs.

  `RegisterRequest`                   Transporte les données envoyées
                                      lors de l'inscription.

  `LoginRequest`                      Transporte les identifiants envoyés
                                      lors de la connexion.

  `AuthResponse`                      Structure la réponse
                                      d'authentification, notamment le
                                      token si le contrôleur le renvoie
                                      ainsi.

  `AppUser`                           Représente l'entité utilisateur
                                      persistée.

  `UserRepository`                    Permet d'accéder aux utilisateurs
                                      dans la base de données.

  `CustomUserDetailsService`          Charge les informations d'un
                                      utilisateur pour Spring Security.

  `JwtService`                        Crée et/ou valide les tokens JWT.

  `JwtFilter`                         Intercepte les requêtes pour
                                      examiner le token
                                      d'authentification.

  `SecurityConfig`                    Définit les règles de sécurité et
                                      la configuration des filtres.
  -----------------------------------------------------------------------

## 5. Prérequis

Avant de démarrer, installer ou vérifier :

-   JDK compatible avec la version Java déclarée dans `pom.xml`.
-   Maven (ou utiliser le Maven Wrapper fourni).
-   La base de données utilisée par la configuration du projet.
-   Postman pour envoyer les requêtes HTTP.

Vérifier la version de Java :

``` bash
java -version
```

## 6. Configuration

Ouvrir le fichier `src/main/resources/application.properties` et
vérifier :

-   le port du serveur ;
-   l'URL de la base de données ;
-   le nom d'utilisateur et le mot de passe de la base de données ;
-   la stratégie de génération ou de mise à jour du schéma, si elle est
    configurée ;
-   le secret JWT et sa durée de validité, si ces propriétés sont
    définies.

**Sécurité :** ne jamais publier un vrai mot de passe de base de données
ni une clé secrète JWT dans le dépôt. Pour un déploiement, privilégier
les variables d'environnement.

## 7. Installation et lancement

Depuis le dossier du projet :

### Windows

``` powershell
.\mvnw.cmd clean install
.\mvnw.cmd spring-boot:run
```

### Linux / macOS

``` bash
chmod +x mvnw
./mvnw clean install
./mvnw spring-boot:run
```

Vous pouvez également ouvrir le projet dans IntelliJ IDEA, attendre la
résolution des dépendances Maven, puis lancer la classe
`Tp2Application`.

Une fois l'application démarrée, consulter les logs pour vérifier que
Spring Boot a bien démarré et que la connexion à la base de données a
réussi.

## 8. Tester l'API avec Postman

Remplacer `8080` par le port configuré dans `application.properties`,
puis utiliser les chemins exacts définis dans `AuthController.java`.

### 8.1 Inscription --- Register

-   Méthode HTTP : `POST`
-   URL : `http://localhost:8080/<endpoint-register>`
-   En-tête : `Content-Type: application/json`
-   Corps : sélectionner **Body → raw → JSON** et fournir les champs
    attendus par `RegisterRequest`.

Exemple indicatif --- adapter les noms de champs à la classe DTO :

``` json
{
  "username": "hanane",
  "password": "MotDePasseTest123"
}
```

Résultat attendu : une réponse confirmant la création du compte.
Vérifier également le code HTTP renvoyé et que l'utilisateur est bien
enregistré en base.

**Capture d'écran à ajouter :** réponse Postman de l'inscription
réussie, avec la méthode, l'URL, le corps de la requête et le statut
HTTP visibles.

``` text
![Inscription réussie](docs/screenshots/register-success.png)
```

### 8.2 Connexion --- Login

-   Méthode HTTP : `POST`
-   URL : `http://localhost:8080/<endpoint-login>`
-   En-tête : `Content-Type: application/json`
-   Corps : fournir les identifiants attendus par `LoginRequest`.

Exemple indicatif :

``` json
{
  "username": "hanane",
  "password": "MotDePasseTest123"
}
```

Résultat attendu : si les identifiants sont valides, l'API doit renvoyer
une réponse d'authentification. Si le projet utilise JWT, cette réponse
contient normalement un token. Le nom exact du champ dépend de
`AuthResponse`.

**Capture d'écran à ajouter :** réponse Postman du login réussi, en
montrant le statut HTTP et le token retourné. Masquer le token avant de
partager publiquement la capture.

``` text
![Connexion réussie](docs/screenshots/login-success.png)
```

### 8.3 Vérification d'une route protégée

Si `UserController` expose une route protégée :

1.  Copier le token obtenu à la connexion.
2.  Envoyer une requête vers l'endpoint protégé.
3.  Dans **Authorization**, choisir **Bearer Token**.
4.  Coller le token et envoyer la requête.
5.  Vérifier que la réponse correspond au comportement prévu par la
    configuration de sécurité.

Sans token ou avec un token invalide/expiré, une route protégée devrait
normalement refuser la requête.

**Capture d'écran à ajouter :** appel réussi d'une route protégée avec
le token Bearer.

``` text
![Accès protégé](docs/screenshots/protected-route.png)
```

### 8.4 Tester les erreurs

Pour compléter les tests, vérifier au minimum :

-   inscription avec des données manquantes ;
-   tentative d'inscription avec un utilisateur déjà existant, si cette
    règle est implémentée ;
-   connexion avec un mauvais mot de passe ;
-   accès à une route protégée sans token ;
-   accès avec un token invalide ou expiré.

Les codes HTTP et les messages exacts dépendent de la gestion des
exceptions implémentée dans le projet.

**Capture d'écran à ajouter :** exemple d'une réponse d'erreur attendue.

``` text
![Test d'erreur](docs/screenshots/auth-error.png)
```

## 9. Captures d'écran du projet

Créer le dossier suivant à la racine du dépôt :

``` text
docs/
└── screenshots/
    ├── register-success.png
    ├── login-success.png
    ├── protected-route.png
    ├── auth-error.png
    └── application-startup.png
```

Captures recommandées :

1.  **Démarrage de l'application** : logs Spring Boot montrant que le
    serveur a démarré.
2.  **Register réussi** : requête et réponse Postman.
3.  **Login réussi** : réponse contenant le JWT, avec le token masqué si
    le dépôt est public.
4.  **Route protégée** : requête avec `Authorization: Bearer ...`.
5.  **Accès refusé** : requête sans token ou avec un token invalide.
6.  **Persistance en base de données** : utilisateur enregistré, sans
    afficher de mot de passe ni de données sensibles.

Pour afficher les images dans GitHub, utiliser les chemins relatifs
ci-dessus et placer les fichiers image dans `docs/screenshots/`.

## 10. Bonnes pratiques de sécurité

-   Ne jamais stocker les mots de passe en clair ; utiliser un encodeur
    adapté, par exemple `BCryptPasswordEncoder`.
-   Ne jamais exposer le secret de signature JWT dans le code public.
-   Définir une durée de validité raisonnable pour les tokens.
-   Valider les données reçues par l'API.
-   Protéger toutes les routes qui ne sont pas explicitement publiques.
-   Éviter d'enregistrer le JWT ou les mots de passe dans les logs.
-   Ne pas committer de secrets ni de fichiers contenant des
    identifiants réels.

## 11. Limites et vérifications à effectuer

Avant de considérer le projet comme terminé, confirmer dans le code :

-   les chemins exacts des endpoints register et login ;
-   les noms exacts des champs de `RegisterRequest` et `LoginRequest` ;
-   le format de la réponse définie par `AuthResponse` ;
-   les routes réellement publiques et protégées dans `SecurityConfig` ;
-   le comportement prévu en cas d'identifiants invalides ;
-   la configuration effective de la base de données et du secret JWT.

## 12. Conclusion

Ce projet illustre la mise en place d'une API REST Spring Boot avec
gestion des utilisateurs et authentification basée sur JWT. Il permet de
comprendre le rôle des contrôleurs, DTO, entités, repository, services
JWT et configuration Spring Security, ainsi que de valider le
fonctionnement au moyen de tests HTTP avec Postman.
