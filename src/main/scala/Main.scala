import recursos.RecursosMateriales
import solicitud.{OrdenConstruccion, SolicitudConstruccion}
import tiposconstrucciones.{CanchaFutbol, Casa, Edificio, Gimnasio, Lago}
import validaciones.Validaciones

import java.util.{Calendar, Date}
import scala.:+
import scala.io.StdIn.readLine

object Main {
  def main(args: Array[String]): Unit = {
    val recursosIniciales: RecursosMateriales = RecursosMateriales(cemento = 1000, grava = 1000, arena = 1000, madera = 1000, adobe = 1000)
    var recursosActuales = recursosIniciales

    var listaSolicitudes: List[SolicitudConstruccion] = List()
    var ordendesConstruccion: List[OrdenConstruccion] = List()

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
                ordendesConstruccion =  ordenesActualizadas
                recursosActuales = recursosActualizados
                opcionSolicitud = opcion

              case "2" => //2. Lago
                val (solicitudesActualizadas, ordenesActualizadas, recursosActualizados, opcion) = Validaciones.manejoOpcionesSolicitudes(listaSolicitudes, ordendesConstruccion, recursosActuales, Lago)
                listaSolicitudes = solicitudesActualizadas
                ordendesConstruccion =  ordenesActualizadas
                recursosActuales = recursosActualizados
                opcionSolicitud = opcion

              case "3" => //Cancha Futbol
              val (solicitudesActualizadas, ordenesActualizadas, recursosActualizados, opcion) = Validaciones.manejoOpcionesSolicitudes(listaSolicitudes, ordendesConstruccion, recursosActuales, CanchaFutbol)
              listaSolicitudes = solicitudesActualizadas
              ordendesConstruccion =  ordenesActualizadas
              recursosActuales = recursosActualizados
              opcionSolicitud = opcion

              case "4" => //Edificio
                val (solicitudesActualizadas, ordenesActualizadas, recursosActualizados, opcion) = Validaciones.manejoOpcionesSolicitudes(listaSolicitudes, ordendesConstruccion, recursosActuales, Edificio)
                listaSolicitudes = solicitudesActualizadas
                ordendesConstruccion =  ordenesActualizadas
                recursosActuales = recursosActualizados
                opcionSolicitud = opcion

              case "5" => //Gimnasio
                val (solicitudesActualizadas, ordenesActualizadas, recursosActualizados, opcion) = Validaciones.manejoOpcionesSolicitudes(listaSolicitudes, ordendesConstruccion, recursosActuales, Gimnasio)
                listaSolicitudes = solicitudesActualizadas
                ordendesConstruccion =  ordenesActualizadas
                recursosActuales = recursosActualizados
                opcionSolicitud = opcion

              case _ => println("Opcion no valida")
                opcionSolicitud = "0"

            }
          }
      }
    }
  }
}
