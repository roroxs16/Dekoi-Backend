/* Populate tabla categoria*/
INSERT INTO categoria(id, nombre) VALUES(1, 'Mesas');
INSERT INTO categoria(id, nombre) VALUES(2, 'Sillas');
INSERT INTO categoria(id, nombre) VALUES(3, 'Comedores');
INSERT INTO categoria(id, nombre) VALUES(4, 'Oficina');
INSERT INTO categoria(id, nombre) VALUES(5, 'Exteriores');
INSERT INTO categoria(id, nombre) VALUES(6, 'Interiores');

INSERT INTO productos(id, descripcion,nombre,stock,valor_unitario,categoria_id) VALUES(1, 'El modelo se fabrica en una estructura de placas prensadas de madera, tapizado y  acolchado por fuera, el contorno  es liso', 'Mesa redonda', '687', '466666', '1');
INSERT INTO productos(id, descripcion,nombre,stock,valor_unitario,categoria_id) VALUES(2, 'asdasd', 'Mesa de roble ancestral', '687', '100000', '1');
INSERT INTO productos(id, descripcion,nombre,stock,valor_unitario,categoria_id) VALUES(3, 'Mesa', 'Mesa cuadrada de alerce', '687', '150000', '1');
INSERT INTO productos(id, descripcion,nombre,stock,valor_unitario,categoria_id) VALUES(4, 'asdasd', 'Silla de cerezo japones', '687', '40000', '3');
INSERT INTO productos(id, descripcion,nombre,stock,valor_unitario,categoria_id) VALUES(5, 'El modelo se fabrica en una estructura de placas prensadas de madera, tapizado y  acolchado por fuera, el contorno  es liso', 'Mesa redonda', '687', '466666', '1');
INSERT INTO productos(id, descripcion,nombre,stock,valor_unitario,categoria_id) VALUES(6, 'asdasd', 'Mesa de roble ancestral', '687', '100000', '4');
INSERT INTO productos(id, descripcion,nombre,stock,valor_unitario,categoria_id) VALUES(7, 'Mesa', 'Mesa cuadrada de alerce', '687', '150000', '6');
INSERT INTO productos(id, descripcion,nombre,stock,valor_unitario,categoria_id) VALUES(8, 'asdasd', 'Silla de cerezo japones', '687', '40000', '6');

INSERT INTO usuarios(id, apellido,ciudad,direccion,email,nombre,password,rut,numero_telefono) VALUES(1, 'Cifuentes', 'Chillán', 'Monte Tibidabo 1339', 'rodrigo.cifuentes@gmail.com','Rodrigo', '$2a$10$UZDG2bZjYlnhHh9Yrpqkketyq2lj92JprtHqDLCZv0/LegbuWlVC.','19.090.005-3', 942750783);
INSERT INTO usuarios(id, apellido,ciudad,direccion,email,nombre,password,rut,numero_telefono) VALUES(2, 'Martinez', 'Chillán', 'Monte Tibidabo 1339', 'Carolina.Martinez@gmail.com','Carolina', '$2a$10$wTlcvl7x5YRKPb410r1plOlx6/Yb5F3iw9gejGaLCp8/tCusj91Me','14.292.654-7', 942750783);
INSERT INTO usuarios(id, apellido,ciudad,direccion,email,nombre,password,rut,numero_telefono) VALUES(3, 'Garrido', 'Chillán', 'Monte Tibidabo 1339', 'felipe.garrido@gmail.com','Felipe', '$2a$10$.ok8C3abMHH/a0mCvMR9I.w991VRqvkzGHmH5FfEXdtkaqH470kLO','11.111.111-1' ,942750783);


INSERT INTO roles(nombre) VALUES('ROLE_ADMIN');
INSERT INTO roles(nombre) VALUES('ROLE_CLIENTE');


INSERT INTO usuarios_roles(usuario_id, roles_id) VALUES(1, 1);
INSERT INTO usuarios_roles(usuario_id, roles_id) VALUES(2, 2);
INSERT INTO usuarios_roles(usuario_id, roles_id) VALUES(3, 2);

INSERT INTO servicios(id, descripcion,nombre) VALUES(1, 'Diseñamos y fabricamos una amplia gama de mobiliario y complementos para el hogar, oficinas y empresas.Desarrollamos la diferenciación representando tu propio estilo con diseños actuales hechos a medida.', 'Diseño de Muebles personalizados');

INSERT INTO servicios(id, descripcion,nombre) VALUES(2, 'Brindamos la asesoría personalizada encontrando el estilo perfecto para tus espacios,  de acuerdo a tus necesidades, estilos de vida y preferencias.', 'Diseño de Interiores-Remodelaciones');
INSERT INTO servicios(id, descripcion,nombre) VALUES(3, 'Te ayudamos a conservar el valor histórico y sentimental de tus muebles, para que con el paso de los años sigan intactos. Contamos con taller especialista en restauración de madera, barnices y retapizado de muebles. Lo clásico no pasa de moda.', 'Restauración de muebles y retapizado.');