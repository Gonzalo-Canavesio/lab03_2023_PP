package namedEntity;

import java.lang.reflect.Field;
import java.io.Serializable;

/*Esta clase modela la nocion de entidad nombrada*/

public class EntidadNombrada implements Serializable {
	String name;
	String category;
	int frequency;
	String tema;
	static protected int EntidadNombradaFrequency = 0;

	public EntidadNombrada(String name, String category, int frequency, String nombre_canonico) {
		super();
		this.name = name;
		this.category = category;
		this.frequency = frequency;
		this.tema = nombre_canonico;
		EntidadNombradaFrequency++;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return name;
	}

	public void setCategory(String name) {
		this.name = name;
	}

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	public void incFrequency() {
		this.frequency++;
		EntidadNombradaFrequency++;
	}

	public int getEntidadNombradaFrequency() {
		return EntidadNombradaFrequency;
	}

	public void setEntidadNombradaFrequency(int entidadNombradaFrequency) {
		EntidadNombradaFrequency = entidadNombradaFrequency;
	}

	@Override
	public String toString() {
		return "ObjectNamedEntity [name=" + name + ", frequency=" + frequency + "]";
	}

	public void prettyPrint(){
        Class<?> clazz = getClass();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true); // Enable access to private fields

            String fieldName = field.getName();
            Object fieldValue;
            try {
                fieldValue = field.get(this); // Get the field value for the current instance
            } catch (IllegalAccessException e) {
                fieldValue = "N/A";
            }
			if(fieldName != "EntidadNombradaFrequency"){ 
            	System.out.println(fieldName + ": " + fieldValue);
			}
        }
		System.out.println("**********************************************************************************************");
    }

	public void setTema(String tema) {
        this.tema = tema;
	}

	public String getTema() {
        return this.tema;
	}

public void reduceFrequency(){
		this.frequency--;
		EntidadNombradaFrequency--;
	}

	public void prettyPrintFrecuencias(){
		System.out.println("**********************************************************************************************");
		System.out.println("Frecuencias clases: ");
		System.out.println("**********************************************************************************************");
		System.out.println("Frecuencia total: " + EntidadNombradaFrequency);
		System.out.println("Frecuencia de personas: " + persona.personaFrequency);
		System.out.println("Frecuencia de lugares: " + lugar.lugarFrequency);
		System.out.println("Frecuencia de organizaciones: " + organizacion.organizacionFrequency);
		System.out.println("Frecuencia de productos: " + producto.productoFrequency);
		System.out.println("Frecuencia de eventos: " + evento.eventoFrequency);
		System.out.println("Frecuencia de fechas: " + fecha.fechaFrequency);
		System.out.println("Frecuencia de otros: " + otro.otroFrequency);
		System.out.println("**********************************************************************************************");
		System.out.println("Frecuencias sublcases: ");
		System.out.println("**********************************************************************************************");	
		System.out.println("Frecuencia de nombres: " + nombre.nombreFrequency);
		System.out.println("Frecuencia de apellidos: " + apellido.apellidoFrequency);
		System.out.println("Frecuencia de titulos: " + titulo.tituloFrequency);
		System.out.println("Frecuencia de pais: " + pais.paisFrequency);
		System.out.println("Frecuencia de ciudad: " + ciudad.ciudadFrequency);
		System.out.println("Frecuencia de direccion: " + direccion.direccionFrequency);
		System.out.println("Frecuencia de OtrosLugares: " + OtroLugar.OtroLugarFrequency);
		System.out.println("**********************************************************************************************");
		System.out.println("Frecuencias subclases con temas: ");
		System.out.println("**********************************************************************************************");
		System.out.println("Frecuencia de apellidos futbol: " + apellidoFutbol.apellidoFutbolFrequency);
		System.out.println("Frecuencia de apellidos cine: " + apellidoCine.apellidoCineFrequency);
		System.out.println("Frecuencia de apellidos nacional: " + apellidoNacional.apellidoNacionalFrequency);
		System.out.println("Frecuencia de nombres futbol: " + nombreFutbol.nombreFutbolFrequency);
		System.out.println("Frecuencia de nombres cine: " + nombreCine.nombreCineFrequency);
		System.out.println("Frecuencia de nombres nacional: " + nombreNacional.nombreNacionalFrequency);
		System.out.println("Frecuencia de pais futbol: " + paisFutbol.paisFutbolFrequency);
		System.out.println("Frecuencia de pais cine: " + paisCine.paisCineFrequency);
		System.out.println("Frecuencia de pais nacional: " + paisNacional.paisNacionalFrequency);
		System.out.println("Frecuencia de ciudad futbol: " + ciudadFutbol.ciudadFutbolFrequency);
		System.out.println("Frecuencia de ciudad cine: " + ciudadCine.ciudadCineFrequency);
		System.out.println("Frecuencia de ciudad nacional: " + ciudadNacional.ciudadNacionalFrequency);
		System.out.println("**********************************************************************************************");
	}
}





