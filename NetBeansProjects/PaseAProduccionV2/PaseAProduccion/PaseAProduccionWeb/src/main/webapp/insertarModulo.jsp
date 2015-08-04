<%-- 
    Document   : insertarModulo
    Created on : 17/07/2015, 10:38:28 AM
    Author     : vvasquez
--%>

<%@page import="com.cis.paseaproduccionweb.hibernate.PpUsuarios"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    if(request.getSession().getAttribute("user") == null){
        response.sendRedirect("mensajeSesionTerminada.jsp");
    }
    else
    {
    PpUsuarios usuario = (PpUsuarios)request.getSession().getAttribute("user");
    if(usuario==null)
        response.sendRedirect("login.jsp");
    
    String sistemaId = request.getParameter("sistemaId");
    String nombreSistema = "";
    if(sistemaId.equals("1"))
        nombreSistema = "SAAS";
    else if(sistemaId.equals("2"))
        nombreSistema = "TDM";
                
    
%>

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

     <!-- App styles -->
     <link rel="stylesheet" href="fonts/pe-icon-7-stroke/css/pe-icon-7-stroke.css" />
     <link rel="stylesheet" href="fonts/pe-icon-7-stroke/css/helper.css" />
     <link rel="stylesheet" href="styles/style.css">
    </head>
    
    <body>
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
                    <div class="stats-label text-color">
                        <img src="images/logo_cis.png" height="105" width="185"/>
                    </div>
                </div>
                <ul class="nav" id="side-menu">
                    <li>
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
                    <li class="active">
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
                            Insertar un Módulo
                        </h2>
                        <small>En esa sección se insertará un módulo en el sistema <% out.print(nombreSistema); %> </small>
                        <br><br><br>
                        <ol class="hbreadcrumb breadcrumb">
                            <li><a href="mantenimiento.jsp" style="font-weight: bold">Mantenimiento Modulos</a></li>                            
                            <li><label style="color: green">Insertar Modulo</label></li>                            
                        </ol>
                    </div>
                </div>
            </div>
            <div class="content animate-panel">
                <div class="row">
                    <div class="col-sm-12 animated-panel zoomIn" style="-webkit-animation: 0.1s 0.1s;">
                        <div class="hpanel">
                            <div class="panel-body">
                                <form method="POST" action="/PaseAProduccionWeb/MantenimientoModulos" enctype="multipart/form-data">
                                    <input type="hidden" name="sistemaId" value="<% out.print(sistemaId); %>">
                                    <p name="mensajeArchivos" id="mensajeArchivos" class="font-bold text-danger"></p>
                                    <div class="row">
                                        <div class="form-group">
                                            <label class="col-sm-4 control-label" style="text-align: right; padding: 6px;">Nombre</label>
                                            <div class="col-sm-5">
                                                <input name="nombre" id="nombre" class="form-control" required="">
                                            </div>
                                        </div>
                                    </div>
                                    <br>
                                    <div class="row">
                                        <div class="form-group">
                                            <label class="col-sm-4 control-label" style="text-align: right; padding: 6px;">Descripcion</label>
                                            <div class="col-sm-5">
                                                <input name="descripcion" id="descripcion" class="form-control" required="">
                                            </div>
                                        </div>
                                    </div>
                                    <br>
                                    <div class="row">
                                        <div class="form-group">
                                            <label class="col-sm-4 control-label" style="text-align: right; padding: 6px;">Archivo Fuente</label>
                                            <div class="col-sm-5">
                                                <input type="file" name="archivoFMB" id="archivoFMB" class="form-control" onblur="validarArchivos();">
                                            </div>
                                        </div>     
                                    </div>
                                    <br>
                                    <div class="row">
                                        <div class="form-group">
                                            <label class="col-sm-4 control-label" style="text-align: right; padding: 6px;">Archivo Ejecutable</label>
                                            <div class="col-sm-5">
                                                <input type="file" name="archivoFMX" id="archivoFMX" class="form-control" onblur="validarArchivos();">
                                            </div>
                                        </div>
                                    </div>
                                    <br>
                                    <br>
                                    <div class="col-sm-4"></div>
                                    <div class="col-sm-5" style="text-align: right">
                                        <button type="submit" class="btn w-xs btn-primary" id="btnGuardar">Guardar</button>
                                        <button type="button" class="btn w-xs btn-danger" onclick="window.location.href='/PaseAProduccionWeb/mantenimiento.jsp'">Cancelar</button>
                                    </div>
                                </form>
                            </div>
                        </div>
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
        
        function validarArchivos(){
            var nombreArchivoFMB = $('input[name=archivoFMB]').val().toUpperCase();
            var nombreArchivoFMX = $('input[name=archivoFMX]').val().toUpperCase();
            nombreArchivoFMB = nombreArchivoFMB.replace(/C:\\fakepath\\/i, '');
            nombreArchivoFMX = nombreArchivoFMX.replace(/C:\\fakepath\\/i, '');
            
            if($('#mensajeArch').text()!=="")
            {
                $('#mensajeArch').remove();
            }
            
            if(nombreArchivoFMB.substr(nombreArchivoFMB.length-3, nombreArchivoFMB.length) === "MMB" &&
                        nombreArchivoFMX.substr(nombreArchivoFMX.length-3, nombreArchivoFMX.length) === "MMX"){
                    $('#btnGuardar').removeAttr('disabled');
                    $('#mensajeArch').remove();
            }
            else{
                    $('#btnGuardar').attr('disabled', 'disabled');
                    $('p[name=mensajeArchivos]').append("<label name=\"mensajeArch\" id=\"mensajeArch\">Elija los archivos correctos</label>");
            }
            
            if(nombreArchivoFMB === "" && nombreArchivoFMX === ""){
                $('#btnGuardar').removeAttr('disabled');
                $('#mensajeArch').remove();
            }
        }
    </script>
</html>
<%
    }
%>