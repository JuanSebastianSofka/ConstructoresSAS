import recursos.RecursosMateriales
import solicitud.SolicitudConstruccion
import tiposconstrucciones.{CanchaFutbol, Casa, Lago}
import validaciones.Validaciones

object Main {
  def main(args: Array[String]): Unit = {
    val recursosIniciales: RecursosMateriales = RecursosMateriales(
      cemento = 1000, grava = 1000, arena = 1000, madera = 1000, adobe = 1000
    )
    val casa = Casa
    val lago = Lago

    var listaSolicitudes: List[SolicitudConstruccion] = List(
      SolicitudConstruccion(casa, 20, 25, 30, 7, 2022),
      SolicitudConstruccion(lago, 50, 60, 31, 7, 2022)
    )
    //val nuevaSolicitudCancha = SolicitudConstruccion(CanchaFutbol, 20, 25, 31, 7, 2022)
    val nuevaSolicitudCancha = SolicitudConstruccion(CanchaFutbol, 21, 22, 31, 7, 2022)

    //--------------------------------------------------------------------------------

    //retorna un boolean de la verificacion de las coordenadas
    val verificacionCoor = Validaciones.verificarCoordenadas(listaSolicitudes, nuevaSolicitudCancha)

    //retorna los recursos actuales luego de aceptada la solicitud
    val recursos = Validaciones.verificarRecursos(recursosIniciales, nuevaSolicitudCancha)

    println(recursos)

    var verificaciontotal = true
    if (!verificacionCoor && !recursos) {
      verificaciontotal = false
      listaSolicitudes = listaSolicitudes :+ nuevaSolicitudCancha
    }
    listaSolicitudes.foreach(solicitud => println(solicitud.tipoConstruccion.nombre))

    //imprime los recursos que tendría por aprobar la solicitud o desaprobarla
    println(Validaciones.cantidadActualRecursos(verificaciontotal,recursosIniciales, nuevaSolicitudCancha))

    /*
    listaSolicitudes.foreach(x=>println(x.tipoConstruccion.nombre))
    println(nuevaSolicitudCancha.getClass)*/


  }


}
