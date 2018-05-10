package sparkle

class Proceso {
	String nombre
	Estado estado

	static hasMany = [pasos: Paso]

	Proceso () {
		estado = Estado.NoIniciada
		pasos = []
	}

	def agregarPaso (Paso paso) {
		if (pasos.any())
			paso.precursor(pasos.last())
		pasos << paso
	}

    static constraints = {
    	nombre nullable:false, blank:false
    }
}
