 #PGR301_applikasjon

Denne repositorien tilsvarer applikasjons koden til eksamen i emnet PGR301_2019 DevOps i skyen.

Tilsvarende Infastruktur-repo: [pgr301infra
](https://github.com/DevopsKristiania/pgr301infra)

## Hvordan kjøre applikasjonskoden
Applikasjonen deployes i CI miljøet for infastruktur repoet ved hjelp av Travis.ci når du pusher koden.
Nedenfor er en liste med det som opprettes når du pusher "master branch" til dette repositoriet.



## Innhold
- Embeded H2-database
- Enhetstester
- Dockerfil som bygger container Image av Spring Boot applikasjonen.
- Travis bygger et nytt Docker image for hver commit til applikasjonen sin master branch, og pusher til DockerHub.
  Link: [DockerHub](https://hub.docker.com/r/devopskristiania/pgr301exam)
- Krav til Applikasjonen er utført, men 