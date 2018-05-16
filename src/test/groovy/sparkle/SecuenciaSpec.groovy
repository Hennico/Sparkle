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
}
