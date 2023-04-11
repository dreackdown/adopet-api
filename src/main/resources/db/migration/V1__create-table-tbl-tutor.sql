create table tbl_tutor(

      id bigint not null auto_increment,
      nome varchar(75) not null,
      telefone varchar(11) not null,
      cidade varchar(50) not null,
      sobre varchar(150) not null,
      email varchar(50) not null unique,
      senha varchar(50) not null,
      image_url varchar(200),
      created_at datetime,
      updated_at datetime,

      primary key(id)
);