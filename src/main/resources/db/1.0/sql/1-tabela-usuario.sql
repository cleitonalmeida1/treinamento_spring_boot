create sequence seq_usuario;

create table tb_usuario
(
    us_id    integer not null primary key,
    us_nome  character varying(200),
    us_login character varying(200),
    us_senha character varying(200),
    us_admin boolean
)