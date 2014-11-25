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
        </script>
        
        <script>
            

            function addField(){
            var productid = [];
            var products = [];
                <c:if test="${requestScope.modificacion == 1}">
                    <c:forEach items="${requestScope.productos}" var="i">
                        productid.push('${i.id}');
                        products.push('${i.nombre}');
                    </c:forEach>
                </c:if>
                var newdiv = document.createElement('div');
                var d;
                for(var i=0;i<productid.length;i++){
                    d+= "<option value='" + productid[i] + "'>" + products[i] + "</option>";
                }
                newdiv.innerHTML ="<input type='hidden' name='id[]' value='0'/>"+ "<select name='producto[]'><option value='0'></option>" + d + "</select>" + "Cantidad:<input type='text' name='cantidad[]' value='0'/><br/>";
                document.getElementById("ModificarVenta").appendChild(newdiv);
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
                                <input name="busqueda" id="SBar" class="Search Bar" type="text" placeholder="${sessionScope.searchtip}"/>
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
                            <input id="BuscOpcBar" type="text" style="" placeholder="B&uacute;squeda"/>
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
                <div id="TituloG">
                    
                </div>
                <div id="List">
                    
                    <c:if test="${requestScope.modificacion == 1}">
                        
                        <form action="./modificarSal" method="POST">
                            <input type="hidden" name="mod" value="${requestScope.venta.id}"/>
                            <c:forEach var="j" items="${requestScope.venta.productos}">
                            <input type="hidden" name="id[]" value="${j.id}"/>
                            <select name="producto[]">
                                <option value="0">
                                    
                                </option>
                                <c:forEach items="${requestScope.productos}" var="i">
                                    <c:set var="contains" value="false" />
                                          <c:if test="${j.id eq i.id}">
                                            <c:set var="contains" value="true" />
                                          </c:if>
                                    <c:choose>
                                        <c:when test="${contains}">
                                            <option selected="selected" value="${i.id}">
                                                ${i.nombre}
                                            </option>
                                            <c:set var="contains" value="false" />
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${i.id}">
                                                ${i.nombre}
                                            </option>
                                        </c:otherwise>
                                    </c:choose>
                                        
                                </c:forEach>
                            </select>
                            Cantidad:<input type="text" name="cantidad[]" value="${j.cantidad}"/><br/>
                            </c:forEach>
                            <div id="ModificarVenta"></div>
                            <a href="javascript:" id="agregarCampo" onclick="addField();">Agregar</a><br/>
                            <input type="submit" value="Modificar"/>
                        </form>
                    </c:if>
                    <c:if test="${requestScope.busquedaAvanzada == 1}">
                        <form action="./searchAdvSales" method="POST">
                            Cliente:<input type="text" name="bcliente"/><br/>
                            Usuario:<input type="text" name="busuario"/><br/>
                            Vendedor:<input type="text" name="bvendedor"/><br/>
                            Producto:<input type="text" name="bproducto"/><br/>
                            <input type="radio" checked="checked" name="type" value="0"/>OR
                            <input type="radio" name="type" value="1"/>AND
                            <input type="submit" value="Buscar"/>
                        </form>
                    </c:if>
                    <c:if test="${requestScope.busquedaAvanzada == 2}">
                        <form action="./searchAdvOrd" method="POST">
                            Cliente:<input type="text" name="bcliente"/><br/>
                            Usuario:<input type="text" name="busuario"/><br/>
                            Vendedor:<input type="text" name="bvendedor"/><br/>
                            Producto:<input type="text" name="bproducto"/><br/>
                            <input type="radio" checked="checked" name="type" value="0"/>OR
                            <input type="radio" name="type" value="1"/>AND
                            <input type="submit" value="Buscar"/>
                        </form>
                    </c:if>
                    <c:if test="${requestScope.realizoConsulta == 1}">
                        <div style="position: relative;display: table;width: 98%;margin: 1%;font-size: 14px;">
                        <div style="display: table-row;width: 100%;height: 20px;">
                            <div class="Cell Head" style="width: 5%;">
                                ID
                            </div>
                            <div class="Cell Head" style="width: 10%;">
                               Estado
                            </div>
                            <div class="Cell Head" style="width: 20%;">
                                Fecha de inicio
                            </div>
                            <div class="Cell Head" style="width: 20%;">
                                Fecha de entrega
                            </div>
                            <div class="Cell Head" style="width: 15%;">
                                Cliente
                            </div>
                            <div class="Cell Head" style="width: 15%;">
                                Usuario
                            </div>
                            <div class="Cell Head" style="width: 15%;">
                                Vendedor
                            </div>
                            <div class="Cell Head" style="width: 20%;">
                                Productos
                            </div>
                            <c:choose>
                                <c:when test="${requestScope.noEdit == 1}"></c:when>
                                        <c:otherwise>
                                            <div class="Cell Head" style="width: 10%;">
                                                Opciones
                                            </div>
                                        </c:otherwise>
                            </c:choose>
                        </div>
                            
                           <c:forEach items="${requestScope.listaVentas}" var="i">
                               <div class="RowOpc"style="display: table-row;width: 100%;height: 20px;">
                            <div class="Cell" style="width: 5%;">
                                ${i.id}
                            </div>
                            <div class="Cell" style="width: 10%;">
                                <c:choose>
                                            <c:when test="${i.estado == 0}">Por Aprobar</c:when>
                                            <c:when test="${i.estado == 1}">Aprobada</c:when>
                                            <c:when test="${i.estado == 2}">Rechazada</c:when>
                                            <c:when test="${i.estado == 3}">Procesada</c:when>
                                            <c:when test="${i.estado == 4}">Cancelada</c:when>
                                            <c:when test="${i.estado == 5}">Enviada</c:when>
                                            <c:when test="${i.estado == 6}">Recibida</c:when>
                                            <c:otherwise>Desconocido</c:otherwise>
                                        </c:choose>
                            </div>
                            <div class="Cell" style="width: 20%;">
                                ${i.fecha_inicio}
                            </div>
                            <div class="Cell" style="width: 20%;">
                                ${i.fecha_entrega}
                            </div>
                            <div class="Cell" style="width: 15%;">
                                ${i.cliente.nombre}
                            </div>
                            <div class="Cell Head" style="width: 15%;">
                                ${i.usuario.nombre}
                            </div>
                            <div class="Cell Head" style="width: 15%;">
                                ${i.vendedor.nombre}
                            </div>
                            <div class="Cell Head" style="width: 20%;">
                                <c:forEach items="${i.productos}" var="j">
                                        #${j.cantidad} - ${j.nombre}<br/>
                                    </c:forEach>
                            </div>
                            
                                                                    <c:choose>
                                        <c:when test="${requestScope.noEdit == 1}">
                                        </c:when>
                                        <c:otherwise>
                                            <div class="Cell Head" style="width: 10%;">
                                                <form action="./aproSal" method="POST">
                                                    <input type="hidden" name="id" value="${i.id}"/>
                                                    <a href="javascript:" onclick="parentNode.submit();">Procesar</a>
                                                </form>
                                                <form action="./cancelSal" method="POST">
                                                    <input type="hidden" name="id" value="${i.id}"/>
                                                    <a href="javascript:" onclick="parentNode.submit();">Cancelar</a>
                                                </form>
                                                <form action="./modSal" method="POST">
                                                    <input type="hidden" name="id" value="${i.id}"/>
                                                    <a href="javascript:" onclick="parentNode.submit();">Modificar</a>
                                                </form>
                                            </div>
                                        </c:otherwise>
                                    </c:choose>
                                </div>                               
                           </c:forEach> 
                    </c:if>
                    <c:if test="${requestScope.verComprasApro == 1}">
                        <div style="position: relative;display: table;width: 98%;margin: 1%;font-size: 14px;">
                        <div style="display: table-row;width: 100%;height: 20px;">
                            <div class="Cell Head" style="width: 5%;">
                                ID
                            </div>
                            <div class="Cell Head" style="width: 10%;">
                               Estado
                            </div>
                            <div class="Cell Head" style="width: 20%;">
                                Fecha de inicio
                            </div>
                            <div class="Cell Head" style="width: 20%;">
                                Fecha de entrega
                            </div>
                            <div class="Cell Head" style="width: 15%;">
                                Cliente
                            </div>
                            <div class="Cell Head" style="width: 15%;">
                                Usuario
                            </div>
                            <div class="Cell Head" style="width: 20%;">
                                Productos
                            </div>
                            <c:choose>
                                <c:when test="${requestScope.noEdit == 1}"></c:when>
                                        <c:otherwise>
                                            <div class="Cell Head" style="width: 10%;">
                                                Opciones
                                            </div>
                                        </c:otherwise>
                            </c:choose>
                        </div>
                            
                           <c:forEach items="${requestScope.comprasAprobador}" var="i">
                               <div class="RowOpc"style="display: table-row;width: 100%;height: 20px;">
                            <div class="Cell" style="width: 5%;">
                                ${i.id}
                            </div>
                            <div class="Cell" style="width: 10%;">
                                <c:choose>
                                            <c:when test="${i.estado == 0}">Por Aprobar</c:when>
                                            <c:when test="${i.estado == 1}">Aprobada</c:when>
                                            <c:when test="${i.estado == 2}">Rechazada</c:when>
                                            <c:when test="${i.estado == 3}">Procesada</c:when>
                                            <c:when test="${i.estado == 4}">Cancelada</c:when>
                                            <c:when test="${i.estado == 5}">Enviada</c:when>
                                            <c:when test="${i.estado == 6}">Recibida</c:when>
                                            <c:otherwise>Desconocido</c:otherwise>
                                        </c:choose>
                            </div>
                            <div class="Cell" style="width: 20%;">
                                ${i.fecha_inicio}
                            </div>
                            <div class="Cell" style="width: 20%;">
                                ${i.fecha_entrega}
                            </div>
                            <div class="Cell" style="width: 15%;">
                                ${i.cliente.nombre}
                            </div>
                            <div class="Cell Head" style="width: 15%;">
                                ${i.usuario.nombre}
                            </div>
                            <div class="Cell Head" style="width: 20%;">
                                <c:forEach items="${i.productos}" var="j">
                                        #${j.cantidad} - ${j.nombre}<br/>
                                    </c:forEach>
                            </div>
                            
                            <c:choose>
                                        <c:when test="${requestScope.noEdit == 1}">
                                        </c:when>
                                <c:when test="${requestScope.receive == 1}">
                                    <div class="Cell Head" style="width: 10%;">
                                                <form action="./recibirSal" method="POST">
                                                    <input type="hidden" name="id" value="${i.id}"/>
                                                    <a href="javascript:" onclick="parentNode.submit();">Recibido</a>
                                                </form>
                                    </div>
                                </c:when>
                                        <c:otherwise>
                                            <div class="Cell Head" style="width: 10%;">
                                                <form action="./aprobarSal" method="POST">
                                                    <input type="hidden" name="id" value="${i.id}"/>
                                                    <a href="javascript:" onclick="parentNode.submit();">Aprobar</a>
                                                </form>
                                                <form action="./rechazarSal" method="POST">
                                                    <input type="hidden" name="id" value="${i.id}"/>
                                                    <a href="javascript:" onclick="parentNode.submit();">Rechazar</a>
                                                </form>
                                            </div>
                                        </c:otherwise>
                                    </c:choose>
                                </div>                               
                           </c:forEach> 
                    </c:if>
                    <c:if test="${requestScope.verComprasAlm == 1}">
                        <div style="position: relative;display: table;width: 98%;margin: 1%;font-size: 14px;">
                        <div style="display: table-row;width: 100%;height: 20px;">
                            <div class="Cell Head" style="width: 5%;">
                                ID
                            </div>
                            <div class="Cell Head" style="width: 10%;">
                               Estado
                            </div>
                            <div class="Cell Head" style="width: 20%;">
                                Fecha de inicio
                            </div>
                            <div class="Cell Head" style="width: 20%;">
                                Fecha de entrega
                            </div>
                            <div class="Cell Head" style="width: 15%;">
                                Cliente
                            </div>
                            <div class="Cell Head" style="width: 15%;">
                                Usuario
                            </div>
                            <div class="Cell Head" style="width: 20%;">
                                Productos
                            </div>
                            <c:choose>
                                <c:when test="${requestScope.noEdit == 1}"></c:when>
                                        <c:otherwise>
                                            <div class="Cell Head" style="width: 10%;">
                                                Opciones
                                            </div>
                                        </c:otherwise>
                            </c:choose>
                        </div>
                            
                           <c:forEach items="${requestScope.comprasAlm}" var="i">
                               <div class="RowOpc"style="display: table-row;width: 100%;height: 20px;">
                            <div class="Cell" style="width: 5%;">
                                ${i.id}
                            </div>
                            <div class="Cell" style="width: 10%;">
                                <c:choose>
                                            <c:when test="${i.estado == 0}">Por Aprobar</c:when>
                                            <c:when test="${i.estado == 1}">Aprobada</c:when>
                                            <c:when test="${i.estado == 2}">Rechazada</c:when>
                                            <c:when test="${i.estado == 3}">Procesada</c:when>
                                            <c:when test="${i.estado == 4}">Cancelada</c:when>
                                            <c:when test="${i.estado == 5}">Enviada</c:when>
                                            <c:when test="${i.estado == 6}">Recibida</c:when>
                                            <c:otherwise>Desconocido</c:otherwise>
                                        </c:choose>
                            </div>
                            <div class="Cell" style="width: 20%;">
                                ${i.fecha_inicio}
                            </div>
                            <div class="Cell" style="width: 20%;">
                                ${i.fecha_entrega}
                            </div>
                            <div class="Cell" style="width: 15%;">
                                ${i.cliente.nombre}
                            </div>
                            <div class="Cell Head" style="width: 15%;">
                                ${i.usuario.nombre}
                            </div>
                            <div class="Cell Head" style="width: 20%;">
                                <c:forEach items="${i.productos}" var="j">
                                        #${j.cantidad} - ${j.nombre}<br/>
                                    </c:forEach>
                            </div>
                            
                            <c:choose>
                                        <c:when test="${requestScope.noEdit == 1}">
                                        </c:when>
                                        <c:otherwise>
                                            <div class="Cell Head" style="width: 10%;">
                                                <form action="./enviarSalAlm" method="POST">
                                                    <input type="hidden" name="id" value="${i.id}"/>
                                                    <a href="javascript:" onclick="parentNode.submit();">Enviar</a>
                                                </form>
                                                <form action="./cancelSalAlm" method="POST">
                                                    <input type="hidden" name="id" value="${i.id}"/>
                                                    <a href="javascript:" onclick="parentNode.submit();">Cancelar</a>
                                                </form>
                                            </div>
                                        </c:otherwise>
                                    </c:choose>
                                </div>                               
                           </c:forEach> 
                    </c:if>
                </div>
            </div>
        </div>
    </body>
</html>
