#  Informe

## Como ejecutar el proyecto

Requerimientos:

- Tener instalado [Apache Spark](https://spark.apache.org/downloads.html)
- Tener instalado [Apache Maven](https://maven.apache.org/download.cgi)

Ejecución:

Entrando en el directorio "Grupal" se puede encontrar un makefile que permite la ejecución del proyecto facilmente. Los comandos para su ejecución son:

- make run (Impresión de los feeds)
- make run-ne (Conteo de entidades nombradas)
- make run-search word=<`palabra clave`> (Busqueda de articulos con esa palabra clave)
- ~~make run-graph (Grafico de Histograma)~~ Por problemas de dependencias no pudimos hacer que funcione la realización del gráfico, intentamos con varias librerias pero ninguna funcionó.

## Elección de base para la parte grupal

En esta seccion grupal del proyecto lo primero que hicimos fue comparar nuestras respectivas partes individuales y nos dimos cuenta que las tres cumplían con lo pedido y lo realizaban de maneras similares, todas tenían un buen funcionamiento, sin embargo el de Gonza habia implementado una estructura map-reduce en Spark en la obtención de feeds además de en la identificación de las entidades nombrada.

Aunque nos hayamos basado centralmente en el de Gonza también se agregaron detalles de las otras 2 implementaciones que sentimos que funcionarían mejor para el lab grupal.

## Primeros cambios en el lab grupal

Tomando en cuenta las correcciones del lab2, intentamos evitar tanta repetición de código en el main e hicimos que el código compartido entre las 3 diferentes posibles ejecuciones (Listar articulos, listar entidades nombradas y buscar articulos por una palabra clave) no se repita y este escrito 1 sola vez.

Otro de los cambios que hicimos fue empezar a usar Maven, ya que algunos de los
integrantes del grupo tenian problemas para instalar las librerias necesarias.

## Estructura del proyecto

A continuación vamos a describir el flujo del programa y la estructura del proyecto, relacionandolo con la estructura MapReduce propia de Spark.

1. Se crea el SparkConf y el SparkContext.
2. Se crea una RDD (Resilient Distributed Dataset) a partir de una lista de feeds RSS crudos (`List<RoughFeed>`)
3. Se aplica una transformación de tipo `map` a la RDD para convertir los feeds RSS crudos en feeds RSS ya procesados mediante el uso de la función `doParse`

Los 3 pasos anteriores se comparten sin importar los argumentos de entrada, ya que son necesarios para cualquier tipo de ejecución.

Para separar las ejecuciones agregamos un condicional al main, para a la hora de correr el programa elegir la opcion de buscar y ordenar las entidades. La palabra que se usará para buscar los articulos será ingresada como argumento al programa. El condicional tiene la siguiente estructura: `args.length == 2 && args[0].equals("-search")`

Los siguientes pasos son los que se ejecutan en particular al realizar la ejecución de la parte grupal del laboratorio, es decir, crear un índice invertido de la colección de documentos.

1. Se aplica una transformación de tipo map mediante `flatMap` a la RDD para obtener de cada feed RSS sus articulos. Entonces se obtiene una RDD de tipo `Article` con mayor cantidad de elementos que la RDD de feeds RSS (Suponiendo que cada feed RSS tenia más de 1 articulo).
2. Se aplica una transformación mediante `zipWithUniqueId` a la RDD de articulos para agregarle un indice a cada articulo. Entonces se obtiene una RDD que contiene tuplas de la forma `(Articulo,ID)`.
3. Se aplica una transformación de tipo filter mediante `filter` para dejar unicamente los pares de la forma `(SearchTerm, ID)`
4. Se aplica una transformación de tipo map mediante `mapToPair` para convertir cada par de la forma `(SearchTerm, ID)` en un par de la forma `(ID, 1)`
5. Se aplica una transformación de tipo reduce mediante `reduceByKey` para sumarizar los valores de cada ID. Entonces se obtiene una RDD que contiene tuplas de la forma `(ID, Cant de apariciones de SearchTerm)`
6. Se ordena la RDD mediante el uso de `mapToPair` y `sortByKey` para obtener una RDD ordenada por la cantidad de apariciones de cada ID.
7. Se aplica una transformación de tipo map mediante `mapToPair` para convertir cada par de la forma `(ID, Cant de apariciones de SearchTerm)` en un par de la forma `(Articulo, Cant de apariciones de SearchTerm)`
8. Se juntan los pares de la forma `(ID, Cant de apariciones de SearchTerm)` en el driver mediante `collect` y se los guarda en una lista de tuplas de la forma `(ID, Cant de apariciones de SearchTerm)`
9. Se juntan los pares de la forma `(Articulo, ID)` en el driver mediante `collect` y se los guarda en una lista de tuplas de la forma `(Articulo, ID)`
10. Se cierra el SparkContext
11. Se combinan las listas de 8. y 9. en una lista de tuplas de la forma `(Articulo, Cant de apariciones de SearchTerm)`
12. Se imprime la lista de articulos (ordenados según la cantidad de apariciones de cada termino de búsqueda) con su titulo, link y cantidad de apariciones de cada termino de búsqueda.

### Funcionamiento del map-reduce

Map-reduce es el modelo que se sigue durante el proyecto para realizar el procesamiento de los datos, es un modelo de programación utilizado para realizar cálculos distribuidos en sistemas de procesamiento de datos masivos.

Se compone de dos fases principales: la fase de "Map" y la fase de "Reduce". A continuación vemos en detalle cada una de ellas:

#### Fase de Map

En esta fase, los datos de entrada se dividen en fragmentos más pequeños y se asignan a los nodos de procesamiento disponibles en un clúster distribuido.
Cada nodo de procesamiento ejecuta una función de mapeo (Map) en paralelo en los datos asignados a él. La función de mapeo toma un conjunto de datos de entrada y los transforma en pares clave-valor.
El resultado de la función de mapeo se almacena en una estructura intermedia llamada "intermediate key-value store" (almacenamiento intermedio clave-valor).
En Spark las funciones de tipo map son `flatMap`, `mapToPair`, `map`, entre otras.


#### Fase de Reduce

En esta fase, los datos intermedios generados en la fase de Map se agrupan según su clave y se envían a los nodos de reducción disponibles en el clúster distribuido.
Cada nodo de reducción ejecuta una función de reducción (Reduce) en paralelo en los datos recibidos. La función de reducción combina los datos con la misma clave y genera un conjunto de resultados reducidos.
Los resultados reducidos se almacenan en un almacenamiento de salida final.
En Spark las funciones de tipo reduce son `reduceByKey`, `reduce`, entre otras.

![MapReduce](https://i.imgur.com/5VjJWQ2.png)

La estructura Map-Reduce en Spark se encarga automáticamente de la distribución de los datos y de la coordinación entre los nodos de procesamiento. Además, maneja tareas como la tolerancia a fallos, la recuperación de errores y la replicación de datos.

La clave del éxito de Map-Reduce radica en su capacidad para procesar grandes volúmenes de datos en paralelo y de manera escalable. Al dividir los datos y las operaciones en tareas más pequeñas y distribuirlas en múltiples nodos, se logra un procesamiento más eficiente y rápido.

## Fuentes utilizadas para el proyecto
Le pedimos a ChatGPT una estructura base para realizar un indice invertido usando Spark en Java, a partir de esa estructura base fuimos entendiendola, corrigiendo un par de errores que presentaba y generando la versión final del proyecto.

Mientras realizamos el proyecto también hicimos uso de GitHub coPilot para la escritura del código y consultamos en varias ocasiones la documentación de Apache Spark para sacarnos dudas sobre el uso y comportamiento de algunas funciones.

## Logros y dificultades
- Pudimos terminar el proyecto utilizando la estructura map-reduce para la obtención del indice invertido
- Pudimos utilizar la estructura map-reduce también para la obtención de feeds
- Aprendimos bastante sobre calculo distribuido, sus ventajas y sus desventajas
- Realizamos test unitario para verificar que el código funciona como debería
- Aunque intentamos hacer el histograma solicitado en el enunciado, no pudimos conseguir que funcionen las dependencias para realizarlo
- En varias ocasiones se complico lidiar con mensajes de error poco entendibles de las librerias
o el mismo java.
    - Intentamos implementar el punto estrella del histograma pero por mas que intentamos varias
    formas no pudimos hacer andar la libreria necesaria.
