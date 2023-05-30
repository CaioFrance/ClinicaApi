create table if not exists pacientes
(
    id bigint not null auto_increment primary key,
    nome varchar(100) not null,
    email varchar(100) not null unique,
    telefone varchar(100) not null,
    cpf varchar(14) not null,
    logradouro varchar(100) not null,
    bairro varchar(100) not null,
    cep varchar(9) not null,
    uf char(2) not null,
    cidade varchar(100) not null,
    complemento varchar(100),
    numero varchar(20)
)