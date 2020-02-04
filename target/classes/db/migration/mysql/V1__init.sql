CREATE TABLE cidade (
	id INT PRIMARY KEY AUTO_INCREMENT,
	ibge_id INT NOT NULL UNIQUE,
	uf CHAR(2) NOT NULL,
	name VARCHAR(50) NOT NULL,
	capital BOOLEAN,
	lon DECIMAL(11, 8),
	lat DECIMAL(10, 8),
	no_accents VARCHAR(50),
	alternative_name VARCHAR(50),
	microregion VARCHAR(50),
	mesoregion VARCHAR(50),
<<<<<<< HEAD
	dateEntry DATETIME NOT NULL,
	dateUpdate DATETIME NOT NULL
=======
	dataCricao DATETIME NOT NULL,
	dataAlteracao DATETIME NOT NULL
>>>>>>> f7054583940279857c54b1d4c3e9f193ad326995
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
