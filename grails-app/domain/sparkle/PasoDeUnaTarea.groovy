package sparkle

class PasoDeUnaTarea extends Paso {
	PasoDeUnaTarea () {
		super()
	}
	
	def agregarTarea(Tarea tarea) {
		vaciarTareas()
		super.agregarTarea(tarea)
	}
	
	private def vaciarTareas() {
		tareas.each { it.asignarPaso(null) }
		tareas = []
	}
}
