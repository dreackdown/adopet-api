create table tbl_adocoes(

      id bigint not null auto_increment,
      pet_id bigint not null,
      tutor_id bigint not null,
      data datetime not null,

      primary key(id),
      constraint fk_adocao_pet_id foreign key(pet_id) references tbl_pets(id),
      constraint fk_adocao_tutor_id foreign key(tutor_id) references tbl_tutores(id)

);