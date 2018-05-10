package sparkle

class BootStrap {

    def init = { servletContext ->
    	def proceso = new Proceso(nombre: "proceso")
    	def pasoUno = new Paso(nombre: "primer paso")
    	def pasoDos = new Paso(nombre: "segundo paso")
    	def tareaUno = new Tarea(nombre: "primera tarea", descripcion: "hacer algo")
    	def tareaDos = new Tarea(nombre: "segunda tarea", descripcion: "hacer otra cosa")

    	proceso.agregarPaso(pasoUno)
    	proceso.agregarPaso(pasoDos)
    	pasoUno.agregarTarea(tareaUno)
    	pasoDos.agregarTarea(tareaDos)

        tareaUno.cambiarEstado(Estado.EnEjecucion)

    	proceso.save()
		pasoUno.save()
		pasoDos.save()
    	tareaUno.save()
    	tareaDos.save()
    }
    def destroy = {
    }
}
