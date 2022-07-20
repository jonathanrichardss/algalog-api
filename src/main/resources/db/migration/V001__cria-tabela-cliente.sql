create table if not exists cliente (
    id uuid not null primary key,
    nome varchar(40) not null,
    email varchar(255) not null,
    telefone varchar(20) not null
    );


