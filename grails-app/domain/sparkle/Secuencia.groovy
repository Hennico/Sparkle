package sparkle

class Secuencia {

	static hasMany = [ pasos: Paso ]

	Secuencia() {
		pasos = []
	}

    static constraints = {
    }
}
