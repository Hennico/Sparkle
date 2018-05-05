package sparkle

class Proceso {
	String nombre
	Estado estado

	List<Paso> pasos

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
