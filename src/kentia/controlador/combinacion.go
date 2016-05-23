package controlador

import (
	"fmt"
	"html/template"
	"kentia/genetico"
	"kentia/modelo"

	"github.com/gin-gonic/gin"
)

type ListaPrendas struct {
	Lista []int
}

//GenerarCombinacionGET maneja la ruta /generarCombinacion
func GenerarCombinacionGET(html *template.Template) gin.HandlerFunc {
	return func(c *gin.Context) {
		mapa := MapaInfo{}
		lp := new(ListaPrendas)
		err := c.BindJSON(&lp)
		if err != nil {
			fmt.Println("no se pudo decodificar datos", err)
		}
		fmt.Println("Lista de ids seleccionados", lp)
		mapa.ObtenerDatosCombinacion(lp.Lista)
		//fmt.Println("datos para la combinación", mapa)
		html.ExecuteTemplate(c.Writer, "combinacion.html", mapa)
		//c.Redirect(http.StatusTemporaryRedirect, "/")
		return
	}
}

//GenerarMejorCombinacion se encarga de buscar cada una de las prendas por color y birllo para generar una combinacion.
func GenerarMejorCombinacion(idsPrendas []int) (prendas [][]modelo.Prenda) {
	prendasSel := modelo.ObtenerPrendas(idsPrendas)
	coloresPrendas := modelo.ConsultarColoresPrendas(prendasSel)
	mejores := genetico.GeneticoMultiple(coloresPrendas, prendasSel)
	for _, mejor := range mejores {
		var combinacion []modelo.Prenda
		for _, color := range mejor.Genotipo {
			fmt.Println(color)
			prenda := modelo.Prenda{}
			prenda.Brillo = color.Brillo
			prenda.ColorID = color.Tono
			prenda.Foto = color.Foto
			/*switch i {
			case 0:
				prenda.TipoPrenda.Nombre = "Calzado"
			case 1:
				prenda.TipoPrenda.Nombre = "Pantalon/Falda"
			case 2:
				prenda.TipoPrenda.Nombre = "Playera"
			case 3:
				prenda.TipoPrenda.Nombre = "Chamarra"
			}*/
			//prenda.BuscaPorIDEnCatalogo(fem.Prendas)
			combinacion = append(combinacion, prenda)
		}
		prendas = append(prendas, combinacion)
	}

	fmt.Println("prendas a combinar", len(prendas))
	return prendas
}
