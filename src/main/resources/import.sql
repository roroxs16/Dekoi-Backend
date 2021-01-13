/* Populate tabla categoria*/
INSERT INTO categoria(id, nombre) VALUES(1, 'Closet');
INSERT INTO categoria(id, nombre) VALUES(2, 'Cocina');
INSERT INTO categoria(id, nombre) VALUES(3, 'Mesas');
INSERT INTO categoria(id, nombre) VALUES(4, 'Escritorio');
INSERT INTO categoria(id, nombre) VALUES(5, 'Repisas');


INSERT INTO productos(id, descripcion,nombre,stock,valor_unitario,estado,categoria_id) VALUES(1, 'Estructura en madera de 22 mm, frentes y puertas en madera barnizada. Dimensiones 2.00mx0.60mx2.00m', 'CLOSET RAULI ', '55', '450000','Habilitado', '1');
INSERT INTO productos(id, descripcion,nombre,stock,valor_unitario,estado,categoria_id) VALUES(2, 'Estructura en melamina blanca , frentes de cajón madera lacada. Dimensiones 2.00mx0.60mx2.00m', 'CLOSET BLANCO ', '23', '450000','Habilitado', '1');
INSERT INTO productos(id, descripcion,nombre,stock,valor_unitario,estado,categoria_id) VALUES(3, 'Estructura en madera de pino barnizada. Dimensiones 4.00m x 0.50mx2.00m', 'CLOSET DESPENSA', '10', '400000','Habilitado', '1');
INSERT INTO productos(id, descripcion,nombre,stock,valor_unitario,estado,categoria_id) VALUES(4, 'Estructura cubierta por melamina de color granito en 15mm, puertas de color granito de 18mm', 'COCINA MELAMINA COLOR GRANITO', '5', '450000','Habilitado', '2');
INSERT INTO productos(id, descripcion,nombre,stock,valor_unitario,estado,categoria_id) VALUES(5, 'Estructura en melamina blanca, puertas cubiertas con melamina blanca 15 MM, cubierta posformada', 'COCINA BLANCA', '5', '350000','Habilitado', '2');
INSERT INTO productos(id, descripcion,nombre,stock,valor_unitario,estado,categoria_id) VALUES(6, 'Mesa de madera de raulí. Dimensiones 150cmX50cmx75cm', 'MESA ARRIMO DE MADERA','15', '230000','Habilitado', '3');
INSERT INTO productos(id, descripcion,nombre,stock,valor_unitario,estado,categoria_id) VALUES(7, 'Mesa de madera de rauli. Dimensiones 60cmX50cmX60cm', 'MESA DE CENTRO', '50', '190000','Habilitado', '3');
INSERT INTO productos(id, descripcion,nombre,stock,valor_unitario,estado,categoria_id) VALUES(8, 'Escritorio hecho en madera y cubierto con melamina de color blanca. Dimensiones 150cmX60cmX75cm', 'ESCRITORIO BLANCO', '20', '200000','Habilitado', '4');
INSERT INTO productos(id, descripcion,nombre,stock,valor_unitario,estado,categoria_id) VALUES(9, 'Escritorio de madera en forma de L recubierto con melamina de color gris', 'ESCRITORIO EN FORMA DE L', '5', '30000','Habilitado', '4');
INSERT INTO productos(id, descripcion,nombre,stock,valor_unitario,estado,categoria_id) VALUES(10, 'Repisa lacada. Dimensiones 80cmX30cmx150cm', 'REPISA LACADA', '10', '200000','Habilitado', '5');
INSERT INTO productos(id, descripcion,nombre,stock,valor_unitario,estado,categoria_id) VALUES(11, 'Repisa fabricada con madera de rauli. Dimensiones 40cmX20cmX30cm', 'REPISA DE MADERA DE RAULI', '10', '90000','Habilitado', '5');


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