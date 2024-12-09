//1) Partiendo de el siguiente array "int[] numeros  = { 3, 7, 0, 1, 9, 6, 2, 4, 5, 8}; "
// tienes que ordenarlo de menor a mayor usando 1 o varios iteradores (for, foreach, while, etc).

fun quickSort(toSortList:MutableList<Int>): List<Int> {

    if (toSortList.size <= 1){
        return toSortList
    }

    val pivot = toSortList[0]

    val parteMenor = mutableListOf<Int>()
    toSortList.forEach {
        if (it < pivot){
            parteMenor.add(it)
        }
    }

    val parteIgual = mutableListOf<Int>()
    toSortList.forEach {
        if (it == pivot){
            parteIgual.add(it)
        }
    }

    val parteMayor = mutableListOf<Int>()
    toSortList.forEach {
        if (it > pivot){
            parteMayor.add(it)
        }
    }

    return quickSort(parteMenor) + parteIgual + quickSort(parteMayor)
}

fun bubleSort(toSortList:MutableList<Int>):MutableList<Int>{

    var numeroDeIteraciones = 0
    // for (var i = 0; i < tosortlist.size;i++)
    for (i in 0..toSortList.size - 1){
        for (j in 0..toSortList.size -2 -i){
            numeroDeIteraciones++
            if ( toSortList[j] > toSortList[j+1] ){
                val moment = toSortList[j]
                toSortList[j] = toSortList[j+1]
                toSortList[j+1] = moment
            }
        }
    }
    println(numeroDeIteraciones)
    return toSortList
}

fun mergeSort(array: List<Int>): List<Int> {

    if (array.size <= 1) {
        return array
    }

    val middle = array.size / 2
    val left = array.subList(0, middle)
    val right = array.subList(middle, array.size)

    return merge(mergeSort(left), mergeSort(right))
}

fun merge(left: List<Int>, right: List<Int>): List<Int> {
    var i = 0
    var j = 0
    val merged = mutableListOf<Int>()

    while (i < left.size && j < right.size) {
        if (left[i] <= right[j]) {
            merged.add(left[i])
            i++
        } else {
            merged.add(right[j])
            j++
        }
    }

    while (i < left.size) {
        merged.add(left[i])
        i++
    }

    while (j < right.size) {
        merged.add(right[j])
        j++
    }

    return merged
}


//2) A partir de un string, mostrar por pantalla si la palabra es o no palíndromo
// (Es decir que se lee igual leyendo de un lado que otro). Por ejemplo la palabra "reconocer"
// es palíndromo pero la palabra "manuel" no lo es por que "manuel" != "leunam"

fun palidromo(palabra:String):Boolean{

    var isPalindromo = true
    var partePalindroma = ""

    for (i in 0..palabra.length -1){
        if (palabra[i] != palabra[palabra.length -1 -i]){
            partePalindroma += "-"
            isPalindromo = false
        }else{
            partePalindroma += palabra[i]

        }
    }
    println(partePalindroma)
    return isPalindromo

}


//3) A partir de un string mostrar por pantalla cuantas letras hay que cada una.
// No puedes usar métodos que te ayuden como String.replace o String.indexOf.

//Por ejemplo: "hello world" sería:
//h:1, e:1, l:3, o:2, w:1, r:1, d:1

fun contarPalabras(frase:String){

    val mapOfWords = mutableMapOf<Char,Int>()

    val splitedFrase = frase.split(" ")

    for (palabra in splitedFrase){
        for (letra in palabra){

            if (mapOfWords.containsKey(letra)){
                mapOfWords[letra] = mapOfWords[letra]!! + 1
            }else{
                mapOfWords[letra] = 1
            }

        }

    }

    println(mapOfWords)

}

//4) Dado una lista de array con números enteros como la siguiente {23, 19, 56, 13, 65, 23, 50, 12, 5, 10}
// crear un nuevo array con solo valores igual o mayores de 18.

fun mayoresOIgualA18(listaNums : List<Int>):MutableList<Int>{

    val toReturnList = mutableListOf<Int>()

    for (num in listaNums){
        if (num >= 18){
            toReturnList.add(num)
        }
    }

    return toReturnList

}

//5) Dado un número, mostrar por pantalla su minimo común multiplo. Es decir,
// la minima cantidad de valores que multiplicandolo da el valor original.

//Ejemplo: el mcm de 50 es 2, 5, 5
//El mcm de 120 es 2, 2, 2, 3, 5

fun minimos(num:Int):MutableList<Int>{

    var temp = num
    val minimos = mutableListOf<Int>()

    while (temp != 0){

        val mcm = minimoComunMultiplo(temp)

        minimos.add(mcm)

        temp /= mcm

        if (temp == 1){
            temp--
        }
    }
    print("num: $num --> $minimos")
    return minimos
}


fun minimoComunMultiplo(numOfMCM:Int):Int{

    if (numOfMCM == 1 ){
        return 1
    }

    for (i in 2..numOfMCM){
        if (numOfMCM % i == 0){
            return i
        }
    }
    return 0
}

//6) Dado un número entre 0 y 999.999.999 mostrar su texto de palabra por pantalla.
// Es decir, si escribes 123 mostrar por pantalla "ciento veintitrés"

// Unico hecho por chatgpt

fun translateNumbers(num:Int):String{

    if (num == 0) return "0"

    val unidades = arrayOf("", "uno", "dos", "tres", "cuatro", "cinco", "seis", "siete", "ocho", "nueve")
    val decenas = arrayOf("", "diez", "veinte", "treinta", "cuarenta", "cincuenta", "sesenta", "setenta", "ochenta", "noventa")
    val especiales = arrayOf("once", "doce", "trece", "catorce", "quince")
    val centenas = arrayOf("", "cien", "doscientos", "trescientos", "cuatrocientos", "quinientos", "seiscientos", "setecientos", "ochocientos", "novecientos")

    var resultado = ""

    if (num >=1000000){
        val millones = num/1000000
        resultado += translateNumbers(millones) + if (millones == 1) " millon " else " millones "
    }

    val restoDeMiles = num % 1000000
    if (restoDeMiles>= 1000){
        val miles = restoDeMiles / 1000
        resultado += translateNumbers(miles) +" mil "
    }

    val restoCentenas = restoDeMiles % 1000
    if (restoCentenas >= 100) {
        val centenasVal = restoCentenas / 100
        resultado += if (centenasVal == 1 && restoCentenas % 100 == 0) "cien " else centenas[centenasVal] + " "
    }

    val restoDecenas = restoCentenas % 100
    if (restoDecenas >= 20) {
        val decena = restoDecenas / 10
        resultado += decenas[decena]
        val unidadesRestantes = restoDecenas % 10
        if (unidadesRestantes > 0) resultado += " y " + unidades[unidadesRestantes]
    } else if (restoDecenas >= 10) {
        if (restoDecenas == 10) resultado += "diez"
        else if (restoDecenas in 11..15) resultado += especiales[restoDecenas - 11]
        else resultado += "dieci" + unidades[restoDecenas - 10]
    } else if (restoDecenas > 0) {
        resultado += unidades[restoDecenas]
    }

    return resultado.trim()
}


//7) Crear un método llamado "Calculadora" que reciba como parámetro de entrada un
// string que sea una operación matemática de sumas y restas de números enteros
// (No incluye multiplicaciones ni divisiones, ni paréntesis, tampoco decimales)
// y devuelve un entero con la solución
// Por ejemplo:
//"4+9+1-3" debería devolver 11
//"56-30+101" debería devolver 127

fun calculadora(cadenaDeOperacion:String):Int{

    var resultado = 0

    val first = cadenaDeOperacion.split("").filter { it.isNotEmpty() && it.isNotBlank() }[0]
    val numbersSringOrder = cadenaDeOperacion.split("+","-").filter { it.isNotEmpty() && it.isNotBlank() }
    val operationOrder = cadenaDeOperacion.split("").filter { it == "+"|| it == "-" }

    // se puede hacer esto suponiendo que la cadena se mete perfectamente
    //val tal = cadenaDeOperacion.split("+","-").filter { it.isNotEmpty() && it.isNotBlank() }.map { it.toInt() }

    val numbersOrder = mutableListOf<Int>()
    numbersSringOrder.forEach {
        try {
            numbersOrder.add(it.toInt())
        } catch (e: NumberFormatException) {
            println("El formato del numero no es correcto: ${e.message}")
        }
    }

    if (first == "-"){
        numbersOrder[0] = numbersOrder[0] * -1
    }

    var contOfOperations = 0
    numbersOrder.forEach {

        var addOrRem = 1
        if (it != numbersOrder.first()){
            contOfOperations++
            addOrRem = if (operationOrder[contOfOperations] == "+") 1 else -1
        }

        resultado += (it * addOrRem)

    }
    return resultado
}

//8) Crear un método llamado ConversorRomanos que le pasa como parámetro de entrada un
// string con un número romano y debe devolver un entero con la conversión.
//Por ejemplo:
//"IX" debería devolver 9
//"LXVI" debería devolver 66


fun conversorRomanos(numeroRomano: String): Int {

    val valores = mapOf(
        "I" to 1,
        "V" to 5,
        "X" to 10,
        "L" to 50,
        "C" to 100,
        "D" to 500,
        "M" to 1000
        )

    val numeroFiltrado = numeroRomano.uppercase().split("").filter { valores.containsKey(it) }

    val listaDeValores = mutableListOf<Int>()
    numeroFiltrado.forEach {
        listaDeValores.add(valores[it]!!)
    }

    var resultado = 0

    for (i in 0..listaDeValores.size - 2){
        if (listaDeValores[i] < listaDeValores[i+1]){
            resultado -= listaDeValores[i]
        }else{
            resultado += listaDeValores[i]
        }

    }

    resultado += listaDeValores.last()


    return resultado
}


//9) Crear un método llamado "Búsqueda" que tenga 2 parámetros de tipo string:
// 1 para un texto completo y otro para el texto búsqueda. Deberá devolver un int con
// la posición en la que se encuentra en el texto suponiendo que el primer carácter corresponde
// a la posición número 0. Si no encuentra nada el valor devuelto será -1. No usar métodos ya existentes como indexOf().
//Ejemplo: Busqueda("Hola mundo", "mundo") deberá devolver 5.

fun busqueda(frase: String,palabraBuscar:String):Int{

    val fraseSeparada = frase.split(" ")

    var letraIndex = 0

    fraseSeparada.forEach{
        if (it == palabraBuscar){
            return letraIndex
        }else{
            letraIndex += it.length
        }
        letraIndex += 1
    }

    return -1


}

//10) Crear un método llamado "DiferenciasString" que tenga 2 parámetros de
// tipo string y devuelva también otro valor de tipo string. El nuevo valor
// devuelto deberá tener los valores diferentes de los 2 string y no incluir los que estén en ambos.
//Ejemplo: DiferenciasString("hola", "caracola") deberá devolver "hcrc"

fun diferenciaasString(palabra1:String,palabra2:String):String{
    var stringToReturn = ""

    for (letra in palabra1){
        if (!contiene(palabra2,letra)){
            stringToReturn+= letra
        }
    }
    for (letra in palabra2){
        if (!contiene(palabra1,letra)){
            stringToReturn += letra
        }
    }

    return stringToReturn
}

fun contiene(palabra: String,letraToFind:Char):Boolean{
    for (letra in palabra){
        if (letra == letraToFind){
            return true
        }
    }
    return false
}

//11) Crear un método llamado "SeriesNumericas" que tendrá 2 parámetros:
// Los 2 serán de tipo int. El primer parámetro indicará cuantos saltos quiere
// dar en la serie (Por ejemplo si es 2 la serie irá de 2 en 2, si es 5 de 5 en 5, etc)
// y el segundo cuantos valores de la serie quiere que aparezca. El método no deberá devolver
// nada y debe mostrar en pantalla la serie empezando siempre desde el 0, en una sola linea.

fun seriesNumericas(pam1:Int,pam2:Int){
    val listToShow = mutableListOf<Int>()

    for (i in 0..pam1*pam2 step pam1) {
        listToShow.add(i)
    }

    println(listToShow.joinToString { "$it" })

}

//12) Crear un método llamado "EliminarRepetidos" que pasándole un array del tipo que
// desees, deberá devolver otro Array pero sin valores repetidos. No estará permitido
// usar Set o algún método que resuelva el ejercicio.
// Por ejemplo:
// { 1, 5, 8, 1, 8, 9 } deberá devolver { 1, 5, 8, 9 }


fun eliminarRepetidos(listaDeCosas: List<Any>):List<Any>{

    val contOfThings = mutableMapOf<Any,Int>()

    val listToReturn = mutableListOf<Any>()


    for (cosa in listaDeCosas){
        if (contOfThings.containsKey(cosa)){
            contOfThings[cosa] = contOfThings[cosa]!! + 1
        }else{
            contOfThings[cosa] = 1
        }
    }

    for ((cosa,cont) in contOfThings){
        listToReturn.add(cosa)
    }
    return listToReturn

}


//13) Para mostrar textos en pantalla se utiliza el siguiente método:
//C#: Console.WriteLine(string);
//Java: System.out.println(string);

//Para pedir textos por consola al usuario se utiliza el siguiente método:
//C#: Console.ReadLine();
//Java: Scanner sc = new Scanner(); sc.next();
//De esta manera podemos crear ejercicios más interactivos ya que influye lo que escriba el usuario.

//Crear un programa que solicite al usuario nombre y apellidos.
// Lo que hará el programa será poner todas las letras de cada
// palabra en minúscula excepto la primera, que será siempre mayúscula.
//Por ejemplo: "mANuEl mARiN" el programa lo mostrará por pantalla "Manuel Marin".
//Puedes usar cualquier método, incluso los toLowerCase y toUpperCase de la clase String.

fun nombreApellidos(){

    print("Dime tu nombre: ")
    var nombre = readln()

    while (nombre.isBlank() || nombre.isEmpty()){
        print("Promt icorrecto, dime un nombre valido: ")
        nombre = readln()
    }

    print("Dime tu apellido: ")
    var apellido = readln()

    while (apellido.isBlank() || apellido.isEmpty()){
        print("Promt icorrecto, dime un nombre valido: ")
        apellido = readln()
    }

    nombre = firstToMayus(nombre)
    apellido = firstToMayus(apellido)

    println("$nombre $apellido")

}


fun firstToMayus(palabra: String):String{
    val palabraLower = palabra.lowercase()
    var toReturn = ""

    //toReturn.drop(0)

    for (i in 1..palabraLower.length-1){
        toReturn+= palabraLower[i]
    }



    return palabra[0].uppercase() + toReturn
}

//forma facil
//fun firstToMayus(palabra: String):String{
//    return palabra.lowercase().replaceFirst(palabra[0],palabra[0].uppercaseChar())
//}



//14) Crear un programa que solicite al usuario un número entre 1 y 100. Si escribe
// un número correcto dentro del rango solicitado se mostrará el número por pantalla,
// pero si no lo pone mostrará un mensaje de error, pidiendo que escriba un número correcto.
// También mostrar el error si pones letras o simbolos en lugar de números. En C# existe un
// método llamado "TryParse" que te simplifica el problema.


fun pedirNumero(){
    // no es lo que pide el ejercicio
    //var num = readln().toIntOrNull()
    //if (num == null) {
    //    print("The num provided was invalid, try again: ")
    //    num = readln().toIntOrNull()
    //}

    val num :Int

    print("dime un numero dentro del 1 al 100: ")
    try {
        num = readln().toInt()
    }catch (e:Exception){
        println("numero invalido, escribe uno correto.")
        return
    }

    if (num in 1..100){
        println("numero correcto y dentro del rango")
    }else println("numero correcto pero fuera ddel rango")


}


//15) Crear un programa que solicite al usuario un número y debe mostrar por pantalla
// el número primo más cercano por arriba. Ejemplo, si pones 38 deberá mostrar 41.

fun primoMasCercanoPorArriba(){
    var num = readln().toIntOrNull()
    while (num == null){
        print("Pon un numero valido: ")
        num = readln().toIntOrNull()
    }


    var numToPrint = 0
    for(i in 0..Int.MAX_VALUE){
        if (esPrimo(i) && i > num){
            numToPrint = i
            break
        }
    }
    println(numToPrint)
}



fun esPrimo(num:Int):Boolean{

    var numDivisibles = 0

    for(i in 2..num-1){
        if (num % i == 0){
            return false
        }
    }

    return true

}

//16) Crear un programa que solicite al usuario dos números y debe mostrar su Máximo Común
// Divisor. Puedes usar la solución del mcm para obtener sus MCD, aunque te recomiendo
// utilizar el algoritmo de Euclides.

fun mcd(num1:Int,num2:Int){

    
}

fun main() {
    //val numeros  = mutableListOf(31, 766, 130,130,130,130, 41, 69, 86, 2, 42, 53)
    //val ordenados = quickSort(numeros)
    //numeros.sort()
    //println(numeros)
    //println(ordenados)
    //println(palidromo("hola oloh"))
    //contarPalabras("hola que tal")
    //println(mayoresOIgualA18(mutableListOf(23, 19, 56, 13, 65, 23, 50, 12, 5, 10)))
    //minimos(888888888)
    //print(translateNumbers(1000000))
    //calculadora("-112+32+3-123")
    //println(conversorRomanos("xiv"))
    //print(busqueda("hola tal", ""))
    //println(diferenciaasString("hola", "caracola"))
    //seriesNumericas(3,20)
    //println(eliminarRepetidos(listOf(1, 5, 8, 1, 8, 9,"hola","hola")))
    //nombreApellidos()
    //pedirNumero()
    primoMasCercanoPorArriba()
}

