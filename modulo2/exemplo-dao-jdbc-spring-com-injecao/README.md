exemplo-dao-jdbc
================

Exemplo de aplicação JDBC simples usando padrão de projeto DAO.

Criar o esquema relacional no PostgreSQL
----------------------------------------
```sql
create database dsp_m2;

create table clientes (
  id serial primary key,
  cpf varchar(11) unique,
  nome varchar(50),
  fone varchar(11),
  renda decimal(10,2)
);
```

Criar o esquema relacional no SQLite
------------------------------------
1. entrar na pasta src/main/resources e digitar: "sqlite3 dsp_m2.db"
2. executar o comando sql a seguir:
```sql
create table clientes (
  id integer primary key autoincrement,
  cpf varchar(11) unique,
  nome varchar(50),
  fone varchar(11),
  renda decimal(10,2)
);
```
