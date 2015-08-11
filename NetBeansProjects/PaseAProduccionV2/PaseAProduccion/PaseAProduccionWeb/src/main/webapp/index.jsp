<%-- 
    Document   : index
    Created on : 16/06/2015, 12:44:23 PM
    Author     : vvasquez
--%>

<%@page import="java.util.Random"%>
<%@page import="com.cis.paseaproduccionweb.dao.UsuariosDao"%>
<%@page import="com.cis.paseaproduccionweb.hibernate.PpArchivosUso"%>
<%@page import="java.util.List"%>
<%@page import="com.cis.paseaproduccionweb.dao.ArchivosUsoDao"%>
<%@page import="com.cis.paseaproduccionweb.hibernate.PpUsuarios"%>
<%@page import="com.cis.paseaproduccionweb.servlet.AuthenticateServlet"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%      
    if(request.getSession().getAttribute("user") == null){
        response.sendRedirect("mensajeSesionTerminada.jsp");
    }
        
    else{
        PpUsuarios usuario = (PpUsuarios)request.getSession().getAttribute("user");
    
        ArchivosUsoDao archvDao = new ArchivosUsoDao();
        UsuariosDao uDao = new UsuariosDao();

        List<PpArchivosUso> misFormsEnUso = archvDao.getArchivosUsoPorUsuario(usuario.getUsuarioId());
        List<PpArchivosUso> formsEnUso= archvDao.getArchivosUso();
    
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>CIS | Pase a Producción</title>
     <link rel="stylesheet" href="vendor/fontawesome/css/font-awesome.css" />
     <link rel="stylesheet" href="vendor/metisMenu/dist/metisMenu.css" />
     <link rel="stylesheet" href="vendor/animate.css/animate.css" />
     <link rel="stylesheet" href="vendor/bootstrap/dist/css/bootstrap.css" />
     <link rel="stylesheet" href="vendor/sweetalert/lib/sweet-alert.css">
     <link rel="stylesheet" href="vendor/toastr/build/toastr.min.css">

     <!-- App styles -->
     <link rel="stylesheet" href="fonts/pe-icon-7-stroke/css/pe-icon-7-stroke.css" />
     <link rel="stylesheet" href="fonts/pe-icon-7-stroke/css/helper.css" />
     <link rel="stylesheet" href="styles/style.css">
     <!-- <link rel="stylesheet" href="styles/static_custom.css"> -->
    </head>
    
    <body>
        <div id="loading" name="loading" style="position: fixed; top: 0; left: 0px; width: 100%; height: 100%; z-index: 10; background-color: rgba(0,0,0,0.5)" hidden="" >
                            <div style="position: fixed; top: 20%; left: 35%">
                                <h1 style="color: white">CIS - Cloud Information Solution</h1>
                                <div class="col-sm-2"></div>
                                <div class="col-sm-5">
                                    <img src="images/loading.gif" style="width: 250px; height: 250px;">
                                </div>
                                <h1 style="color: white">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Realizando cambios...</h1>
                            </div>
    </div>
        <div class="splash">
            <div class="color-line"></div>
            <div class="splash-title">
                <h1>CIS - Cloud Information Solution</h1>
                <img src="images/loading-bars.svg" width="64" height="64" />
            </div>
        </div>
        <div id="header">
            <div class="color-line"></div>
            <nav role="navigation">
                <div class="header-link hide-menu"><i class="fa fa-bars"></i></div>
                <div style="text-align: right;" class="col-sm-7"><span class="font-extra-bold font-uppercase" style="font-size: 30px;">CIS - PASE A PRODUCCION</span></div>
                <div class="navbar-right">
                    <ul class="nav navbar-nav no-borders">
                        <li style="top: 15px;">
                            Bienvenido, <span class="font-extra-bold font-uppercase"><% out.print(usuario.getNombre()); %></span>
                        </li>
                        <li class="dropdown">
                            <a href="login.jsp">
                                <i class="pe-7s-upload pe-rotate-90"></i>
                            </a>
                        </li>
                    </ul>
                </div>
            </nav>
        </div>
        
        <aside id="menu">
            <div id="navigation">
                <div class="profile-picture" style="padding-left: 0px;">
                    <img src="images/logo_cis.png" height="105" width="185"/>
                </div>
                <ul class="nav" id="side-menu">
                    <li class="active">
                        <a href="index.jsp"> <span class="nav-label">Inicio</span>  </a>
                    </li>
                    <li>
                        <a href="mostrarEntornos.jsp"> <span class="nav-label">Reservar Formulario</span> </a>
                    </li>
                    <li class>
                        <a href="#"> 
                            <span class="nav-label">Historial</span> 
                            <span class="fa arrow"></span>
                        </a>
                        <ul class="nav nav-second-level collapse" aria-expanded="false" style="height: 0px;">
                            <li>
                                <a href="/PaseAProduccionWeb/Historial?sistemaId=1"><span class="nav-label">Historial de SAAS</span></a>
                            </li>
                            <li>
                                <a href="/PaseAProduccionWeb/Historial?sistemaId=2"><span class="nav-label">Historial de TDM</span></a>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <a href="perfil.jsp"> <span class="nav-label">Perfil</span> </a>
                    </li>
                    <li>
                        <a href="mantenimiento.jsp"> <span class="nav-label">Mantenimiento</span> </a>
                    </li>
                </ul>
            </div>
        </aside>
        <div id="wrapper">
            <div class="normalheader transition animated fadeIn">
                <div class="hpanel">
                    <div class="panel-body">
                        <h2 class="font-light m-b-xs">
                            Formularios en uso
                        </h2>
                        <small>En esta sección se visualizarán los formularios que estan siendo usados con sus detalles</small>
                        <div class="row">
                            <div class="col-sm-3">
                                <a class=" homerDemo1 btn btn-success " href="mostrarEntornos.jsp" style="margin: 20px;">
                                    <i class="fa fa-download"></i>
                                    <span class="bold">Reservar formulario</span>
                                </a>                
                            </div>
                        </div>  
                    </div>
                </div>
            </div>
            <div class="content animate-panel">
                <div class="row">
                    <div class="col-sm-6 animated-panel zoomIn" style="-webkit-animation: 0.1s;">
                        <div class="hpanel">
                            <div class="panel-heading">
                                Mis formularios en uso
                            </div>
                            <div class="panel-body" style="display: block;">
                                <div class="table-responsive">
                                    <table cellpadd  ing="1" cellspacing="1" class="table table-condensed table-striped">
                                        <thead>
                                            <tr>
                                                <th>Sistema</th> 
                                                <th>Formulario</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%
                                            for(int i=0; i<misFormsEnUso.size(); i++){
                                                out.print("<tr>");
                                                out.print("<td>");
                                                if(misFormsEnUso.get(i).getSistemaId().toString().equals("1"))
                                                    out.print("SAAS");
                                                else if(misFormsEnUso.get(i).getSistemaId().toString().equals("2"))
                                                    out.print("TDM");
                                                out.print("</td>");
                                                out.print("<td>");
                                                out.print(misFormsEnUso.get(i).getNombreArchivo() + "&nbsp;&nbsp;&nbsp;&nbsp;");
                                                out.print("</td>");
                                                out.print("<td>");
                                                if(misFormsEnUso.get(i).getFlagNoche().equals("S")){
                                                    out.print("<button class=\"btn btn-warning2 btn-xs\" type=\"button\" onclick=\"setValues(\'"
                                                            + misFormsEnUso.get(i).getArchivoId()+ "\', \'" + misFormsEnUso.get(i).getTipo() +"\');\" data-toggle=\"modal\" data-target=\"#modalCancelar\">");
                                                    out.print("<i class=\"fa fa-upload\"></i> Cancelar pase");
                                                    out.print("</button>");
                                                }
                                                else{
                                                    out.print("<button class=\"btn btn-primary btn-xs\" type=\"button\" onclick=\"setValues(\'"
                                                            + misFormsEnUso.get(i).getArchivoId()+ "\', \'" + misFormsEnUso.get(i).getTipo() +"\', \'"+ misFormsEnUso.get(i).getNombreArchivo() +"\');\" data-toggle=\"modal\" data-target=\"#modalPasarAProduccion\">");
                                                    out.print("<i class=\"fa fa-upload\"></i> Pasar a producción");
                                                    out.print("</button>");
                                                }
                                                out.print("</td>");
                                                out.print("<td>");
                                                out.print("&nbsp;&nbsp;<button class=\"btn btn-danger btn-xs\" type=\"button\" onclick=\"setValues(\'"
                                                        + misFormsEnUso.get(i).getArchivoId()+ "\', \'" + misFormsEnUso.get(i).getTipo() +"\');\" data-toggle=\"modal\" data-target=\"#modalLiberar\">");
                                                out.print("<i class=\"fa fa-times\"></i> Liberar");
                                                out.print("</button>");
                                                out.print("</td>");
                                                out.print("</tr>");
                                            }                            
                                            %>
                                        </tbody>
                                    </table>
                                </div> 
                            </div>
                            <div class="panel-footer" style="display: block;">
                                <%
                                  out.print(misFormsEnUso.size() + " formularios se encuentran en uso por el usuario " + usuario.getNombre());
                                %>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-6 animated-panel zoomIn" style="-webkit-animation: 0.1s;">
                        <div class="hpanel">
                            <div class="panel-heading">
                                Formularios en uso (todos)
                            </div>
                            <div class="panel-body" style="display: block;">
                                <div class="table-responsive">
                                    <table cellpadding="1" cellspacing="1" class="table table-condensed table-striped">
                                        <thead>
                                            <tr>
                                                <th>Sistema</th>
                                                <th>Formulario</th>
                                                <th>Usuario</th>
                                                <th>Tipo</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%
                                  for(int i=0; i<formsEnUso.size(); i++)
                                  {
                                    out.print("<tr>");
                                    out.print("<td>");
                                    if(formsEnUso.get(i).getSistemaId().toString().equals("1"))
                                        out.print("SAAS");
                                    else if(formsEnUso.get(i).getSistemaId().toString().equals("2"))
                                        out.print("TDM");
                                    out.print("</td>");
                                    out.print("<td>");
                                    out.print(formsEnUso.get(i).getNombreArchivo());
                                    out.print("</td>");
                                    out.print("<td>");
                                    out.print(uDao.getUsuarioById(formsEnUso.get(i).getUsuarioId()).getNombre());
                                    out.print("</td>");
                                    out.print("<td>");
                                    out.print(formsEnUso.get(i).getTipo());
                                    out.print("</td>");
                                    out.print("</tr>");  
                                  }
                                            %>
                                        </tbody>
                                    </table>
                                </div> 
                            </div>
                            <div class="panel-footer" style="display: block;">
                                ${cantFormsEnUso}
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        
        <div class="modal fade hmodal-danger" id="modalMensajeFallo" tabindex="-1" role="dialog" aria-hidden="true" style="display: none;">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="color-line"></div>
                    <div class="modal-header">
                        <h4 class="modal-title">Pase a Producción Fallado</h4>
                    </div>
                    <div class="modal-body">
                        <p>El pase a producción <strong>NO SE PUDO COMPLETAR</strong> con exito</p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">ACEPTAR</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal fade hmodal-success" id="modalMensajeRecibido" tabindex="-1" role="dialog" aria-hidden="true" style="display: none;">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="color-line"></div>
                    <div class="modal-header">
                        <h4 class="modal-title">Pase a Producción Realizado</h4>
                    </div>
                    <div class="modal-body">
                        <p>El pase a producción se <strong>REALIZÓ</strong> con exito</p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">ACEPTAR</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal fade hmodal-danger" id="modalLiberar" tabindex="-1" role="dialog" aria-hidden="true" style="display: none;">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="color-line"></div>
                    <div class="modal-header">
                        <h4 class="modal-title">Liberar Archivo</h4>
                    </div>
                    <div class="modal-body">
                        <p>¿Esta usted seguro que desea <strong>LIBERAR</strong> el elemento seleccionado?</p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">NO</button>
                        <button type="button" class="btn btn-primary" onclick="cancelarPaseAProduccion(1);">Si</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal fade hmodal-warning" id="modalCancelar" tabindex="-1" role="dialog" aria-hidden="true" style="display: none;">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="color-line"></div>
                    <div class="modal-header">
                        <h4 class="modal-title">Cancelar Pase a Producción</h4>
                    </div>
                    <div class="modal-body">
                        <p>¿Esta usted seguro que desea <strong>CANCELAR</strong> el pase a producción del elemento seleccionado y seguirlo trabajando?</p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">NO</button>
                        <button type="button" class="btn btn-primary" onclick="cancelarPaseAProduccion(2);">Si</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal fade" id="modalPasarAProduccion" tabindex="-1" role="dialog" aria-hidden="true" style="display: none;">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="color-line"></div>
                    <div class="modal-header">
                        <h4 class="modal-title">Pasar archivo a Producción</h4>
                        <h4 name="nombreFormulario"></h4>
                    </div>
                    <div class="modal-body">
                        <form id="paseForm" action="/PaseAProduccionWeb/PaseAProduccion" method="post" enctype="multipart/form-data" onsubmit="mostrarLoading();">
                        <input type="hidden" name="archivoId" id="archivoId"/>
                        <input type="hidden" name="archivoTipo" id="tipo"/>
                        <input type="hidden" name="paseTipo" id="paseTipo"/>
                        <input type="hidden" name="nombreArchivo" id="nombreArchivo"/>
                        <div id="FMB" hidden=""><p>Seleccione el archivo fuente<input type="file" class="form-control" name="archivoFMB" id="archivoFMB" value="" onchange="activarBotones();" ></p></div>
                        <div id="FMX" hidden=""><p>Seleccione el archivo ejecutable<input type="file" class="form-control" name="archivoFMX" id="archivoFMX" value="" onchange="activarBotones();" ></p></div>
                        <div id="RDF" hidden=""><p>Seleccione el reporte<input type="file" class="form-control" name="archivoRDF" id="archivoRDF" value="" onchange="activarBotones();"></p></div>
                        <br>                          
                        <p name="mensajeArchivos" id="mensajeArchivos" class="font-bold text-danger"></p>
                        <div id="botones" hidden="">
                            <p>Escriba un comentario acerca de los cambios realizados:</p>
                            <p><textarea class="form-control" required="" name="comentarioPase" id="comentarioPase" style="resize: none;" rows="4"></textarea></p>
                            <p>Elija el método por el cual desea pasar a producción el archivo seleccionado:</p>
                            <p>
                                <button name="btnSinBajar" type="button" class="btn btn-primary" style="width: 200px; height: 120px; text-align: center" onclick="setPaseTipo(0);">Intentar pasar a <br> producción sin bajar <br> servicios</button>
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp
                                <button name="btnNocturno" type="button" class="btn btn-primary" style="width: 200px; height: 120px; text-align: center" onclick="setPaseTipo(2);">Pasar a producción <br> en horario nocturno</button>
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp
                                <button name="btnBajar" type="button" class="btn btn-danger" style="width: 200px; height: 120px; text-align: center"
                                                              data-toggle="modal" data-target="#modalConfirmacion">Pasar a producción <br>bajando servicios</button>
                            </p>
                        </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal fade hmodal-danger" id="modalConfirmacion" tabindex="-1" role="dialog" aria-hidden="true" style="display: none;">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="color-line"></div>
                    <div class="modal-header">
                        <h4 class="modal-title">Esta a punto de <strong>BAJAR</strong> los servicios</h4>
                    </div>
                    <div class="modal-body">
                        <p>¿Esta totalmente seguro que desea <strong>BAJAR</strong> los servicios del sistema?</p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal" data-toggle="modal" data-target="#modalBajarServicios" onclick="resetearCampos();">SI</button>
                        <button type="button" class="btn btn-danger" data-dismiss="modal">NO</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal fade hmodal-danger" id="modalBajarServicios" tabindex="-1" role="dialog" aria-hidden="true" style="display: none;">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="color-line"></div>
                    <div class="modal-header">
                        <h4 class="modal-title">Esta a punto de <strong>BAJAR</strong> los servicios</h4>
                    </div>
                    <div class="modal-body">
                        <p>Para confirmar la bajada de servicios es necesario que confirme el siguiente código</p>
                        <p name="mensajeLogin" id="mensajeLogin" class="font-bold text-danger"></p>
                        <div class="form-group" >
                            <input type="text" value="" name="captcha" id="captcha" disabled="" class="col-sm-8" style="padding-top: 6px; padding-bottom: 6px; font-weight: bold;">
                            <button type="button" class="btn btn-outline btn-primary" style="padding-top: 6px; padding-bottom: 6px; padding-left: 15px; padding-right: 15px;" onclick="generarCaptcha();">
                                <i class="fa fa-refresh"></i>
                            </button>
                        </div>
                        <div class="form-group">
                            <label class="control-label">Ingrese el codigo correcto</label>
                            <input title="Por favor ingrese el codigo" placeholder="Ingrese el codigo" required="" value="" name="valorCaptcha" id="valorCaptcha" class="form-control">
                        </div>
                        <div class="form-group">
                            <label class="control-label">Comentario</label>
                            <textarea required="" name="comentarioServicios" id="comentarioServicios" class="form-control" style="resize: none" rows="4"></textarea>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                        <button type="submit" class="btn btn-primary" onclick="validar();">Aceptar</button>
                    </div>
                </div>
            </div>
        </div>
                            
    </body>
                    
    <script src="vendor/jquery/dist/jquery.min.js"></script>
    <script src="vendor/jquery-ui/jquery-ui.min.js"></script>
    <script src="vendor/slimScroll/jquery.slimscroll.min.js"></script>
    <script src="vendor/bootstrap/dist/js/bootstrap.min.js"></script>
    <script src="vendor/jquery-flot/jquery.flot.js"></script>
    <script src="vendor/jquery-flot/jquery.flot.resize.js"></script>
    <script src="vendor/jquery-flot/jquery.flot.pie.js"></script>
    <script src="vendor/flot.curvedlines/curvedLines.js"></script>
    <script src="vendor/sparkline/index.js"></script>
    <script src="vendor/jquery.flot.spline/index.js"></script>
    <script src="vendor/metisMenu/dist/metisMenu.min.js"></script>
    <script src="vendor/iCheck/icheck.min.js"></script>
    <script src="vendor/peity/jquery.peity.min.js"></script>
    <script src="vendor/bootstrap-star-rating/js/star-rating.min.js"></script>
    <script src="scripts/homer.js"></script>
    <script src="scripts/charts.js"></script>
    
    <script>

        $(function () {

            /**
             * Flot charts data and options
             */
            var data1 = [ [0, 55], [1, 48], [2, 40], [3, 36], [4, 40], [5, 60], [6, 50], [7, 51] ];
            var data2 = [ [0, 56], [1, 49], [2, 41], [3, 38], [4, 46], [5, 67], [6, 57], [7, 59] ];

            var chartUsersOptions = {
                series: {
                    splines: {
                        show: true,
                        tension: 0.4,
                        lineWidth: 1,
                        fill: 0.4
                    },
                },
                grid: {
                    tickColor: "#f0f0f0",
                    borderWidth: 1,
                    borderColor: 'f0f0f0',
                    color: '#6a6c6f'
                },
                colors: [ "#62cb31", "#efefef"],
            };

            $.plot($("#flot-line-chart"), [data1, data2], chartUsersOptions);

            /**
             * Flot charts 2 data and options
             */
            var chartIncomeData = [
                {
                    label: "line",
                    data: [ [1, 10], [2, 26], [3, 16], [4, 36], [5, 32], [6, 51] ]
                }
            ];

            var chartIncomeOptions = {
                series: {
                    lines: {
                        show: true,
                        lineWidth: 0,
                        fill: true,
                        fillColor: "#64cc34"

                    }
                },
                colors: ["#62cb31"],
                grid: {
                    show: false
                },
                legend: {
                    show: false
                }
            };

            $.plot($("#flot-income-chart"), chartIncomeData, chartIncomeOptions);



        });

        (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
            (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
                m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
        })(window,document,'script','http://www.google-analytics.com/analytics.js','ga');

        ga('create', 'UA-4625583-2', 'webapplayers.com');
        ga('send', 'pageview');
        
        
        function activarBotones(){
            
            var nombre = $('input[name=nombreArchivo]').val().toUpperCase();
            nombre = nombre.substr(0, nombre.length-4);
            
            if($('input[name=archivoTipo]').val() === "REP"){
                var nombreArchivoRDF = $('input[name=archivoRDF]').val().toUpperCase();
                nombreArchivoRDF = nombreArchivoRDF.replace(/C:\\fakepath\\/i, '');
                
                if($('#mensajeArch').text()!=="")
                {
                    $('#mensajeArch').remove();
                }
                
                if(nombreArchivoRDF.substr(nombreArchivoRDF.length-3, nombreArchivoRDF.length) === "RDF" &&
                        nombreArchivoRDF.substr(0, nombreArchivoRDF.length-4) === nombre){
                    $('#botones').show();
                    $('button[name=btnNocturno]').hide();
                    $('button[name=btnBajar]').hide();
                }
                else{
                    $('#botones').hide();
                    $('p[name=mensajeArchivos]').append("<label name=\"mensajeArch\" id=\"mensajeArch\">Elija los archivos correctos</label>");
                }
                    
            }
            else if($('input[name=archivoTipo]').val() === "FOR"){
                var nombreArchivoFMB = $('input[name=archivoFMB]').val().toUpperCase();
                var nombreArchivoFMX = $('input[name=archivoFMX]').val().toUpperCase();
                nombreArchivoFMB = nombreArchivoFMB.replace(/C:\\fakepath\\/i, '');
                nombreArchivoFMX = nombreArchivoFMX.replace(/C:\\fakepath\\/i, '');
                
                if($('#mensajeArch').text()!=="")
                {
                    $('#mensajeArch').remove();
                }
                
                if(nombreArchivoFMB.substr(nombreArchivoFMB.length-3, nombreArchivoFMB.length) === "FMB" &&
                        nombreArchivoFMB.substr(0, nombreArchivoFMB.length-4) === nombre &&
                        nombreArchivoFMX.substr(nombreArchivoFMX.length-3, nombreArchivoFMX.length) === "FMX" &&
                        nombreArchivoFMX.substr(0, nombreArchivoFMX.length-4) === nombre){
                    $('#botones').show();
                }
                else{
                    $('#botones').hide();
                    $('p[name=mensajeArchivos]').append("<label name=\"mensajeArch\" id=\"mensajeArch\">Elija los archivos correctos</label>");
                }
                        
            }
            else if($('input[name=archivoTipo]').val() === "MOD"){
                var nombreArchivoMMB = $('input[name=archivoFMB]').val().toUpperCase();
                var nombreArchivoMMX = $('input[name=archivoFMX]').val().toUpperCase();
                nombreArchivoMMB = nombreArchivoMMB.replace(/C:\\fakepath\\/i, '');
                nombreArchivoMMX = nombreArchivoMMX.replace(/C:\\fakepath\\/i, '');
                
                if($('#mensajeArch').text()!=="")
                {
                    $('#mensajeArch').remove();
                }
                
                if(nombreArchivoMMB.substr(nombreArchivoMMB.length-3, nombreArchivoMMB.length) === "MMB" &&
                        nombreArchivoMMB.substr(0, nombreArchivoMMB.length-4) === nombre &&
                        nombreArchivoMMX.substr(nombreArchivoMMX.length-3, nombreArchivoMMX.length) === "MMX" &&
                        nombreArchivoMMX.substr(0, nombreArchivoMMX.length-4) === nombre){
                    $('#botones').show();
                }
                else{
                    $('#botones').hide();
                    $('p[name=mensajeArchivos]').append("<label name=\"mensajeArch\" id=\"mensajeArch\">Elija los archivos correctos</label>");
                }
            }
        }
        
        function setValues(archivoId, tipo, nombre){
            
            $('input[name=archivoId]').val(archivoId.toString());
            $('input[name=archivoTipo]').val(tipo.toString());
            $('input[name=nombreArchivo]').val(nombre.toString());
            $('input[name=archivoFMB]').val("");
            $('input[name=archivoFMX]').val("");
            $('input[name=archivoRDF]').val("");
            if($('#mostrarNombre').text()!=="")
            {
                $('#mostrarNombre').remove();
            }
            $('h4[name=nombreFormulario]').append("<label name=\"mostrarNombre\" id=\"mostrarNombre\">"+ nombre +"</label>");
            if($('input[name=archivoTipo]').val() === "REP")
            {
                $('#RDF').show();
                $('#FMB').hide();
                $('#FMX').hide();
            }
            else{
                $('#RDF').hide();
                $('#FMB').show();
                $('#FMX').show();
            }
            $('#botones').hide();
            if($('#mensajeArch').text()!=="")
            {
                $('#mensajeArch').remove();
            }
        }
        
        function setPaseTipo(paseTipo){
            $("#modalBajarServicios").hide();
            $("#modalPasarAProduccion").hide();
            $('input[name=paseTipo]').val(paseTipo);
            $('#paseForm').submit();
        }
        
        function cancelarPaseAProduccion(tipoCancelacion){
            
            $('#modalCancelar').hide();
            var archivoId = $('input[name=archivoId]').val();
            var archivoTipo = $('input[name=archivoTipo]').val();
            document.location.href = '/PaseAProduccionWeb/CancelarFormulario?archivoId='+archivoId.toString()+'&archivoTipo='+archivoTipo.toString()
                                    +'&tipoCancelacion='+tipoCancelacion;
        }
        
        function mostrarLoading(){
            $("#loading").show();
        }
        
        function resetearCampos(){
            
            generarCaptcha();
            $('#valorCaptcha').val("");
            document.getElementById("comentario").value = "";
            $('#mensaje').remove();
        }
        
        function generarCaptcha(){
            var text="";
            var possible = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
            
            for( var i=0; i < 8; i++ )
                text += possible.charAt(Math.floor(Math.random() * possible.length));
            
            $('#captcha').val(text);
        }
        
        function validar(){
            var valorCaptcha = $('#valorCaptcha').val();
            var comentario = document.getElementById("comentarioServicios").value;
            
            if($('#mensaje').text()!=="")
            {
                $('#mensaje').remove();
            }
                
            if(valorCaptcha === "" || comentario === ""){
                $('p[name=mensajeLogin]').append("<label name=\"mensaje\" id=\"mensaje\">Debe llenar todos los campos</label>");
            }
            else{
                $('#mensaje').remove();
                if($('#valorCaptcha').val() === $('#captcha').val())
                    setPaseTipo(1);
                else
                    $('p[name=mensajeLogin]').append("<label name=\"mensaje\" id=\"mensaje\">El codigo insertado es incorrecto</label>");
                
            }
        }
    </script>
    
</html>
<%
    }
%>
<!-- 192.168.185.25   intra   PROYECTO01  PROYECTO01-->
