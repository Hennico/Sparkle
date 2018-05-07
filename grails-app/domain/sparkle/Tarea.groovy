package sparkle

class Tarea {
	String nombre
	String descripcion
	Estado estado
	Paso paso

	public Tarea() {
		estado = Estado.NoIniciada
	}
	void cambiarEstado(Estado nuevoEstado) {
		validarTransicionA(nuevoEstado)
		estado = nuevoEstado
		if (paso != null)
			paso.informar()
	}
	void asignarPaso(Paso paso) {
		this.paso = paso
	}
	private void validarTransicionA(Estado nuevoEstado) {
		def esMismoEstado      = estado == nuevoEstado
		def estaConcluida      = estado == Estado.Finalizada || estado == Estado.Cancelada
		def desinicia          = estado == Estado.EnEjecucion && nuevoEstado == Estado.NoIniciada
		def finalizaNoIniciada = estado == Estado.NoIniciada  && nuevoEstado == Estado.Finalizada
		def elPasoNoPermite    = paso && !paso.permiteCambio()
		if (esMismoEstado || estaConcluida || finalizaNoIniciada || desinicia || elPasoNoPermite)
			fallarCambioEstado()
	}
	private void fallarCambioEstado() {
		throw new CambioEstadoInvalidoException()
	}

    static constraints = {
		nombre blank:false, nullable:false
		estado nullable:false
    }
}
