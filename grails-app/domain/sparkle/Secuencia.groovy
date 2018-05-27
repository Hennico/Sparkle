package sparkle

class Secuencia {
	Estado estado
	String nombre

	static hasMany = [ pasos: Paso ]

	Secuencia() {
		pasos = []
		nombre = ""
		estado = Estado.NoIniciada
	}

	def agregarPaso(PasoDeUnaTarea paso) {
		pasos << paso
	}

    static constraints = {
    }
}
