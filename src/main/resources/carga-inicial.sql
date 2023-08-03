INSERT INTO tb_usuario
(nome, username, email, senha, ativo)
VALUES('Danilo Ribeiro','danilo1337','danilo1338@gmail.com', '$2a$10$M3wBbMW8xe264Mbulpc/TONJVph1JeYjCmia0/vb4/hT/JSr0A0JC', 'S');

INSERT INTO tb_usuario
(nome, username, email, senha, ativo)
VALUES('Application Web', 'web','a@a.com', '$2a$10$M3wBbMW8xe264Mbulpc/TONJVph1JeYjCmia0/vb4/hT/JSr0A0JC', 'S');

--senha 123456 

--Permissões
INSERT INTO tb_permissao (username, permissao) VALUES ('danilo1337','ROLE_ADMIN');
INSERT INTO tb_permissao (username, permissao) VALUES ('danilo1337','ROLE_USER');
INSERT INTO tb_permissao (username, permissao) VALUES ('web','ROLE_USER');