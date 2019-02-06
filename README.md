StarWars API
=================

**``API REST``** desenvolvida para guardar informações de planetas do universo **StarWars**.

Cartacterísticas
---------------

A API funciona através da utilização de verbos ``HTTP`` e troca de arquivos ``JSON``.

Cada planeta possui as seguintes características:
- Id 
- Name
- Climate 
- Terrain
###### * Id gerado randomicamente


Funcionalidades
---------------

Funcionalidades disponibilizadas pela API:

- Adicionar planeta
- Listar planeta(s)
- Atualizar planeta
- Buscar planeta por nome 
- Remover planeta(s).

Começando
---------------
Após iniciar a aplicação e o banco de dados, a API pode ser acessada através da utilização de ``URLs``.

###### URL
 ``http://localhost:8080/api/planet``.

GET
---------------

Verbo ``HTTP`` que possibilita fazer a requisição que retornará os dados contidos no banco de dados da aplicação. 

Existem duas possibilidades: 

- Retornar planeta específico
- Retornar todos os planetas

#### Exemplo 1:
Requisição a um planeta específico, JSON de retorno e status de resposta:

###### URL
``http://localhost:8080/api/planet/{id}``

###### JSON
```javascript
{
  "_id" : "value",
  "name" : "value",
  "climate" : "value",
  "terrain" : "value"
}
```

###### RESPONSE
```HTTP
Status: 200 (Ok)
```

#### Exemplo 2:
Requisição a todos os planetas, JSON de retorno e status de resposta:
###### URL
``http://localhost:8080/api/planet/``

###### JSON
```javascript
[
 {
   "_id" : "value",
   "name" : "value",
   "climate" : "value",
   "terrain" : "value"
 }
 {
   "_id" : "value",
   "name" : "value",
   "climate" : "value",
   "terrain" : "value"
 }
]
```
###### RESPONSE
```HTTP
Status: 200 (Ok)
```

POST
---------------

Verbo ``HTTP`` que possibilita fazer a requisição que irá gravar os dados no banco de dados da aplicação.

O formato de envio de dados é o JSON, e deve ser seguir o template de exemplo abaixo:

###### JSON
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

PUT
---------------

Verbo ``HTTP`` que possibilita fazer a requisição que irá atualizar os dados no banco de dados da aplicação. A utlização do verbo ``PUT`` só é possível para atualização de registros específicos, sendo necessário mencionar o id na ``URL`` e seguir o exemplo do ``JSON`` do template abaixo:

###### JSON
```javascript
{
  "name" : "Plutão",
  "climate" : "frio",
  "terrain" : "irregular"
}
```
Esse exemplo resultará na seguinte resposta:

###### JSON
```javascript
{
  "name" : "Plutão",
  "climate" : "frio",
  "terrain" : "irregular"
}
```

##### Response
```HTTP
Status: 200 (Ok)
```

Caso o envio aconteça sem algum desses campos, o servidor irá atualizar apenas o campos modificados.

Caso o JSON de envio conter os mesmos dados já salvos no banco de dados, o servidor retornará o seguinte status:

##### Response
```HTTP
Status: 304 (Not Modified)
```

#### Exemplos de possíveis erros:

Requisição sem informação do id:

###### URL
``http://localhost:8080/api/planet/``

##### Response
```HTTP
Status: 405 (Method Not Allowed)
```

DELETE
---------------

Verbo ``HTTP`` que possibilita fazer a requisição que irá deletar os dados contidos no banco da aplicação. 

Existem duas possibilidades: 

- Deletar planeta específico
- Deletar todos os planetas

#### Para deletar um planeta específico utilize:
###### URL
``http://localhost:8080/api/planet/{id}``

#### Para deletar todos os planetas utilize:
###### URL
``http://localhost:8080/api/planet/``

#### Exemplos de possíveis erros:
Requisição com informação de id inválido:

##### Response
```HTTP
Status: 404 (Not Found)
```
```javascript
{
  "ErrorMessage" : "Não foi possível excluir o planeta. Id {id} não encontrado."
}
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
