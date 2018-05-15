package sparkle

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class PasoDeUnaTareaSpec extends Specification implements DomainUnitTest<PasoDeUnaTarea> {
    def setup() {
    }

    def cleanup() {
    }
	def """
		teniendo un paso vacio 
		cuando agrego una tarea 
		entonces el paso tiene la tarea asociada
	""" () {
		given: "un paso de una tarea con una tarea"
			def paso = new PasoDeUnaTarea()
			def tarea = new Tarea()
		when: "agrego otra tarea"
			paso.agregarTarea(tarea)
		then: "el paso tiene una sola tarea"
			paso.tareas.any { it == tarea }
	}
	
	def """
		teniendo un paso de una tarea con una tarea 
		cuando agrego otra tarea 
		entonces el paso tiene una sola tarea asociada
	""" () {
		given: "un paso de una tarea con una tarea"
			def paso = new PasoDeUnaTarea()
			def tareaUno = new Tarea()
			def tareaDos = new Tarea()
			
			paso.agregarTarea(tareaUno)
		when: "agrego otra tarea"
			paso.agregarTarea(tareaDos)
		then: "el paso tiene una sola tarea"
			paso.tareas.size() == 1
	}
	
	def """
		teniendo un paso de una tarea con una tarea
		cuando agrego otra tarea
		entonces la primera tarea no esta asociada al paso
	""" () {
		given: "un paso de una tarea con una tarea"
			def paso = new PasoDeUnaTarea()
			def tareaUno = new Tarea()
			def tareaDos = new Tarea()
			
			paso.agregarTarea(tareaUno)
		when: "agrego otra tarea"
			paso.agregarTarea(tareaDos)
		then: "la primera tarea no esta asociada al paso"
			tareaUno.paso != paso
	}
	
	def """
		teniendo un paso de una tarea con una tarea
		cuando agrego otra tarea
		entonces la segunda tarea pertenece al paso
	""" () {
		given: "un paso de una tarea con una tarea"
			def paso = new PasoDeUnaTarea()
			def tareaUno = new Tarea()
			def tareaDos = new Tarea()
			
			paso.agregarTarea(tareaUno)
		when: "agrego otra tarea"
			paso.agregarTarea(tareaDos)
		then: "la segunda tarea pertenece al paso"
			paso.tareas.any { it == tareaDos }
	}
	
	def """
		teniendo un paso de una tarea con una tarea
		cuando agrego otra tarea
		entonces la segunda tarea esta asociada al paso
	""" () {
		given: "un paso de una tarea con una tarea"
			def paso = new PasoDeUnaTarea()
			def tareaUno = new Tarea()
			def tareaDos = new Tarea()
			
			paso.agregarTarea(tareaUno)
		when: "agrego otra tarea"
			paso.agregarTarea(tareaDos)
		then: "la segunda tarea esta asociada al paso"
			tareaDos.paso == paso
	}
}
