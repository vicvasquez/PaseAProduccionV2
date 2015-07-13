<%-- 
    Document   : historial
    Created on : 13/07/2015, 11:21:37 AM
    Author     : vvasquez
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="com.cis.paseaproduccionweb.dao.FormulariosDao"%>
<%@page import="com.cis.paseaproduccionweb.dao.UsuariosDao"%>
<%@page import="java.util.List"%>
<%@page import="com.cis.paseaproduccionweb.dao.HistorialesDao"%>
<%@page import="com.cis.paseaproduccionweb.hibernate.PpHistoriales"%>
<%@page import="com.cis.paseaproduccionweb.hibernate.PpUsuarios"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    PpUsuarios usuario = (PpUsuarios)request.getSession().getAttribute("user");
    if(usuario==null)
        response.sendRedirect("login.jsp");
    
    HistorialesDao dHistorial = new HistorialesDao();
    List<PpHistoriales> lstHistorial = dHistorial.getHistorial();
    UsuariosDao dUsuario = new UsuariosDao();
    FormulariosDao dFormulario = new FormulariosDao();
        
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
                    <li>
                        <a href="index.jsp"> <span class="nav-label">Inicio</span>  </a>
                    </li>
                    <li>
                        <a href="mostrarEntornos.jsp"> <span class="nav-label">Reservar Formulario</span> </a>
                    </li>
                    <li>
                        <a href="perfil.jsp"> <span class="nav-label">Perfil</span> </a>
                    </li>
                    <li>
                        <a href="#"> <span class="nav-label">Mantenimiento</span> </a>
                    </li>
                    <li class="active">
                        <a href="historial.jsp"> <span class="nav-label">Historial</span> </a>
                    </li>
                </ul>
            </div>
        </aside>
        <div id="wrapper">
            <div class="normalheader transition animated fadeIn">
                <div class="hpanel">
                    <div class="panel-body">
                        <h2 class="font-light m-b-xs">
                            Historial
                        </h2>
                        <small>Se visualiza el historial de los formularios y reportes pasados a produccion</small>
                        <br><br><br>
                        <ol class="hbreadcrumb breadcrumb">
                            <li><label style="color: green">Historial</label></li>
                        </ol>
                    </div>
                </div>
            </div>
            <div class="content animate-panel">    
                <div class="row">
                    <form class="form-horizontal" action="/PaseAProduccionWeb/Formularios" method="post">
                        <div class="form-group">
                            <label class="col-sm-3 control-label">Nombre Formulario</label>
                            <div class="col-sm-3">
                                <input type="text" class="form-control" name="filtroNombre" id="filtroNombre">
                            </div>
                            <button class="btn btn-primary" type="submit">
                                <i class="fa fa-search"></i>
                            </button>
                            <div class="col-sm-2">
                                <input type="date" class="form-control" name="filtroFecha" id="filtroFecha">
                            </div>
                        </div>
                    </form>
                </div>
                <div class="row">
                    <% for(int i=0; i<lstHistorial.size(); i++){
                        String nombreFormulario = dFormulario.getFormularioByFormularioId(lstHistorial.get(i).getPpFormularios().getFormularioId()).getNombreFormulario();
                        String nombreFormularioAnterior = "";
                        if(i>0)
                            nombreFormularioAnterior = dFormulario.getFormularioByFormularioId(lstHistorial.get(i-1).getPpFormularios().getFormularioId()).getNombreFormulario();
                        if(i==0){
                            out.print("<div class=\"panel-group\" id=\"accordion\" role=\"tablist\" aria-multiselectable=\"true\">");
                            out.print("<div class=\"panel panel-default\">");
                            out.print("<div class=\"panel-heading\" role=\"tab\" id=\"headingOne\">");
                            out.print("<h4 class=\"panel-title\">");
                            out.print("<h4 class=\"panel-title\">");
                            out.print("<a data-toggle=\"collapse\" data-parent=\"#accordion\" href=\"#collapseOne\" aria-expanded=\"false\" aria-controls=\"collapseOne\" class=\"collapsed\">");
                            out.print(nombreFormulario);
                            out.print("</a>");
                            out.print("</h4>");
                            out.print("</div>");
                            out.print("<div id=\"collapseOne\" class=\"panel-collapse collapse\" role=\"tabpanel\" aria-labelledby=\"headingOne\" aria-expanded=\"false\" style=\"height: 0px;\">");
                            out.print("<div class=\"panel-body\">");
                        }
                        else{
                            if(!nombreFormulario.equals(nombreFormularioAnterior))
                            {
                                out.print("<div class=\"panel-group\" id=\"accordion\" role=\"tablist\" aria-multiselectable=\"true\">");
                                out.print("<div class=\"panel panel-default\">");
                                out.print("<div class=\"panel-heading\" role=\"tab\" id=\"headingOne\">");
                                out.print("<h4 class=\"panel-title\">");
                                out.print("<h4 class=\"panel-title\">");
                                out.print("<a data-toggle=\"collapse\" data-parent=\"#accordion\" href=\"#collapseOne\" aria-expanded=\"false\" aria-controls=\"collapseOne\" class=\"collapsed\">");
                                out.print(nombreFormulario);
                                out.print("</a>");
                                out.print("</h4>");
                                out.print("</div>");
                            }
                        }
                        if(i==0 || !nombreFormulario.equals(nombreFormularioAnterior)){
                    %>
                    <div class="hpanel">
                        <div class="panel-body">
                            <div class="table-responsive">
                                <table cellpadding="1" cellspacing="1" class="table table-bordered table-striped">
                                    <thead>
                                        <tr>
                                            <th style="text-align: center">Nombre</th>
                                            <th style="text-align: center">Ultimo usuario</th>
                                            <th style="text-align: center">Fecha</th>
                                            <th style="text-align: center">Version</th>
                                            <th style="text-align: center">Comentario</th>
                                            <th style="text-align: center">Motivo de Baja de servicios</th>
                                            <th style="text-align: center">Descargar</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    <% }
                                        DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy HH:mm:ss");
                                        out.print("<tr>");
                                        out.print("<td>"+ dFormulario.getFormularioByFormularioId(lstHistorial.get(i).getPpFormularios().getFormularioId()).getNombreFormulario()+"</td>");
                                        out.print("<td>"+ dUsuario.getUsuarioById(lstHistorial.get(i).getUsuarioId()).getNombre() +"</td>");
                                        out.print("<td>"+ dateFormat.format(lstHistorial.get(i).getFecha()) +"</td>");
                                        out.print("<td style=\"text-align: center\">"+ lstHistorial.get(i).getNroVersion() +"</td>");
                                        if(lstHistorial.get(i).getComentarioPase() == null)
                                            out.print("<td>No hay comentarios del Pase a Producción</td>");
                                        else
                                            out.print("<td>"+ lstHistorial.get(i).getComentarioPase()+"</td>");
                                        if(lstHistorial.get(i).getComentarioServicios() == null)
                                            out.print("<td>No se bajaron los servicios para este pase a producción</td>");
                                        else
                                            out.print("<td>"+ lstHistorial.get(i).getComentarioServicios()+"</td>");
                                        out.print("<td style=\"text-align: center\"><button class=\"btn btn-success\" data-toggle=\"modal\" data-target=\"#modalDescargar\" type=\"button\" "
                                                    + "onclick=\"setValues(\'"+ lstHistorial.get(i).getPpFormularios().getFormularioId() +"\', \'trabajo\');\"><i class=\"fa fa-download\"></i>&nbsp;&nbsp;<span class=\"bold\">Descargar</span></button></td>");
                                        out.print("</tr>");
                                        
                                        if(i>0 && !nombreFormulario.equals(nombreFormularioAnterior)){
                                    %>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>                 
                    <%
                    }}
                    out.print("</div>");
                    out.print("</div>");
                    out.print("</div>");
                    out.print("</div>");
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
            
            document.location.href = '/PaseAProduccionWeb/DownloadFormulario?formularioId='+formularioId.toString() + '&tipoDescarga=' + tipoDescarga.toString()
                    +'&tipoPadre=' + tipoPadre.toString() + '&padreId='+padreId.toString() +'&sistemaId='+sistemaId.toString();
        }
        
    </script>
</html>