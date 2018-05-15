package sparkle

class PasoDeUnaTarea extends Paso {
	PasoDeUnaTarea () {
		tareas = []
		estado = Estado.NoIniciada
	}
	
	def agregarTarea(Tarea tarea) {
		//vaciarTareas()
		//super.agregarTarea()
	}
	
	private def vaciarTareas() {
		tareas.each { it.asignarPaso(null) }
		tareas = []
	}
}
