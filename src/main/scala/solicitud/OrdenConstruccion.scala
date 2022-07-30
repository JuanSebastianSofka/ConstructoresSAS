package solicitud

import tiposconstrucciones.TiposConstrucciones

case class OrdenConstruccion(
                            tiposConstrucciones: TiposConstrucciones,
                            estado: String,
                            fechaInicio: String,
                            diaInicio: Int, //si la solicitud es el dia 1, aqui sería el dia 2
                            mesInicio: Int,
                            anioInicio: Int
                            )
