<%-- 
    Document   : index
    Created on : 16/06/2015, 12:44:23 PM
    Author     : vvasquez
--%>

<%@page import="com.cis.paseaproduccionweb.dao.UsuariosDao"%>
<%@page import="com.cis.paseaproduccionweb.hibernate.PpArchivosUso"%>
<%@page import="java.util.List"%>
<%@page import="com.cis.paseaproduccionweb.dao.ArchivosUsoDao"%>
<%@page import="com.cis.paseaproduccionweb.hibernate.PpUsuarios"%>
<%@page import="com.cis.paseaproduccionweb.servlet.AuthenticateServlet"%>>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    PpUsuarios usuario = (PpUsuarios)request.getSession().getAttribute("user");
    if(usuario==null)
        response.sendRedirect("login.jsp");
    
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
            <div id="logo" class="light-version">
                <span>
                    Pase a Producción
                </span>
            </div>
            <nav role="navigation">
                <div class="header-link hide-menu"><i class="fa fa-bars"></i></div>
                <div class="small-logo">
                    <span class="text-primary">Pase a Producción</span>
                </div>
                <form role="search" class="navbar-form-custom" method="post" action="#">
                    <div class="form-group">
                        <input type="text" placeholder="" class="form-control" name="search">
                    </div>
                </form>
                <div class="navbar-right">
                    <ul class="nav navbar-nav no-borders">

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
                <div class="profile-picture">
                    <div class="stats-label text-color">
                        <span class="font-extra-bold font-uppercase">Nombre</span>
                        <div id="sparkline1" class="small-chart m-t-sm"></div>
                        <div>
                            <small class="text-muted">Roll</small>
                        </div>
                    </div>
                </div>
                <ul class="nav" id="side-menu">
                    <li class="active">
                        <a href="index.jsp"> <span class="nav-label">Inicio</span>  </a>
                    </li>
                    <li>
                        <a href="#"> <span class="nav-label">Perfil</span> </a>
                    </li>
                    <li>
                        <a href="#"> <span class="nav-label">Mantenimiento</span> </a>
                    </li>
                    <li>
                        <a href="#"> <span class="nav-label">Parametros</span> </a>
                    </li>
                    <li>
                        <a href="#"> <span class="nav-label">Por Aprobar</span> </a>
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
                                <a class="btn btn-success " href="mostrarEntornos.jsp" style="margin: 20px;">
                                    <i class="fa fa-download"></i>
                                    <span class="bold">Descargar formulario</span>
                                </a>                
                            </div>
                        </div>  
                    </div>
                </div>
            </div>
            
            <br>
            <div class="col-sm-6 animated-panel zoomIn" style="-webkit-animation: 0.1s;">
                <div class="hpanel">
                    <div class="panel-heading">
                        Mis formularios en uso
                    </div>
                    <div class="panel-body" style="display: block;">
                        <div class="table-responsive">
                            <table cellpadding="1" cellspacing="1" class="table table-condensed table-striped">
                                <thead>
                                    <tr>
                                        <th>Formulario</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td style=""></td>
                                    </tr>
                                    <%
                                    for(int i=0; i<misFormsEnUso.size(); i++){
                                        out.print("<tr>");
                                        out.print("<td>");
                                        out.print(formsEnUso.get(i).getNombreArchivo() + "&nbsp;&nbsp;&nbsp;&nbsp;");
                                        out.print("</td>");
                                        out.print("<td>");
                                        out.print("<button class=\"btn btn-primary btn-xs\" type=\"button\" onclick=\"pasarAProduccion(this);\" value=\""+ formsEnUso.get(i).getId().getArchivoId()+"\">");
                                        out.print("<i class=\"fa fa-upload\"></i> Pasar a producción");
                                        out.print("</button>");
                                        out.print("&nbsp;&nbsp;<button class=\"btn btn-danger btn-xs\" type=\"button\" onclick=\"cancelarPaseAProduccion(this);\" value=\""+ formsEnUso.get(i).getId().getArchivoId()+"\">");
                                        out.print("<i class=\"fa fa-times\"></i> Cancelar");
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
                                        <th>Formulario</th>
                                        <th>Usuario</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                          for(int i=0; i<formsEnUso.size(); i++)
                          {
                            out.print("<tr>");
                            out.print("<td>");
                            out.print(formsEnUso.get(i).getNombreArchivo());
                            out.print("</td>");
                            out.print("<td>");
                            out.print(uDao.getUsuarioById(formsEnUso.get(i).getUsuarioId()).getNombre());
                            out.print("</td>");
                            out.print("</tr>");  
                          }
                                    %>
                                    <c:forEach items="${formsEnUso}" var="formulario">
                                        <tr>
                                            <td>${formulario.nombreArchivo}</td>
                                            <td>${formulario.usuarioId}</td>
                                        </tr>
                                    </c:forEach>
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
    </body>
    
    <script src="vendor/jquery/dist/jquery.min.js"></script>
    <script src="vendor/jquery-ui/jquery-ui.min.js"></script>
    <script src="vendor/slimScroll/jquery.slimscroll.min.js"></script>
    <script src="vendor/bootstrap/dist/js/bootstrap.min.js"></script>
    <script src="vendor/jquery-flot/jquery.flot.js"></script>
    <script src="vendor/jquery-flot/jquery.flot.resize.js"></script>
    <script src="vendor/jquery-flot/jquery.flot.pie.js"></script>
    <script src="vendor/flot.curvedlines/curvedLines.js"></script>
    <script src="vendor/jquery.flot.spline/index.js"></script>
    <script src="vendor/metisMenu/dist/metisMenu.min.js"></script>
    <script src="vendor/iCheck/icheck.min.js"></script>
    <script src="vendor/peity/jquery.peity.min.js"></script>
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

    </script>
    <script>
        (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
            (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
                m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
        })(window,document,'script','http://www.google-analytics.com/analytics.js','ga');

        ga('create', 'UA-4625583-2', 'webapplayers.com');
        ga('send', 'pageview');
        
        function pasarAProduccion(formulario){
            alert("Se paso a produccion el formulario " + formulario.value);
        }
        
        function cancelarPaseAProduccion(formulario){
            alert("Se cancelo el pase a produccion del formulario " + formulario.value);
        }
    </script>
</html>

<!-- 192.168.185.25   intra   PROYECTO01  PROYECTO01-->