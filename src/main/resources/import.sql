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

INSERT INTO producto(id, descripcion,nombre,stock,valor_unitario,categoria_id) VALUES(5, 'El modelo se fabrica en una estructura de placas prensadas de madera, tapizado y  acolchado por fuera, el contorno  es liso', 'Mesa redonda', '687', '466666', '1');
INSERT INTO producto(id, descripcion,nombre,stock,valor_unitario,categoria_id) VALUES(6, 'asdasd', 'Mesa de roble ancestral', '687', '100000', '3');
INSERT INTO producto(id, descripcion,nombre,stock,valor_unitario,categoria_id) VALUES(7, 'Mesa', 'Mesa cuadrada de alerce', '687', '150000', '2');
INSERT INTO producto(id, descripcion,nombre,stock,valor_unitario,categoria_id) VALUES(8, 'asdasd', 'Silla de cerezo japones', '687', '40000', '5');

INSERT INTO producto(id, descripcion,nombre,stock,valor_unitario,categoria_id) VALUES(9, 'El modelo se fabrica en una estructura de placas prensadas de madera, tapizado y  acolchado por fuera, el contorno  es liso', 'Mesa redonda', '687', '466666', '1');
INSERT INTO producto(id, descripcion,nombre,stock,valor_unitario,categoria_id) VALUES(10, 'asdasd', 'Mesa de roble ancestral', '687', '100000', '4');
INSERT INTO producto(id, descripcion,nombre,stock,valor_unitario,categoria_id) VALUES(11, 'Mesa', 'Mesa cuadrada de alerce', '687', '150000', '6');
INSERT INTO producto(id, descripcion,nombre,stock,valor_unitario,categoria_id) VALUES(12, 'asdasd', 'Silla de cerezo japones', '687', '40000', '6');



INSERT INTO usuarios(id, apellido,ciudad,direccion,email,fecha_nacimiento,nombre,password,rut,numero_telefono) VALUES(1, 'Cifuentes', 'Chillán', 'Monte Tibidabo 1339', 'rodrigo.cifuentes@gmail.com','1995-08-08','Rodrigo', '$2a$10$UZDG2bZjYlnhHh9Yrpqkketyq2lj92JprtHqDLCZv0/LegbuWlVC.','19.090.005-3', 942750783);
INSERT INTO usuarios(id, apellido,ciudad,direccion,email,fecha_nacimiento,nombre,password,rut,numero_telefono) VALUES(2, 'Martinez', 'Chillán', 'Monte Tibidabo 1339', 'Carolina.Martinez@gmail.com','1967-12-31','Carolina', '$2a$10$wTlcvl7x5YRKPb410r1plOlx6/Yb5F3iw9gejGaLCp8/tCusj91Me','19.090.005-3', 942750783);
INSERT INTO usuarios(id, apellido,ciudad,direccion,email,fecha_nacimiento,nombre,password,rut,numero_telefono) VALUES(3, 'Ampuero', 'Chillán', 'Monte Tibidabo 1339', 'Pablo.Ampuero@gmail.com','1995-12-31','Pablo', '$2a$10$.ok8C3abMHH/a0mCvMR9I.w991VRqvkzGHmH5FfEXdtkaqH470kLO','19.090.005-3' ,942750783);


INSERT INTO roles(nombre) VALUES('ROLE_ADMIN');
INSERT INTO roles(nombre) VALUES('ROLE_CLIENTE');


INSERT INTO usuarios_roles(usuario_id, roles_id) VALUES(1, 1);
INSERT INTO usuarios_roles(usuario_id, roles_id) VALUES(2, 2);
INSERT INTO usuarios_roles(usuario_id, roles_id) VALUES(3, 2);
