package hardtech.repository

import hardtech.entity.*
import org.springframework.data.jpa.repository.JpaRepository


interface ProductRepository : JpaRepository<Product, Long>

interface MotherboardDetailsRepository : JpaRepository<MotherboardDetails, Long>

interface PowerSupplyDetailsRepository : JpaRepository<PowerSupplyDetails, Long>

interface ProcessorDetailsRepository : JpaRepository<ProcessorDetails, Long>

interface RAMDetailsRepository : JpaRepository<RAMDetails, Long>

interface SalesHistoryRepository : JpaRepository<SalesHistory, Long>

interface OrdersRepository : JpaRepository<Orders, Long>

interface OrderDetailsRepository : JpaRepository<OrderDetails, Long>
interface CategoryRepository : JpaRepository<Categories, Long>