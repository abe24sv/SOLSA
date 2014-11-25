<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<html>
    <head>
        <title>E-SOLSA</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        
        <link href="./STL/SOLSA.css" rel='stylesheet' type='text/css'/>
        
        <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'/>
        <link href='http://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'/>
        <c:choose>
            <c:when test="${sessionScope.mainsearch}">
            <script>
        window.onload=
        window.onresize=function(){
                var tamano=window.innerHeight;
                var ancho=window.innerWidth;
                var bar=Math.floor((ancho-200)*0.5);
                document.getElementById("HSearch").setAttribute("style","width:"+(ancho-200)+"px;");
                document.getElementById("SearchCon").setAttribute("style","width:"+(bar)+"px;");
                document.getElementById("SBar").setAttribute("style","width:"+(bar-50)+"px;");
                document.getElementById("NavBar").setAttribute("style","height:"+(tamano-80)+"px;");
                document.getElementById("Content").setAttribute("style","width:"+(ancho-220)+"px;"); 
                document.getElementById("Modificar").setAttribute("style","top: "+(tamano-60)+"px;left: "+(ancho-60)+"px;");
            };
            </script>
            </c:when>
            <c:otherwise>
            <script>
            window.onload=
            window.onresize=function(){
                    var tamano=window.innerHeight;
                    var ancho=window.innerWidth;
                    var bar=Math.floor((ancho-200)*0.5);
                    document.getElementById("NavBar").setAttribute("style","height:"+(tamano-80)+"px;");
                    document.getElementById("Content").setAttribute("style","width:"+(ancho-220)+"px;");
                    document.getElementById("Modificar").setAttribute("style","top: "+(tamano-60)+"px;left: "+(ancho-60)+"px;");
            };
            </script>
            </c:otherwise>
        </c:choose>
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
            function displayClients(){
                var tipo=document.getElementById("tipo").value;
                var clientes=document.getElementById("clientes");
                if(tipo==3 || tipo==4){
                    clientes.setAttribute("style","");
                }else{
                    clientes.setAttribute("style","display: none;");
                }
            }
        </script>
    </head>
    <body style="margin: 0px;">
        <div id="Header" class="Header">
            <a href="./main.jsp">
                <div id="Hlogo" class="Header Container">
                    <div id="Logo">
                        <img src="./IMG/SOLSA.png" style="height: 80px;"/>
                    </div>
                </div>
            </a>
            <c:if test="${sessionScope.mainsearch}">
                <div id="HSearch" class="Header Container">
                    <div id="Search">
                        <div id="SearchCon">
                            <form method="POST" action="${sessionScope.searchurl}">
                                <input id="SBar" class="Search Bar" type="text" name="busca" placeholder="${sessionScope.searchtip}"/>
                                <input type="image" src="./IMG/Search.png" style="height: 25px;position: relative;top: 3px;"/>
                            </form>
                        </div>
                        <a href="${sessionScope.advsearchurl}" style="font-size: 12px;">B&uacute;squeda Avanzada</a>
                    </div>
                </div>
            </c:if>
            <c:if test="${sessionScope.canbuy}">
                <a href="./Carrito">
                    <div id="HCar" class="Header Container">
                        <div id="Car">
                            <img src="./IMG/Cart.png" style="height: 40px;"/>
                            <div id="costo" style="color: black;">
                            &dollar; 0.00
                            </div>
                        </div>
                    </div>
                </a>
            </c:if>
        </div>
        <div id="Middle">
            <div id="NavBar">      
                <div class="Opcion" style="height: 70px;">
                    <a href="./User">
                    <div style="height: 40px;display: table;">
                        <div style="display: table-cell;width: 50px;vertical-align: middle;">
                            <c:choose>
                                <c:when test="${sessionScope.cliente==null}">
                                    <img src="./IMG/SOLSA.png" style="width: 50px;"/>
                                </c:when>
                                <c:otherwise>
                                    <img src="${sessionScope.cliente.logo}" style="width: 50px;"/>
                                </c:otherwise>
                            </c:choose>
                        </div>
                        <div style="display: table-cell;position: relative;width: 150px;vertical-align: middle;text-align: center;color: black;">
                            ${sessionScope.user.nombre}
                        </div>
                    </div>
                    </a>
                    <div style="text-align: center;margin-top: 2px;">
                        <form method="POST" action="./Logout">
                            <input id="ButOut" type="submit" value="Cerrar Sesi&oacute;n" style="font-size: 13px;width: 100%;height: 25px;background-color: orange;border: none;border-radius: 5px;box-shadow: 0px 0px 5px rgb(111,111,111);"/>
                        </form>
                    </div>
                </div>
                
                <c:forEach items="${sessionScope.interfazlat}" var="opcion">
                    
                <div class="Opcion Multiple" onclick="ampliarOpcion('${opcion.titulo}')">
                    <div style="width: 20px;float: left;">
                        <img id="opc${opcion.titulo}" src="./IMG/Mostrar.png" style="width: 10px;"/>
                    </div>
                    <div style="width: 165px;float: left;margin-left: 5px;">
                        ${opcion.titulo}
                    </div>
                </div>
                <div id="${opcion.titulo}" style="display: none;">
                <c:if test="${opcion.lateral.searchbar}">
                    <div id="BuscOpc" class="Opcion">
                        <form method="POST" action="${opcion.lateral.searchurl}">
                            <input id="BuscOpcBar" type="text" style="" name="busca" placeholder="B&uacute;squeda"/>
                            <input type="image" src="./IMG/Search.png" style="height: 20px;position: relative;top: 0px;"/>
                        </form>
                    </div>
                    <div style="text-align: center;">
                     <a href="${opcion.lateral.advsearchurl}" style="font-size: 12px;">B&uacute;squeda Avanzada</a>
                    </div>
                </c:if>
                <c:forEach items="${opcion.lateral.opciones}" var="subopt">
                    <a id="SubOpt" href="${subopt.url}">
                        <div class="Opcion Sub">
                            <span>${subopt.texto}</span>
                        </div>
                    </a>
                </c:forEach>
                </div>
                    
                </c:forEach>
                
            </div>
            <div id="Content">
                <c:choose>
                    <c:when test="${requestScope.user==null}">
                        <div style="width: 100%; font-size: 20px; margin-bottom: 10px;background-color: rgba(240,240,240,0.98);height: 50px;display: table;min-width: 824px;">
                            <div style="display: table-cell; vertical-align: middle;text-align: center;">Nuevo Usuario</div>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div style="width: 100%; font-size: 20px; margin-bottom: 10px;background-color: rgba(240,240,240,0.98);height: 50px;display: table;min-width: 824px;">
                            <div style="display: table-cell; vertical-align: middle;text-align: center;">Editar Usuario</div>
                        </div>
                    </c:otherwise>
                </c:choose>
            <div id="List">
                <div style=" width: 600px;height: 450px;margin: 0 auto;padding: 5px;">
                    <div style="margin: 0 auto;width: 80%;text-align: center">
                        <c:choose>
                            <c:when test="${requestScope.user==null}">
                            <form method="POST" action="./CreateUser">
                                Nombre
                                <input type="text" class="Input" placeholder="Nombre" name="name" style="width: 100%;"/>
                                Apellido
                                <input type="text" class="Input" placeholder="Apellido" name="apellido" style="width: 100%;"/>
                                Username
                                <input type="text" class="Input" placeholder="Username" name="username" style="width: 100%;"/>
                                Password
                                <input type="password" class="Input" placeholder="Contrase&ntilde;a" name="password" style="width: 100%;"/>
                                <input type="password" class="Input" placeholder="Confirmar Contrase&ntilde;a" name="cpassword" style="width: 100%;"/>

                                <div id="Modificar" class="OpFlo">
                                    <div style="position: absolute;top: -10px;left: -2px;color: white;">
                                        <input type="submit" style="background-color: transparent;border: none;color: white;font-size: 50px;cursor: pointer;"  value=">"/>
                                    </div>
                                </div>
                                <c:if test="${sessionScope.user.tipo==0}">
                                Tipo de Usuario
                                <select id="tipo" class="Input" style="width: 100%;" name="tipo" onchange="displayClients();">
                                    <option value="0">Administrador</option>
                                    <option value="1">Ventas</option>
                                    <option value="2">Almac&eacute;n</option>
                                    <option value="3">Capturista</option>
                                    <option value="4">Aprobador</option>
                                </select>
                                </c:if>
                                <c:if test="${sessionScope.user.tipo==0}">
                                <div id="clientes" style="display: none;">
                                    Cliente
                                    <select class="Input" style="width: 100%;" name="cliente">
                                        <c:choose>
                                            <c:when test="${requestScope.clientes==null}">
                                                <option value="-1">Error Al Cargar Clientes</option>
                                            </c:when>

                                            <c:otherwise>
                                                <c:forEach items="${requestScope.clientes}" var="cliente">
                                                    <option value="${cliente.id}">${cliente.nombre}</option>
                                                </c:forEach>
                                            </c:otherwise>   
                                        </c:choose>
                                    </select>
                                </div>
                                </c:if>
                            </form>
                            </c:when>
                            <c:otherwise>
                                <form method="POST" action="./EditUser">
                                Nombre
                                <input type="text" ${requestScope.disable} class="Input" placeholder="Nombre" name="name" style="width: 100%;" value="${requestScope.user.nombre}"/>
                                Apellido
                                <input type="text" ${requestScope.disable} class="Input" placeholder="Apellido" name="apellido" style="width: 100%;" value="${requestScope.user.apellido}"/>
                                Username
                                <input type="text" ${requestScope.disable} class="Input" placeholder=" Nuevo Username" name="username" style="width: 100%;"/>
                                Password
                                <input type="password" ${requestScope.disable} class="Input" placeholder="Nuevo Contrase&ntilde;a" name="password" style="width: 100%;"/>
                                <input type="password" ${requestScope.disable} class="Input" placeholder="Confirmar Contrase&ntilde;a" name="cpassword" style="width: 100%;"/>

                                <div id="Modificar" class="OpFlo">
                                    <div style="position: absolute;top: -10px;left: -2px;color: white;">
                                        <input type="hidden" name="id" value="${requestScope.user.id}"/>
                                        <input ${requestScope.disable} type="submit" style="background-color: transparent;border: none;color: white;font-size: 50px;cursor: pointer;"  value=">"/>
                                    </div>
                                </div>
                            </form>  
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
        </div>
        </div>
    </body>
</html>
