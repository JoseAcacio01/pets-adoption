-- Eliminar la tabla Owner_Pets si existe
drop table if exists Owner_Pets;

-- Crear la tabla Pet
create table if not exists Pet (
  id identity,
  name varchar(50) not null,
  birthdate timestamp not null,
  race varchar(50) not null,
  weight int not null,
  has_chip boolean not null
);

-- Crear la tabla Owner
create table if not exists Owner (
  id identity,
  name varchar(50) not null,
  last_name varchar(50) not null,
  email varchar(50) not null,
  fon_number int not null,
  address varchar(50) not null
);

-- Crear la tabla Owner_Pets
create table if not exists Owner_Pets (
    owner_id bigint not null,
    pets_id bigint not null,
    primary key (owner_id, pets_id)
);

-- Añadir claves foráneas a Owner_Pets
alter table Owner_Pets
    add foreign key (owner_id) references Owner(id);
alter table Owner_Pets
    add foreign key (pets_id) references Pet(id);