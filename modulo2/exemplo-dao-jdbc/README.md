exemplo-dao-jdbc
================

Exemplo de aplicação JDBC simples usando padrão de projeto DAO.

Download
--------
Através do botão "Download ZIP" ou através de um comando "git clone https://github.com/regispires/exemplo-dao-jdbc.git"
O 'git clone' pode ser realizado diretamente através do Eclipse:
- Mudar para a perspectiva para "Git Repository Exploring".
- Clicar no botão Clone Git repository.
- Colar a URI do repositório em Location -> URI.

Importar o projeto para o Eclipse
---------------------------------
O Eclipse JEE (Eclipse IDE for Java EE Developers) versão Kepler ou superior possui suporte nativo ao Maven.

- Faça: File -> Import -> Maven -> Existing Maven Projects
- Clique em 'Next >'
- Selecione o diretório raiz (Root directory) do projeto baixado
- Clique em "Finish"

Criar o esquema relacional no PostgreSQL
----------------------------------------
```sql
create database contatos;

create table clientes (
  id serial primary key,
  cpf varchar(11),
  nome varchar(50),
  fone varchar(11),
  renda decimal(10,2)
);
```
