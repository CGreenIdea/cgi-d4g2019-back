# Back Project for the challenge Design4Green by CGI in Luxembourg

## Technologies used

### Front

* NodeJS + Webpack
* Hugo (gohugo.io)

### Back

* Java 8
* Quarkus
* JPA with panache

## Quick start

1. Prerequisites: you should have Git, Node.js and NPM installed.<br>
2. Clone the repository.<br>

## Licences

Les sources de ce challenge restent la propriété de l'équipe de développeurs CGreenIdea (CGI):

- Cyrille CHOPELET
- Cyril MELLA
- Aurore SIMON
- Alexandre VALLES
- Fabien HANESSE
  
## Contribution

### Running the dev mode

* `mvn compile quarkus:dev` : démarre un serveur local (http://localhost:8080 et http://localhost:8080/swagger-ui/)

## Login

* A login page is available for security testing at http://localhost:8080/

## Front

* `npm i` : installation des dépendances (une fois après le clone ou mise à jour des dépendances)
* `npm run start` : démarre un serveur local (http://localhost:3000)
* `npm run build` : construit le front dans le répertoire `dist`
