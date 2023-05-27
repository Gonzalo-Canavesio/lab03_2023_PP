package namedEntity;

class apellido extends persona{
    String origen;
    apellido(String name, String category, int frequency, int id, String origen) {
        super(name, category, frequency, id);
        this.origen = origen;
    }
}

class nombre extends persona{
    String origen;
    String formasAlt;
    nombre(String name, String category, int frequency, int id, String origen, String formasAlt) {
        super(name, category, frequency, id);
        this.origen = origen;
        this.formasAlt = formasAlt;
    }
}

class titulo extends persona{
    String profesional;
    titulo(String name, String category, int frequency, int id, String profesional) {
        super(name, category, frequency, id);
        this.profesional = profesional;
    }
}

class pais extends lugar{
    int poblacion;
    String lenguaOficial;;
    pais (String name, String category, int frequency, int id, int poblacion, String lenguaOficial) {
        super(name, category, frequency, id);
        this.poblacion = poblacion;
        this.lenguaOficial = lenguaOficial;
    }
}

class ciudad extends lugar{
    pais pais;
    String capital;
    int poblacion;
    ciudad (String name, String category, int frequency, int id, pais pais, String capital, int poblacion) {
        super(name, category, frequency, id);
        this.pais = pais;
        this.capital = capital;
        this.poblacion = poblacion;
    }
}

class direccion extends lugar{
    ciudad ciudad;
    direccion (String name, String category, int frequency, int id, ciudad ciudad) {
        super(name, category, frequency, id);
        this.ciudad = ciudad;
    }
}

class OtroLugar extends lugar{
    OtroLugar(String name, String category, int frequency, int id) {
        super(name, category, frequency, id);
    }
}
