StarWars API
=================

**`API REST`** desenvolvida para guardar informações de planetas do universo **StarWars** onde pude utilizar alguns princípios de metodologias ágeis e desenvolver novas skills técnicas e de gestão de projetos.

Cartacterísticas
---------------

A API funciona através da utilização dos verbos **`HTTP`** e troca de arquivos **`JSON`**.

Cada planeta possui as seguintes características:
- id 
- name
- climate 
- terrain
###### * id gerado randomicamente


Funcionalidades
---------------

Funcionalidades disponibilizadas pela API:

- Adicionar planeta
- Listar planeta(s)
- Buscar por nome 
- Remover planeta(s).

Começando
---------------
Após iniciar a aplicação e o banco de dados, a API pode ser acessada através da URL ``http://localhost:8080/api/planet``.

### GET

O formato de envio de dados é o JSON, e deve ser seguir o template de exemplo abaixo:
```javascript
{
  "name" : "value",
  "climate" : "value",
  "terrain" : "value"
}
```
Caso o envio aconteça sem algum desses campos, o servidor irá retornar erro. Exemplo do envio e do status de erro:

##### JSON
```javascript
{
  "name" : "value",
  "terrain" : "value"
}
```
##### Response
```HTTP
Status: 400 (Bad Request)
```

### TEMPLATE EM CONSTRUÇÃO...
---------------


Tecnologias utilizadas
---------------

- Java
  - Eclipse
  - Maven 
  - Spring Boot 4
  - SpringBoot Data Rest
  - SpringBoot Data MongoDB
- MongoDB
