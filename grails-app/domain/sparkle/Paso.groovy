package sparkle

class Paso {
	int orden
	String nombre
	Paso pasoAnterior

	List<Tarea> tareas

	Paso () {
		tareas = []
	}

	def agregarTarea(Tarea tarea) {
		if (estaIniciado() && !tareas.every { it.estado == Estado.Cancelada })
			throw new Exception("El paso no puede agregar tareas cuando esta iniciado")
		tarea.asignarPaso(this)
		tareas << tarea
	}
	def precursor(Paso paso) {
		pasoAnterior = paso
	}
	boolean permiteCambio() {
		!pasoAnterior || pasoAnterior.estaTerminado()
	}
	Estado getEstado() {
		
	}
	private boolean estaTerminado() {
		tareas.any { it.estado == Estado.Finalizada } && 
		tareas.every { it.estado == Estado.Finalizada || it.estado == Estado.Cancelada }
	}
	private boolean estaIniciado() {
		tareas.any { it.estado != Estado.NoIniciada } 
	}
    static constraints = {
    	orden blank:false, nullable: false
		nombre blank:false, nullable: false
		pasoAnterior blank:false, nullable: false 
    }
}
