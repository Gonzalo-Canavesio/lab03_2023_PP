# Compilar y ejecutar

Para compilar el proyecto, ejecutar el siguiente comando en la carpeta raíz del proyecto  

    ```bash
    mvn package
    ```

Para ejecutar el proyecto, ejecutar el siguiente comando en la carpeta raíz del proyecto  

    ```bash
    java -cp "lib/*:target/FeedReader-1.0.jar" FeedReaderMain -ne
    ```

También se puede hacer 

    ```bash
    mvn exec:java
    ```

Para **limpiar** los compilados hacer   

    ```bash
    mvn clean
    ```
