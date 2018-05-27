package sparkle

enum Estado {
	NoIniciada('No iniciada'),
	EnEjecucion('En ejecucion'),
	Finalizada('Finalizada'),
	Cancelada('Cancelada')

	final String displayName

	String toString() {
		displayName
	}

	String getKey() {
		name()
	}

	Estado (String displayName) {
		this.displayName = displayName
	}
}