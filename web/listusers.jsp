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
                document.getElementById("Agregar").setAttribute("style","top: "+(tamano-60)+"px;left: "+(ancho-60)+"px;");
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
                    document.getElementById("Agregar").setAttribute("style","top: "+(tamano-60)+"px;left: "+(ancho-60)+"px;");
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
            function avisar(){
                    if(confirm('¿Está seguro?')){
                        return true;
                    }else{
                        return false;
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
                <div style="width: 100%; font-size: 20px; margin-bottom: 10px;background-color: rgba(240,240,240,0.98);height: 50px;display: table;min-width: 824px;">
                    <div style="display: table-cell; vertical-align: middle;text-align: center;">Usuarios</div>
                </div>
                <c:forEach items="${requestScope.listausr}" var="usuario">
                    <div id="TituloG" style="width: 80%;">
                        ${usuario.titulo}
                    </div>
                    <div id="List" style="margin-bottom: 5px;">
                        <div style="position: relative;display: table;width: 80%;font-size: 14px;margin: 0 auto;">
                            <div style="display: table-row;width: 100%;height: 20px;">
                                <div class="Cell Head" style="width: 5%;">
                                    ID
                                </div>
                                <div class="Cell Head" style="width: 75%;">
                                    Nombre 
                                </div>
                                <div class="Cell Head" style="width: 5%;">
                                </div>
                                <div class="Cell Head" style="width: 15%;">
                                    Tipo
                                </div>
                            </div>
                            <c:forEach items="${usuario.registros}" var="registro">
                                <div class="RowOpc"style="display: table-row;width: 100%;height: 20px;">
                                    <div class="Cell" style="width: 5%;">
                                        <form method="POST" action="./User">
                                            <input type="hidden" name="id" value="${registro.id}"/>
                                            <input type="hidden" value="editar" name="tipo"/>
                                            <input type="submit" value="${registro.id}" style="width: 100%;cursor: pointer;background-color: transparent;border: none;font-size: 15px;"/>
                                        </form>
                                    </div>
                                    <div class="Cell" style="width: 75%;">
                                        <form method="POST" action="./User">
                                            <input type="hidden" name="id" value="${registro.id}"/>
                                            <input type="hidden" value="editar" name="tipo"/>
                                            <input type="submit" value="${registro.nombre} ${registro.apellido}" style="width: 100%;cursor: pointer;background-color: transparent;border: none;font-size: 15px;"/>
                                        </form>
                                    </div>
                                    <div class="Cell" style="width: 5%;">
                                        <form method="POST" onsubmit="return avisar();" action="./BorraUsuario">
                                            <input type="hidden" name="id" value="${registro.id}"/>
                                            <input type="hidden" name="tipo" value="${registro.tipo}"/>
                                            <input type="image" src="./IMG/Borrar.png" style="height: 25px;"/>
                                        </form>
                                    </div>
                                    <div class="Cell" style="width: 15%;">
                                        <c:choose>
                                            <c:when test="${registro.tipo==0}">
                                                Administrador
                                            </c:when>
                                            <c:when test="${registro.tipo==1}">
                                                Ventas
                                            </c:when>
                                            <c:when test="${registro.tipo==2}">
                                                Almacen
                                            </c:when>
                                            <c:when test="${registro.tipo==3}">
                                                Capturista
                                            </c:when>
                                            <c:when test="${registro.tipo==4}">
                                                Aprobador
                                            </c:when>
                                        </c:choose>
                                        
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <div id="Agregar" class="OpFlo">
                <div style="position: absolute;top: -10px;left: -3px;color: white;">
                    <form method="POST" action="./User">
                        <input type="submit" style="background-color: transparent;border: none;color: white;font-size: 50px;cursor: pointer;"  value="+"/>
                        <input type="hidden" value="crear" name="tipo"/>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
