package namedEntity;

import namedEntity.heuristic.Heuristic;
import namedEntity.heuristic.QuickHeuristic;

/*Esta clase modela la nocion de entidad nombrada*/

public class EntidadNombrada {
	String name;
	String category;
	int frequency;
	String tema;
	private static int count = 0;
	static protected int EntidadNombradaFrequency = 0;

	public EntidadNombrada(String name, String category, int frequency) {
		super();
		this.name = name;
		this.category = category;
		this.frequency = frequency;
		EntidadNombradaFrequency++;
		count++;
	}

	public static int getCount() {
		return count;
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
		System.out.println(this.getName() + " " + this.getFrequency());
	}

	public void setTema(String tema) {
        this.tema = tema;
	}

	public String getTema() {
        return this.tema;
	}

	public EntidadNombrada createEntity(String namedEntity){
		EntidadNombrada ne;
		Heuristic h = new QuickHeuristic(); // Da igual que heuristica se use, ya que solo se usa para obtener la categoria
		String category = h.getCategory(namedEntity);
		// TODO: Crear una entidad nombrada de la categoria correspondiente y devolverla (Ver como hacer para que el constructor no de problema)
		if(category == null){
			ne = new EntidadNombrada(namedEntity, "unknown", 1);
		} else if(category == "persona"){
			ne = new persona(namedEntity, "persona", 1, 0);
		} else {
			ne = new EntidadNombrada(namedEntity, "unknown", 1);
		}
		return ne;
	}
}


