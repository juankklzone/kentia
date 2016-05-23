package controlador

import (
	"html/template"
	"kentia/modelo"
	"strings"

	"github.com/gin-gonic/gin"
)

func convertirID(s string) string {
	s = s[strings.Index(s, "\"")+1 : strings.LastIndex(s, "\"")]
	return s
}

func guadarImagen(c *gin.Context, p *modelo.Prenda) {
	/*file, _, err := c.Request.FormFile("foto")
	if err != nil {
		c.String(http.StatusSeeOther, "Sin imagen")
		return
	}
	defer file.Close()

	data, _ := ioutil.ReadAll(file)

	ruta := "/img/foto" + p.ID.Hex() + ".png"*/
	ruta := c.PostForm("foto")
	p.Foto = ruta

	/*out, err := os.Create("public" + p.Foto)
	if err != nil {
		c.String(http.StatusTemporaryRedirect, err.Error())
		return
	}

	_, err = out.Write(data)
	if err != nil {
		c.String(http.StatusTemporaryRedirect, err.Error())
		return
	}
	defer out.Close()*/
}

//RegistroPrendaPOST recibe el formulario y se encarga de registrarlo en la BD.
/*func RegistroPrendaPOST() gin.HandlerFunc {
	return func(c *gin.Context) {
		usuarioID := GetSession(sessions.Default(c).Get("UsuarioID"))
		fmt.Println("usuarioID", usuarioID)
		uID, _ := strconv.Atoi(usuarioID)
		//if usuarioID != "0" { yolo
		var p modelo.Prenda
		if c.Bind(&p) == nil {
			u := modelo.Usuario{ID: uID}
			if u.BuscarPorID() {
				//p.ID = bson.NewObjectId()
				p.Color =
				p.Color.BuscarPorTono()

				p.Clima.ID, _ = strconv.Atoi(c.PostForm("clima"))
				p.Clima.BuscarPorID()
				p.TipoPrendaID, _ = strconv.Atoi(c.PostForm("tipoPrenda"))
				//				p.TipoPrenda.BuscarPorID()

				p.OcasionID, _ = strconv.Atoi(c.PostForm("ocasion"))
				//p.Ocasion.BuscarPorID()

				guadarImagen(c, &p)

				u.Prendas = append(u.Prendas, p)
				if u.Modificar() {
					//BIEN
					fmt.Println("se registro la prenda", u)
					c.Redirect(302, "/registroPrenda")
				} else {
					fmt.Println("ALGO MAL", u)
					c.Redirect(302, "/")
				}
			} else {
				fmt.Println("Algo salió mal")
			}
			return
		}
	}
}

func RegistroPrendaGET(html *template.Template) gin.HandlerFunc {
	return func(c *gin.Context) {
		mapa := MapaInfo{}
		mapa.ObtenerDatosRegistroPrenda()
		html.ExecuteTemplate(c.Writer, "registroPrenda.html", mapa)
	}
}
*/
func MuestraPrendasGET(html *template.Template) gin.HandlerFunc {
	return func(c *gin.Context) {
		mapa := MapaInfo{}
		mapa.ObtenerDatosPrendas()
		html.ExecuteTemplate(c.Writer, "principal.html", mapa)
	}
}
