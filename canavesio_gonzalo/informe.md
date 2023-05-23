# Informe 

## Modalidad

En el proyecto de manera individual estaré utilizando la estrategia de "una inteligencia artificial especificamente orientada a código". En la sección del informe, estarán denotadas...
> de la siguiente manera

...aquellas secciones/textos generados por coPilot y no por mí. Intentaré que la mayor parte del informe/investigación para entender Spark sea generada por coPilot y en casos de necesitar fuentes externas o agregar explicaciones propias, lo haré con formato normal.

## Preguntas básicas de la consigna

Estas preguntas me ayudaron a entender mejor como encarar la parte individual del proyecto.

> ### ¿Cómo se instala Apache Spark con Java en Windows?
> Para instalar Apache Spark con Java en Windows, primero hay que descargar el archivo .tgz de la página oficial de Apache Spark. Luego, hay que descomprimirlo en la carpeta que se desee. Por último, hay que configurar las variables de entorno para que el sistema operativo pueda encontrar los archivos de Spark.

Realicé todos los pasos anteriores hasta la descompresión del archivo .tgz. Al hacerlo me encontré con una carpeta jars, la cual copie su contenido en la carpeta lib de mi proyecto de Java.

> ### ¿Que estructura tiene un programa de Spark?
> Un programa de Spark tiene una estructura de flujo de datos. En este flujo de datos, se pueden distinguir dos tipos de operaciones: las operaciones de transformación y las operaciones de acción. Las operaciones de transformación son aquellas que toman un RDD y devuelven otro RDD. Las operaciones de acción son aquellas que toman un RDD y devuelven un valor.
> 
> Un RDD es una colección de elementos que se distribuyen a través de los nodos del cluster. Los RDDs son inmutables, es decir, que no se pueden modificar. Los RDDs se pueden crear a partir de archivos o de colecciones de datos en memoria.

Literalmente no entendí nada.

> ### ¿Qué estructura tiene un programa de conteo de palabras en diferentes documentos en Spark?
> Un programa de conteo de palabras en diferentes documentos en Spark tiene la siguiente estructura: 
> 1. Se crea un SparkContext.
> 2. Se crea un RDD a partir de los archivos de texto.
> 3. Se realiza un flatMap para separar las palabras de cada línea.
> 4. Se realiza un map para asignarle a cada palabra un valor de 1.
> 5. Se realiza un reduceByKey para sumar los valores de las palabras.
> 6. Se realiza un collect para obtener el resultado.
> 7. Se cierra el SparkContext.

El primer renglón fue fácil de generar, todo lo demás fue necesario escribir el "1." para que coPilot me sugiera el siguiente paso.

> ### ¿Cómo se integra una estructura orientada a objetos con la estructura funcional de map-reduce?
> Una estructura orientada a objetos se puede integrar con la estructura funcional de map-reduce a través de la programación funcional. La programación funcional es un paradigma de programación que trata a las funciones como ciudadanos de primera clase. Esto significa que las funciones se pueden asignar a variables, se pueden pasar como argumentos y se pueden devolver como valores de otras funciones. La programación funcional se puede utilizar para implementar la estructura funcional de map-reduce.

Literalmente no me dijo nada.

> ### ¿Que cosas debo importar para poder utilizar Spark en Java?
> Para poder utilizar Spark en Java, hay que importar las siguientes clases:
> - org.apache.spark.SparkConf
> - org.apache.spark.api.java.JavaSparkContext
> - org.apache.spark.api.java.JavaRDD
> - org.apache.spark.api.java.function.Function
> - org.apache.spark.api.java.function.FlatMapFunction
> - org.apache.spark.api.java.function.PairFunction
> - org.apache.spark.api.java.function.Function2
> - org.apache.spark.api.java.function.VoidFunction

Estoy seguro que la mitad de las clases que me sugirió no son necesarias.

## Realización del proyecto



