package namedEntity;


class apellidoFutbol extends apellido implements Futbol{
        apellidoFutbol(String name, String category, int frequency, int id, String origen){
            super(name, category, frequency, id, origen);
        }
}

            
class apellidoCine extends apellido implements Cine{
        apellidoCine(String name, String category, int frequency, int id, String origen){
            super(name, category, frequency, id, origen);
        }
}

            
class apellidoNacional extends apellido implements Nacional{
        apellidoNacional(String name, String category, int frequency, int id, String origen){
            super(name, category, frequency, id, origen);
        }
}

            
class nombreFutbol extends nombre implements Futbol{
        nombreFutbol(String name, String category, int frequency, int id, String origen, String formasAlt){
            super(name, category, frequency, id, origen, formasAlt);
        }
}

            
class nombreCine extends nombre implements Cine{
        nombreCine(String name, String category, int frequency, int id, String origen, String formasAlt){
            super(name, category, frequency, id, origen, formasAlt);
        }
}

            
class nombreNacional extends nombre implements Nacional{
        nombreNacional(String name, String category, int frequency, int id, String origen, String formasAlt){
            super(name, category, frequency, id, origen, formasAlt);
        }
}

            
class paisFutbol extends pais implements Futbol{
        paisFutbol(String name, String category, int frequency, int id, int poblacion, String lenguaOficial){
            super(name, category, frequency, id, poblacion, lenguaOficial);
        }
}

            
class paisCine extends pais implements Cine{
        paisCine(String name, String category, int frequency, int id, int poblacion, String lenguaOficial){
            super(name, category, frequency, id, poblacion, lenguaOficial);
        }
}

            
class paisNacional extends pais implements Nacional{
        paisNacional(String name, String category, int frequency, int id, int poblacion, String lenguaOficial){
            super(name, category, frequency, id, poblacion, lenguaOficial);
        }
}

            
class ciudadFutbol extends ciudad implements Futbol{
        ciudadFutbol(String name, String category, int frequency, int id, pais pais, String capital, int poblacion){
            super(name, category, frequency, id, pais, capital, poblacion);
        }
}

            
class ciudadCine extends ciudad implements Cine{
        ciudadCine(String name, String category, int frequency, int id, pais pais, String capital, int poblacion){
            super(name, category, frequency, id, pais, capital, poblacion);
        }
}

            
class ciudadNacional extends ciudad implements Nacional{
        ciudadNacional(String name, String category, int frequency, int id, pais pais, String capital, int poblacion){
            super(name, category, frequency, id, pais, capital, poblacion);
        }
}

            