/**
 * Clase Compra
 * @param cliente cliente que realizo la compra
 * @param dia dia de la compra
 * @param monto monto de la compra
 * @constructor Crea una compra con cliente, dia y monto
 */
data class Compra(val cliente: Cliente, val dia: String, val monto: Double)

/**
 * Clase Cliente
 * @param nombre nombre del cliente
 * @param domicilio domicilio del cliente
 * @constructor Crea un cliente con nombre y domicilio
 */
data class Cliente(val nombre: String, val domicilio: Domicilio)


/**
 * Clase Domicilio
 * @param calle calle del domicilio
 * @param numero numero del domicilio
 * @constructor Crea un domicilio con calle y numero
 */
data class Domicilio(val calle: String, val numero: String) {
    /**
     * @return el domicilio completo con la calle y el número.
     */
    fun dirCompleta():String{
        return "$calle $numero"
    }
}

/**
 * Clase RepositorioCompras
 * @constructor Crea un repositorio de compras
 */
class RepositorioCompras {
    private val compras = mutableListOf<Compra>()

    /**
     * Añade una compra a compras
     * @param compra la compra a añadir
     */
    fun agregarCompra(compra: Compra) {
        compras.add(compra)
    }

    /**
     * Devueelve los domicilios que deben algo
     * @return un cojunto con los domicilios
     */
    fun domicilios(): Set<String> {
        val domiciliosUnicos = mutableSetOf<String>()
        for (compra in compras) {
            domiciliosUnicos.add(compra.cliente.domicilio.dirCompleta())
        }
        return domiciliosUnicos
    }
}

/**
 * la funcion principal, donde se crean las clases metodos y esas cosas
 */
fun main() {
    val repoCompras = RepositorioCompras()

    val domicilio1 = Domicilio("Calle A", "123")
    val cliente1 = Cliente("Cliente1", domicilio1)
    val compra1 = Compra(cliente1, "2022-01-01", 100.0)

    val domicilio2 = Domicilio("Calle B", "456")
    val cliente2 = Cliente("Cliente2", domicilio2)
    val compra2 = Compra(cliente2, "2022-01-02", 150.0)

    repoCompras.agregarCompra(compra1)
    repoCompras.agregarCompra(compra2)


    val domiciliosUnicos = repoCompras.domicilios()


    println("Domicilios para enviar facturas:")
    domiciliosUnicos.forEach { println(it) }



}