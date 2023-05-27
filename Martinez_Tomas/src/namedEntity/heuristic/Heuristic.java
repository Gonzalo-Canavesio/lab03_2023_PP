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
		// Rellene el mapa de entidades con entidades, su categoría y otros datos
		entityMap.put("Microsoft", createEntityData("Company"));
		entityMap.put("Apple", createEntityData("Company"));
		entityMap.put("Google", createEntityData("Company"));
		entityMap.put("Musk", createEntityData("Person"));
		entityMap.put("Biden", createEntityData("Person"));
		entityMap.put("Trump", createEntityData("Person"));
		entityMap.put("Messi", createEntityData("Person"));
		entityMap.put("Federer", createEntityData("Person"));
		entityMap.put("USA", createEntityData("Country"));
		entityMap.put("Russia", createEntityData("Country"));
	}

	private Map<String, Object> createEntityData(String category) {
		Map<String, Object> entityData = new HashMap<>();
		entityData.put("category", category);
		return entityData;
	}

	public String getCategory(String entity) {
		Map<String, Object> entityData = entityMap.get(entity);
		if (entityData != null) {
			return (String) entityData.get("category");
		}
		return null;
	}

	public abstract boolean isEntity(String word);
		
}
