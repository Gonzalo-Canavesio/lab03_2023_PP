package namedEntity;

class persona extends EntidadNombrada{
    int id;
    static int personaFrequency = 0;
    persona(String name, String category, int frequency, String nc, int id) {
        super(name, category, frequency, nc);
        this.id = id;
        personaFrequency+=frequency;
    }

    public void incFrequency() {
        super.incFrequency();
        personaFrequency+=frequency;
    }
}

class lugar extends EntidadNombrada{
    static int lugarFrequency = 0;
    lugar(String name, String category, int frequency, String nc) {
        super(name, category, frequency, nc);
        lugarFrequency+=frequency;
    }

    public void incFrequency() {
        super.incFrequency();
        lugarFrequency+=frequency;
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
        organizacionFrequency+=frequency;
    }

    public void incFrequency() {
        super.incFrequency();
        organizacionFrequency+=frequency;
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
        productoFrequency+=frequency;
    }

    public void incFrequency() {
        super.incFrequency();
        productoFrequency+=frequency;
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
        eventoFrequency+=frequency;
    }

    public void incFrequency() {
        super.incFrequency();
        eventoFrequency+=frequency;
    }
}

class fecha extends EntidadNombrada{
    String fechaPrecisa;
    static int fechaFrequency = 0;
    fecha(String name, String category, int frequency, String nc, String fechaPrecisa) {
        super(name, category, frequency, nc);
        this.fechaPrecisa = fechaPrecisa;
        fechaFrequency+=frequency;
    }

    public void incFrequency() {
        super.incFrequency();
        fechaFrequency+=frequency;
    }
}

class otro extends EntidadNombrada {
    String Comments;
    static int otroFrequency = 0;
    otro(String name, String category, int frequency, String nc, String Comments) {
        super(name, category, frequency, nc);
        this.Comments = Comments;
        otroFrequency+=frequency;
    }

    public void incFrequency() {
        super.incFrequency();
        otroFrequency+=frequency;
    }
}

