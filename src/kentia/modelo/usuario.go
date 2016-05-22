package modelo

import "kentia/log"

//Usuario define los valores que identifican a un usuario del sistema.
type Usuario struct {
	ID            int    `gorm:"primary_key"`
	Nombre        string `form:"nombre"`
	Correo        string `form:"correo" binding:"required"`
	Contraseña    string `form:"pass" binding:"required"`
	Genero        string `form:"genero"`
	Prendas       []Prenda
	Combinaciones []Combinacion
}

const coleccionUsuario = "usuario"

//Registrar registra un usuario en la DB.
func (u *Usuario) Registrar() bool {
	conn := conectar()
	defer conn.desconectar()
	err := conn.db.Debug().Create(u).Error
	if err != nil {
		log.RegistrarError(err)
		return false
	}
	return true
}

//Modificar modifica un usuario en la DB.
func (u *Usuario) Modificar() bool {
	conn := conectar()
	defer conn.desconectar()
	err := conn.db.First(u).Save(u).Error
	if err != nil {
		log.RegistrarError(err)
		return false
	}
	return true
}

//ConsultarUsuarios regresa el catálogo de usuarios.
func ConsultarUsuarios() (usuarios []Usuario) {
	conn := conectar()
	defer conn.desconectar()
	err := conn.db.Model(&Usuario{}).Find(&usuarios).Error
	if err != nil {
		log.RegistrarError(err)
	}
	for i := range usuarios {
		conn.db.Model(&usuarios[i]).Related(&usuarios[i].Prendas)
		conn.db.Model(&usuarios[i]).Related(&usuarios[i].Combinaciones)
	}
	return usuarios
}

//IniciarSesion comprueba las credenciales y autoriza una sesion.
func (u *Usuario) IniciarSesion() bool {
	conn := conectar()
	defer conn.desconectar()
	err := conn.db.Where(&Usuario{Correo: u.Correo, Contraseña: u.Contraseña}).First(u).Error
	if err != nil {
		log.RegistrarError(err)
		return false
	}
	return true
}

//BuscarPorID busca un usuario en la DB por ID.
func (u *Usuario) BuscarPorID() bool {
	conn := conectar()
	defer conn.desconectar()
	err := conn.db.Find(u).First(u).Error
	if err != nil {
		log.RegistrarError(err)
		return false
	}
	return true
}
