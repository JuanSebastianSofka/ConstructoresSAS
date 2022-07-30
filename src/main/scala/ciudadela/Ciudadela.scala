package ciudadela

import solicitud.OrdenConstruccion

case class Ciudadela(
                      ordenesPendientes: List[OrdenConstruccion], //depende del estado "pendiente"
                      ordenesTerminadas: List[OrdenConstruccion], //depende del estado "terminado"
                      ordenesEnProgreso: List[OrdenConstruccion], //depende del estado "en progreso"
                      diaFinal: Int,
                      mesFinal: Int,
                      anioFinal: Int
                    )
