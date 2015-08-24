<%-- 
    Document   : multipleswArchivos
    Created on : 13/08/2015, 12:23:54 PM
    Author     : vvasquez
--%>

<%@page import="java.math.BigDecimal"%>
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
        
        String sisId = request.getParameter("sistemaId");
        
        BigDecimal sistemaId = new BigDecimal(sisId);
    
        ArchivosUsoDao archvDao = new ArchivosUsoDao();
        UsuariosDao uDao = new UsuariosDao();

        List<PpArchivosUso> misFormsEnUso = archvDao.getArchivosUsoPorUsuarioYSistemaId(usuario.getUsuarioId(), sistemaId);
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
                    <li class>
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
                            Pase de múltiples archivos <strong>BAJANDO</strong> servicios del sistema 
                            <% if(sisId.equals("1"))
                                out.print("SAAS");
                               if(sisId.equals("2"))
                                out.print("TDM");
                            %>
                        </h2>
                        <small>En esta sección se seleccionarán los archivos que se desean pasar a producción
                            <strong>BAJANDO</strong> los servicios</small>
                        <div class="row">
                        </div>  
                    </div>
                </div>
                <input type="hidden" name="cantidadArchivos" id="cantidadArchivos" value="0">
                <div class="content animate-panel">
                    <div class="row">
                        <div class="col-sm-4 animated-panel zoomIn" style="-webkit-animation: 0.1s;">
                            <div class="hpanel">
                                <div class="panel-heading">
                                    Mis formularios en uso
                                </div>
                                <div class="panel-body" style="display: block;">
                                    <div class="table-responsive">
                                        <table cellpadd  ing="1" cellspacing="1" class="table table-condensed table-striped">
                                            <thead>
                                                <tr>
                                                    <th></th>
                                                    <th>Sistema</th> 
                                                    <th>Formulario</th>
                                                    <th>Tipo</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <%
                                                for(int i=0; i<misFormsEnUso.size(); i++){
                                                    if(misFormsEnUso.get(i).getFlagNoche().equals("N")){
                                                        out.print("<tr>");
                                                        out.print("<td>");
                                                        out.print("<input type=\"checkbox\" value=\""+ misFormsEnUso.get(i).getArchivoUsoId()+"\" onchange=\"crearArchivo(this, '"
                                                                + misFormsEnUso.get(i).getNombreArchivo() +"', '"+ misFormsEnUso.get(i).getTipo() +"');\">");
                                                        out.print("</td>");
                                                        out.print("<td>");
                                                        if(misFormsEnUso.get(i).getSistemaId().toString().equals("1"))
                                                            out.print("SAAS");
                                                        else if(misFormsEnUso.get(i).getSistemaId().toString().equals("2"))
                                                            out.print("TDM");
                                                        out.print("</td>");
                                                        out.print("<td>");
                                                        out.print(misFormsEnUso.get(i).getNombreArchivo());
                                                        out.print("</td>");
                                                        out.print("<td>");
                                                        out.print(misFormsEnUso.get(i).getTipo());
                                                        out.print("</td>");
                                                        out.print("</tr>");
                                                    }
                                                }                            
                                                %>
                                            </tbody>
                                        </table>
                                    </div> 
                                </div>
                            </div>
                            <br>
                            <button type="button" class="btn w-xs btn-primary" id="btnGuardar" disabled="" data-dismiss="modal" data-toggle="modal" data-target="#modalConfirmacion">Aceptar</button>
                            <button type="button" class="btn w-xs btn-danger" onclick="window.location.href='index.jsp'">Cancelar</button>
                        </div>
                        <div class="col-sm-8 animated-panel zoomIn" style="-webkit-animation: 0.1s;">
                            <form id="formulario" action="/PaseAProduccionWeb/PaseMultiplesArchivos" method="POST" enctype="multipart/form-data" onsubmit="mostrarLoading();">
                                <input type="hidden" name="sistemaId" id="sistemaId" value="<% out.print(sistemaId); %>">
                                <input type="hidden" name="comentarioBajada" id="comentarioBajada">
                                <div id="archivos">

                                </div>
                                <br>
                            </form>
                        </div>
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
                        <button type="button" class="btn btn-danger" data-dismiss="modal" data-toggle="modal" data-target="#modalBajarServicios" onclick="resetearCampos();">SI</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal">NO</button>
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
                        <button type="button" class="btn btn-primary" onclick="validar();" data-dismiss="modal">Aceptar</button>
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
    
        function crearArchivo(checkbox, nombreArchivo, archivoTipo){
            
            var nombre = nombreArchivo.substr(0, nombreArchivo.length-4);
            var cantidadArchivos = $('input[name=cantidadArchivos]').val();
            cantidadArchivos = parseInt(cantidadArchivos);
                        
            if(checkbox.checked){
                cantidadArchivos = cantidadArchivos+1;
                $('input[name=cantidadArchivos]').val(cantidadArchivos);
                if(archivoTipo === "FOR"){
                    $("#archivos").append("<div class=\"row\" id=\""+ nombre +"\"> <br>"
                        + "<input type=\"hidden\" name=\"archivoUsoId\" value=\""+ checkbox.value +"\" >"
                        + "<div class=\"col-sm-12 animate-panel zoomIn\">"
                        +"<div class=\"hpanel\">"
                        +"<div class=\"panel-heading hbuilt\">"
                        + nombreArchivo
                        +"</div>"
                        +"<div class=\"alert alert-danger\">"
                        +"<i class=\"fa fa-times\">&nbsp; Elija los archivos correctamente</i>"
                        +"</div>"
                        +"<div class=\"alert alert-success\" hidden>"
                        +"<i class=\"fa fa-bolt\">&nbsp; Archivos elegidos correctamente</i>"
                        +"</div>"
                        +"<div class=\"panel-body\">"
                        +"<div class=\"row\">"
                        +"<div class=\"col-sm-5\">"
                        +"<div class=\"col-sm-12\">"
                        +"<strong>ARCHIVO FUENTE</strong><input type=\"file\" class=\"form-control\" name=\"ArchivoFMB\" value= \"\" onchange=\"validarArchivo('"+ nombre +"', '"+ archivoTipo +"');\" required>"
                        +"</div>"
                        +"<div class=\"col-sm-12\">"
                        +"<strong>ARCHIVO EJECUTABLE</strong><input type=\"file\" class=\"form-control\" name=\"ArchivoFMX\" value=\"\" onchange=\"validarArchivo('"+ nombre +"', '"+ archivoTipo +"');\" required>"
                        +"</div>"
                        +"</div>"
                        +"<div class=\"col-sm-7\">"
                        +"<p>Escriba un comentario acerca de los cambios realizados:</p>"
                        +"<p><textarea class=\"form-control\" name=\"comentarioPase\" id=\"comentarioPase\" style=\"resize: none;\" rows=\"4\"></textarea></p>"
                        +"</div> </div> </div> </div> </div> </div>");
                }
                else if(archivoTipo === "MOD"){
                    $("#archivos").append("<div class=\"row\" id=\""+ nombre +"\"> <br>"
                        + "<input type=\"hidden\" name=\"archivoUsoId\" value=\""+ checkbox.value +"\" >"
                        +"<div class=\"col-sm-12 animate-panel zoomIn\">"
                        +"<div class=\"hpanel\">"
                        +"<div class=\"panel-heading hbuilt\">"
                        + nombreArchivo
                        +"</div>"
                        +"<div class=\"alert alert-danger\">"
                        +"<i class=\"fa fa-times\">&nbsp; Elija los archivos correctamente</i>"
                        +"</div>"
                        +"<div class=\"alert alert-success\" hidden>"
                        +"<i class=\"fa fa-bolt\">&nbsp; Archivos elegidos correctamente</i>"
                        +"</div>"
                        +"<div class=\"panel-body\">"
                        +"<div class=\"row\">"
                        +"<div class=\"col-sm-5\">"
                        +"<div class=\"col-sm-12\">"
                        +"<strong>ARCHIVO FUENTE</strong><input type=\"file\" class=\"form-control\" name=\"ArchivoFMB\" value= \"\" onchange=\"validarArchivo('"+ nombre +"', '"+ archivoTipo +"');\" required>"
                        +"</div>"
                        +"<div class=\"col-sm-12\">"
                        +"<strong>ARCHIVO EJECUTABLE</strong><input type=\"file\" class=\"form-control\" name=\"ArchivoFMX\" value=\"\" onchange=\"validarArchivo('"+ nombre +"', '"+ archivoTipo +"');\" required>"
                        +"</div>"
                        +"</div>"
                        +"<div class=\"col-sm-7\">"
                        +"<p>Escriba un comentario acerca de los cambios realizados:</p>"
                        +"<p><textarea class=\"form-control\" name=\"comentarioPase\" id=\"comentarioPase\" style=\"resize: none;\" rows=\"4\"></textarea></p>"
                        +"</div> </div> </div> </div> </div> </div>");
                }
                else if(archivoTipo === "REP"){
                    $("#archivos").append("<div class=\"row\" id=\""+ nombre +"\"> <br>"
                        +"<input type=\"hidden\" name=\"archivoUsoId\" value=\""+ checkbox.value +"\" >"
                        +"<div class=\"col-sm-12 animate-panel zoomIn\">"
                        +"<div class=\"hpanel\">"
                        +"<div class=\"panel-heading hbuilt\">"
                        + nombreArchivo
                        +"</div>"
                        +"<div class=\"alert alert-danger\">"
                        +"<i class=\"fa fa-times\">&nbsp; Elija el archivo correctamente</i>"
                        +"</div>"
                        +"<div class=\"alert alert-success\" hidden>"
                        +"<i class=\"fa fa-bolt\">&nbsp; Archivos elegidos correctamente</i>"
                        +"</div>"
                        +"<div class=\"panel-body\">"
                        +"<div class=\"col-sm-5\">"
                        +"<strong>ARCHIVO DEL REPORTE</strong><input type=\"file\" class=\"form-control\" name=\"ArchivoRDF\" value= \"\" onchange=\"validarArchivo('"+ nombre +"', '"+ archivoTipo +"');\" required>"
                        +"</div>"
                        +"<div class=\"col-sm-7\">"
                        +"<p>Escriba un comentario acerca de los cambios realizados:</p>"
                        +"<p><textarea class=\"form-control\" name=\"comentarioPase\" id=\"comentarioPase\" style=\"resize: none;\" rows=\"4\"></textarea></p>"
                        +"</div> </div> </div> </div> </div>");
                }
            }
            else{
                cantidadArchivos = cantidadArchivos-1;
                $('input[name=cantidadArchivos]').val(cantidadArchivos);
                $("#" + nombre).remove();
            }
            
            if(cantidadArchivos <= 0)
                $('#btnGuardar').attr('disabled', '');
            
            else{
                var mensajes = document.getElementsByClassName("alert alert-danger");
                $("#btnGuardar").removeAttr("disabled");
                for(var i=0; i<mensajes.length; i++)
                {
                    if($(mensajes[i]).is(":visible")){
                        $("#btnGuardar").attr("disabled", "");
                        break;
                    }
                }
            }
        }
        
        function validarArchivo(nombre, tipo){
            var archivos = document.getElementById(nombre).querySelectorAll("input");
            var mensaje = document.getElementById(nombre).querySelectorAll("div");
            if(tipo === "REP"){
                var extension = archivos[1].value.toString();
                var nombreArchivoRDF = extension.substr(0, extension.length-4);
                nombreArchivoRDF = nombreArchivoRDF.replace(/C:\\fakepath\\/i, '');
                extension = extension.substr(extension.length-3, extension.length).toUpperCase();
                if(nombreArchivoRDF === nombre && extension === 'RDF'){
                        mensaje[3].setAttribute('hidden', '');
                        mensaje[4].removeAttribute('hidden');
                }
                else{
                    mensaje[4].setAttribute('hidden', '');
                    mensaje[3].removeAttribute('hidden');
                }
            }
            else if (tipo === "FOR"){
                var extensionFMB = archivos[1].value.toString();
                var extensionFMX = archivos[2].value.toString();
                var nombreArchivoFMB = extensionFMB.substr(0, extensionFMB.length-4);
                var nombreArchivoFMX = extensionFMX.substr(0, extensionFMX.length-4);
                nombreArchivoFMB = nombreArchivoFMB.replace(/C:\\fakepath\\/i, '');
                nombreArchivoFMX = nombreArchivoFMX.replace(/C:\\fakepath\\/i, '');
                extensionFMB = extensionFMB.substr(extensionFMB.length-3, extensionFMB.length).toUpperCase();
                extensionFMX = extensionFMX.substr(extensionFMX.length-3, extensionFMX.length).toUpperCase();
                if(nombreArchivoFMB === nombre && extensionFMB === 'FMB' &&
                        nombreArchivoFMX === nombre && extensionFMX === 'FMX'){
                        mensaje[3].setAttribute('hidden', '');
                        mensaje[4].removeAttribute('hidden');
                }
                else{
                    mensaje[4].setAttribute('hidden', '');
                    mensaje[3].removeAttribute('hidden');
                }   
                
            }
            else if(tipo === "MOD"){
                var extensionMMB = archivos[1].value.toString();
                var extensionMMX = archivos[2].value.toString();
                var nombreArchivoMMB = extensionMMB.substr(0, extensionMMB.length-4);
                var nombreArchivoMMX = extensionMMX.substr(0, extensionMMX.length-4);
                nombreArchivoMMB = nombreArchivoMMB.replace(/C:\\fakepath\\/i, '');
                nombreArchivoMMX = nombreArchivoMMX.replace(/C:\\fakepath\\/i, '');
                extensionMMB = extensionMMB.substr(extensionMMB.length-3, extensionMMB.length).toUpperCase();
                extensionMMX = extensionMMX.substr(extensionMMX.length-3, extensionMMX.length).toUpperCase();
                
                if(nombreArchivoMMB === nombre && extensionMMB === 'MMB' &&
                        nombreArchivoMMX === nombre && extensionMMX === 'MMX'){
                    mensaje[3].setAttribute('hidden', '');
                    mensaje[4].removeAttribute('hidden');
                }
                else{
                    mensaje[4].setAttribute('hidden', '');
                    mensaje[3].removeAttribute('hidden');
                }
                
            }
            
            var mensajes = document.getElementsByClassName("alert alert-danger");
            $("#btnGuardar").removeAttr("disabled");
            for(var i=0; i<mensajes.length; i++)
            {
                if($(mensajes[i]).is(":visible")){
                    $("#btnGuardar").attr("disabled", "");
                    break;
                }
            }
        }
    
        function setValues(){
            
            if($('#mensajeArch').text()!=="")
            {
                $('#mensajeArch').remove();
            }
        }
             
        function mostrarLoading(){
            $("#loading").show();
        }
        
        function resetearCampos(){
            
            generarCaptcha();
            $('#valorCaptcha').val("");
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
                if($('#valorCaptcha').val() === $('#captcha').val()){
                    setValues();
                    $('#comentarioBajada').val(comentario);
                    $('#formulario').submit();
                }                    
                else
                    $('p[name=mensajeLogin]').append("<label name=\"mensaje\" id=\"mensaje\">El codigo insertado es incorrecto</label>");
                
            }
        }
    </script>
    
</html>
<%
    }
%>