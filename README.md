### WebFlux REST API - Resumo

A **WebFlux REST API** é um projeto de transformação criado para consolidar os conhecimentos adquiridos em Spring Webflux e MongoDB.

O projeto consiste em uma API que permite salvar e recuperar um conjunto de playlists no banco de dados, de maneira reativa, assíncrona e não bloqueante.

O projeto foi inspirado no curso [API REST com Spring Webflux e MongoDB](https://www.youtube.com/playlist?list=PL8iIphQOyG-CyD9uuRTMiqxEut5QAKHga) — disponível no YouTube no canal [Michelli Brito](https://www.youtube.com/@MichelliBrito).

### Tecnologias
  * Java SE (21)
  * Spring Boot 3
    * Spring Reactive Web [WEB]
    * Spring Data Reactive MongoDB [NoSQL]
  * Maven
  * MongoDB

**Obs.:** O Spring WebFlux (Spring Reactive Web) utiliza o servidor Netty (servidor que trabalha com tempo de execução assíncrono) ao invés do TomCat, como geralmente ocorre em uma aplicação tradicional com Spring MVC.
  
### IDE  
  * Visual Studio Code

### Perfis de Inicialização
A aplicação possui dois perfis de inicialização: test e dev.

No arquivo application.properties, o perfil test é configurado como padrão.


