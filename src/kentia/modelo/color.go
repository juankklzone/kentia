package modelo

import "kentia/log"

//Color es la estructura que definen los colores de la prenda.
type Color struct {
	ID     int `gorm:"primary_key"`
	Tono   int `form:"tono" binding:"required"`
	Nombre string
}

const coleccionColor = "color"

//Registrar se encarga de registrar el color en la BD.
func (c *Color) Registrar() bool {
	conn := conectar()
	defer conn.desconectar()
	err := conn.db.Create(c).Error
	if err != nil {
		log.RegistrarError(err)
		return false
	}
	return true
}

//Modificar se encarga de actualizar el color en la BD.
func (c *Color) Modificar() bool {
	conn := conectar()
	defer conn.desconectar()
	err := conn.db.First(c).Save(c).Error
	if err != nil {
		log.RegistrarError(err)
		return false
	}
	return true
}

//ConsultarColores regresa un catálogo de colores.
func ConsultarColores() (colores []Color) {
	conn := conectar()
	defer conn.desconectar()
	err := conn.db.Model(&Color{}).Find(&colores).Error
	if err != nil {
		log.RegistrarError(err)
	}
	return colores
}

//BuscarPorID busca en la BD un color que coincida con el ID dado.
func (c *Color) BuscarPorID() bool {
	conn := conectar()
	defer conn.desconectar()
	err := conn.db.Find(c).First(c).Error
	if err != nil {
		log.RegistrarError(err)
		return false
	}
	return true
}

//BuscarPorTono busca en la BD un color que coincida con el ID dado.
func (c *Color) BuscarPorTono() bool {
	conn := conectar()
	defer conn.desconectar()
	err := conn.db.Where(c.Tono).Find(c).Error
	if err != nil {
		log.RegistrarError(err)
		return false
	}
	return true
}
