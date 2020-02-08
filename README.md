# Gestao-Cidades-API_Java

API REST para Gestão de Cidades com Spring-Boot, Spring-JPA, flyway, Swagger e MySQL.

O Principal objetivo deste projeto foi participar de um desafio proposto, além de ser uma grande oportunidade para estudar e evoluir em relação as tecnologias mencionadas. Resumidamente, é uma aplicação REST com intuito de gerenciar cidades. Incluindo dentre suas principais propostas: fazer o upload de um arquivo texto (CSV) com milhares de cidades e cadastrá-las no banco de dados; encontrar a distância entre as duas cidades mais longes, tendo como referência suas coordenadas geográficas (latitude/Longitude); CRUD de forma geral; e outros diversos desafios.

### Documentação da API com detalhes de acesso aos Endpoints.
Obs: disponível apenas ao executar a aplicação. 
- http://localhost:8080/swagger-ui.html


#### Instruções para execução da API
- Fazer um GIT Clone
- Instalar servidor MySql MariaDB
- criar a base de dados 'gestaocidades'
- Configurar usuário e senha de preferência
- Alterar a configuração de usuário e senha no arquivo 'application.properties'


#### Nota
Após realizar as intruções para execução, basta rodar a apicação que a estrututa de banco será implementada automaticamente e o sistemas estará pronto para uso. O projeto ainda pode ser melhorado em diversos aspectos para atender boas práticas de programação, como: Melhor definição e especificação de exceções, versionamento, adicionar mais padrões de projetos (Design Patterns), melhorar valiações para retornar códigos de resposta HTTP mais precisos, etc.


### Sugestão de Leitura: Princípios e boas práticas REST
- https://www.smarti.blog.br/api-rest-principios-boas-praticas-para-arquiteturas-restful/
