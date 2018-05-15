package sparkle

class PasoDeUnaTarea extends Paso {
	def agregarTarea(Tarea tarea) {
		vaciarTareas()
		super.agregarTarea()
	}
	
	private def vaciarTareas() {
		tareas.each { it.asignarPaso(null) }
		tareas = []
	}
}