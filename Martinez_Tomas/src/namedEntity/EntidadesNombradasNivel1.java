package namedEntity;

class persona extends EntidadNombrada{
    int id;
    persona(String name, String category, int frequency, int id) {
        super(name, category, frequency);
        this.id = id;
    }
}

class lugar extends EntidadNombrada{
    lugar(String name, String category, int frequency, int id) {
        super(name, category, frequency);
    }
}

class organizacion extends EntidadNombrada{
    private static int count = 0;
    int NroMiembros;
    String tipoOrg;
    organizacion(String name, String category, int frequency, int NroMiembros, String tipoOrg){
        super(name, category, frequency);
        this.NroMiembros = NroMiembros;
        this.tipoOrg = tipoOrg;
        count++;
    }
}

class producto extends EntidadNombrada{
    private static int count = 0;
    String Comercial;
    String Productor;
    producto(String name, String category, int frequency, String Comercial, String Productor) {
        super(name, category, frequency);
        this.Comercial = Comercial;
        this.Productor = Productor;
        count++;
    }
    public static int getCount() {
        return count;
    }
}
class evento extends EntidadNombrada{
    private static int count = 0;
    String fecha;
    int recurrente;
    evento(String name, String category, int frequency, String fecha, int recurrente) {
        super(name, category, frequency);
        this.fecha = fecha;
        this.recurrente = recurrente;
        count++;
    }
    public static int getCount() {
        return count;
    }
}
class fecha extends EntidadNombrada{
    private static int count = 0;
    String fechaPrecisa;
    fecha(String name, String category, int frequency, String fechaPrecisa) {
        super(name, category, frequency);
        this.fechaPrecisa = fechaPrecisa;
        count++;
    }
    public static int getCount() {
        return count;
    }
}

class otro extends EntidadNombrada {
    private static int count = 0;
    String Comments;
    otro(String name, String category, int frequency, String Comments) {
        super(name, category, frequency);
        this.Comments = Comments;
        count++;
    }
    public static int getCount() {
        return count;
    }
}

