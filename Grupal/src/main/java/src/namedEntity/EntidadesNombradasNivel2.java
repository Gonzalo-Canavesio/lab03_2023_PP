package src.namedEntity;

class apellido extends persona {
  String origen;
  static int apellidoFrequency = 0;

  apellido(String name, String category, int frequency, String nc, int id, String origen) {
    super(name, category, frequency, nc, id);
    this.origen = origen;
    apellidoFrequency += frequency;
  }

  public void incFrequency() {
    super.incFrequency();
    apellidoFrequency += frequency;
  }
}

class nombre extends persona {
  String origen;
  String formasAlt;
  static int nombreFrequency = 0;

  nombre(
      String name,
      String category,
      int frequency,
      String nc,
      int id,
      String origen,
      String formasAlt) {
    super(name, category, frequency, nc, id);
    this.origen = origen;
    this.formasAlt = formasAlt;
    nombreFrequency += frequency;
  }

  public void incFrequency() {
    super.incFrequency();
    nombreFrequency += frequency;
  }
}

class titulo extends persona {
  String profesional;
  static int tituloFrequency = 0;

  titulo(String name, String category, int frequency, String nc, int id, String profesional) {
    super(name, category, frequency, nc, id);
    this.profesional = profesional;
    tituloFrequency += frequency;
  }

  public void incFrequency() {
    super.incFrequency();
    tituloFrequency += frequency;
  }
}

class pais extends lugar {
  int poblacion;
  String lenguaOficial;
  static int paisFrequency = 0;

  pais(
      String name, String category, int frequency, String nc, int poblacion, String lenguaOficial) {
    super(name, category, frequency, nc);
    this.poblacion = poblacion;
    this.lenguaOficial = lenguaOficial;
    paisFrequency += frequency;
  }

  public void incFrequency() {
    super.incFrequency();
    paisFrequency += frequency;
  }
}

class ciudad extends lugar {
  String pais;
  String capital;
  int poblacion;
  static int ciudadFrequency = 0;

  ciudad(
      String name,
      String category,
      int frequency,
      String nc,
      String pais,
      String capital,
      int poblacion) {
    super(name, category, frequency, nc);
    this.pais = pais;
    this.capital = capital;
    this.poblacion = poblacion;
    ciudadFrequency += frequency;
  }

  public void incFrequency() {
    super.incFrequency();
    ciudadFrequency += frequency;
  }
}

class direccion extends lugar {
  String ciudad;
  static int direccionFrequency = 0;

  direccion(String name, String category, int frequency, String nc, String ciudad) {
    super(name, category, frequency, nc);
    this.ciudad = ciudad;
    direccionFrequency += frequency;
  }

  public void incFrequency() {
    super.incFrequency();
    direccionFrequency += frequency;
  }
}

class OtroLugar extends lugar {
  static int OtroLugarFrequency = 0;
  String comments;

  OtroLugar(String name, String category, int frequency, String nc, String comments) {
    super(name, category, frequency, nc);
    this.comments = comments;
    OtroLugarFrequency += frequency;
  }

  public void incFrequency() {
    super.incFrequency();
    OtroLugarFrequency += frequency;
  }
}
