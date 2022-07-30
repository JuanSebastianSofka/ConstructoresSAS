package solicitud

import tiposconstrucciones.TiposConstrucciones

case class SolicitudConstruccion(
                                  tipoConstruccion: TiposConstrucciones,
                                  coordenadaX: Double,
                                  coodernadaY: Double,
                                  diaSolicitud: Int,
                                  mesSolicitud: Int,
                                  anioSolicitud: Int
                                )
