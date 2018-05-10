package sparkle

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class TareaSpec extends Specification implements DomainUnitTest<Tarea> {

    def setup() {
    }

    def cleanup() {
    }

    private class PasoNoPermiteCambio extends Paso {
        boolean permiteCambio() {
            false
        }
    }
    def "cuando creo una tarea con nombre inicial la tarea esta en estado no iniciada" () {
        given: "nada"
        when: "creo una tarea inicializador de grails"
        then: "estado no iniciada"
            false == true
    }

    def "cuando creo una tarea con nombre inicial la tarea esta en estado no iniciada" () {
        given: "nada"
        when: "creo una tarea inicializador de grails"
            def tarea = new Tarea(nombre:"tarea", desctipcion:"tarea")
        then: "estado no iniciada"
            tarea.estado == Estado.NoIniciada
    }

    def "cuando creo una tarea el estado es no iniciada"() {
		given: "dado nada"
		when: "creo una tarea"
	    	def tarea = new Tarea()
        then: "estado no iniciadad"
            tarea.estado == Estado.NoIniciada
    }

    def "Dada una tarea nueva Cuando asigno un paso Entonces el paso queda asignado" () {
        given: "una tarea nueva"
            def tarea = new Tarea()
            def paso = new Paso()
        when: "asigno un paso"
            tarea.asignarPaso(paso)
        then: "el paso queda asignado"
            tarea.paso == paso
    }

    def """
        Dada una tarea asignada a un paso que no permite cambios
        Cuanto quiero cambiar el estado
        Entonces un error no me lo permite
    """ () {
        given: "Una tarea en un paso que no permite cambio"
            def tarea = new Tarea()
            def paso = new PasoNoPermiteCambio()
            tarea.asignarPaso(paso)
        when: "Quiero cambiar estado"
            tarea.cambiarEstado(Estado.EnEjecucion)
        then: "no permite el cambio"
            thrown(CambioEstadoInvalidoException)
            tarea.estado == Estado.NoIniciada
    }

    // cambio de no iniciada a otro estado
    def "dada una tarea en no iniciada cuando cambio al estado no iniciada no permite el cambio"() {
    	given: "tarea en no iniciada"
    		def tarea = new Tarea()
    	when: "cambio estado a en ejecucion"
    		tarea.cambiarEstado(Estado.NoIniciada)
    	then: "la tarea pasa a estar en ejecucion"
    		thrown(CambioEstadoInvalidoException)
    		tarea.estado == Estado.NoIniciada
    }

    def "dada una tarea en no iniciada cuando cambio al estado en ejecucion se realiza el cambio"() {
    	given: "tarea en no iniciada"
    		def tarea = new Tarea()
    	when: "cambio estado a en ejecucion"
    		tarea.cambiarEstado(Estado.EnEjecucion)
    	then: "la tarea pasa a estar en ejecucion"
    		tarea.estado == Estado.EnEjecucion
    }

    def "dada una tarea en no iniciada cuando cambio al estado cancelada se realiza el cambio"() {
    	given: "tarea en no iniciada"
    		def tarea = new Tarea()
    	when: "cambio estado a cancelada"
    		tarea.cambiarEstado(Estado.Cancelada)
    	then: "la tarea pasa a estar cancelada"
    		tarea.estado == Estado.Cancelada
    }

    def "dada una tarea no iniciada cuando cambio su estado a finalizada no permite el cambio" () {
    	given: "tarea en no iniciada"
    		def tarea = new Tarea()
    	when: "cambio estado a finalizada"
    		tarea.cambiarEstado(Estado.Finalizada)
    	then: "no permite el cambio"
    		thrown(CambioEstadoInvalidoException)
    		tarea.estado == Estado.NoIniciada
    }

    // cambio de en ejecucion a otro estado
    def "dada una tarea en ejecucion cuando cambio su estado a no iniciada no permite el cambio" () {
    	given: "tarea en ejecucion"
    		def tarea = new Tarea()
    		tarea.cambiarEstado(Estado.EnEjecucion)
    	when: "cambio estado a no iniciada"
    		tarea.cambiarEstado(Estado.NoIniciada)
    	then: "no permite el cambio"
    		thrown(CambioEstadoInvalidoException)
    		tarea.estado == Estado.EnEjecucion
    }

	def "dada una tarea en ejecucion cuando cambio su estado a en ejecucion no permite el cambio" () {
    	given: "tarea en ejecucion"
    		def tarea = new Tarea()
    		tarea.cambiarEstado(Estado.EnEjecucion)
    	when: "cambio estado a en ejecucion"
    		tarea.cambiarEstado(Estado.EnEjecucion)
    	then: "no permite el cambio"
    		thrown(CambioEstadoInvalidoException)
    		tarea.estado == Estado.EnEjecucion
    }

    def "dada una trea en ejecucion cuando cambio su estado a cancelada se realiza el cambio" () {
    	given: "tarea en ejecucion"
    		def tarea = new Tarea()
    		tarea.cambiarEstado(Estado.EnEjecucion)
    	when: "cambio estado a cancelada"
    		tarea.cambiarEstado(Estado.Cancelada)
    	then: "la tarea esta cancelada"
    		tarea.estado == Estado.Cancelada
    }

    def "dada una tarea en ejecucion cuando cambio su estado a finalizada se realiza el cambio" () {
    	given: "tarea en ejecucion"
    		def tarea = new Tarea()
    		tarea.cambiarEstado(Estado.EnEjecucion)
    	when: "cambio estado a finalizada"
    		tarea.cambiarEstado(Estado.Finalizada)
    	then: "la tarea esta finalizada"
    		tarea.estado == Estado.Finalizada
    }

    // cambio de finalizada a otro estado
    def "dada una tarea finalizada cuando cambio su estado a no iniciada no permite el cambio" () {
    	given: "tarea finalizada"
    		def tarea = new Tarea()
    		tarea.cambiarEstado(Estado.EnEjecucion)
    		tarea.cambiarEstado(Estado.Finalizada)
    	when: "cambio estado a no iniciada"
    		tarea.cambiarEstado(Estado.NoIniciada)
    	then: "no permite el cambio"
    		thrown(CambioEstadoInvalidoException)
    		tarea.estado == Estado.Finalizada
    }

    def "dada una tarea finalizada cuando cambio su estado a en ejecucion no permite el cambio" () {
    	given: "tarea finalizada"
    		def tarea = new Tarea()
    		tarea.cambiarEstado(Estado.EnEjecucion)
    		tarea.cambiarEstado(Estado.Finalizada)
    	when: "cambio estado a en ejecucion"
    		tarea.cambiarEstado(Estado.EnEjecucion)
    	then: "no permite el cambio"
    		thrown(CambioEstadoInvalidoException)
    		tarea.estado == Estado.Finalizada
    }

    def "dada una tarea finalizada cuando cambio su estado a cancelada no permite el cambio" () {
    	given: "tarea finalizada"
    		def tarea = new Tarea()
    		tarea.cambiarEstado(Estado.EnEjecucion)
    		tarea.cambiarEstado(Estado.Finalizada)
    	when: "cambio estado a cancelada"
    		tarea.cambiarEstado(Estado.Cancelada)
    	then: "no permite el cambio"
    		thrown(CambioEstadoInvalidoException)
    		tarea.estado == Estado.Finalizada
    }

    def "dada una tarea finalizada cuando cambio su estado a finalizada no permite el cambio" () {
    	given: "tarea finalizada"
    		def tarea = new Tarea()
    		tarea.cambiarEstado(Estado.EnEjecucion)
    		tarea.cambiarEstado(Estado.Finalizada)
    	when: "cambio estado a finalizada"
    		tarea.cambiarEstado(Estado.Finalizada)
    	then: "no permite el cambio"
    		thrown(CambioEstadoInvalidoException)
    		tarea.estado == Estado.Finalizada
    }


    // cambio de cancelada a otro estado
    def "dada una tarea cancelada cuando cambio su estado a no iniciada no permite el cambio" () {
    	given: "tarea cancelada"
    		def tarea = new Tarea()
    		tarea.cambiarEstado(Estado.Cancelada)
    	when: "cambio estado a no iniciada"
    		tarea.cambiarEstado(Estado.NoIniciada)
    	then: "no permite el cambio"
    		thrown(CambioEstadoInvalidoException)
    		tarea.estado == Estado.Cancelada
    }

    def "dada una tarea cancelada cuando cambio su estado a en ejecicion no permite el cambio" () {
    	given: "tarea cancelada"
    		def tarea = new Tarea()
    		tarea.cambiarEstado(Estado.Cancelada)
    	when: "cambio estado a en ejecucion"
    		tarea.cambiarEstado(Estado.EnEjecucion)
    	then: "no permite el cambio"
    		thrown(CambioEstadoInvalidoException)
    		tarea.estado == Estado.Cancelada
    }

    def "dada una tarea cancelada cuando cambio su estado a cancelada no permite el cambio" () {
    	given: "tarea cancelada"
    		def tarea = new Tarea()
    		tarea.cambiarEstado(Estado.Cancelada)
    	when: "cambio estado a cancelada"
    		tarea.cambiarEstado(Estado.Cancelada)
    	then: "no permite el cambio"
    		thrown(CambioEstadoInvalidoException)
    		tarea.estado == Estado.Cancelada
    }

    def "dada una tarea cancelada cuando cambio su estado a finalizada no permite el cambio" () {
    	given: "tarea cancelada"
    		def tarea = new Tarea()
    		tarea.cambiarEstado(Estado.Cancelada)
    	when: "cambio estado a finalizada"
    		tarea.cambiarEstado(Estado.Finalizada)
    	then: "no permite el cambio"
    		thrown(CambioEstadoInvalidoException)
    		tarea.estado == Estado.Cancelada
    }
}
