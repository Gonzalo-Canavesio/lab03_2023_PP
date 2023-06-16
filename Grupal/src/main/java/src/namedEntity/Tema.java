package src.namedEntity;

interface Tema {
  String getTema();

  void setTema(String tema);
}

interface Deporte extends Tema {}

interface Futbol extends Deporte {}

interface Basquet extends Deporte {}

interface Tenis extends Deporte {}

interface Formula1 extends Deporte {}

interface OtrosDeportes extends Deporte {}

interface Cultura extends Tema {}

interface Cine extends Cultura {}

interface Musica extends Cultura {}

interface OtrosCultura extends Cultura {}

interface Politica extends Tema {}

interface Nacional extends Politica {}

interface Internacional extends Politica {}

interface OtrosPolitica extends Politica {}

interface Otro extends Tema {}
