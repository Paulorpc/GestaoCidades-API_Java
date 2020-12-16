# Gestao-Cidades-API_Java

API REST para Gestão de Cidades com Spring-Boot, Spring-JPA, Flyway, Swagger e MySQL.

O Principal objetivo deste projeto foi participar de um desafio proposto, além de ser uma grande oportunidade para estudar e evoluir em relação as tecnologias mencionadas. Resumidamente, é uma aplicação REST com intuito de gerenciar cidades. Incluindo dentre suas principais propostas: fazer o upload de um arquivo texto (CSV) com milhares de cidades e cadastrá-las no banco de dados; encontrar a distância entre as duas cidades mais longes, tendo como referência o par de coordenadas geográficas (latitude/Longitude); CRUD de forma geral; e outros diversos desafios.

### Documentação da API com detalhes de acesso aos Endpoints.
Obs: disponível apenas ao executar a aplicação. 
- http://localhost:8080/swagger-ui.html


#### Instruções para execução da API
- Fazer um GIT Clone
- Instalar servidor MySql MariaDB
- criar a base de dados 'gestaocidades'
- Configurar usuário e senha de preferência
- Alterar a configuração de usuário e senha no arquivo 'application.properties'


#### MARIADB COM DOCKER
Uma possibilidade para montadem do ambiente é utilizar o banco de dados através de containers. O Docker possibilita usarmos esse ambiente com muita facilidade. Será necessário apenas baixar a imagem e criar o database. No demais, a aplicação se vira com o migration através do flyway. Para montar o ambiente de BD, realize os seguintes comandos:
```shell
$ docker pull mariadb
$ docker run --name mariadb -e MYSQL_ROOT_PASSWORD=root -eMYSQL_DATABASE=gestaocidades -p 3306:3306 -d mariadb:latest
```

Para certificar que o container e está em execução e que a base foi gerada corretamente:
````shell
$ docker exec -it mariadb bash
$ mysql -u root -p
$ show databases; 
```


#### Nota
Após realizar as instruções para execução, basta rodar a aplicação que a estrutura de banco será implementada automaticamente e o sistema estará pronto para uso. O projeto ainda pode ser melhorado em diversos aspectos para atender boas práticas de programação, como: Melhor definição e especificação de exceções, versionamento, adicionar mais padrões de projetos (Design Patterns), melhorar validações para retornar códigos de resposta HTTP mais precisos, etc.


### Sugestão de Leitura: Princípios e boas práticas REST
- https://www.smarti.blog.br/api-rest-principios-boas-praticas-para-arquiteturas-restful/
