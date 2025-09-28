IF OBJECT_ID('tb_localizacao_moto', 'U') IS NOT NULL DROP TABLE tb_localizacao_moto;
IF OBJECT_ID('tb_evento_moto', 'U') IS NOT NULL DROP TABLE tb_evento_moto;
IF OBJECT_ID('tb_evento', 'U') IS NOT NULL DROP TABLE tb_evento;
IF OBJECT_ID('tb_moto', 'U') IS NOT NULL DROP TABLE tb_moto;
IF OBJECT_ID('tb_estrutura_patio', 'U') IS NOT NULL DROP TABLE tb_estrutura_patio;
IF OBJECT_ID('tb_patio', 'U') IS NOT NULL DROP TABLE tb_patio;

CREATE TABLE tb_patio (
    id_patio VARCHAR(255) PRIMARY KEY,
    id_filial VARCHAR(255), 
    estrutura_criada_patio BIT
);

CREATE TABLE tb_estrutura_patio (
    id_patio VARCHAR(255) REFERENCES tb_patio(id_patio),
    coordenada_x_estrutura INT,
    coordenada_y_estrutura INT,
    tamanho_estrutura INT,
    rotacao_estrutura INT
);

CREATE TABLE tb_moto (
    id_moto VARCHAR(255) PRIMARY KEY,
    id_patio VARCHAR(255) REFERENCES tb_patio(id_patio),
    placa_moto VARCHAR(255),
    modelo_moto VARCHAR(255),
    status_moto VARCHAR(255),
    chassi_moto VARCHAR(255),
    iot_moto VARCHAR(255)
);

CREATE TABLE tb_evento (
    id_evento VARCHAR(255) PRIMARY KEY,
    descricao_evento VARCHAR(255),
    cor_evento VARCHAR(255)
);

CREATE TABLE tb_evento_moto (
    id_moto VARCHAR(255) REFERENCES tb_moto(id_moto),
    id_evento VARCHAR(255) REFERENCES tb_evento(id_evento),
    data_hora_evento DATETIME,
    visualizado_evento BIT
);

CREATE TABLE tb_localizacao_moto (
    id_moto VARCHAR(255) PRIMARY KEY REFERENCES tb_moto(id_moto),
    latitude_moto VARCHAR(255),
    longitude_moto VARCHAR(255)
);

INSERT INTO tb_patio (id_patio, id_filial, estrutura_criada_patio) VALUES
('1', '1', 0),
('2', '2', 0),
('3', '3', 0),
('4', '4', 0),
('5', '5', 1);

INSERT INTO tb_estrutura_patio (id_patio, coordenada_x_estrutura, coordenada_y_estrutura, tamanho_estrutura, rotacao_estrutura) VALUES
('5', 12, 10, 50, 90),
('5', 20, 0, 100, 180),
('5', 20, 100, 100, 180),
('5', 20, 0, 100, 90),
('5', 120, 100, 65, 180);

INSERT INTO tb_moto (id_moto, id_patio, placa_moto, modelo_moto, status_moto, chassi_moto, iot_moto) VALUES
('1', '5', 'XXXX-777', 'MOTTU-POP', 'PRONTA_ALUGUEL', 'ID-CHASSI', 'ID-IOT'),
('2', '5', 'AAAA-123', 'MOTTU-SPORT', 'PRONTA_ALUGUEL', 'ID-CHASSI', 'ID-IOT'),
('3', '5', 'FFFF-321', 'MOTTU-E', 'PRONTA_ALUGUEL', 'ID-CHASSI', 'ID-IOT'),
('4', '5', 'YYYY-453', 'MOTTU-POP', 'SEM_PLACA', 'ID-CHASSI', 'ID-IOT'),
('5', '5', 'OOOO-544', 'MOTTU-POP', 'PRONTA_ALUGUEL', 'ID-CHASSI', 'ID-IOT');

INSERT INTO tb_localizacao_moto (id_moto, latitude_moto, longitude_moto) VALUES
('1', '1232334132', '2312321312'),
('2', '3231312332', '8928101011'),
('3', '8748041278', '3210937811'),
('4', '2189038012', '2301837218'),
('5', '3812738129', '8904819441');

INSERT INTO tb_evento (id_evento, descricao_evento, cor_evento) VALUES
('1', 'Moto em manutenção!', 'CINZA'),
('2', 'Moto pronta para ser alugada!', 'VERDE'),
('3', 'Moto chegou sem placa!', 'VERMELHO');

INSERT INTO tb_evento_moto (id_moto, id_evento, data_hora_evento, visualizado_evento) VALUES
('1', '1', '2025-04-25 10:00:00', 1),
('1', '2', '2025-04-25 12:00:00', 1),
('2', '2', '2025-04-26 09:00:00', 1),
('3', '3', '2025-04-27 15:30:00', 0),
('3', '2', '2025-04-27 17:30:00', 0);
