**Association - Back-End**
**Aperçu du Projet**

Ce projet est le back-end d'une application web développée dans le cadre du cours "Application Web" à l'ENSEEIHT (S8, 2024-2025). L'application "Association" permet de gérer une association à travers diverses fonctionnalités, telles que la gestion des utilisateurs, des missions, des dons, des discussions et des participations. Le back-end est construit avec Spring Boot et utilise PostgreSQL 17 comme base de données.

Ce README décrit la structure et le contenu du code back-end.
Structure du Code Back-End

Le code source du back-end est organisé dans le package racine com.giveandgo.association. Voici une description des principaux sous-packages et de leur rôle :
1. com.giveandgo.association

    Rôle : Package racine contenant la classe principale de l'application.
    Contenu :
        AssociationApplication.java : Point d'entrée de l'application Spring Boot. Cette classe contient la méthode main qui lance l'application.

2. com.giveandgo.association.model

    Rôle : Contient les entités JPA qui représentent les tables de la base de données.
    Contenu :
        Utilisateur.java : Entité représentant un utilisateur (administrateur, membre, bénévole) avec des champs comme email, motDePasse, nom, prenom, etc.
        Mission.java : Entité pour une mission, avec des champs comme nom, description, lieu, date, type (de type CategorieMission), et une relation @ManyToOne avec Membre.
        Don.java : Entité pour un don, avec des champs comme montant, date, nomDonateur, et une relation avec Mission.
        Membre.java : Entité pour un membre de l'association, héritant de Utilisateur, avec des champs comme dateInscription et roleUtilisateur.
        Benevole.java : Entité pour un bénévole, héritant de Utilisateur, avec des champs comme heuresContribuees et competences.
        Discussion.java : Entité pour une discussion liée à une mission, avec des champs comme dateCreation et une relation avec Mission.
        Message.java : Entité pour un message dans une discussion, avec des champs comme contenu, dateEnvoi, et des relations avec Discussion et Utilisateur.
        Participation.java : Entité pour une participation à une mission, avec des champs comme dateInscription et des relations avec Benevole et Mission.
        CategorieMission.java : Énumération pour le type de mission (ex. SOCIALE, ENVIRONNEMENTALE, EDUCATIVE).

3. com.giveandgo.association.repository

    Rôle : Contient les interfaces de repository Spring Data JPA pour interagir avec la base de données.
    Contenu :
        UtilisateurRepository.java : Repository pour l'entité Utilisateur.
        MissionRepository.java : Repository pour l'entité Mission, avec des méthodes comme findByType(CategorieMission type) pour rechercher des missions par type.
        DonRepository.java : Repository pour l'entité Don.
        MembreRepository.java : Repository pour l'entité Membre.
        BenevoleRepository.java : Repository pour l'entité Benevole.
        DiscussionRepository.java : Repository pour l'entité Discussion.
        MessageRepository.java : Repository pour l'entité Message.
        ParticipationRepository.java : Repository pour l'entité Participation.

4. com.giveandgo.association.service

    Rôle : Contient la logique métier de l'application.
    Contenu :
        UtilisateurService.java : Service pour gérer les utilisateurs (inscription, authentification, etc.).
        MissionService.java : Service pour gérer les missions (création, modification, recherche par type, etc.).
        DonService.java : Service pour gérer les dons (enregistrement, consultation, etc.).
        MembreService.java : Service pour gérer les membres.
        BenevoleService.java : Service pour gérer les bénévoles.
        DiscussionService.java : Service pour gérer les discussions.
        MessageService.java : Service pour gérer les messages.
        ParticipationService.java : Service pour gérer les participations aux missions.

5. com.giveandgo.association.controller

    Rôle : Contient les contrôleurs REST qui exposent les API pour le front-end.
    Contenu :
        UtilisateurController.java : Contrôleur pour les endpoints liés aux utilisateurs (ex. /api/utilisateurs).
        MissionController.java : Contrôleur pour les endpoints liés aux missions (ex. /api/missions).
        DonController.java : Contrôleur pour les endpoints liés aux dons (ex. /api/dons).
        MembreController.java : Contrôleur pour les endpoints liés aux membres.
        BenevoleController.java : Contrôleur pour les endpoints liés aux bénévoles.
        DiscussionController.java : Contrôleur pour les endpoints liés aux discussions.
        MessageController.java : Contrôleur pour les endpoints liés aux messages.
        ParticipationController.java : Contrôleur pour les endpoints liés aux participations.

6. com.giveandgo.association.config

    Rôle : Contient les configurations spécifiques de l'application (sécurité, CORS, etc.).
    Contenu :
        (Exemple) SecurityConfig.java : Configuration de la sécurité (si applicable, par exemple avec Spring Security).
        (Exemple) CorsConfig.java : Configuration CORS pour permettre les requêtes depuis le front-end.

7. src/main/resources

    Rôle : Contient les fichiers de configuration et les ressources statiques.
    Contenu :
        application.properties : Fichier de configuration principal pour Spring Boot, incluant les paramètres de la base de données PostgreSQL (URL, utilisateur, mot de passe, etc.).
        (Optionnel) data.sql ou schema.sql : Scripts SQL pour initialiser la base de données (si spring.jpa.hibernate.ddl-auto n'est pas utilisé pour créer le schéma).

**Technologies Utilisées**

    Spring Boot 3.4.3 : Framework principal pour le développement du back-end.
    Spring Data JPA : Pour l'accès aux données et la gestion des entités.
    PostgreSQL 17 : Base de données relationnelle.
    Hibernate : ORM pour mapper les entités Java aux tables de la base de données.
    Maven : Gestion des dépendances et construction du projet.
**Comment lancé le projet**
Assure toi de lancé le serveur de bases donées postgreSQL (version 17) et d'avoir crée une base de donéees nommée "association_db". Puis lancé l'application Spring à l'aide de la commande : ./mvnw spring-boot:run
