package sparkle

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class ProcesoSpec extends Specification implements DomainUnitTest<Proceso> {

    def setup() {
    }

    def cleanup() {
    }

    void "cuando creo un nuevo proceso el estado es no iniciada" () {
        given: "nada"
        when: "creo proceso"
            def proceso = new Proceso()
        then: "estado no iniciada"
            proceso.estado == Estado.NoIniciada
    }
    
    void "cuando creo un nuevo proceso no tiene pasos" () {
        given: "nada"
        when: "creo nuevo proceso"
            def proceso = new Proceso()
        then: "no tiene pasos"
            proceso.pasos.size == 0
    }
    
    void "dado un nuevo proceso cuando agrego un paso entonces el paso se agrega" () {
        given: "nuevo proceso"
            def proceso = new Proceso()
            def paso = new Paso()
        when: "agrego un paso"
            proceso.agregarPaso(paso)
        then: "el paso se agrega"
            proceso.pasos.any {it == paso}
    }

    void "dado un proceso con un paso cuando agrego otro paso entonces el segundo paso tiene como paso anterior al primero" () {
    	given: "un proceso con un paso"
    		def proceso = new Proceso()
    		def paso = new Paso()
    		def otroPaso = new Paso()
    		proceso.agregarPaso(paso)
    	when: "agrego otro paso"
    		proceso.agregarPaso(otroPaso)
    	then: "el segundo paso tiene como paso anterior al primero"
    		otroPaso.pasoAnterior == paso
    }
}
