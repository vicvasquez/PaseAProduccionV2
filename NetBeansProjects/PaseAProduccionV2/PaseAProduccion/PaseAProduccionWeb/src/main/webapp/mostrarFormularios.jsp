<%-- 
    Document   : mostrarFormularios
    Created on : 17/06/2015, 12:49:57 PM
    Author     : vvasquez
--%>

<%@page import="com.cis.paseaproduccionweb.dao.UsuariosDao"%>
<%@page import="java.util.List"%>
<%@page import="com.cis.paseaproduccionweb.hibernate.PpFormularios"%>
<%@page import="com.cis.paseaproduccionweb.dao.FormulariosDao"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="com.cis.paseaproduccionweb.hibernate.PpUsuarios"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    PpUsuarios usuario = (PpUsuarios)request.getSession().getAttribute("user");
    if(usuario==null)
        response.sendRedirect("login.jsp");
    
    String tipoPadre = request.getParameter("tipoPadre");
    BigDecimal padreId = new BigDecimal(request.getParameter("padreId"));
    String sistemaId = request.getParameter("sistemaId");
    String filtro = request.getParameter("filtro");
    List<PpFormularios> lstFormularios = null;
    
    FormulariosDao dFormularios = new FormulariosDao();
    UsuariosDao dUsuario = new UsuariosDao();
    
    if(tipoPadre.equals("modulo"))
        lstFormularios = dFormularios.getFormulariosByModuloId(padreId);
    else if(tipoPadre.equals("submenu"))
        lstFormularios = dFormularios.getFormulariosBySubmenuId(padreId);
    else
        lstFormularios = null;
    
    if(filtro!=null)
        lstFormularios = dFormularios.filtrarFormularios(lstFormularios, filtro);
    
    PpFormularios frm = null;
        
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
     <link rel="stylesheet" href="vendor/datatables_plugins/integration/bootstrap/3/dataTables.bootstrap.css">

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
                        <a href="#"> <span class="nav-label">Aprobacion</span> </a>
                    </li>
                </ul>
            </div>
        </aside>
        <div id="wrapper">
            <div class="normalheader transition animated fadeIn">
                <div class="hpanel">
                    <div class="panel-body">
                        <h2 class="font-light m-b-xs">
                            Seleccione el elemento deseado
                        </h2>
                        <small>Estos son los formularios y reportes</small>
                        <br><br><br>
                        <ol class="hbreadcrumb breadcrumb">
                            <li><a href="mostrarEntornos.jsp" style="font-weight: bold">Entorno</a></li>
                            <li><a href="Modulos?sistemaId=<% out.print(sistemaId); %>" style="font-weight: bold">Módulos</a></li>
                            <li><label style="color: green">Formularios</label></li>
                        </ol>
                    </div>
                </div>
            </div>
            <div class="content animate-panel">    
                <div class="row">
                    <form class="form-horizontal" action="/PaseAProduccionWeb/Formularios" method="post">
                        <div class="form-group">
                            <label class="col-sm-3 control-label">BUSCAR</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" name="filtro" id="filtro">
                            </div>
                            <button class="btn btn-primary" type="submit" onclick="buscar();">
                                <i class="fa fa-search"></i>
                            </button>
                        </div>
                        <input type="hidden" value="<% out.print(tipoPadre); %>" name="tipoPadre"/>
                        <input type="hidden" value="<% out.print(padreId); %>" name="padreId"/>
                        <input type="hidden" value="<% out.print(sistemaId); %>" name="sistemaId"/>
                    </form>
                </div>
                    <input type="hidden" name="formulario" id="formulario"/>
                    <div class="row">
                    <%
                    for(int i=0; i<lstFormularios.size(); i++){
                        out.print("<div class=\"col-md-4 animated-panel zoomIn\" style=\"-webkit-animation: 0.1s;\">");
                        out.print("<div class=\"hpanel\">");
                        out.print("<div class=\"panel-body\">");
                        out.print("<div class=\"text-center\">");
                        out.print("<h2 class=\"m-b-xs\">"+ lstFormularios.get(i).getNombreFormulario() +"</h2>");
                        if(lstFormularios.get(i).getFlagUso().equals("S"))
                            out.print("<p class=\"font-bold text-danger\">EN USO POR "
                                    +  dUsuario.getUsuarioById(lstFormularios.get(i).getPpusuarioUsuarioId()).getNombre().toUpperCase()+"</p>");
                        else
                            out.print("<p class=\"font-bold text-success\">DISPONIBLE</p>");
                        out.print("<div class=\"m\"><i class=\"pe-7s-cloud-download fa-5x\"></i></div>");
                        out.print("<p class=\"small\">"+ lstFormularios.get(i).getDescFormulario() +"</p>");
                        if(lstFormularios.get(i).getFlagUso().equals("S"))
                            out.print("<div class=\"btn btn-success btn-sm\" disabled=\"true\">Descargar para trabajar</div>&nbsp;&nbsp;");
                        else
                            out.print("<button class=\"btn btn-success btn-sm\" data-toggle=\"modal\" data-target=\"#modalDescargar\" type=\"button\" "
                                    + "onclick=\"setValues(\'"+ lstFormularios.get(i).getFormularioId() +"\', \'trabajo\');\">Descargar para trabajar</button>&nbsp;&nbsp;");
                        out.print("<button class=\"btn btn-info btn-sm\" data-toggle=\"modal\" data-target=\"#modalDescargar\" type=\"button\" "
                                    + "onclick=\"setValues(\'"+ lstFormularios.get(i).getFormularioId() +"\', \'consulta\');\">Descargar para consultar</button>&nbsp;&nbsp;");
                        out.print("</div>");
                        out.print("</div>");
                        out.print("</div>");
                        out.print("</div>");
                    }
                    %>
                </div>
            </div>
        </div>
    </body>
    
    <form action="/PaseAProduccionWeb/Download" method="post">
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
    <script src="vendor/metisMenu/dist/metisMenu.min.js"></script>
    <script src="vendor/iCheck/icheck.min.js"></script>
    <script src="vendor/sparkline/index.js"></script>
    <script src="vendor/datatables/media/js/jquery.dataTables.min.js"></script>
    <script src="vendor/datatables_plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"></script>
    <script src="scripts/homer.js"></script>
    
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
            var tipoPadre = $('input[name=tipoPadre]').val();
            var padreId = $('input[name=padreId]').val();
            var sistemaId = $('input[name=sistemaId]').val();
            var tipoDescarga = $('input[name=tipoDescarga]').val();
            
            document.location.href = '/PaseAProduccionWeb/Download?formularioId='+formularioId.toString() + '&tipoDescarga=' + tipoDescarga.toString()
                    +'&tipoPadre=' + tipoPadre.toString() + '&padreId='+padreId.toString() +'&sistemaId='+sistemaId.toString();
        }
        
    </script>
</html>