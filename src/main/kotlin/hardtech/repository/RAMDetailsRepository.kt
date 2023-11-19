package hardtech.repository

import hardtech.entity.*
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository : JpaRepository<Product, Long>

interface MotherboardDetailsRepository : JpaRepository<MotherboardDetails, Long>

interface PowerSupplyDetailsRepository : JpaRepository<PowerSupplyDetails, Long>

interface ProcessorDetailsRepository : JpaRepository<ProcessorDetails, Long>

interface RAMDetailsRepository : JpaRepository<RAMDetails, Long>



