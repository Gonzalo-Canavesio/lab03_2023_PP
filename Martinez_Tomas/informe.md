# Informe: Lab 03

## Forma de trabajo

En la parte individual de este lab, usare la estrategia de ChatGPT.
Utilizare esta estrategia realizando preguntas acerca de las preguntas y las utilizare para responderlas, ademas tambien para ayudarme a utilizar spark y asi realizar el lab.

## Preguntas

> ### ¿Cómo se instala Spark en una computadora personal?
Primero que nada para poder instalarlo en mi nootebook con ubuntu le hice la sig pregunta a chatGPT:
![Pregunta en ingles, sobre como instalar spark](/home/tomas22/Imágenes/instalar_spark01.png)
Luego me dio la siguiente respuesta la cual segui paso a paso:
![Chequear que este todo en orden](/home/tomas22/Imágenes/instalar_spark02.png)
Aca chatgpt me pide que me fije mi version de java.
![Links de descarga](/home/tomas22/Imágenes/instalar_spark03.png)
Aca me dice que me descarge en la pagina oficial de spark y descarge la version que quiera y que despues vaya a donde esta el tgz descargado (en descargas).
![Descomprimir y configurar](/home/tomas22/Imágenes/instalar_spark04.png)
Aca descomprimo tal como me dice que lo haga, en este caso utilizando el nombre adecuado del archivo que tenga y dsp moverlo a la carpeta de opt.
Despues me dice que tengo que configurar las variables de entorno, con el codigo que me da.
![Configurar variables de entorno y verificar instalacion](/home/tomas22/Imágenes/instalar_spark05.png) 
Por ultmo me dice que agrege esas lineas al final del codigo de bashrc, guardarlos y subirlos, y despues verificar que este todo en orden ejecutando ese comando.
Al hacerlo claramente esta todo bien.
![Verificar instalacion](/home/tomas22/Imágenes/instalar_spark06.png)

> ### ¿Qué estructura tiene un programa en Spark?
Primero que nada le realizo la pregunta:
![Pregunta en ingles, sobre la estructura de un programa en spark](/home/tomas22/Imágenes/Estructura01.png)
Y me dice lo siguiente, voy a ir comentando el proceso:
Me dice que la estructura en spark tiene las siguientes 6 componentes:
![Respuesta](/home/tomas22/Imágenes/Estructura02.png)
Estos componentes nos hablan de lo siguiente:
 - Configuracion: Se encarga de configurar los parametros de ejecucion de spark, el cual es el punto de entrada a la funcionalidad de spark. Por medio de algun codigo o mediante la especificacion de propiedades en un archivo de configuracion.
 - Creacion del contexto: Crea el contexto de spark, que proporciona la interfaz principal para interactuar con el entorno de Spark. Como dice **SparkContext** para aplicaciones básicas o **SparkSession** para aplicaciones estructuradas.
 - Carga de datos: Carga los datos de entrada en un RDD, DataFrame o DataSet (Estructuras de datos principales de Spark).

Luego nos sigue contando:
![Mas respuesta](/home/tomas22/Imágenes/Estructura03.png)
 - Transformaciones: Aplican una función a un RDD, DataFrame o DataSet para crear un nuevo RDD, DataFrame o DataSet. Las transformaciones  no se ejecutan hasta que se llama a una acción.
 - Acciones: Realizan una operación en un RDD, DataFrame o DataSet y devuelven el resultado al controlador de la aplicación o escriben el resultado en un sistema de almacenamiento externo . Las acciones son el punto de entrada para que Spark ejecute el trabajo de la aplicación.
 - Gestion de recursos: Se realiza la gestión de los recursos utilizados por la aplicación de Spark, como la liberación de memoria, la liberación de conexiones a recursos externos, etc. Es importante liberar los recursos utilizados por la aplicación de Spark para que puedan ser utilizados por otras aplicaciones.


> ### ¿Qué estructura tiene un programa de conteo de palabras en diferentes documentos en Spark?
