import recursos.RecursosMateriales
import solicitud.{OrdenConstruccion, SolicitudConstruccion}
import tiposconstrucciones.{CanchaFutbol, Casa, Edificio, Gimnasio, Lago}
import validaciones.Validaciones

import scala.io.StdIn.readLine

import org.joda.time.DateTime

object Main {
  def main(args: Array[String]): Unit = {
    val recursosIniciales: RecursosMateriales = RecursosMateriales(cemento = 1000, grava = 1000, arena = 1000, madera = 1000, adobe = 1000)
    var recursosActuales = recursosIniciales

    var ordenesPendientes: List[OrdenConstruccion] = List()
    var ordenesEnProgreso: List[OrdenConstruccion] = List()
    var ordenesFinalizadas: List[OrdenConstruccion] = List()

    var listaSolicitudes: List[SolicitudConstruccion] = List()
    var ordendesConstruccion: List[OrdenConstruccion] = List()

    var diaSimulado = DateTime.now() //simula el paso de los días para verificar las solicitudes pendientes, en progreso y finalizadas

    var opcionProceso = ""

    while (opcionProceso != "0") {
      println(
        """Ingrese el proceso que desea realizar
          |0. Cancelar
          |1. Ingresar nueva solicitud
          |2. Consultar fecha de terminacion del proyecto de la ciudadela
          |3. Informe solicitudes pendientes
          |4. Informe solicitudes en progreso
          |5. Informe solicitudes finalizadas
          |""".stripMargin)

      opcionProceso = readLine()

      opcionProceso match {
        case "0" => opcionProceso = "0" //0. Cancelar

        case "1" => //1. Ingresar nueva solicitud
          var opcionSolicitud = ""

          while (opcionSolicitud != "0") {
            println(
              """
                |¿Qué tipo de solicitud desea hacer?
                |0. Salir
                |1. Casa
                |2. Lago
                |3. Cancha de Fútbol
                |4. Edificio
                |5. Gimnasio
                |""".stripMargin)

            opcionSolicitud = readLine()

            opcionSolicitud match {
              case "0" => opcionSolicitud = "0" //0. Salir

              case "1" => //1. Casa
                val (solicitudesActualizadas, ordenesActualizadas, recursosActualizados, opcion) = Validaciones.manejoOpcionesSolicitudes(listaSolicitudes, ordendesConstruccion, recursosActuales, Casa)
                listaSolicitudes = solicitudesActualizadas
                ordendesConstruccion = ordenesActualizadas
                recursosActuales = recursosActualizados

                diaSimulado = diaSimulado.plusHours(24)

                opcionSolicitud = opcion

              case "2" => //2. Lago
                val (solicitudesActualizadas, ordenesActualizadas, recursosActualizados, opcion) = Validaciones.manejoOpcionesSolicitudes(listaSolicitudes, ordendesConstruccion, recursosActuales, Lago)
                listaSolicitudes = solicitudesActualizadas
                ordendesConstruccion = ordenesActualizadas
                recursosActuales = recursosActualizados

                diaSimulado = diaSimulado.plusHours(24)

                opcionSolicitud = opcion

              case "3" => //Cancha Futbol
                val (solicitudesActualizadas, ordenesActualizadas, recursosActualizados, opcion) = Validaciones.manejoOpcionesSolicitudes(listaSolicitudes, ordendesConstruccion, recursosActuales, CanchaFutbol)
                listaSolicitudes = solicitudesActualizadas
                ordendesConstruccion = ordenesActualizadas
                recursosActuales = recursosActualizados

                diaSimulado = diaSimulado.plusHours(24)

                opcionSolicitud = opcion

              case "4" => //Edificio
                val (solicitudesActualizadas, ordenesActualizadas, recursosActualizados, opcion) = Validaciones.manejoOpcionesSolicitudes(listaSolicitudes, ordendesConstruccion, recursosActuales, Edificio)
                listaSolicitudes = solicitudesActualizadas
                ordendesConstruccion = ordenesActualizadas
                recursosActuales = recursosActualizados

                diaSimulado = diaSimulado.plusHours(24)

                opcionSolicitud = opcion

              case "5" => //Gimnasio
                val (solicitudesActualizadas, ordenesActualizadas, recursosActualizados, opcion) = Validaciones.manejoOpcionesSolicitudes(listaSolicitudes, ordendesConstruccion, recursosActuales, Gimnasio)
                listaSolicitudes = solicitudesActualizadas
                ordendesConstruccion = ordenesActualizadas
                recursosActuales = recursosActualizados

                diaSimulado = diaSimulado.plusHours(24)

                opcionSolicitud = opcion

              case _ => println("Opcion no valida")
                opcionSolicitud = "0"
            }
          }

        case "2" => //2. Consultar fecha de terminacion del proyecto de la ciudadela
          Validaciones.consultarFechaCiudadela(ordendesConstruccion)

        case "3" => //3. Informe solicitudes pendientes
          ordenesPendientes = Validaciones.listaOrdenesPendientes(ordendesConstruccion, diaSimulado)
          if (ordenesPendientes.isEmpty) {
            println("No hay solicitudes en progreso")
          } else {
            val listaPendientesReal = ordenesPendientes.filter(orden => diaSimulado.toDate.before(orden.fechaInicio) && orden.estado.equals("Pendiente"))
            Validaciones.mostrarInformacionFiltradaProgresoPendiente(listaPendientesReal)
          }

        case "4" => //4. Informe solicitudes en progreso
          ordenesEnProgreso = Validaciones.listaOrdenesPendientes(ordendesConstruccion, diaSimulado)
          if (ordenesEnProgreso.isEmpty) {
            println("No hay solicitudes en progreso")
          } else {
            val listaEnProgresosReal = ordenesEnProgreso.filter(orden => diaSimulado.toDate.equals(orden.fechaInicio) || diaSimulado.toDate.after(orden.fechaInicio)
              && diaSimulado.toDate.before(orden.fechaFinal) && orden.estado.equals("En Progreso"))
            Validaciones.mostrarInformacionFiltradaProgresoPendiente(listaEnProgresosReal)
          }

        case "5" => //4. Informe solicitudes finalizadas
          ordenesFinalizadas = Validaciones.listaOrdenesPendientes(ordendesConstruccion, diaSimulado)
          if (ordenesFinalizadas.isEmpty) {
            println("No hay solicitudes en progreso")
          } else {
            val listaFinalizadaReal = ordenesFinalizadas.filter(orden => diaSimulado.toDate.after(orden.fechaFinal) && orden.estado.equals("Finalizado"))
            Validaciones.mostrarInformacionFiltradaFinalizada(listaFinalizadaReal)
          }

        case _ => println("Opción no válida")
      }
    }
  }
}
