CREATE TABLE pessoa (
    codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL,
    ativo BOOLEAN NOT NULL,
    logradouro VARCHAR(100),
    numero VARCHAR(10),
    complemento VARCHAR(50),
    bairro VARCHAR(50),
    cep VARCHAR(20),
    cidade VARCHAR(50),
    estado VARCHAR(50) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 1
INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado) 
values ('João', true, 'Rua A', '123', 'Casa', 'Centro', '12345-678', 'São Paulo', 'SP');

-- 2

INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado)
values ('Maria', true, 'Rua B', '456', 'Apartamento', 'Jardim', '23456-789', 'Rio de Janeiro', 'RJ');

-- 3

INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado)
values ('Pedro', false, 'Rua C', '789', '', 'Vila', '34567-890', 'Belo Horizonte', 'MG');

-- 4

INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado)
values ('Ana', true, null, null, null, null, null, null, null);

-- 5

INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado)
values ('Carlos', false, 'Rua D', '321', 'Casa', 'Centro', '45678-901', 'Curitiba', 'PR');

-- 6

INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado)
values ('Daniela', true, 'Rua E', '654', 'Apartamento', 'Jardim', '56789-012', 'Salvador', 'BA');

-- 7

INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado)
values ('Lucas', false, null, null, null, null, null, null, null);

-- 8

INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado)
values ('Fernanda', true, 'Rua F', '987', 'Casa', 'Vila', '67890-123', 'Fortaleza', 'CE');

-- 9

INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado)
values ('Rafael', false, 'Rua G', '654', 'Apartamento', 'Centro', '78901-234', 'Recife', 'PE');

-- 10

INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado)
values ('Juliana', true, 'Rua H', '321', 'Casa', 'Jardim', '89012-345', 'Porto Alegre', 'RS');
