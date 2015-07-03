<%-- 
    Document   : mostrarModulos
    Created on : 17/06/2015, 04:14:00 PM
    Author     : vvasquez
--%>

<%@page import="com.cis.paseaproduccionweb.dao.FormulariosDao"%>
<%@page import="com.cis.paseaproduccionweb.hibernate.PpFormularios"%>
<%@page import="com.cis.paseaproduccionweb.hibernate.PpSubmenus"%>
<%@page import="com.cis.paseaproduccionweb.dao.SubMenusDao"%>
<%@page import="java.util.List"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="com.cis.paseaproduccionweb.hibernate.PpModulos"%>
<%@page import="com.cis.paseaproduccionweb.dao.ModulosDao"%>
<%@page import="com.cis.paseaproduccionweb.hibernate.PpUsuarios"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.Random"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%    
    PpUsuarios usuario = (PpUsuarios)request.getSession().getAttribute("user");
    if(usuario==null)
        response.sendRedirect("login.jsp");
    
    BigDecimal sistemaId = new BigDecimal(request.getParameter("sistemaId"));
    
    ModulosDao mDao = new ModulosDao();
    List<PpModulos> lstModulos = mDao.getModulosBySistemaId(sistemaId);
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
                        <span class="font-extra-bold font-uppercase"><% out.print(usuario.getNombre()); %></span>
                        <br/>
                        <img src="images/logo_cis.jpg" height="70" width="130"/>
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
                            Modulos y Submenus
                        </h2>
                        <small>Seleccione el módulo o submenu del cual desea descargar los formularios</small>
                        <br><br><br>
                        <ol class="hbreadcrumb breadcrumb">
                            <li><a href="mostrarEntornos.jsp" style="font-weight: bold">Entorno</a></li>
                            <li><label style="color: green">Módulos</label></li>
                        </ol>
                    </div>
                </div>
            </div>
            <form action="/PaseAProduccionWeb/Formularios" method="post">
            <input type="hidden" id="tipoPadre" name="tipoPadre"/>
            <input type="hidden" id="padreId" name="padreId"/>
            <input type="hidden" id="sistemaId" name="sistemaId" value="<% out.print(sistemaId); %>"/>
            <div class="content animate-panel">
                <%
                SubMenusDao dSubMenu = new SubMenusDao();
                FormulariosDao dFormulario = new FormulariosDao();
                List<PpSubmenus> lstSubmenus = null;
                List<PpFormularios> lstFormularios = null;
                int cantForms=0;
                for(int i=0; i<lstModulos.size(); i++){
                    out.print("<div class=\"row\">");
                    out.print("<div class=\"hpanel col-lg-12\">");
                    out.print("<div class=\"panel-heading hbuilt\">");
                    out.print("<div class=\"panel-tools\">");
                    out.print("<a class=\"showhide\"><i class=\"fa fa-chevron-up\"></i></a>");
                    out.print("</div>");
                    out.print("<i class=\"pe-7s-folder\" style=\"font-size: 30px; vertical-align: bottom\"></i>");
                    out.print("&nbsp;&nbsp;"+lstModulos.get(i).getNombreModulo()+"&nbsp;&nbsp;");
                    out.print("<span class=\"badge badge-primary\">");
                    out.print(dFormulario.getFormulariosByModuloId(lstModulos.get(i).getModuloId()).size());
                    out.print("</span>");
                    out.print("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
                    out.print("<button class=\"btn btn-primary btn-sm\" id=\"btnModulo\" type=\"submit\" value=\""+ 
                            lstModulos.get(i).getModuloId() +"\" onclick=\"verFormularios(this);\">");
                    out.print("<i class=\"fa fa-search\"></i> Ver Todos");
                    out.print("</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
                    if(lstModulos.get(i).getFlagUso().equals("S")){
                        out.print("<div class=\"btn btn-success btn-sm\" disabled=\"true\">");
                        out.print("<i class=\"fa fa-download\"></i> Descargar Menú para trabajar");
                        out.print("</div>");
                        
                    }
                    else{
                        out.print("<button class=\"btn btn-success btn-sm\" data-toggle=\"modal\" data-target=\"#modalDescargar\""
                                + "type=\"button\" onclick=\"setValues(\'"+ lstModulos.get(i).getModuloId()+"\', \'trabajo\');\">");
                        out.print("<i class=\"fa fa-download\"></i> Descargar Menú para trabajar");
                        out.print("</button>");
                    }
                    
                    out.print("&nbsp;&nbsp;&nbsp;<button class=\"btn btn-info btn-sm\" data-toggle=\"modal\" data-target=\"#modalDescargar\""
                                + "type=\"button\" onclick=\"setValues(\'"+ lstModulos.get(i).getModuloId()+"\', \'consulta\');\">");
                    out.print("<i class=\"fa fa-download\"></i> Descargar Menú para consultar");
                    out.print("</button>");
                    out.print("</div>");
                    out.print("<div class= \"panel-body no-padding\">");
                    out.print("<ul class=\"list-group\">");
                    
                    lstSubmenus = dSubMenu.getSubMenuByModuloId(lstModulos.get(i).getModuloId());
                    for(int j=0; j<lstSubmenus.size(); j++){
                        lstFormularios = dFormulario.getFormulariosBySubmenuId(lstSubmenus.get(j).getSubmenuId());
                        if(lstFormularios != null)
                            cantForms=lstFormularios.size();
                        else
                            cantForms=0;
                        out.print("<li class=\"list-group-item\">");
                        out.print("<div class=\"row\">");
                        out.print("<div class=\"col-lg-10\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
                        out.print("<i class=\"pe-7s-folder\"></i>&nbsp;&nbsp;"+ lstSubmenus.get(j).getNombreSubmenu());
                        out.print("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
                        out.print("<button class=\"btn btn-outline btn-primary btn-xs\" id=\"btnSubmenu\" type=\"submit\" value=\""
                                + lstSubmenus.get(j).getSubmenuId() +"\" onclick=\"verFormularios(this);\">");
                        out.print("<i class=\"fa fa-search\"></i> Ver Todos");
                        out.print("</button> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
                        out.print("</div>");
                        out.print("<div class=\"col-lg-1\">");
                        out.print("<span class=\"badge badge-primary\">"+ cantForms +"</span>");
                        out.print("</div>");
                        out.print("</div>");
                        out.print("</li>");
                    } 
                   out.print("</ul>");
                   out.print("</div>");
                   out.print("</div>");
                   out.print("</div>");
                }
                %>
            </div>
            </form>
        </div>
    </body>
    
    <form action="/PaseAProduccionWeb/DownloadModulo" method="post">
        <input type="hidden" name="formularioId" id="formularioId"/>
        <input type="hidden" name="tipoDescarga" id="tipoDescarga"/>
        <div class="modal fade" id="modalDescargar" tabindex="-1" role="dialog" aria-hidden="true" style="display: none;">
            <div class="modal-dialog modal-sm">
                <div class="modal-content">
                    <div class="color-line"></div>
                    <div class="modal-header">
                        <h4 class="modal-title">Descarga</h4>
                    </div>
                    <div class="modal-body">
                        <p>¿Esta usted seguro que desea <strong>DESCARGAR</strong> el elemento seleccionado?</p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">NO</button>
                        <button type="button" class="btn btn-primary" onclick="descargar();">Si</button>
                    </div>
                </div>
            </div>
        </div>
    </form>
    
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
        
        function setValues(formularioId, tipoDescarga){
            $('input[name=formularioId]').val(formularioId.toString());
            $('input[name=tipoDescarga]').val(tipoDescarga.toString());
        }
        
        function descargar()
        {
            $('#modalDescargar').hide();
            var formularioId = $('input[name=formularioId]').val();
            var tipoDescarga = $('input[name=tipoDescarga]').val();
            var sistemaId = $('input[name=sistemaId]').val();
            
            document.location.href = '/PaseAProduccionWeb/DownloadModulo?moduloId='+formularioId.toString() + '&tipoDescarga=' + tipoDescarga.toString()
                    +'&sistemaId='+ sistemaId.toString();
        }
        
        function verFormularios(menu){    
        
            if(menu.id == "btnModulo")
                $('input[name=tipoPadre]').val("modulo")
               
            else if(menu.id == "btnSubmenu")
                $('input[name=tipoPadre]').val("submenu")
            else
                $('input[name=tipoPadre]').val("")
            
            var x = $(menu).val();
            $('input[name=padreId]').val(x);
        }
    </script>
</html>

