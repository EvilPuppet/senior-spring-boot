# senior-spring-boot
#Docker
comandos a executar 
* docker pull postgres
  * docker network create --driver brigde postgre-network
  * docker run --name postgres --network=postgres-network -e "POSTGRES_admin" -p 5432:5432 -d postgres
  

# Projeto foi construido usando spring-boot java 8 e postgres 13
Conexao com o banco
spring.datasource.url=jdbc:postgresql://localhost:5432/senior
spring.datasource.username=postgres
spring.datasource.password=admin

Para executar o projeto executar a classe ChallangeApplication


* Endpoints
  * [GET] pedido/:id  > findById
  * [GET] pedido/:pageNo/:pageSize > findAll paginacao
  * [POST] pedido /

`` {
	"situacao":false,
	"produtos": [
    {
      "id": "5569cb05-1b6f-41e3-9f8b-9f0974286f24",
      "nome": "Faxina",
      "servico": true,
      "valor": 250.00,
      "desativado": false  
    },
    {
      "id": "b05301a0-7cfb-488d-8996-03f96b25e1f3",
      "nome": "Sapato",
      "servico": false,
      "valor": 100.00,
      "desativado": false
    },
    {
      "id": "b05301a0-7cfb-488d-8996-03f96b25e1f3",
      "nome": "Sapato",
      "servico": false,
      "valor": 100.00,
      "desativado": false
    }
  ]
}  ``

* [DEL] pedido/:id > delete
* [PUT] pedido/ > change


* Endpoints Produto
  * [GET] produto/:id  > findById
  * [GET] produto/:pageNo/:pageSize > findAll paginacao
  * [POST] produto/

``
{    
    "nome": "Faxina",
    "servico": true,
    "valor": 250.00,
    "desativado": false  
}
``
  

 * [DEL] produto/:id > delete
 * [PUT] produto/

``
{
    "id": "5569cb05-1b6f-41e3-9f8b-9f0974286f24",
    "nome": "Faxina",
    "servico": true,
    "valor": 250.00,
    "desativado": false  
}
``
> change

