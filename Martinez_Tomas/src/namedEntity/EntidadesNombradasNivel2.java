package namedEntity;

class apellido extends persona{
    String origen;
    static int apellidoFrequency = 0;
    apellido(String name, String category, int frequency, String nc, int id, String origen) {
        super(name, category, frequency, nc, id);
        this.origen = origen;
        apellidoFrequency++;
    }

    public void incFrequency() {
        super.incFrequency();
        apellidoFrequency++;
    }
}

class nombre extends persona{
    String origen;
    String formasAlt;
    static int nombreFrequency = 0;
    nombre(String name, String category, int frequency, String nc, int id, String origen, String formasAlt) {
        super(name, category, frequency, nc, id);
        this.origen = origen;
        this.formasAlt = formasAlt;
        nombreFrequency++;
    }

    public void incFrequency() {
        super.incFrequency();
        nombreFrequency++;
    }
}

class titulo extends persona{
    String profesional;
    static int tituloFrequency = 0;
    titulo(String name, String category, int frequency, String nc, int id, String profesional) {
        super(name, category, frequency, nc, id);
        this.profesional = profesional;
        tituloFrequency++;
    }

    public void incFrequency() {
        super.incFrequency();
        tituloFrequency++;
    }
}

class pais extends lugar{
    int poblacion;
    String lenguaOficial;
    static int paisFrequency = 0;
    pais (String name, String category, int frequency, String nc, int poblacion, String lenguaOficial) {
        super(name, category, frequency, nc);
        this.poblacion = poblacion;
        this.lenguaOficial = lenguaOficial;
        paisFrequency++;
    }

    public void incFrequency() {
        super.incFrequency();
        paisFrequency++;
    }
}

class ciudad extends lugar{
    String pais;
    String capital;
    int poblacion;
    static int ciudadFrequency = 0;
    ciudad (String name, String category, int frequency, String nc, String pais, String capital, int poblacion) {
        super(name, category, frequency, nc);
        this.pais = pais;
        this.capital = capital;
        this.poblacion = poblacion;
        ciudadFrequency++;
    }

    public void incFrequency() {
        super.incFrequency();
        ciudadFrequency++;
    }
}

class direccion extends lugar{
    String ciudad;
    static int direccionFrequency = 0;
    direccion (String name, String category, int frequency, String nc, String ciudad) {
        super(name, category, frequency, nc);
        this.ciudad = ciudad;
        direccionFrequency++;
    }

    public void incFrequency() {
        super.incFrequency();
        direccionFrequency++;
    }
}

class OtroLugar extends lugar{
    static int OtroLugarFrequency = 0;
    String comments;
    OtroLugar(String name, String category, int frequency, String nc, String comments) {
        super(name, category, frequency, nc);
        this.comments = comments;
        OtroLugarFrequency++;
    }

    public void incFrequency() {
        super.incFrequency();
        OtroLugarFrequency++;
    }
}
