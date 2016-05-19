package modelo

import (
	"kentia/log"
	"time"
)

//Combinacion es la estructura que definen los colores de la prenda.
type Combinacion struct {
	ID        int `gorm:"primary_key"`
	Prendas   []Prenda
	FechaUso  []time.Time
	Favorito  bool
	UsuarioID int
}

const coleccionCombinacion = "combinacion"

//Registrar se encarga de registrar la combinación en la BD.
func (c *Combinacion) Registrar() bool {
	conn := conectar()
	defer conn.desconectar()
	err := conn.db.Create(c).Error
	if err != nil {
		log.RegistrarError(err)
		return false
	}
	return true
}

//ConsultarPorID para consultar una combinacion por ID.
func (c *Combinacion) ConsultarPorID() bool {
	conn := conectar()
	defer conn.desconectar()
	err := conn.db.Find(c).First(c).Error
	if err != nil {
		log.RegistrarError(err)
		return false
	}
	return true
}

//Modificar se encarga de modificar la combinacion en la BD.
func (c *Combinacion) Modificar() bool {
	conn := conectar()
	defer conn.desconectar()
	err := conn.db.First(c).Save(c).Error
	if err != nil {
		log.RegistrarError(err)
		return false
	}
	return true
}
