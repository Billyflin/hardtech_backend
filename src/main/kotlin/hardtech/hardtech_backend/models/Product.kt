package hardtech.hardtech_backend.models

import jakarta.persistence.*
import jakarta.validation.constraints.*

/**
 * Entidad que representa un procesador.
 */
@Entity
class Processor(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = 0,

    @NotBlank(message = "Marca requerida") @Size(
        max = 50, message = "La marca no puede tener más de 50 caracteres"
    ) var brand: String?,
    @NotBlank(message = "Nombre requerido") @Size(
        max = 50, message = "El nombre no puede tener más de 50 caracteres"
    ) @Column(unique = true) var name: String?,
    @Positive(message = "Precio debe ser mayor a 0") var price: Double = 0.0,
    @Min(value = 1, message = "Generación debe ser mayor a 0") var gen: Int = 1,
    @Min(value = 1, message = "Núcleos debe ser mayor a 0") var cores: Int = 1,
    @Min(value = 1, message = "Hilos debe ser mayor a 0") var threads: Int = 1,
    @Min(value = 1, message = "Frecuencia debe ser mayor a 0") var speed: Double = 1.0,
    @Min(value = 1, message = "Frecuencia turbo debe ser mayor a 0") var turbo: Double = 1.0,
    @Min(value = 1, message = "TDP debe ser mayor a 0") var tdp: Int = 1,
    @NotBlank(message = "Socket requerido") @Pattern(
        regexp = "AM4|LGA\\d{4}", message = "El socket debe ser AM4 o LGA seguido de 4 dígitos"
    ) var socket: String

)

/**
 * Entidad que representa una tarjeta gráfica.
 */
@Entity
class GraphicsCard(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long,
    @NotBlank(message = "Marca requerida") @Size(
        max = 50, message = "La marca no puede tener más de 50 caracteres"
    ) var brand: String,
    @NotBlank(message = "Nombre requerido") @Size(
        max = 50, message = "El nombre no puede tener más de 50 caracteres"
    ) @Column(unique = true) var name: String,
    @Positive(message = "Precio debe ser mayor a 0") var price: Double,
    @NotBlank(message = "Chipset requerido") @Size(
        max = 50, message = "El chipset no puede tener más de 50 caracteres"
    ) var chipset: String,
    @NotBlank(message = "Tipo de memoria requerido") @Pattern(
        regexp = "DDR\\d|GDDR\\d",
        message = "El tipo de memoria debe ser DDR seguido de un número o GDDR seguido de un número"
    ) var memoryType: String,
    @Min(value = 1, message = "Memoria debe ser mayor a 0") var memory: Int,
    @Min(value = 1, message = "Velocidad debe ser mayor a 0") var speed: Double,
    @Min(value = 1, message = "TDP debe ser mayor a 0") var tdp: Int,
    @Min(value = 1, message = "Potencia debe ser mayor a 0") var power: Int,
    @Min(value = 1, message = "Longitud debe ser mayor a 0") var length: Int,
    @Min(value = 1, message = "Ventiladores debe ser mayor a 0") var fans: Int,
    @Min(value = 1, message = "Puertos de pantalla debe ser mayor a 1") var displayPorts: Int,
    @Min(value = 0, message = "HDMI debe ser mayor a 0") var hdmi: Int,
    @Min(value = 0, message = "VGA debe ser mayor a 0") var vga: Int
)

/**
 * Entidad que representa una tarjeta RAM.
 */
@Entity
class Ram(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long,
    @NotBlank(message = "Marca requerida") @Size(
        max = 50, message = "La marca no puede tener más de 50 caracteres"
    ) var brand: String,
    @NotBlank(message = "Nombre requerido") @Size(
        max = 50, message = "El nombre no puede tener más de 50 caracteres"
    ) @Column(unique = true) var name: String,
    @Positive(message = "Precio debe ser mayor a 0") var price: Double,
    @NotBlank(message = "Tipo requerido") @Pattern(
        regexp = "DDR\\d", message = "El tipo debe ser DDR seguido de un número"
    ) var type: String,
    @Min(value = 1, message = "Velocidad debe ser mayor a 0") var speed: Int,
    @Min(value = 1, message = "Tamaño debe ser mayor a 0") var size: Int,
    @Min(value = 1, message = "Módulos debe ser mayor a 0") var modules: Int,
    @Min(value = 1, message = "CAS debe ser mayor a 0") var cas: Int,
    @Min(value = 1, message = "Voltaje debe ser mayor a 0") var voltage: Double
)

/**
 * Entidad que representa un almacenamiento.
 */
@Entity
class Storage(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = 0,
    @NotBlank(message = "Marca requerida") @Size(
        max = 50, message = "La marca no puede tener más de 50 caracteres"
    ) var brand: String,
    @NotBlank(message = "Nombre requerido") @Size(
        max = 50, message = "El nombre no puede tener más de 50 caracteres"
    ) @Column(unique = true) var name: String,
    @Positive(message = "Precio debe ser mayor a 0") var price: Double = 0.0,
    @NotBlank(message = "Tipo requerido") var type: String?,
    @Min(value = 1, message = "Capacidad debe ser mayor a 0") var capacity: Int = 1,
    @Min(value = 1, message = "Velocidad de lectura debe ser mayor a 0") var readSpeed: Int = 1,
    @Min(value = 1, message = "Velocidad de escritura debe ser mayor a 0") var writeSpeed: Int = 1
)

/**
 * Entidad que representa una fuente de poder.
 */
@Entity
class PowerSupply(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long,
    @NotBlank(message = "Marca requerida") @Size(
        max = 50, message = "La marca no puede tener más de 50 caracteres"
    ) var brand: String,
    @NotBlank(message = "Nombre requerido") @Size(
        max = 50, message = "El nombre no puede tener más de 50 caracteres"
    ) @Column(unique = true) var name: String,
    @Positive(message = "Precio debe ser mayor a 0") var price: Double,
    @Min(value = 1, message = "Watts debe ser mayor a 0") var watts: Int,
    @NotBlank(message = "Eficiencia requerida") @Pattern(
        regexp = "80 PLUS (Standard|Bronze|Silver|Gold|Platinum|Titanium)",
        message = "La eficiencia debe ser una de las siguientes: 80 PLUS Standard, 80 PLUS Bronze, 80 PLUS Silver, 80 PLUS Gold, 80 PLUS Platinum, 80 PLUS Titanium"
    ) var efficiency: String,
    @NotBlank(message = "Modular requerido") @Pattern(
        regexp = "Non-Modular|Semi-Modular|Fully Modular",
        message = "El modular debe ser uno de los siguientes: Non-Modular, Semi-Modular, Fully Modular"
    ) var modular: String
)

/**
 * Entidad que representa un sistema de enfriamiento.

 */
@Entity
class Cooling(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long,
    @NotBlank(message = "Marca requerida") @Size(
        max = 50, message = "La marca no puede tener más de 50 caracteres"
    ) var brand: String,
    @NotBlank(message = "Nombre requerido") @Size(
        max = 50, message = "El nombre no puede tener más de 50 caracteres"
    ) @Column(unique = true) var name: String,
    @Positive(message = "Precio debe ser mayor a 0") var price: Double,
    @NotBlank(message = "Tipo requerido") @Pattern(
        regexp = "Air|Liquid", message = "El tipo debe ser Air o Liquid"
    ) var type: String,
    @Min(value = 1, message = "Ventiladores debe ser mayor a 0") var fans: Int,
    @Min(value = 1, message = "Tamaño del ventilador debe ser mayor a 0") var fanSize: Int,
    @Min(value = 1, message = "RPM del ventilador debe ser mayor a 0") var fanRpm: Int,
    @Min(value = 1, message = "Nivel de ruido debe ser mayor a 0") var noiseLevel: Int,
    @NotBlank(message = "Color requerido") @Pattern(
        regexp = "Black|White|Gray|Red|Blue|Green|Yellow|Orange|Purple|Pink",
        message = "El color debe ser uno de los siguientes: Black, White, Gray, Red, Blue, Green, Yellow, Orange, Purple, Pink"
    ) var color: String,
    var rgb: Boolean = false,
    var rgbHeader: Boolean = false,
    var blockRgb: Boolean = false
)