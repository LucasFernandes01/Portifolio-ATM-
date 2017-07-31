CREATE DATABASE atm;
USE atm;

CREATE TABLE conta(
	con_numero VARCHAR(20) PRIMARY KEY,
	con_agencia VARCHAR(20) NOT NULL,
    con_nome VARCHAR(100),
	con_saldo DOUBLE,
    con_senha VARCHAR(20)
);

#PRÉ INSERINDO AS CONTAS

#DROP TABLE conta;

INSERT INTO conta VALUES
('500','500','Lucas','10000','500'),
('501','501','Ana','10000','501'),
('502','502','Maria','10000','502');

SELECT * FROM conta;
TRUNCATE TABLE conta;

UPDATE conta
SET con_saldo = '10000'
WHERE con_numero = '500';

#-------------------------------------------

CREATE TABLE extrato(
	ext_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    ext_data DATE,
    ext_transacao VARCHAR(100),
    ext_valor DOUBLE,
	con_numero VARCHAR(20),
    
    FOREIGN KEY (con_numero) REFERENCES conta (con_numero)
);

SELECT con_numero FROM extrato;
TRUNCATE TABLE extrato;
SELECT * FROM extrato;
DESCRIBE extrato;

#-------------------------------------------

CREATE TABLE transferencia(
	tra_id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
	tra_data DATE,
	tra_valor DOUBLE,
	tra_contaAlvo VARCHAR(20),
	con_numero VARCHAR(20),
    ext_id INT,
    
	FOREIGN KEY (con_numero) REFERENCES conta (con_numero),
    FOREIGN KEY (ext_id) REFERENCES extrato (ext_id)
);

SELECT * FROM transferencia;
TRUNCATE TABLE transferencia;
DESCRIBE transferencia;
DROP TABLE transferencia;


#-------------------------------------------

 CREATE TABLE deposito(
		dep_id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
		dep_valor DOUBLE NOT NULL,
		dep_data DATE,
		con_numero VARCHAR(20),
        ext_id INT,
    
	FOREIGN KEY (con_numero) REFERENCES conta (con_numero),
    FOREIGN KEY (ext_id) REFERENCES extrato (ext_id)
);

SELECT * FROM deposito;
TRUNCATE TABLE deposito;
DROP TABLE deposito;
DESCRIBE deposito;

#-------------------------------------------

CREATE TABLE saque(
	saq_id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    saq_valor DOUBLE,
    saq_data DATE,
	con_numero VARCHAR(20),
    ext_id INT,
    
	FOREIGN KEY (con_numero) REFERENCES conta (con_numero),
    FOREIGN KEY (ext_id) REFERENCES extrato (ext_id)
);

SELECT * FROM saque;
TRUNCATE TABLE saque;
DROP TABLE saque; 
DESCRIBE saque;

SHOW TABLES;




