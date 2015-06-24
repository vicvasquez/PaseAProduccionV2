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
                        <img src="images/logo_cis.gif" height="70" width="150"/>
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
                <form action="/PaseAProduccionWeb/Download" method="post">
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
                            out.print("<button class=\"btn btn-success btn-sm\" disabled=\"true\" type=\"submit\" value=\"" 
                                    + lstFormularios.get(i).getFormularioId() +"\" onclick=\"descargar(this);\">Descargar para trabajar</button>&nbsp;&nbsp;");
                        else
                            out.print("<button class=\"btn btn-success btn-sm\" type=\"submit\" value=\"" 
                                    + lstFormularios.get(i).getFormularioId() +"\" onclick=\"descargar(this);\">Descargar para trabajar</button>&nbsp;&nbsp;");
                        out.print("<button class=\"btn btn-info btn-sm\" type=\"submit\" value=\"" 
                                    + lstFormularios.get(i).getFormularioId() +"\" onclick=\"descargar(this);\">Descargar para consultar</button>");
                        out.print("</div>");
                        out.print("</div>");
                        out.print("</div>");
                        out.print("</div>");
                    }
                    %>
                </div>
                </form>
            </div>
        </div>
    </body>
    
    <div class="modal fade" id="myModal6" tabindex="-1" role="dialog" aria-hidden="true" style="display: none;">
                        <div class="modal-dialog modal-sm">
                            <div class="modal-content">
                                <div class="color-line"></div>
                                <div class="modal-header">
                                    <h4 class="modal-title"></h4>
                                    <small class="font-bold">Lorem Ipsum is simply dummy text.</small>
                                </div>
                                <div class="modal-body">
                                    <p><strong>Lorem Ipsum is simply dummy</strong> text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown
                                        printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting,
                                        remaining essentially unchanged.</p>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                    <button type="button" class="btn btn-primary">Save changes</button>
                                </div>
                            </div>
                        </div>
    </div>
    
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
        
        function setValues(formulario){
            var formId = formulario.value;
            
            $('.modal-header').text();
        }
    
        function descargar(formulario)
        {
            alert("Se descargara el formulario " + formulario.value);
        }
        
        function buscar(){
            
        }
    </script>
</html>