/* Populate tabla categoria*/
INSERT INTO categoria(id, nombre) VALUES(1, 'Mesas');
INSERT INTO categoria(id, nombre) VALUES(2, 'Sillas');
INSERT INTO categoria(id, nombre) VALUES(3, 'Comedores');
INSERT INTO categoria(id, nombre) VALUES(4, 'Oficina');
INSERT INTO categoria(id, nombre) VALUES(5, 'Exteriores');
INSERT INTO categoria(id, nombre) VALUES(6, 'Interiores');


INSERT INTO producto(id, descripcion,nombre,stock,valor_unitario,categoria_id) VALUES(1, 'El modelo se fabrica en una estructura de placas prensadas de madera, tapizado y  acolchado por fuera, el contorno  es liso', 'Mesa redonda', '687', '466666', '1');
INSERT INTO producto(id, descripcion,nombre,stock,valor_unitario,categoria_id) VALUES(2, 'asdasd', 'Mesa de roble ancestral', '687', '100000', '1');
INSERT INTO producto(id, descripcion,nombre,stock,valor_unitario,categoria_id) VALUES(3, 'Mesa', 'Mesa cuadrada de alerce', '687', '150000', '1');
INSERT INTO producto(id, descripcion,nombre,stock,valor_unitario,categoria_id) VALUES(4, 'asdasd', 'Silla de cerezo japones', '687', '40000', '2');

