package modelo

import "kentia/log"

//Clima es la estructura que define los climas para los que se usará la prenda.
type Clima struct {
	ID     int `gorm:"primary_key"`
	Nombre string
}

const coleccionClima = "clima"

//Registrar se encarga de registrar el clima en la BD.
func (c *Clima) Registrar() bool {
	conn := conectar()
	defer conn.desconectar()
	err := conn.db.Create(c).Error
	if err != nil {
		log.RegistrarError(err)
		return false
	}
	return true
}

//Modificar actualiza los datos del clima.
func (c *Clima) Modificar() bool {
	conn := conectar()
	defer conn.desconectar()
	err := conn.db.First(c).Save(c).Error
	if err != nil {
		log.RegistrarError(err)
		return false
	}
	return true
}

//ConsultarClimas regresa un catálogo de colores
func ConsultarClimas() (climas []Clima) {
	conn := conectar()
	defer conn.desconectar()
	err := conn.db.Model(&Clima{}).Find(&climas).Error
	if err != nil {
		log.RegistrarError(err)
	}
	return climas
}

//BuscarPorID busca en la BD un clima que coincida con el ID dado.
func (c *Clima) BuscarPorID() bool {
	conn := conectar()
	defer conn.desconectar()
	err := conn.db.Find(c).First(c).Error
	if err != nil {
		log.RegistrarError(err)
		return false
	}
	return true
}
