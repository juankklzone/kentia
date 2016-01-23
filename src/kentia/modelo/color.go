package modelo

import (
	"kentia/log"

	"gopkg.in/mgo.v2/bson"
)

//Color es la estructura que definen los colores de la prenda
type Color struct {
	ID     bson.ObjectId `bson:"_id" form:"id"`
	Nombre string
}

const coleccionColor = "color"

//Registrar se encarga de registrar el color en la BD
func (c *Color) Registrar() bool {
	conn := conectar()
	defer conn.desconectar()
	err := conn.db.C(coleccionColor).Insert(c)
	if err != nil {
		log.RegistrarError(err)
		return false
	}
	return true
}