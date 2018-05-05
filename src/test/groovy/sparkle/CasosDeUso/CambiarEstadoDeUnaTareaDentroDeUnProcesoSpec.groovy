package sparkle

import org.grails.testing.GrailsUnitTest
import spock.lang.Specification

class CambiarEstadoDeUnaTareaDentroDeUnProcesoSpec extends Specification implements GrailsUnitTest {

    def setup() {
    }

    def cleanup() {
    }


    def """
    	Dado un proceso con dos tareas en dos pasos distintos ambos pendientes
    	Cuando queres cambiar el estado de la tarea en el segundo pasos
    	Entonces lanza un error y no permite el cambio
    """ () {
    	given: "Un proceso con dos tareas en dos pasos distintos ambos pendientes"
    		def proceso = new Proceso()
    		def pasoUno = new Paso()
    		def pasoDos = new Paso()
    		def tareaUno = new Tarea()
    		def tareaDos = new Tarea()

    		proceso.agregarPaso(pasoUno)
    		proceso.agregarPaso(pasoDos)

    		pasoUno.agregarTarea(tareaUno)
    		pasoDos.agregarTarea(tareaDos)

    	when: "Queres cambiar el estado de la tarea en el segundo paso"
    		tareaDos.cambiarEstado(Estado.EnEjecucion)

    	then: "Lanza un error y no permite el cambio"
    		thrown(CambioEstadoInvalidoException)
    		tareaDos.estado == Estado.NoIniciada
    }

	def """
    	Dado un proceso con dos tareas en dos pasos distintos ambos pendientes
    	Cuando queres cambiar el estado de la tarea en el primer pasos
    	Entonces el estado de la tarea se actualiza
    """ () {
    	given: "Un proceso con dos tareas en dos pasos distintos ambos pendientes"
    		def proceso = new Proceso()
    		def pasoUno = new Paso()
    		def pasoDos = new Paso()
    		def tareaUno = new Tarea()
    		def tareaDos = new Tarea()

    		proceso.agregarPaso(pasoUno)
    		proceso.agregarPaso(pasoDos)

    		pasoUno.agregarTarea(tareaUno)
    		pasoDos.agregarTarea(tareaDos)

    	when: "Queres cambiar el estado de la tarea en el segundo paso"
    		tareaUno.cambiarEstado(Estado.EnEjecucion)

    	then: "Lanza un error y no permite el cambio"
    		tareaUno.estado == Estado.EnEjecucion
    }

    def """
    	Dado un proceso con dos tareas en dos pasos distintos el primero terminado
    	Cuando queres cambiar el estado de la tarea en el segundo pasos
    	Entonces el estado de la tarea se actualiza
    """ () {
    	given: "Un proceso con dos tareas en dos pasos distintos ambos pendientes"
    		def proceso = new Proceso()
    		def pasoUno = new Paso()
    		def pasoDos = new Paso()
    		def tareaUno = new Tarea()
    		def tareaDos = new Tarea()

    		proceso.agregarPaso(pasoUno)
    		proceso.agregarPaso(pasoDos)

    		pasoUno.agregarTarea(tareaUno)
    		pasoDos.agregarTarea(tareaDos)

    		tareaUno.cambiarEstado(Estado.EnEjecucion)
    		tareaUno.cambiarEstado(Estado.Finalizada)
    	when: "Queres cambiar el estado de la tarea en el segundo paso"
    		tareaDos.cambiarEstado(Estado.EnEjecucion)

    	then: "Lanza un error y no permite el cambio"
    		tareaDos.estado == Estado.EnEjecucion
    }
}