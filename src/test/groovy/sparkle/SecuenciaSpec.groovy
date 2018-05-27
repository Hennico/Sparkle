package sparkle

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class SecuenciaSpec extends Specification implements DomainUnitTest<Secuencia> {

    def setup() {
    }

    def cleanup() {
    }

    def "nueva secuencia no tiene pasos"() {
        given: "nada"
        when: "creas una nueva secuencia"
        	def secuencia = new Secuencia()
        then: "no tiene pasos"
        	secuencia.pasos.size() == 0
    }

    def "nueva secuencia tiene estado NoIniciada"() {
    	given: "nada"
    	when: "creas una nueva secuencia"
    		def secuencia = new Secuencia()
    	then: "tiene estado no iniciada"
    		secuencia.estado == Estado.NoIniciada
    }

    def "nueva secuencia no tiene nombre" () {
    	given: "nada"
    	when: "creas una nueva secuencia"
    		def secuencia = new Secuencia()
    	then: "no tiene nombre"
    		secuencia.nombre == ""
    }

    def "dada una secuencia nuevo cuento agregas un paso entonces el paso pertenece a la secuencia" () {
    	given: "una secuencia nueva"
    		def secuencia = new Secuencia()
    		def paso = new PasoDeUnaTarea()
    	when: "agregas un paso"
    		secuencia.agregarPaso(paso)
    	then: "el paso pertenece a la secuencia"
    		secuencia.pasos.any { it == paso }
    }

    def "dada una secuencia "
}
