<%-- 
    Document   : mostrarFormularios
    Created on : 17/06/2015, 12:49:57 PM
    Author     : vvasquez
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

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
                            Seleccione el elemento deseado
                        </h2>
                        <small>Estos son los formularios y reportes</small>
                        <br><br><br>
                        <ol class="hbreadcrumb breadcrumb">
                            <li><a href="mostrarEntornos.jsp" style="font-weight: bold">Entorno</a></li>
                            <li><a href="mostrarModulos.jsp" style="font-weight: bold">Módulos</a></li>
                            <li><label style="color: green">Formularios</label></li>
                        </ol>
                    </div>
                </div>
            </div>
            <div class="content animate-panel">    
                <div class="row">
                    <form class="form-horizontal">
                        <div class="form-group"><label class="col-sm-3 control-label">BUSCAR</label>
                        <div class="col-sm-4"><input type="text" class="form-control"></div>
                        <button class="btn btn-primary" type="button" onclick="buscar(this);"><i class="fa fa-search"></i></button>
                        <div></div>
                    </div>
                    </form>
                </div>
                <div class="row">
                    <c:forEach var="i" begin="1" end="99">
                        <button type="button" class="btn btn-default btn-lg col-sm-2" style="padding: 15px; margin: 20px;" 
                                value="<c:out value="${i}"/>" onclick="descargar(this)">
                            <i class="fa fa-download"></i>
                            Formulario <c:out value="${i}"/>
                        </button>
                    </c:forEach>
                </div>
            </div>
        </div>
    </body>
    
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
        
        function descargar(formulario)
        {
            alert("Se descargara el formulario " + formulario.value);
        }
        
        function buscar()
        {
            window.location = "mostrarFormularios.jsp";
        }
        
    </script>
</html>