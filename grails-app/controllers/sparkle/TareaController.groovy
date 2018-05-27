package sparkle

class TareaController {

    static scaffold = Tarea

    def actualizarEstado() {
    	Tarea oldTarea = Tarea.get(params.id)
		
		Estado estado = Estado.values().find { it.name() == params.estadoNew }
		oldTarea.cambiarEstado(estado)
		oldTarea.save()
		
		redirect(action:"edit", params: params)
    }
}