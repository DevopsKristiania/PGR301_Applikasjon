 #PGR301_applikasjon

Denne repositorien tilsvarer applikasjons koden til eksamen i emnet PGR301_2019 DevOps i skyen.
Applikasjonen er en springboot-applikasjon skrevet i Kotlin. 

Tilsvarende Infastruktur-repo: [pgr301infra
](https://github.com/DevopsKristiania/pgr301infra)

## Hvordan kjøre applikasjonskoden
Applikasjonen deployes i CI miljøet for infastruktur repoet ved hjelp av Travis.ci når du pusher koden,
 og er lagt til infra repoet som remote.

Når du pusher "master" branch deployes kodebasen til CI miljøet i infra repoet.

Nedenfor er en liste med innhold + det som opprettes når du pusher "master" branch.


## Innhold
- Embeded H2-database

  http://localhost:8080/h2-console
- Enhetstester

    travis er konfigurert slikt at mvn test kjøres.
- Dockerfil som bygger container Image av Spring Boot applikasjonen.
- Travis bygger et nytt Docker image for hver commit til applikasjonen sin master branch, og pusher til DockerHub.
  Link: [DockerHub](https://hub.docker.com/r/devopskristiania/pgr301exam)
- Krav til Applikasjonen er utført, men desverre ikke på ønskelig måte. Den er skrevet på en måte som prøver å følge "best practice"
for rest-api'er. 