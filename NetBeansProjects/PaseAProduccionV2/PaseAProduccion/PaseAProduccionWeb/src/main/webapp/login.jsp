<%-- 
    Document   : login
    Created on : 16/06/2015, 12:44:29 PM
    Author     : vvasquez
--%>

<%
    
    session.invalidate();

%>


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
        <link rel="stylesheet" href="fonts/pe-icon-7-stroke/css/pe-icon-7-stroke.css" />
        <link rel="stylesheet" href="fonts/pe-icon-7-stroke/css/helper.css" />
        <link rel="stylesheet" href="styles/style.css">
    </head>
    <body class="blank">
        <div class="splash"> 
            <div class="color-line"></div>
            <div class="splash-title">
                <h1>CIS - Cloud Information Solution</h1>
                <img src="images/loading-bars.svg" width="64" height="64" /> 
            </div> 
        </div>
        <div class="color-line"></div>
        <div class="login-container">
            <div class="row">
                <div class="col-md-12">
                    <div class="text-center m-b-md">
                        <h3>Pase a Producción - CIS</h3>
                    </div>
                    <div class="hpanel">
                        <div class="panel-body">
                            <form action="/PaseAProduccionWeb/Login" method="post" id="loginForm">
                                <div class="form-group">
                                    <label class="control-label" for="username">Ususario</label>
                                    <input type="text" placeholder="user" title="Por favor ingrese su usario" required="" value="" name="username" id="username" class="form-control">
                                </div>
                                <div class="form-group">
                                    <label class="control-label" for="password">Contraseña</label>
                                    <input type="password" title="Por favor ingrese su contraseña" placeholder="******" required="" value="" name="password" id="password" class="form-control">
                                </div>
                                    <div class="checkbox"> </div>
                                    <button class="btn btn-success btn-block">Ingresar</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12 text-center">
                <br/> 2015 Copyright Cloud Information Solution
            </div>
        </div>
    <script src="vendor/jquery/dist/jquery.min.js"></script>
    <script src="vendor/jquery-ui/jquery-ui.min.js"></script>
    <script src="vendor/slimScroll/jquery.slimscroll.min.js"></script>
    <script src="vendor/bootstrap/dist/js/bootstrap.min.js"></script>
    <script src="vendor/metisMenu/dist/metisMenu.min.js"></script>
    <script src="vendor/iCheck/icheck.min.js"></script>
    <script src="vendor/sparkline/index.js"></script>
    <script src="scripts/homer.js"></script>
    </body>
</html>
