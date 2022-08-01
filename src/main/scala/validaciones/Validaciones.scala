package validaciones

import recursos.RecursosMateriales
import solicitud.{OrdenConstruccion, SolicitudConstruccion}
import tiposconstrucciones.{Casa, Lago, TiposConstrucciones}

import java.util.{Calendar, Date}
import scala.io.StdIn.readLine

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
  def cantidadActualRecursos(verificacion: Boolean, recursos: RecursosMateriales, solicitud: SolicitudConstruccion): RecursosMateriales = {
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
    } else {
      nuevosRecursos = recursos
    }
    nuevosRecursos //puede llegar a tener numeros negativos por lo tanto se valida con ello si no hay recursos
  }

  def manejoOpcionesSolicitudes(listaSolicitudes: List[SolicitudConstruccion], ordendesConstruccion: List[OrdenConstruccion], recursosActuales: RecursosMateriales, construccion: TiposConstrucciones) = {
    if (listaSolicitudes.isEmpty) {
      println("Ingrese la coordenada X donde se localizará la construcción")
      val coorX = readLine()

      println("Ingrese la coordenada Y donde se localizará la construcción")
      val coorY = readLine()

      val solicitud = SolicitudConstruccion(tipoConstruccion = construccion,
        coordenadaX = coorX.toInt, coodernadaY = coorY.toInt,
        fecha = Calendar.getInstance().getTime
       /* diaSolicitud = Calendar.getInstance().get(Calendar.DAY_OF_MONTH),
        mesSolicitud = Calendar.getInstance().get(Calendar.MONTH) + 1,
        anioSolicitud = Calendar.getInstance().get(Calendar.YEAR)*/)

      val fechaInicioOrden = new Date(solicitud.fecha.getTime + (1000 * 60 * 60 * 24)) //dia de mañana
      val fechaFinalOrden = new Date(fechaInicioOrden.getTime + ((1000 * 60 * 60 * 24) * (solicitud.tipoConstruccion.diasConstruccion + 1)))

      val ordenPrueba = OrdenConstruccion(
        solicitud.tipoConstruccion,
        "pendiente",
        fechaInicioOrden,
        fechaFinalOrden
      )

      val solicitudesActualizadas = listaSolicitudes :+ solicitud //listaSolicitudes = listaSolicitudes :+ solicitud
      val ordenesActualizadas = ordendesConstruccion :+ ordenPrueba //ordendesConstruccion = ordendesConstruccion :+ ordenPrueba

      //recursosActuales = Validaciones.cantidadActualRecursos(verificacion = false, recursos = recursosActuales, solicitud = solicitud)
      val recursosActualizados = Validaciones.cantidadActualRecursos(verificacion = false, recursos = recursosActuales, solicitud = solicitud)

      println(
        """
          |Se ha ingresado la solicitud con exito
          |Se ha creado la orden de construccion con exito
          |""".stripMargin)
      solicitudesActualizadas.foreach(solicitud => {
        println(s"Tipo Construccion: ${solicitud.tipoConstruccion.nombre}")
        println(s"Coordenada X: ${solicitud.coordenadaX}")
        println(s"Coordenada X: ${solicitud.coodernadaY}")
        println(s"Fecha de la Solicitud: ${solicitud.fecha}")
        println("")
      })
      ordenesActualizadas.foreach(orden => {
        println(
          s"""Obra: ${orden.tiposConstrucciones.nombre}
             |Fecha de Inicio: ${orden.fechaInicio}
             |Fecha Finalizacion: ${orden.fechaFinal}
             |Estado: ${orden.estado}
             |
             |""".stripMargin)
      })

      val opcionSolicitud = "0"

      (solicitudesActualizadas, ordenesActualizadas, recursosActualizados, opcionSolicitud)
    } else {
      //Si la lista no está vacía
      println("Ingrese la coordenada X donde se localizará la construcción")
      val coorX = readLine()

      println("Ingrese la coordenada Y donde se localizará la construcción")
      val coorY = readLine()

      val solicitud = SolicitudConstruccion(tipoConstruccion = construccion,
        coordenadaX = coorX.toInt, coodernadaY = coorY.toInt,
        fecha = Calendar.getInstance().getTime
        /*diaSolicitud = Calendar.getInstance().get(Calendar.DAY_OF_MONTH),
        mesSolicitud = Calendar.getInstance().get(Calendar.MONTH) + 1,
        anioSolicitud = Calendar.getInstance().get(Calendar.YEAR)*/)

      //si alguna coordenada de la lista de solicitudes coincide con la nueva solicitud retorna true
      val verificarCoor = Validaciones.verificarCoordenadas(listaSolicitudes, solicitud)

      //si por algun motivo los recursos son menores a lo que necesito retorna true
      val verificarRec = Validaciones.verificarRecursos(recursosActuales, solicitud)

      if (verificarRec == false && verificarCoor == false) {
        //listaSolicitudes = listaSolicitudes :+ solicitud
        val solicitudesActualizadas = listaSolicitudes :+ solicitud //listaSolicitudes = listaSolicitudes :+ solicitud

        val fechaInicioOrden = new Date(ordendesConstruccion.last.fechaFinal.getTime + (1000 * 60 * 60 * 24))
        val fechaFinalOrden = new Date(fechaInicioOrden.getTime + ((1000 * 60 * 60 * 24) * (solicitud.tipoConstruccion.diasConstruccion + 1)))

        val ordenPrueba = OrdenConstruccion(
          solicitud.tipoConstruccion,
          "pendiente",
          fechaInicioOrden,
          fechaFinalOrden
        )

        //ordendesConstruccion = ordendesConstruccion :+ ordenPrueba //añado la orden con la fecha "movida" según si hay solicitudes previas
        val ordenesActualizadas = ordendesConstruccion :+ ordenPrueba //ordendesConstruccion = ordendesConstruccion :+ ordenPrueba

        //recursosActuales = Validaciones.cantidadActualRecursos(verificacion = false, recursos = recursosActuales, solicitud = solicitud) //recalculo los recursos
        val recursosActualizados = Validaciones.cantidadActualRecursos(verificacion = false, recursos = recursosActuales, solicitud = solicitud)

        println(
          """
            |Se ha ingresado la solicitud con exito
            |Se ha creado la orden de construccion con exito
            |""".stripMargin)
        solicitudesActualizadas.foreach(solicitud => {
          println(s"Tipo Construccion: ${solicitud.tipoConstruccion.nombre}")
          println(s"Coordenada X: ${solicitud.coordenadaX}")
          println(s"Coordenada X: ${solicitud.coodernadaY}")
          println(s"Fecha de la Solicitud: ${solicitud.fecha}")
          println("")
        })
        ordenesActualizadas.foreach(orden => {
          println(
            s"""Obra: ${orden.tiposConstrucciones.nombre}
               |Fecha de Inicio: ${orden.fechaInicio}
               |Fecha Finalizacion: ${orden.fechaFinal}
               |Estado: ${orden.estado}
               |
               |""".stripMargin)
        })

        val opcionSolicitud = "0"

        (solicitudesActualizadas, ordenesActualizadas, recursosActualizados, opcionSolicitud)

      } else {
        println(
          """Las coordenadas ingresadas coinciden con alguna coordenada existe
            |o los recursos no son suficientes y por ende no podemos aceptar la
            |solicitud
            |""".stripMargin)

        val opcionSolicitud = "0"

        (listaSolicitudes, ordendesConstruccion, recursosActuales, opcionSolicitud)
      }
    }
  }
}
