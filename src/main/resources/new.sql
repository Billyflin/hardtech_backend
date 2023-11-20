CREATE TABLE Categories
(
    CategoryID serial PRIMARY KEY,
    CategoryName varchar(255) UNIQUE
);

-- Tabla de Productos
CREATE TABLE Product
(
    ProductID serial PRIMARY KEY,
    ProductName varchar(255) UNIQUE,
    Brand varchar(255),
    Price double precision NOT NULL,
    ProductType varchar(255)
);

-- Tabla de Detalles de Cooling
CREATE TABLE CoolingDetails
(
    ProductID integer PRIMARY KEY REFERENCES Product(ProductID),
    BlockRGB boolean NOT NULL,
    FanRPM integer NOT NULL,
    FanSize integer NOT NULL,
    Fans integer NOT NULL,
    NoiseLevel integer NOT NULL
);

-- Tabla de Detalles de Graphics Card
CREATE TABLE GraphicsCardDetails
(
    ProductID integer PRIMARY KEY REFERENCES Product(ProductID),
    DisplayPorts integer NOT NULL,
    Fans integer NOT NULL,
    HDMI integer NOT NULL,
    Length integer NOT NULL,
    Memory integer NOT NULL,
    Power integer NOT NULL,
    Speed double precision NOT NULL,
    TDP integer NOT NULL,
    VGA integer NOT NULL
);

-- Tabla de Detalles de Motherboard
CREATE TABLE MotherboardDetails
(
    ProductID integer PRIMARY KEY REFERENCES Product(ProductID),
    Chipset varchar(255) NOT NULL,
    FormFactor varchar(255) NOT NULL,
    MemorySlots integer NOT NULL,
    MemoryType varchar(255) NOT NULL,
    M2Slots integer NOT NULL,
    PCIEx16Slots integer NOT NULL,
    PCIEx1Slots integer NOT NULL,
    SATA3Ports integer NOT NULL,
    Socket varchar(255) NOT NULL,
    USB2Ports integer NOT NULL,
    USB3Ports integer NOT NULL
);

-- Tabla de Detalles de Power Supply
CREATE TABLE PowerSupplyDetails
(
    ProductID integer PRIMARY KEY REFERENCES Product(ProductID),
    Efficiency varchar(255) NOT NULL,
    Fanless boolean NOT NULL,
    FormFactor varchar(255) NOT NULL,
    Modular boolean NOT NULL,
    Power double precision NOT NULL
);

-- Tabla de Detalles de Processor
CREATE TABLE ProcessorDetails
(
    ProductID integer PRIMARY KEY REFERENCES Product(ProductID),
    Cores integer NOT NULL,
    Frequency double precision NOT NULL,
    L3Cache integer NOT NULL,
    Socket varchar(255) NOT NULL,
    TDP integer NOT NULL,
    Threads integer NOT NULL
);
-- Tabla de Detalles de RAM
CREATE TABLE RAMDetails
(
    ProductID integer PRIMARY KEY REFERENCES Product(ProductID),
    CASLatency integer NOT NULL,
    Color varchar(255) NOT NULL,
    Frequency double precision NOT NULL,
    HeatSpreader boolean NOT NULL,
    MemoryType varchar(255) NOT NULL,
    Modules integer NOT NULL,
    Size integer NOT NULL,
    Voltage double precision NOT NULL
);

-- Y así sucesivamente para las otras tablas de detalles de productos (PowerSupplyDetails, ProcessorDetails, RAMDetails, StorageDetails)
-- Tabla de Carrito de Compras
CREATE TABLE ShoppingCart
(
    CartID serial PRIMARY KEY,
    UserID integer REFERENCES "user"("user_id"),
    ProductID integer REFERENCES Product(ProductID),
    Quantity integer NOT NULL
);
-- Tabla de Historial de Ventas
CREATE TABLE SalesHistory
(
    SaleID serial PRIMARY KEY,
    UserID integer REFERENCES "user"("user_id"),
    ProductID integer REFERENCES Product(ProductID),
    Quantity integer NOT NULL,
    SaleDate timestamp NOT NULL DEFAULT current_timestamp
);

create view UserOrders  as SELECT
                               u.username AS Usuario,
                               p.ProductName AS Producto,
                               s.Quantity AS Cantidad,
                               p.Price AS Precio,
                               (s.Quantity * p.Price) AS Total
                           FROM
                               "user" u
                                   JOIN
                               ShoppingCart s ON u.user_id = s.UserID
                                   JOIN
                               Product p ON s.ProductID = p.ProductID
                           WHERE
                                   u.user_id = :userID;

-- Vista para el estado de las ventas
CREATE VIEW SalesStatus AS
SELECT
    u.username AS Usuario,
    COUNT(sh.SaleID) AS Ventas,
    SUM(sh.Quantity * p.Price) AS Ingresos
FROM
    "user" u
        JOIN
    SalesHistory sh ON u.user_id = sh.UserID
        JOIN
    Product p ON sh.ProductID = p.ProductID
GROUP BY
    u.username;

-- Vista para los clientes
CREATE VIEW Customers AS
SELECT
    u.username AS Usuario,
    COUNT(DISTINCT sh.ProductID) AS ProductosComprados
FROM
    "user" u
        JOIN
    SalesHistory sh ON u.user_id = sh.UserID
GROUP BY
    u.username;

-- Vista para los productos
CREATE VIEW Products AS
SELECT
    p.ProductName AS Producto,
    COUNT(sh.SaleID) AS Ventas,
    SUM(sh.Quantity) AS CantidadVendida
FROM
    Product p
        JOIN
    SalesHistory sh ON p.ProductID = sh.ProductID
GROUP BY
    p.ProductName;
-- Tabla de Categorías
