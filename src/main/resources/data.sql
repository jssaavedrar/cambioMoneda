DROP TABLE IF EXISTS moneda;
 
CREATE TABLE moneda (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  nombre VARCHAR(250) NOT NULL,
  tipo VARCHAR(250) NOT NULL
);
 
INSERT INTO moneda (nombre, tipo) VALUES
  ('soles', '1'),
  ('euros', '3.5'),
  ('dolares', '3');
