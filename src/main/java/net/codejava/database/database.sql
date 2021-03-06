''' CAMBIOS A LA BASE DE DATOS DE POSTGRE SQL '''
-- se cambian los tipos de dato de las columnas y se agregan las constraint unique necesarias --

alter table usuario add constraint unique_id unique(id),
alter column id
set data type integer;

alter table usuario
alter column apellido
set data type varchar(255);

alter table usuario add constraint unique_contraseña unique(contraseña),
alter column contraseña
set data type varchar(255);

alter table usuario
alter column edad
set data type integer;

alter table usuario add constraint unique_email unique(email),
alter column email
set data type varchar(255) ;

alter table usuario
alter column genero
set data type bpchar(1);

alter table usuario
alter column nombre
set data type varchar(255);

-- se agregan las constraints not null para cada columna y se cambia el nombre de la constraint --

alter table usuario rename constraint unique_id to usuario_id_unique;

alter table usuario
alter column id
set not null;

alter table usuario
alter apellido
set not null;

alter table usuario rename constraint unique_contraseña to usuario_contraseña_unique;

alter table usuario
alter column contraseña
set not null;

alter table usuario
alter edad
set not null;

alter table usuario rename constraint unique_email to usuario_email_unique;

alter table usuario
alter column email
set not null;

alter table usuario
alter genero
set not null;

alter table usuario
alter nombre
set not null;

-- se agrega la clave foranea para la tabla imc y luego se cambió el nombre de la constraint --

alter table imc add constraint imc_fkey
foreign key (id_usuario) references usuario (id);

alter table imc rename constraint imc_fkey to imc_fkey_usuario_id_unique