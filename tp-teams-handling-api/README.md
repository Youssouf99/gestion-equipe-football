# Getting Started

### Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.3.0/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.3.0/maven-plugin/reference/html/#build-image)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/3.3.0/reference/htmlsingle/index.html#data.sql.jpa-and-spring-data)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/3.3.0/reference/htmlsingle/index.html#using.devtools)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.3.0/reference/htmlsingle/index.html#web)

### Guides

The following guides illustrate how to use some features concretely:

* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)


# Explications des tests

* `@WebMvcTest` : Cette annotation est utilisée pour tester les contrôleurs Spring MVC. Elle configure uniquement les parties nécessaires de l'infrastructure Spring MVC et ne charge pas l'ensemble de l'application.

* `@Mock et @InjectMocks` : Ces annotations de Mockito sont utilisées pour injecter les dépendances simulées dans le contrôleur TeamController.

* `MockMvc` : C'est une classe fournie par Spring pour tester les contrôleurs sans démarrer un serveur HTTP.

* `setup()` : Cette méthode configure les mocks avant chaque test.

* `@BeforeEach` : Annotation JUnit pour exécuter une méthode avant chaque test.

* `@Test` : Annotation JUnit pour indiquer que la méthode est un test.

* `MockitoAnnotations.openMocks(this)` : Initialisation des mocks avec Mockito.

* `when(...).thenReturn(...) `: Utilisé pour définir le comportement attendu du mock.

* `perform(...)` : Méthode de MockMvc pour exécuter la requête.

* `andExpect(...)` : Méthodes de MockMvcResultMatchers pour vérifier les résultats attendus de la requête.

# Explications
* `@SpringBootTest` : Cette annotation indique à Spring Boot de charger l'application entière pour le test.

* `@AutoConfigureMockMvc` : Cette annotation est utilisée pour configurer automatiquement MockMvc.

* `@Mock et @InjectMocks` : Ces annotations de Mockito sont utilisées pour injecter les dépendances simulées dans le contrôleur TeamController.

* `@Autowired` : Utilisé pour injecter le WebApplicationContext.

* `@BeforeEach` : Cette méthode est exécutée avant chaque test pour configurer Rest Assured Mock MVC avec le contexte de l'application Web.

* `MockitoAnnotations.openMocks(this)` : Initialisation des mocks avec Mockito.

* `RestAssuredMockMvc.mockMvc(mockMvc)` : Cette méthode configure Rest Assured Mock MVC avec le MockMvc configuré pour votre contrôleur.

* `given(), when(), then()` : Méthodes de Rest Assured pour spécifier les conditions initiales, l'action à tester, et les assertions à effectuer.

* `.get(), .post(), .delete()` : Méthodes pour effectuer les requêtes HTTP GET, POST, DELETE respectivement.

* `.statusCode()` : Vérifie le code de statut HTTP retourné.

* `.contentType()` : Vérifie le type de contenu retourné.

* `.body()` : Vérifie le contenu du corps de la réponse.


# Explications des ajouts et modifications :
>`Test testGetAllTeams` : Ajout de vérifications pour chaque élément de la liste retournée afin de s'assurer que les attributs sont corrects.

> `Test testGetTeamById` : Vérifie que les détails de l'équipe retournée par l'ID sont corrects.

> `Test testCreateTeam `: Vérifie que la création d'une nouvelle équipe fonctionne et que les détails de l'équipe créée sont corrects.

> `Test testDeleteTeam `: Vérifie que la suppression d'une équipe retourne le statut 204 No Content.




Pour annuler un commit dans Git, il existe plusieurs méthodes, chacune ayant ses propres avantages et inconvénients. Le choix de la méthode la plus adaptée dépend de votre situation spécifique :

**1. `git reset`**

La commande `git reset` permet de déplacer le pointeur HEAD vers un commit antérieur, annulant ainsi les commits ultérieurs. Elle est utile pour annuler des commits locaux qui n'ont pas encore été poussés vers un dépôt distant.

**Syntaxe de base :**

```
git reset <commit>
```

**Options utiles :**

* `--soft` : Annule le commit mais conserve les modifications dans l'index. Vous devrez ensuite commiter ces modifications manuellement.
* `--hard` : Annule le commit et supprime toutes les modifications non sauvegardées dans l'index et dans l'arborescence de travail.

**Exemple :**

Pour annuler le dernier commit local et conserver les modifications dans l'index, utilisez la commande suivante :

```
git reset --soft HEAD~1
```

**2. `git revert`**

La commande `git revert` crée un nouveau commit qui inverse les modifications apportées par un commit spécifique. Cette méthode est utile pour annuler des commits qui ont déjà été poussés vers un dépôt distant, car elle permet de préserver l'historique des commits.

**Syntaxe de base :**

```
git revert <commit>
```

**Exemple :**

Pour annuler le dernier commit poussé vers le dépôt distant, utilisez la commande suivante :

```
git revert HEAD~1
```

**3. `git checkout`**

La commande `git checkout` permet de changer de branche ou de restaurer un fichier à une version antérieure. Elle peut être utilisée pour annuler un commit en restaurant l'état des fichiers à l'état précédant le commit.

**Syntaxe de base :**

```
git checkout <fichier> <commit>
```

**Exemple :**

Pour restaurer le fichier `index.html` à l'état précédant le dernier commit, utilisez la commande suivante :

```
git checkout index.html HEAD~1
```

**Remarques importantes :**

* **Annuler un commit local est simple.** Il vous suffit d'utiliser la commande `git reset` avec l'option appropriée.
* **Annuler un commit distant est plus délicat.** Il est important de ne pas réécrire l'historique des commits, car cela pourrait causer des problèmes aux autres contributeurs. Utilisez la commande `git revert` pour créer un nouveau commit qui inverse les modifications du commit annulé.
* **Avant d'annuler un commit, assurez-vous que vous avez sauvegardé toutes les modifications importantes.** Les commits annulés ne peuvent généralement pas être récupérés.

**Ressources supplémentaires :**



N'hésitez pas à me poser d'autres questions si vous avez besoin de plus d'informations.