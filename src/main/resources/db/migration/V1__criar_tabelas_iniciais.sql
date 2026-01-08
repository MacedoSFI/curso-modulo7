CREATE TABLE categoria (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL
);

CREATE TABLE produto (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    preco FLOAT(10,2) NOT NULL,
    categoria_id BIGINT,
    CONSTRAINT fk_categoria FOREIGN KEY (categoria_id) REFERENCES categoria(id)
);