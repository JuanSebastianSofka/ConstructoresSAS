package validaciones

import recursos.RecursosMateriales
import solicitud.SolicitudConstruccion

object Validaciones {
  /**
   *
   * @param solicitudesExistentes
   * @param nuevaSolicitud
   * @return
   */
  def verificarCoordenadas(solicitudesExistentes: List[SolicitudConstruccion], nuevaSolicitud: SolicitudConstruccion) = {
    //se usa var para poder identificar sí o sí en que momento hay una solicitud con las mismas coordenadas
    var verificacion = false

    solicitudesExistentes.foreach(solicitud => {
      if (solicitud.coordenadaX == nuevaSolicitud.coordenadaX
        && solicitud.coodernadaY == nuevaSolicitud.coodernadaY) {
        verificacion = true
      }
    })
    verificacion
  }

  /**
   *
   * @param recursosActuales
   * @param solicitud
   * @return
   */
  def verificarRecursos(recursosActuales: RecursosMateriales, solicitud: SolicitudConstruccion): Boolean = {
    //se usa var para poder identificar sí o sí que hay recursos disponibles
    var verificacion = false

    if (recursosActuales.cemento < solicitud.tipoConstruccion.cemento ||
      recursosActuales.grava < solicitud.tipoConstruccion.grava ||
      recursosActuales.arena < solicitud.tipoConstruccion.arena ||
      recursosActuales.madera < solicitud.tipoConstruccion.madera ||
      recursosActuales.adobe < solicitud.tipoConstruccion.adobe
    ) {
      verificacion = true
    }
    verificacion
  }

  /**
   *
   * @param verificacion
   * @param recursos
   * @param solicitud
   * @return
   */
  def cantidadActualRecursos(verificacion: Boolean, recursos: RecursosMateriales, solicitud: SolicitudConstruccion):RecursosMateriales = {
    var nuevosRecursos: RecursosMateriales = null

    if (verificacion == false) {
      val cemento = recursos.cemento - solicitud.tipoConstruccion.cemento
      val grava = recursos.grava - solicitud.tipoConstruccion.grava
      val arena = recursos.arena - solicitud.tipoConstruccion.arena
      val madera = recursos.madera - solicitud.tipoConstruccion.madera
      val adobe = recursos.adobe - solicitud.tipoConstruccion.adobe

      nuevosRecursos = RecursosMateriales(
        cemento = cemento,
        grava = grava,
        arena = arena,
        madera = madera,
        adobe = adobe
      )
    }else{
      nuevosRecursos = recursos
    }
    nuevosRecursos //puede llegar a tener numeros negativos por lo tanto se valida con ello si no hay recursos
  }
}
