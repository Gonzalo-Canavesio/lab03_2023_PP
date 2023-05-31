package namedEntity;

class persona extends EntidadNombrada{
    int id;
    static int personaFrequency = 0;
    persona(String name, String category, int frequency, String nc, int id) {
        super(name, category, frequency, nc);
        this.id = id;
        personaFrequency++;
    }

    public void incFrequency() {
        super.incFrequency();
        personaFrequency++;
    }
}

class lugar extends EntidadNombrada{
    static int lugarFrequency = 0;
    lugar(String name, String category, int frequency, String nc) {
        super(name, category, frequency, nc);
        lugarFrequency++;
    }

    public void incFrequency() {
        super.incFrequency();
        lugarFrequency++;
    }
}

class organizacion extends EntidadNombrada{
    int NroMiembros;
    String tipoOrg;
    static int organizacionFrequency = 0;
    organizacion(String name, String category, int frequency, String nc, int NroMiembros, String tipoOrg){
        super(name, category, frequency, nc);
        this.NroMiembros = NroMiembros;
        this.tipoOrg = tipoOrg;
        organizacionFrequency++;
    }

    public void incFrequency() {
        super.incFrequency();
        organizacionFrequency++;
    }
}

class producto extends EntidadNombrada{
    String Comercial;
    String Productor;
    static int productoFrequency = 0;
    producto(String name, String category, int frequency, String nc, String Comercial, String Productor) {
        super(name, category, frequency, nc);
        this.Comercial = Comercial;
        this.Productor = Productor;
        productoFrequency++;
    }

    public void incFrequency() {
        super.incFrequency();
        productoFrequency++;
    }
}

class evento extends EntidadNombrada{
    String fecha;
    int recurrente;
    static int eventoFrequency = 0;
    evento(String name, String category, int frequency, String nc, String fecha, int recurrente) {
        super(name, category, frequency, nc);
        this.fecha = fecha;
        this.recurrente = recurrente;
        eventoFrequency++;
    }

    public void incFrequency() {
        super.incFrequency();
        eventoFrequency++;
    }
}

class fecha extends EntidadNombrada{
    String fechaPrecisa;
    static int fechaFrequency = 0;
    fecha(String name, String category, int frequency, String nc, String fechaPrecisa) {
        super(name, category, frequency, nc);
        this.fechaPrecisa = fechaPrecisa;
        fechaFrequency++;
    }

    public void incFrequency() {
        super.incFrequency();
        fechaFrequency++;
    }
}

class otro extends EntidadNombrada {
    String Comments;
    static int otroFrequency = 0;
    otro(String name, String category, int frequency, String nc, String Comments) {
        super(name, category, frequency, nc);
        this.Comments = Comments;
        otroFrequency++;
    }

    public void incFrequency() {
        super.incFrequency();
        otroFrequency++;
    }
}

