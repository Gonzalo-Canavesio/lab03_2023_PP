package parser.subscriptionParser;
import parser.GeneralParser;
import subscription.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public abstract class SubscriptionParser<T> implements GeneralParser<Subscription, String> {

    public Subscription parse(String path) throws IOException {
        Subscription subscription = new Subscription(path);
 
        FileReader reader = openFileReader(path);

        List<T> objects = getListObjects(reader);

        // Itero sobre cada una de las suscripciones
        for (T object : objects) {

            // Parseo la suscripción
            SingleSubscription singleSubscription = parseObject(object);

            // Agrego la suscripción al objeto subscription
            subscription.addSingleSubscription(singleSubscription);
        }

        reader = closeFileReader(reader);

        return subscription; 
    }

    protected FileReader openFileReader(String path) {
        FileReader reader;
        try{
            reader = new FileReader(path);
        } catch (Exception e){
            System.out.println("Error al abrir el archivo de suscripciones");
            e.printStackTrace();
            return null;
        }
        return reader;
    }

    protected FileReader closeFileReader(FileReader reader) {
        try {
            reader.close(); // Cierro el archivo
        } catch (Exception e){
            System.out.println("Error al cerrar el archivo de suscripciones");
            e.printStackTrace();
            return null;
        }
        return reader;
    }

    protected abstract List<T> getListObjects(FileReader reader) throws IOException;

    protected abstract SingleSubscription parseObject(T object);

}
