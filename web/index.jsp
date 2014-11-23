<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<c:choose>
    <c:when test="${sessionScope.user==null}">
<html>
    <head>
        <title>Iniciar Sesi&oacute;n</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        
        <link href="./STL/SOLSA.css" rel='stylesheet' type='text/css'/>
        
        <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'/>
        <link href='http://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'/>
    
        <script>
        window.onload=
        window.onresize=function(){
                var tamano=window.innerHeight;
                var ancho=window.innerWidth;
                var bar=Math.floor((ancho-200)*0.5);
                document.getElementById("Log").setAttribute("style","height:"+(tamano-80)+"px;");
            };
        </script>
        <script>
            var container = [];
            function ampliarOpcion(opcion){
                if(container[opcion]==undefined){
                    container[opcion]=1;
                    document.getElementById(opcion).setAttribute("style","");
                    document.getElementById("opc"+opcion).setAttribute("src","./IMG/Ocultar.png");
                    document.getElementById("opc"+opcion).setAttribute("style","width:18px");
                }else{
                    if(container[opcion]==0){
                        container[opcion]=1;
                        document.getElementById(opcion).setAttribute("style","");
                        document.getElementById("opc"+opcion).setAttribute("src","./IMG/Ocultar.png");
                        document.getElementById("opc"+opcion).setAttribute("style","width:18px");
                    }else{
                        container[opcion]=0;
                        document.getElementById(opcion).setAttribute("style","display:none;");
                        document.getElementById("opc"+opcion).setAttribute("src","./IMG/Mostrar.png");
                        document.getElementById("opc"+opcion).setAttribute("style","width:10px");
                    }
                }
                
            }
        </script>
    </head>
    <body style="margin: 0px;">
        <div id="Header" class="Header">
            <a href="./index.jsp">
                <div id="Hlogo" class="Header Container">
                    <div id="Logo">
                        <img src="./IMG/SOLSA.png" style="height: 80px;"/>
                    </div>
                </div>
            </a>
        </div>
        <div id="Middle" style="display: table; width: 100%;">
            <div id="Log">
                <div style="width: 400px; height: 200px;background-color: rgb(240,240,240);margin: 0 auto;border-top:2px solid rgb(111,111,111);border-bottom:2px solid rgb(111,111,111);">
                    <div style="display: table; width: 100%">
                        <div style="display: table-cell;width: 400px;height: 200px;vertical-align: middle;width: 100% ">
                            <div style="margin: 0 auto;width: 80%;text-align: center;">
                                <form method="POST" action="./Login">
                                    <c:choose>
                                        <c:when test="${requestScope.username!=null}">
                                            <input type="text" placeholder="Username" id="Input" value="${requestScope.username}" name="username" style="width: 100%;"/>
                                        </c:when>
                                        <c:otherwise>
                                            <input type="text" placeholder="Username" id="Input" name="username" style="width: 100%;"/>
                                        </c:otherwise>
                                    
                                    </c:choose>
                                    <input type="password" placeholder="Password" id="Input" name="password" style="width: 100%;margin-bottom: 30px;"/>
                                    <input type="submit" value="Login" id="Button" style="width: 100px;"/>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
    </c:when>
    <c:otherwise>
        <c:redirect url="./main.jsp"/>
    </c:otherwise>
</c:choose>