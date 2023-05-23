package namedEntity;


class apellidoFutbol extends apellido implements Futbol{
    static int apellidoFutbolFrequency = 0;
        apellidoFutbol(String name, String category, int frequency, String nc, int id, String origen){
            super(name, category, frequency, nc, id, origen);
            apellidoFutbolFrequency++;
        }

        public void incFrequency(){
            super.incFrequency();
            apellidoFutbolFrequency++;
        }
}

            
class apellidoCine extends apellido implements Cine{
    static int apellidoCineFrequency = 0;
        apellidoCine(String name, String category, int frequency, String nc, int id, String origen){
            super(name, category, frequency, nc, id, origen);
            apellidoCineFrequency++;
        }

        public void incFrequency(){
            super.incFrequency();
            apellidoCineFrequency++;
        }
}

            
class apellidoNacional extends apellido implements Nacional{
    static int apellidoNacionalFrequency = 0;
        apellidoNacional(String name, String category, int frequency, String nc, int id, String origen){
            super(name, category, frequency, nc, id, origen);
            apellidoNacionalFrequency++;
        }

        public void incFrequency(){
            super.incFrequency();
            apellidoNacionalFrequency++;
        }
}

            
class nombreFutbol extends nombre implements Futbol{
    static int nombreFutbolFrequency = 0;
        nombreFutbol(String name, String category, int frequency, String nc, int id, String origen, String formasAlt){
            super(name, category, frequency, nc, id, origen, formasAlt);
            nombreFutbolFrequency++;
        }

        public void incFrequency(){
            super.incFrequency();
            nombreFutbolFrequency++;
        }
}

            
class nombreCine extends nombre implements Cine{
    static int nombreCineFrequency = 0;
        nombreCine(String name, String category, int frequency, String nc, int id, String origen, String formasAlt){
            super(name, category, frequency, nc, id, origen, formasAlt);
            nombreCineFrequency++;
        }

        public void incFrequency(){
            super.incFrequency();
            nombreCineFrequency++;
        }
}

            
class nombreNacional extends nombre implements Nacional{
    static int nombreNacionalFrequency = 0;
        nombreNacional(String name, String category, int frequency, String nc, int id, String origen, String formasAlt){
            super(name, category, frequency, nc, id, origen, formasAlt);
            nombreNacionalFrequency++;
        }

        public void incFrequency(){
            super.incFrequency();
            nombreNacionalFrequency++;
        }
}

            
class paisFutbol extends pais implements Futbol{
    static int paisFutbolFrequency = 0;
        paisFutbol(String name, String category, int frequency, String nc, int poblacion, String lenguaOficial){
            super(name, category, frequency, nc, poblacion, lenguaOficial);
            paisFutbolFrequency++;
        }

        public void incFrequency(){
            super.incFrequency();
            paisFutbolFrequency++;
        }
}

            
class paisCine extends pais implements Cine{
    static int paisCineFrequency = 0;
        paisCine(String name, String category, int frequency, String nc, int poblacion, String lenguaOficial){
            super(name, category, frequency, nc, poblacion, lenguaOficial);
            paisCineFrequency++;
        }

        public void incFrequency(){
            super.incFrequency();
            paisCineFrequency++;
        }
}

            
class paisNacional extends pais implements Nacional{
    static int paisNacionalFrequency = 0;
        paisNacional(String name, String category, int frequency, String nc, int poblacion, String lenguaOficial){
            super(name, category, frequency, nc, poblacion, lenguaOficial);
            paisNacionalFrequency++;
        }

        public void incFrequency(){
            super.incFrequency();
            paisNacionalFrequency++;
        }
}

            
class ciudadFutbol extends ciudad implements Futbol{
    static int ciudadFutbolFrequency = 0;
        ciudadFutbol(String name, String category, int frequency, String nc, String pais, String capital, int poblacion){
            super(name, category, frequency, nc, pais, capital, poblacion);
            ciudadFutbolFrequency++;
        }

        public void incFrequency(){
            super.incFrequency();
            ciudadFutbolFrequency++;
        }
}

            
class ciudadCine extends ciudad implements Cine{
    static int ciudadCineFrequency = 0;
        ciudadCine(String name, String category, int frequency, String nc, String pais, String capital, int poblacion){
            super(name, category, frequency, nc, pais, capital, poblacion);
            ciudadCineFrequency++;
        }

        public void incFrequency(){
            super.incFrequency();
            ciudadCineFrequency++;
        }
}

            
class ciudadNacional extends ciudad implements Nacional{
    static int ciudadNacionalFrequency = 0;
        ciudadNacional(String name, String category, int frequency, String nc, String pais, String capital, int poblacion){
            super(name, category, frequency, nc, pais, capital, poblacion);
            ciudadNacionalFrequency++;
        }

        public void incFrequency(){
            super.incFrequency();
            ciudadNacionalFrequency++;
        }
}