package modelo

import "math/rand"

//ColoresPrendas indica los colores de las prendas disponibles.
type ColoresPrendas struct {
	Calzado  []FormaColor
	Pantalon []FormaColor
	Playera  []FormaColor
	Chamarra []FormaColor
}

//FormaColor informacion básica de un color para una prenda.
type FormaColor struct {
	Tono   int
	Brillo int
}

//GetColores regresa los colores de una prenda en específico.
func (p ColoresPrendas) GetColores(n int) []FormaColor {
	switch n {
	case 0:
		return p.Calzado
	case 1:
		return p.Pantalon
	case 2:
		return p.Playera
	case 3:
		return p.Chamarra
	default:
		return nil
	}
}

//GetRandom regresa un elemento random para el elmento n.
func (p ColoresPrendas) GetRandom(n int) FormaColor {
	disponibles := p.GetColores(n)
	return disponibles[rand.Intn(len(disponibles))]
}

//ConsultarColoresPrendas regresa los colores de prenda que tiene el usuario.
func (u *Usuario) ConsultarColoresPrendas(prendas []Prenda) (cp ColoresPrendas) {
	for _, prenda := range prendas {
		c := Color{ID: prenda.ColorID}
		c.BuscarPorID()
		fc := FormaColor{Tono: c.Tono, Brillo: prenda.Brillo}
		switch prenda.TipoPrendaID {
		case 1: //calzado
			cp.Calzado = append(cp.Calzado, fc)
		case 2: //pantalon
			cp.Pantalon = append(cp.Pantalon, fc)
		case 3: //playera
			cp.Playera = append(cp.Playera, fc)
		case 4: //chamarra
			cp.Chamarra = append(cp.Chamarra, fc)
		}
	}
	return cp
}
