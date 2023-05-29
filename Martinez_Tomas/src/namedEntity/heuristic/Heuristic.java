package namedEntity.heuristic;

import java.util.Map;
import java.util.HashMap;

public abstract class Heuristic {

	// TODO: Crear un map que asocie el string de una entidad con un map (o un array) que contenga su categoria y otros datos para rellenar sus atributos
	private Map<String, Map<String, Object>> entityMap = new HashMap<>();
	public Heuristic() {
		initializeEntityMap();
	}
	private void initializeEntityMap() {
        // Apellido entries
        entityMap.put("Trump", Map.of("categoria", "Apellido",
         "tema", "Internacional",
         "id", "1",
         "origen", "estadounidense"));
        entityMap.put("Musk", Map.of("categoria", "Apellido",
         "tema", "Otro",
         "id", "2",
         "origen", "sudafricano"));
        entityMap.put("Biden", Map.of("categoria", "Apellido",
         "tema", "Internacional",
         "id", "3",
         "origen", "estadounidense"));
        entityMap.put("Jefferson", Map.of("categoria", "Apellido",
         "tema", "Otro",
         "id", "4",
         "origen", "estadounidense"));
        entityMap.put("Kugler", Map.of("categoria", "Apellido",
         "tema", "Otro",
         "id", "5",
         "origen", "desconocido"));
        entityMap.put("Yaccarino", Map.of("categoria", "Apellido",
         "tema", "Otro",
         "id", "6",
         "origen", "desconocido"));
        entityMap.put("Licht", Map.of("categoria", "Apellido",
         "tema", "Otro",
         "id", "7",
         "origen", "desconocido"));
        entityMap.put("Ros", Map.of("categoria", "Apellido",
         "tema", "Otro",
         "id", "8",
         "origen", "desconocido"));

        // Nombre entries
        entityMap.put("Elon", Map.of("categoria", "Nombre",
         "tema", "Otro",
         "id", "9",
         "origen", "desconocido"));
        entityMap.put("Philip", Map.of("categoria", "Nombre",
         "tema", "Otro",
         "id", "10",
         "origen", "desconocido"));
        entityMap.put("Adriana", Map.of("categoria", "Nombre",
         "tema", "Otro",
         "id", "11",
         "origen", "desconocido"));
        entityMap.put("Chris", Map.of("categoria", "Nombre",
         "tema", "Otro",
         "id", "12",
         "origen", "desconocido"));
        entityMap.put("Ana", Map.of("categoria", "Nombre",
         "tema", "Otro",
         "id", "13",
         "origen", "desconocido"));
        entityMap.put("Donald", Map.of("categoria", "Nombre",
         "tema", "Internacional",
         "id", "14",
         "origen", "desconocido"));
        entityMap.put("Jayson", Map.of("categoria", "Nombre",
         "tema", "Otros",
         "id", "15",
         "origen", "desconocido"));

        // Ciudad entries
        entityMap.put("Washington", Map.of("categoria", "Ciudad",
         "tema", "Internacional",
         "id", "16",
         "pais", "Estados Unidos", "capital", "Washington",
         "poblacion", "705749"));
        entityMap.put("Slovenia", Map.of("categoria", "Ciudad",
         "tema", "Otro",
         "id", "17",
         "pais", "Eslovenia",
         "capital", "Liubliana",
         "poblacion", "280000"));
        entityMap.put("Boston", Map.of("categoria", "Ciudad",
         "tema", "Otro",
         "id", "18",
         "pais", "Estados Unidos", "capital", "Washington",
         "poblacion", "694583"));
        entityMap.put("Chicago", Map.of("categoria", "Ciudad",
         "tema", "Otro",
         "id", "19",
         "pais", "Estados Unidos", "capital", "Washington",
         "poblacion", "2705994"));
        entityMap.put("Houston", Map.of("categoria", "Ciudad",
         "tema", "Otro",
         "id", "20",
         "pais", "Estados Unidos", "capital", "Washington",
         "poblacion", "2320268"));

		entityMap.put("Messi", Map.of("categoria", "Apellido",
         "tema", "Futbol",
         "id", "21",
         "origen", "italiano"));
		entityMap.put("Lionel", Map.of("categoria", "Nombre",
         "tema", "Futbol",
         "id", "21",
         "origen", "frances"));
		entityMap.put("Spielberg", Map.of("categoria", "Apellido",
         "tema", "Cine",
         "id", "22",
         "origen", "aleman"));
		entityMap.put("Steven", Map.of("categoria", "Nombre",
         "tema", "Cine",
         "id", "22",
         "origen", "griego"));
		entityMap.put("Fernandez", Map.of("categoria", "Apellido",
         "tema", "Nacional",
         "id", "23",
         "origen", "español"));
        entityMap.put("Manchester",Map.of("categoria","Ciudad","tema","Futbol", "id",
         "24", "pais",
         "Inglaterra", "capital",
         "", "poblacion", "530300"));
		entityMap.put("USA", Map.of("categoria", "Pais",
         "tema", "Internacional",
         "id", "25",
         "capital", "Washington",
         "poblacion", "328200000"));
		entityMap.put("Cordoba", Map.of("categoria", "Ciudad",
         "tema", "Nacional",
         "id", "26",
         "pais", "Argentina",
         "capital", "Cordoba",
         "poblacion", "1330023"));
	}

	public Map<String, Object> getData(String entity) {
		return entityMap.get(entity);
	}

	public abstract boolean isEntity(String word);

}
