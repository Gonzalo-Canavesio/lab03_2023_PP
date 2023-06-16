## Estructura del proyecto

A continuación vamos a describir el flujo del programa y la estructura del proyecto, relacionandolo con la estructura MapReduce propia de Spark.

1. Se crea el SparkConf y el SparkContext.
2. Se crea una RDD (Resilient Distributed Dataset) a partir de una lista de feeds RSS crudos (`List<RoughFeed>`)
3. Se aplica una transformación de tipo `map` a la RDD para convertir los feeds RSS crudos en feeds RSS ya procesados mediante el uso de la función `doParse`

Los 3 pasos anteriores se comparten sin importar los argumentos de entrada, ya que son necesarios para cualquier tipo de ejecución.

Los siguientes pasos son los que se ejecutan en particular al realizar la ejecución de la parte grupal del laboratorio, es decir, crear un índice invertido de la colección de documentos.

1. Se aplica una transformación de tipo map mediante `flatMap` a la RDD para obtener de cada feed RSS sus articulos. Entonces se obtiene una RDD de tipo `Article` con mayor cantidad de elementos que la RDD de feeds RSS (Suponiendo que cada feed RSS tenia más de 1 articulo).
2. Se aplica una transformación de tipo map mediante `zipWithUniqueId` a la RDD de articulos para agregarle un indice a cada articulo. Entonces se obtiene una RDD que contiene tuplas de la forma `(Articulo,ID)`.
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


