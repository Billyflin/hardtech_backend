insert into "user" (username, password, nickname, activated)
values ('admin', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 'admin', true);
insert into "user" (username, password, nickname, activated)
values ('user', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'user', true);

insert into authority (authority_name)
values ('ROLE_USER');
insert into authority (authority_name)
values ('ROLE_ADMIN');

insert into user_authority (user_id, authority_name)
values (1, 'ROLE_USER');
insert into user_authority (user_id, authority_name)
values (1, 'ROLE_ADMIN');
insert into user_authority (user_id, authority_name)
values (2, 'ROLE_USER');

-- Datos de prueba para la tabla de Categorías
INSERT INTO categories (category_id, category_name)
VALUES (1, 'Cooling'),
       (2, 'Graphics Card'),
       (3, 'Motherboard');

-- Datos de prueba para la tabla de Productos
INSERT INTO Product (price, category_id, brand, product_name)
VALUES (100, 1, 'Cooler Master', 'Hyper 212 EVO'),
       (200, 2, 'MSI', 'GeForce GTX 1050 Ti'),
       (300, 3, 'ASUS', 'ROG Strix B450-F Gaming');


-- -- Datos de prueba para la tabla de Detalles de Cooling
-- INSERT INTO cooling_details (blockrgb, fan_size, fanrpm, fans, noise_level, product_id)
-- values (false, 120, 2000, 2, 36, 1)
-- ;
--
--
-- -- Datos de prueba para la tabla de Detalles de Graphics Card
-- INSERT INTO graphics_card_details (display_ports, fans, hdmi, length, memory, power, speed, tdp, vga, product_id)
-- VALUES (1, 1, 1, 229, 4, 75, 1354, 75, 0, 2);

-- Datos de prueba para la tabla de Detalles de Motherboard
INSERT INTO motherboard_details (m2slots, memory_slots, pci_ex16slots, pci_ex1slots, sata3ports, usb2ports, usb3ports,
                                 product_id, chipset, form_factor, memory_type, socket)
VALUES (2, 4, 2, 3, 6, 4, 2, 3, 'AMD B450', 'ATX', 'DDR4', 'AM4');