<%-- 
    Document   : mantenimientoSubmenu
    Created on : 17/07/2015, 12:21:14 PM
    Author     : vvasquez
--%>
<%@page import="java.util.List"%>
<%@page import="com.cis.paseaproduccionweb.dao.SubMenusDao"%>
<%@page import="com.cis.paseaproduccionweb.hibernate.PpSubmenus"%>
<%@page import="com.cis.paseaproduccionweb.dao.ModulosDao"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="com.cis.paseaproduccionweb.hibernate.PpUsuarios"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    if(request.getSession().getAttribute("user") == null){
        response.sendRedirect("mensajeSesionTerminada.jsp");
    }
    else{
        
    
    PpUsuarios usuario = (PpUsuarios)request.getSession().getAttribute("user");
    if(usuario==null)
        response.sendRedirect("login.jsp");
    
    String modId = request.getParameter("moduloId");
    
    BigDecimal moduloId = new BigDecimal(modId);
    
    ModulosDao dModulo = new ModulosDao();
    SubMenusDao dSubmenu = new SubMenusDao();
    
    List<PpSubmenus> lstSubmenus = dSubmenu.getSubMenuByModuloId(moduloId);
    

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
                    <li>
                        <a href="historial.jsp"> <span class="nav-label">Historial</span> </a>
                    </li>
                    <li>
                        <a href="perfil.jsp"> <span class="nav-label">Perfil</span> </a>
                    </li>
                    <li class="active">
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
                            Mantenimiento de Submenus
                        </h2>
                        <small>En esa sección se realizará el mantenimiento de los submenus del modulo <% out.print(dModulo.getModuloByModuloId(moduloId).getNombreModulo()); %></small>
                        <br><br><br>
                        <ol class="hbreadcrumb breadcrumb">
                            <li><a href="mantenimiento.jsp" style="font-weight: bold">Mantenimiento Modulos</a></li>                            
                            <li><label style="color: green">Modulo <% out.print(dModulo.getModuloByModuloId(moduloId).getNombreModulo()); %></label></li>
                        </ol>
                    </div>
                </div>
            </div>
            <div class="content animate-panel">
                <div class="row">
                    <div class="col-lg-1"></div>
                        <div class="col-lg-10 animated-panel zoomIn" style="-webkit-animation: 0.5s;">
                            <div class="hpanel">
                                <form method="POST" action="/PaseAProduccionWeb/MantenimientoSubmenus">
                                    <input type="hidden" value="<% out.print(moduloId); %>" name="moduloId" id="moduloId">
                                    <div class="panel-heading">
                                        <label style="font-size: 25px; margin: 0px;"><% out.print(dModulo.getModuloByModuloId(moduloId).getNombreModulo()); %> &nbsp;</label>
                                        <button type="submit" class="btn btn-primary btn-xs" style="margin-bottom: 10px;"><i class="fa fa-plus fa-2x"></i></button>
                                    </div>
                                </form>
                                <form method="POST" action="/PaseAProduccionWeb/mantenimientoFormulario.jsp">
                                    <input type="hidden" name="submenuId" id="submenuId">
                                <div class="panel-body">
                                    <div class="table-responsive">
                                        <table cellpadd  ing="1" cellspacing="1" class="table table-condensed table-striped">
                                            <thead>
                                                <tr>
                                                    <th>Submenu</th>
                                                    <th>Nombre</th>
                                                    <th>Estado</th>
                                                    <th></th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <%
                                                for(int i=0; i<lstSubmenus.size(); i++){
                                                    out.print("<tr>");
                                                    out.print("<td>");
                                                    out.print(lstSubmenus.get(i).getDescSubmenu());
                                                    out.print("</td>");
                                                    out.print("<td>");
                                                    out.print(lstSubmenus.get(i).getNombreSubmenu());
                                                    out.print("</td>");
                                                    out.print("<td>");
                                                    if(lstSubmenus.get(i).getFlagEstado().equals("A"))
                                                        out.print("Activo");
                                                    else if(lstSubmenus.get(i).getFlagEstado().equals("I"))
                                                        out.print("Inactivo");
                                                    out.print("</td>");
                                                    out.print("<td>");
                                                    out.print("<button class=\"btn btn-primary btn-xs\" onclick=\"setSubmenuId("+ lstSubmenus.get(i).getSubmenuId()+");\">Formularios y Reportes</button>");
                                                    out.print("</td>");
                                                    out.print("</tr>");
                                                }
                                                %>
                                            </tbody>
                                        </table>
                                    </div> 
                                </div>
                                </form>
                                <div class="panel-footer">
                                    El modulo <% out.print(dModulo.getModuloByModuloId(moduloId).getNombreModulo()); %> tiene <% out.print(lstSubmenus.size());  %> modulos
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
        
        function setSubmenuId(submenuId){
            $("#submenuId").val(submenuId);
        }
        
    </script>
</html>
<%
    }
%>