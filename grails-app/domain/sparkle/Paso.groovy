package sparkle

class Paso {
	String nombre
	Paso pasoAnterior
	Estado estado
	
	static hasMany = [tareas: Tarea]

	Paso () {
		estado = Estado.NoIniciada
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
	private boolean estaTerminado() {
		tareas.any { it.estado == Estado.Finalizada } &&
		tareas.every { it.estado == Estado.Finalizada || it.estado == Estado.Cancelada }
	}
	private boolean estaIniciado() {
		tareas.any { it.estado != Estado.NoIniciada }
	}

	def void informar (){
		estado = Estado.NoIniciada
		if (this.estaIniciado())
			estado = Estado.EnEjecucion
		if (estaTerminado())
			estado = Estado.Finalizada
		if (tareas.every {it.estado == Estado.Cancelada })
			estado = Estado.Cancelada
	}


    static constraints = {
		nombre blank:false, nullable: false
		pasoAnterior nullable: true
    }
}
