package sparkle

class Tarea {
	String nombre
	String descripcion
	Estado estado
	
	static belongsTo = [paso: Paso]

	public Tarea() {
		estado = Estado.NoIniciada
	}
	void cambiarEstado(Estado nuevoEstado) {
		validarTransicionA(nuevoEstado)
		estado = nuevoEstado
		if (paso != null)
			paso.informar()
	}
	void asignarPaso(Paso pasoNuevo) {
		paso = pasoNuevo
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
		paso nullable:true
		nombre nullable:false, blank:false
		descripcion nullable:false, blank:false
    }
}
