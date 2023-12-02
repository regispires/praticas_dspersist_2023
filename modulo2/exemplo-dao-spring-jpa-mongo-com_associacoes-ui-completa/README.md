exemplo-dao-spring-jpa-mongo-com_associacoes-ui-completa
========================================================

Exemplo de aplicação usando JPA e Mongo com Spring Boot, Spring Data JPA e Spring Data MongoDB.

A aplicação possui menus e sub-menus para cadastro de clientes, produtos, compras e itens de compras.

```mermaid
classDiagram
    Compra "1" *-- "*" ItemCompra
    Produto "1" -- "&nbsp;*" ItemCompra
    Cliente "1" -- "*" Compra

class Cliente {
    -id: Integer
    -cpf: String
    -nome: String
    -fone: String
    -renda: float
}

class Compra {
    -id: Integer
    -dataHora: LocalDateTime
    +getValorTotal() float

}

class ItemCompra {
    -id: Integer
    -quantidade: int
    -valorUnitario: float
    +getValorTotal() float
}

class Produto {
    -id: Integer
    -nome: String
    -quantidade: float
    -valorAtual: float
}
```
