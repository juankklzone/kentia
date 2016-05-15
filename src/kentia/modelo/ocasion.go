package modelo

import "kentia/log"

//Ocasion estructura para conocer la ocasion en que se usará la prenda.
type Ocasion struct {
	ID     int `gorm:"primary_key"`
	Nombre string
}

const coleccionOcasion = "ocasion"

//Registrar un nuevo tipo de ocacion en la bd.
func (c *Ocasion) Registrar() bool {
	conn := conectar()
	defer conn.desconectar()
	err := conn.db.Create(c).Error
	if err != nil {
		log.RegistrarError(err)
		return false
	}
	return true
}

//Modificar actualizara la ocasión.
func (c *Ocasion) Modificar() bool {
	conn := conectar()
	defer conn.desconectar()
	err := conn.db.First(c).Save(c).Error
	if err != nil {
		log.RegistrarError(err)
		return false
	}
	return true
}

//ConsultarOcasiones regresa un catálogo de ocasiones.
func ConsultarOcasiones() (ocasiones []Ocasion) {
	conn := conectar()
	defer conn.desconectar()
	err := conn.db.Model(&Ocasion{}).Find(&ocasiones).Error
	if err != nil {
		log.RegistrarError(err)
	}
	return colores
}

//BuscarPorID busca en la BD un ocasion que coincida con el ID dado.
func (c *Ocasion) BuscarPorID() bool {
	conn := conectar()
	defer conn.desconectar()
	err := conn.db.Find(c).First(c).Error
	if err != nil {
		log.RegistrarError(err)
		return false
	}
	return true
}
