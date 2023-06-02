package namedEntity;
import namedEntity.heuristic.Heuristic;
import namedEntity.heuristic.QuickHeuristic;
import java.util.Map;
import java.io.Serializable;


public class CreadorEntidades implements Serializable {
    public EntidadNombrada createEntity(String namedEntity, Integer frequency){
		EntidadNombrada ne = null;
		Heuristic h = new QuickHeuristic(); // Da igual que heuristica se use, ya que solo se usa para obtener la categoria
		Map<String, Object> data = h.getData(namedEntity);
		if(data == null){
			ne = new EntidadNombrada(namedEntity, "unknown", frequency, "unknown");
		} else {
			String category = (String) data.get("categoria");
			String tema = (String) data.get("tema");
			String nc = (String) data.get("nombre_canonico");
			if(category == "Apellido" && tema == "Futbol"){
				ne = new apellidoFutbol(namedEntity, category, frequency, nc, Integer.parseInt((String) data.get("id")), (String) data.get("origen"));
				ne.setTema(tema);
			} else if(category == "Apellido" && tema == "Cine"){
				ne = new apellidoCine(namedEntity, category, frequency, nc, Integer.parseInt((String) data.get("id")), (String) data.get("origen"));
				ne.setTema(tema);
			} else if(category == "Apellido" && tema == "Nacional"){
				ne = new apellidoNacional(namedEntity, category, frequency, nc, Integer.parseInt((String) data.get("id")), (String) data.get("origen"));
				ne.setTema(tema);
			} else if(category == "Nombre" && tema == "Futbol"){
				ne = new nombreFutbol(namedEntity, category, frequency, nc, Integer.parseInt((String) data.get("id")), (String) data.get("origen"), (String) data.get("formasAlt"));
				ne.setTema(tema);
			} else if(category == "Nombre" && tema == "Cine") {
				ne = new nombreCine(namedEntity, category, frequency, nc, Integer.parseInt((String) data.get("id")), (String) data.get("origen"), (String) data.get("formasAlt"));
				ne.setTema(tema);
			} else if(category == "Nombre" && tema == "Cine") {
				ne = new nombreNacional(namedEntity, category, frequency, nc, Integer.parseInt((String) data.get("id")), (String) data.get("origen"), (String) data.get("formasAlt"));
				ne.setTema(tema);
			} else if(category == "Pais" && tema == "Futbol"){
				ne = new paisFutbol(namedEntity, category, frequency, nc, Integer.parseInt((String) data.get("poblacion")), (String) data.get("lenguaOficial"));
				ne.setTema(tema);
			} else if(category == "Pais" && tema == "Cine"){
				ne = new paisCine(namedEntity, category, frequency, nc, Integer.parseInt((String) data.get("poblacion")), (String) data.get("lenguaOficial"));
				ne.setTema(tema);
			} else if(category == "Pais" && tema == "Nacional"){
				ne = new paisNacional(namedEntity, category, frequency, nc, Integer.parseInt((String) data.get("poblacion")), (String) data.get("lenguaOficial"));
				ne.setTema(tema);
			} else if(category == "Ciudad" && tema == "Futbol"){
				ne = new ciudadFutbol(namedEntity, category, frequency, nc, (String) data.get("Pais"), (String) data.get("Capital"), Integer.parseInt((String) data.get("poblacion")));
				ne.setTema(tema);
			} else if(category == "Ciudad" && tema == "Cine"){
				ne = new ciudadCine(namedEntity, category, frequency, nc, (String) data.get("Pais"), (String) data.get("Capital"), Integer.parseInt((String) data.get("poblacion")));
				ne.setTema(tema);
			} else if(category == "Ciudad" && tema == "Cine"){
				ne = new ciudadNacional(namedEntity, category, frequency, nc, (String) data.get("Pais"), (String) data.get("Capital"), Integer.parseInt((String) data.get("poblacion")));
				ne.setTema(tema);
			} else if(category == "Apellido"){
				ne = new apellido(namedEntity, category, frequency, nc, Integer.parseInt((String) data.get("id")), (String) data.get("origen"));
				ne.setTema(tema);
			} else if(category == "Nombre"){
				ne = new nombre(namedEntity, category, frequency, nc, Integer.parseInt((String) data.get("id")), (String) data.get("origen"), (String) data.get("formasAlt"));
				ne.setTema(tema);
			} else if(category == "Titulo"){
				ne = new titulo(namedEntity, category, frequency, nc, Integer.parseInt((String) data.get("id")),(String) data.get("Profesional"));
				ne.setTema(tema);
			} else if(category == "Pais"){
				ne = new pais(namedEntity, category, frequency, nc, Integer.parseInt((String) data.get("poblacion")), (String) data.get("lenguaOficial"));
				ne.setTema(tema);
			} else if(category == "Ciudad"){
				ne = new ciudad(namedEntity, category, frequency, nc, (String) data.get("Pais"), (String) data.get("Capital"), Integer.parseInt((String) data.get("poblacion")));
				ne.setTema(tema);
			} else if(category == "Direccion"){
				ne = new direccion(namedEntity, category, frequency, nc, (String) data.get("ciudad"));
				ne.setTema(tema);
			} else if(category == "OtroLugar"){
				ne = new OtroLugar(namedEntity, category, frequency, nc, (String) data.get("comentario"));
				ne.setTema(tema);
			} else if(category == "Persona"){
				ne = new persona(namedEntity, category, frequency, nc, Integer.parseInt((String) data.get("id")));
				ne.setTema(tema);
			} else if(category == "Lugar"){
				ne = new lugar(namedEntity, category, frequency, nc);
			} else if(category == "Organizacion"){
				ne = new organizacion(namedEntity, category, frequency, nc, Integer.parseInt((String) data.get("NroMiembros")), (String) data.get("tipoOrg"));
				ne.setTema(tema);
			} else if(category == "Producto"){
				ne = new producto(namedEntity, category, frequency, nc, (String) data.get("Comercial"), (String) data.get("Productor"));
				ne.setTema(tema);
			} else if(category == "Evento"){
				ne = new evento(namedEntity, category, frequency, nc, (String) data.get("fecha"), Integer.parseInt((String) data.get("recurrente")));
				ne.setTema(tema);
			} else if(category == "Fecha"){
				ne = new fecha(namedEntity, category, frequency, nc, (String) data.get("fechaPrecisa"));
				ne.setTema(tema);
			} else if(category == "Otro"){
				ne = new otro(namedEntity, category, frequency, nc, (String) data.get("comentario"));
				ne.setTema(tema);
			} else {
				ne = new EntidadNombrada(namedEntity, category, frequency, nc);
				ne.setTema(tema);
			}
		}
		return ne;
	}
}
