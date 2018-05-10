package sparkle

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class PasoSpec extends Specification implements DomainUnitTest<Paso> {

    def setup() {
    }

    def cleanup() {
    }

    private class TareaComun extends Tarea {}
    private class TareaEnEstado extends Tarea {
        public TareaEnEstado(Estado estadoInicial) {
            this.estado = estadoInicial
        }
    }

    def "cuando creo un nuevo paso no tiene tareas" () {
        given: "nada"
        when: "creo nuevo paso"
            def paso = new Paso()
        then: "no tiene tareas"
            paso.tareas.size() == 0
    }

    def "cuando creo un nuevo paso no tiene nombre" () {
        given: "nada"
        when: "creo nuevo paso"
            def paso = new Paso()
        then: "no tiene nombre"
            paso.nombre == null
    }

    def "cuando creo un nuevo paso no tiene paso anterior" () {
        given: "nada"
        when: "creo nuevo paso"
            def paso = new Paso()
        then: "no tiene paso anterior"
            paso.pasoAnterior == null
    }

    def "dado un paso cuando agrego una tarea la agrega a sus tareas" () {
        given: "un paso y una tarea"
            def paso = new Paso()
            def tarea = new TareaComun()
        when: "agrego la tarea"
            paso.agregarTarea(tarea)
        then: "se agrego la tarea"
            paso.tareas.any {it == tarea}
    }

    def "dado un paso con una tarea no iniciada cuando agrego una tarea la agrega" () {
        given: "un paso con una tarea"
            def paso = new Paso()
            def tarea = new TareaEnEstado(Estado.NoIniciada)
            def otraTarea = new TareaComun()
            paso.agregarTarea(tarea)
        when: "agrego otra tarea"
            paso.agregarTarea(otraTarea)
        then: "agrega la tarea"
            paso.tareas.any {it == otraTarea}
    }

    def "dado un paso con una tarea en ejecucion cuando agrego una tarea tarea no lo permite" () {
        given: "un paso con una tarea en ejecucion"
            def paso = new Paso()
            def tarea = new TareaEnEstado(Estado.EnEjecucion)
            def otraTarea = new TareaComun()
            paso.agregarTarea(tarea)
        when: "agrego una tarea"
            paso.agregarTarea(otraTarea)
        then: "no lo permite"
            thrown(Exception)
            !paso.tareas.any {it == otraTarea}
    }

    def "dado un paso con una tarea finalizada cuando agrego una tarea no lo permite" () {
        given: "un paso con tarea finalizada"
            def paso = new Paso()
            def tarea = new TareaEnEstado(Estado.Finalizada)
            def otraTarea = new TareaComun()
            paso.agregarTarea(tarea)
        when: "agrego otra tarea"
            paso.agregarTarea(otraTarea)
        then: "no lo permite"
            thrown(Exception)
            !paso.tareas.any {it == otraTarea}
    }

    def "dado un paso con una tarea cancelada cuando se agrega otra lo permite" () {
        given: "un paso con tarea cancelada"
            def paso = new Paso()
            def tarea = new TareaEnEstado(Estado.Cancelada)
            def otraTarea = new TareaComun()
            paso.agregarTarea(tarea)
        when: "agergo otra tarea"
            paso.agregarTarea(otraTarea)
        then: "agrega la tarea"
            paso.tareas.any {it == otraTarea}
    }

    def "dado un paso nuevo cuando le asigno precursor se agrega el paso anterior" () {
        given: "un paso nuevo"
            def primerPaso = new Paso()
            def segundoPaso = new Paso()
        when: "le asigno precursor"
            segundoPaso.precursor(primerPaso)
        then: "se le asigna"
            segundoPaso.pasoAnterior == primerPaso
    }

    def "Dado un paso Cuando agrego una tarea El paso se asigna a la tarea" () {
        given: "un paso"
            def paso = new Paso()
            def tarea = new Tarea()
        when: "le agrego una tarea"
            paso.agregarTarea(tarea)
        then: "el paso se asigna a la tarea"
            tarea.paso == paso
    }

    def "Dado un paso sin paso anterior Cuando pregunto si permite cambio Entonces regresa verdadero" () {
        given: "Un paso sin paso anterior"
            def paso = new Paso()
        when: "Pregunto si permite cambio"
            def resultado = paso.permiteCambio()
        then: "regresa verdadero"
            resultado
    }

    def "Dado un paso con un paso anterior no completado Cuando pregunto si permite cambio Entonces regresa falso" () {
        given: "Un paso con un paso anterior no completado"
            def paso = new Paso()
            def otroPaso = new Paso()
            paso.precursor(otroPaso)
        when: "Pregunto is permite cambio"
            def respuesta = paso.permiteCambio()
        then: "Regresa falso"
            !respuesta
    }

    def "Dado un paso con un paso anterior completado Cuando pregunto si permite cambio Entonces regresa verdadero" () {
        given: "Un paso con un paso anterior completado"
            def paso = new Paso()
            def otroPaso = new Paso()
            def tarea = new Tarea()
            paso.precursor(otroPaso)
            otroPaso.agregarTarea(tarea)
            tarea.cambiarEstado(Estado.EnEjecucion)
            tarea.cambiarEstado(Estado.Finalizada)
        when: "Pregunto si permite cambio"
            def respuesta = paso.permiteCambio()
        then: "Regresa verdadero"
            respuesta
    }

    def "Dado un paso nuevo Cuando obtenes su estado Entonces su estado es no iniciado" () {
        given: "Un paso nuevo"
            def paso = new Paso()
        when: "Obtengo el estado"
            def estado = paso.estado
        then: "El estado es no iniciado"
            estado == Estado.NoIniciada
    }

    def "Dado un paso dos tareas no iniciadas Cuando obtenes su estado Entonces su estado es no iniciado" () {
        given: "Un paso con dos tareas no iniciadas"
            def paso = new Paso()
            def tareaUno = new TareaEnEstado(Estado.NoIniciada)
            def tareaDos = new TareaEnEstado(Estado.NoIniciada)

            paso.agregarTarea(tareaUno)
            paso.agregarTarea(tareaDos)

        when: "Obtengo el estado"
            def estado = paso.estado
        then: "El estado es no iniciado"
            estado == Estado.NoIniciada
    }

    def """
        Dado un paso con una tarea no iniciada y otra en ejecucion
        Cuando obtenes su estado
        Entonces su estado es en ejecucion
    """ () {
        given: "Un paso con una tarea no iniciada y otra en ejecucion"
            def paso = new Paso()
            def tareaUno = new TareaEnEstado(Estado.NoIniciada)
            def tareaDos = new Tarea()

            paso.agregarTarea(tareaUno)
            paso.agregarTarea(tareaDos)

            tareaDos.cambiarEstado(Estado.EnEjecucion)
        when: "Obtengo el estado"
            def estado = paso.estado
        then: "El estado es no ejecucion"
            estado == Estado.EnEjecucion
    }

    def """
        Dado un paso con una tarea no iniciada y otra finalizada
        Cuando obtenes su estado
        Entonces su estado es en ejecucion
    """ () {
        given: "Un paso con una tarea no iniciada y otra finalizada"
            def paso = new Paso()
            def tareaUno = new TareaEnEstado(Estado.NoIniciada)
            def tareaDos = new Tarea()

            paso.agregarTarea(tareaUno)
            paso.agregarTarea(tareaDos)

            tareaDos.cambiarEstado(Estado.EnEjecucion)
            tareaDos.cambiarEstado(Estado.Finalizada)
        when: "Obtengo el estado"
            def estado = paso.estado
        then: "El estado es no ejecucion"
            estado == Estado.EnEjecucion
    }

    def """
        Dado un paso con una tarea no iniciada y otra cancelada
        Cuando obtenes su estado
        Entonces su estado es en ejecucion
    """ () {
        given: "Un paso con una tarea no iniciada y otra cancelada"
            def paso = new Paso()
            def tareaUno = new TareaEnEstado(Estado.NoIniciada)
            def tareaDos = new Tarea()

            paso.agregarTarea(tareaUno)
            paso.agregarTarea(tareaDos)

            tareaDos.cambiarEstado(Estado.Cancelada)
        when: "Obtengo el estado"
            def estado = paso.estado
        then: "El estado es no ejecucion"
            estado == Estado.EnEjecucion
    }

    def """
        Dado un paso con dos tareas en ejecucion
        Cuando obtenes su estado
        Entonces su estado es en ejecucion
    """ () {
        given: "Un paso con dos tareas en ejecucion"
            def paso = new Paso()
            def tareaUno = new Tarea()
            def tareaDos = new Tarea()

            paso.agregarTarea(tareaUno)
            paso.agregarTarea(tareaDos)

            tareaUno.cambiarEstado(Estado.EnEjecucion)
            tareaDos.cambiarEstado(Estado.EnEjecucion)
        when: "Obtengo el estado"
            def estado = paso.estado
        then: "El estado es no ejecucion"
            estado == Estado.EnEjecucion
    }

    def """
        Dado un paso con una tarea en ejecucion y otra finalizada
        Cuando obtenes su estado
        Entonces su estado es en ejecucion
    """ () {
        given: "Un paso con una tarea en ejecucion y otra finalizada"
            def paso = new Paso()
            def tareaUno = new Tarea()
            def tareaDos = new Tarea()

            paso.agregarTarea(tareaUno)
            paso.agregarTarea(tareaDos)

            tareaUno.cambiarEstado(Estado.EnEjecucion)
            tareaDos.cambiarEstado(Estado.EnEjecucion)
            tareaDos.cambiarEstado(Estado.Finalizada)
        when: "Obtengo el estado"
            def estado = paso.estado
        then: "El estado es no ejecucion"
            estado == Estado.EnEjecucion
    }

    def """
        Dado un paso con una tarea en ejecucion y otra cancelada
        Cuando obtenes su estado
        Entonces su estado es en ejecucion
    """ () {
        given: "Un paso con una tarea en ejecucion y otra cancelada"
            def paso = new Paso()
            def tareaUno = new Tarea()
            def tareaDos = new Tarea()

            paso.agregarTarea(tareaUno)
            paso.agregarTarea(tareaDos)

            tareaUno.cambiarEstado(Estado.EnEjecucion)
            tareaDos.cambiarEstado(Estado.Cancelada)
        when: "Obtengo el estado"
            def estado = paso.estado
        then: "El estado es no ejecucion"
            estado == Estado.EnEjecucion
    }

    def """
        Dado un paso con dos tareas finalizadas
        Cuando obtenes su estado
        Entonces su estado es finalizada
    """ () {
        given: "Un paso con dos tareas finalizadas"
            def paso = new Paso()
            def tareaUno = new Tarea()
            def tareaDos = new Tarea()

            paso.agregarTarea(tareaUno)
            paso.agregarTarea(tareaDos)

            tareaUno.cambiarEstado(Estado.EnEjecucion)
            tareaUno.cambiarEstado(Estado.Finalizada)
            tareaDos.cambiarEstado(Estado.EnEjecucion)
            tareaDos.cambiarEstado(Estado.Finalizada)
        when: "Obtengo el estado"
            def estado = paso.estado
        then: "El estado es finalizada"
            estado == Estado.Finalizada
    }

    def """
        Dado un paso con una tarea finalizada y otra cancelada
        Cuando obtenes su estado
        Entonces su estado es finalizada
    """ () {
        given: "Un paso con una tarea finalizada y otra cancelada"
            def paso = new Paso()
            def tareaUno = new Tarea()
            def tareaDos = new Tarea()

            paso.agregarTarea(tareaUno)
            paso.agregarTarea(tareaDos)

            tareaUno.cambiarEstado(Estado.EnEjecucion)
            tareaUno.cambiarEstado(Estado.Finalizada)
            tareaDos.cambiarEstado(Estado.Cancelada)
        when: "Obtengo el estado"
            def estado = paso.estado
        then: "El estado es finalizada"
            estado == Estado.Finalizada
    }

    def """
        Dado un paso con dos tareas canceladas
        Cuando obtenes su estado
        Entonces su estado cancelada
    """ () {
        given: "Un paso con dos tareas canceladas"
            def paso = new Paso()
            def tareaUno = new Tarea()
            def tareaDos = new Tarea()

            paso.agregarTarea(tareaUno)
            paso.agregarTarea(tareaDos)

            tareaUno.cambiarEstado(Estado.Cancelada)
            tareaDos.cambiarEstado(Estado.Cancelada)
        when: "Obtengo el estado"
            def estado = paso.estado
        then: "El estado es cancelada"
            estado == Estado.Cancelada
    }
}
