<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Welcome to Grails</title>

    <asset:link rel="icon" href="favicon.ico" type="image/x-ico" />
</head>
<body>
    <div id=Cronograma>
        <table>
        <g:each var="proceso" in="${sparkle.Proceso.getAll()}">
            <tr>
                <td>
                    Proceso:
                    <a href="${createLink(controller:'Proceso',action:'show',id:proceso.id)}" >
                        ${proceso.nombre}
                    </a>
                    
                </td>
                <td>
                    <table>
                    <g:each var="paso" in="${proceso.pasos}">
                        <tr>
                            <td>
                                <ul>
                                <li>
                                    Paso:
                                    <a href="${createLink(controller:'Paso',action:'show',id:paso.id)}" >
                                        ${paso.nombre}
                                    </a>
                                </li>
                                <li>Estado: ${paso.estado.toString()}</li>
                                </ul>
                            </td>
                            <td>
                                <ul>
                                <g:each var="tarea" in="${paso.tareas}">
                                    <li>
                                        <ul><li>Tarea:
                                    <a href="${createLink(controller:'Tarea',action:'show',id:tarea.id)}" >
                                        ${tarea.nombre}
                                    </a></li>
                                        <li>Estado: ${tarea.estado.toString()}</li></ul>
                                    </li>
                                </g:each>
                                </ul>
                            </td>
                        </tr>
                    </g:each>
                    </table>
                </td>
            </tr>
        </g:each>
        </table>
    </div>
    <div id="content" role="main">
        <section class="row colset-2-its">
            <div id="controllers" role="navigation">
                <h2>Available Controllers:</h2>
                <ul>
                    <g:each var="c" in="${grailsApplication.controllerClasses.sort { it.fullName } }">
                        <li class="controller">
                            <g:link controller="${c.logicalPropertyName}">${c.fullName}</g:link>
                        </li>
                    </g:each>
                </ul>
            </div>
        </section>
    </div>
</body>
</html>
