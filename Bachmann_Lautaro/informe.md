# Modalidad
Busquedas en stack overflow o la internet en general.
Las busquedas se realizan en inglés.

# Preguntas
## ¿Cómo se instala Spark en Arch Linux?
### Instalacion
Busqueda: how to install apache spark on arch linux

> Install the apache-spark (AUR) package.

[Fuente](https://wiki.archlinux.org/title/Apache_Spark)

En mi caso, tengo que correr el siguiente comando:
```
paru -S apache-spark
```

### Comprobar instalacion
Para comprobar que se haya instalado bien añadi algunos imports que encontré
en el ejemplo de este
[link](https://spark.apache.org/docs/0.9.1/java-programming-guide.html)
al proyecto

Como al compilar me saltó un error que me decia que el paquete `org.apache.spark`
no estaba definido, seguí buscando hasta que encontré lo siguiente:

![](./informe_figs/fig1.png)

en este [link](https://dzone.com/articles/the-magic-of-apache-spark-in-java-1)

Con lo cual me di cuenta que debia instalar una libreria de java para poder usar
Spark.

### Instalar libreria de java
Busqué `apache-spark` en [Maven](https://mvnrepository.com/artifact/org.apache.spark/spark-core_2.13/3.3.0)
y descargue el archivo .jar de la libreria y lo puse en el directorio lib/

Seguia dandome error al compilar, así que me puse a revisar el makefile que estaba usando
y me di cuenta que no estaba añadiendo la libreria spark al classpath al compilar
el codigo.
Así que la añadi tomando como ejemplo otras partes del makefile
y finalmente no me dio error al compilar el codigo.

## ¿Qué estructura tiene un programa en Spark?
[Fuente]()

## ¿Qué estructura tiene un programa de conteo de palabras en diferentes documentos en Spark?
[Fuente]()

## ¿Cómo adaptar el código del Laboratorio 2 a la estructura del programa objetivo en Spark?
[Fuente]()

## ¿Cómo se integra una estructura orientada a objetos con la estructura funcional de map-reduce?
[Fuente]()
