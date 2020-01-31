CREATE TABLE cidade (
	id INT PRIMARY KEY AUTO_INCREMENT,
	ibge_id INT NOT NULL,
	uf CHAR(2) NOT NULL,
	name VARCHAR(50) NOT NULL,
	capital BOOLEAN,
	lon DECIMAL(11, 8),
	lat DECIMAL(10, 8),
	no_accents VARCHAR(50),
	alternative_name VARCHAR(50),
	microregion VARCHAR(50),
	mesoregion VARCHAR(50)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
