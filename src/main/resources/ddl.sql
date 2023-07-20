CREATE TABLE tb_usuario (
  id int identity(1,1),
  nome VARCHAR(255) NOT NULL,
  senha VARCHAR(255) NOT NULL,
  email varchar(255) NOT NULL UNIQUE,
  dt_criacao DATETIME DEFAULT getdate() NOT NULL,
  dt_atualizacao DATETIME DEFAULT getdate() NOT NULL,
  ativo char(1)
  CONSTRAINT pk_usuario PRIMARY KEY (id)
);

CREATE TABLE tb_importacao (
  id int identity(1,1),
  usuario_id INT NOT NULL,
  dt_importacao DATETIME DEFAULT getdate() NOT NULL,
  arquivo_base64 NVARCHAR(MAX) NOT NULL,
  nome_arquivo varchar(100) NOT NULL,
  CONSTRAINT pk_importacao PRIMARY KEY (id),
  CONSTRAINT fk_tb_importacao_to_tb_usuario_id FOREIGN KEY (usuario_id) REFERENCES tb_usuario (id)
);

CREATE TABLE tb_contato(
	id INT IDENTITY(1,1),
	cpf_cnpj varchar(16),
	nome varchar(255) NOT NULL,
	telefone varchar(30) NOT NULL,
	observacao varchar(100),
	usuario_id INT NOT NULL,
	importacao_id INT NOT NULL,	
	CONSTRAINT pk_contato PRIMARY KEY (id),
	CONSTRAINT fk_tb_contato_to_tb_usuario FOREIGN KEY (usuario_id) REFERENCES tb_usuario (id),
	CONSTRAINT fk_tb_contato_to_tb_importacao FOREIGN KEY (importacao_id) REFERENCES tb_importacao (id)
);