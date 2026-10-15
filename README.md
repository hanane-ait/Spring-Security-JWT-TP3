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
<img width="1020" height="593" alt="image" src="https://github.com/user-attachments/assets/7eaaf897-829a-4a82-9451-17584f725ebb" />


### 8.2 Connexion --- Login

<img width="1020" height="574" alt="image" src="https://github.com/user-attachments/assets/5c503827-93f2-4854-81be-78311cfa669f" />



## 19. Conclusion

Ce projet illustre la mise en place d'une API REST Spring Boot avec
gestion des utilisateurs et authentification basée sur JWT. Il permet de
comprendre le rôle des contrôleurs, DTO, entités, repository, services
JWT et configuration Spring Security, ainsi que de valider le
fonctionnement au moyen de tests HTTP avec Postman.
