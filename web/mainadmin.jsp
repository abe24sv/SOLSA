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
                                <input id="SBar" class="Search Bar" type="text" placeholder="${sessionScope.searchtip}"/>
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
                            <input name = "busca" id="BuscOpcBar" type="text" style="" placeholder="B&uacute;squeda"/>
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
                <c:choose>
                <c:when test="${requestScope.catalogos!=null}">
                    Cat&aacute;logos
                </c:when>
                </c:choose>
                <c:choose>
                <c:when test="${requestScope.clientes!=null}">
                   Clientes
                </c:when>
                </c:choose>
                <c:choose>
                <c:when test = "${requestScope.nota!=null}">
                  	
                <c:out value="${requestScope.nota}"/>
                  	
                </c:when>
                </c:choose>                
                </div>
            <div id="List">
                  
                  	<c:choose>
                  	<c:when test="${requestScope.catalogos!=null}">
                  	
  			<div style="position: relative;display: table;width: 98%;margin: 1%;font-size: 14px;">
                        	<div style="display: table-row;width: 100%;height: 20px;">
                        	<div class="Cell Head" style="width: 5%;">
                        		ID
                        	</div>
                        	<div class="Cell Head" style="width: 20%;">
                        		Nombre
                        	</div>
                        	<div class="Cell Head" style="width: 10%;">
                        		Descripcion
                        	</div>
                        	<div class="Cell Head" style="width: 50%;">
                        	</div>
                        </div>
 			<c:forEach items="${requestScope.catalogos}" var="dato">
 			<div class="RowOpc"style="display: table-row;width: 100%;height: 20px;">
                             <div class="Cell" style="width: 5%;">
                                 <a href="./modCat?id=${dato.id}" style="text-decoration: none;color: black;">
                                     <c:out value="${dato.id}"/>
                                 </a>
                             </div>
                             <div class="Cell" style="width: 20%;">
                                 <a href="./modCat?id=${dato.id}" style="text-decoration: none;color: black;">
                                     <c:out value="${dato.nombre}"/>
                                 </a>
                             </div>
                             <div class="Cell" style="width: 20%;">
			         <a href="./modCat?id=${dato.id}" style="text-decoration: none;color: black;">
			             <c:out value="${dato.descripcion}"/>
			         </a>
                             </div>
                             <div class="Cell" style="width: 5%;">
                                 <form method="get" action="./delCat">
				 	<input type="image" src="./IMG/Borrar.png" style="height: 25px;"/>
				        <input type="hidden" name="idCat" value="${dato.id}">
                                 </form>
                             </div>
                         </div>
			</c:forEach>
			
                  	</c:when>
                  	</c:choose>
           
           			<c:choose>
                  		<c:when test="${requestScope.clientes!=null}">
                  		
				<div style="position: relative;display: table;width: 98%;margin: 1%;font-size: 14px;">
                        	<div style="display: table-row;width: 100%;height: 20px;">
                        	<div class="Cell Head" style="width: 5%;">
                        		ID
                        	</div>
                        	<div class="Cell Head" style="width: 20%;">
                        		Nombre
                        	</div>
                        	<div class="Cell Head" style="width: 10%;">
                        		Logo
                        	</div>
                        	<div class="Cell Head" style="width: 50%;">
                        	</div>
                        	</div>
 			<c:forEach items="${requestScope.clientes}" var="dato">
 			<div class="RowOpc"style="display: table-row;width: 100%;height: 20px;">
                             <div class="Cell" style="width: 5%;">
                                 <a href="./modCli?id=${dato.id}" style="text-decoration: none;color: black;">
                                     <c:out value="${dato.id}"/>
                                 </a>
                             </div>
                             <div class="Cell" style="width: 20%;">
                                 <a href="./modCli?id=${dato.id}" style="text-decoration: none;color: black;">
                                     <c:out value="${dato.nombre}"/>
                                 </a>
                             </div>
                             <div class="Cell" style="width: 20%;">
			         <a href="./modCli?id=${dato.id}" style="text-decoration: none;color: black;">
			             <img src = "<c:out value="${dato.logo}"/>"/ style="height: 50px;">
			         </a>
                             </div>
                             <div class="Cell" style="width: 5%;">
                                 <form method="get" action="./delCli">
                                     <input type="image" src="./IMG/Borrar.png" style="height: 25px;"/>
                                     <input type="hidden" name="idCli" value="${dato.id}">
                                 </form>
                             </div>
                         </div>
			</c:forEach>
                  		
                  		</c:when>
                  	</c:choose>
                  	
                  	<c:choose>
                  	<c:when test = "${requestScope.catalogo!=null}">
                  	
                  	<div id="TituloG">
                  		Productos en el cat&aacute;logo
                  	</div>
                  	
                  	<div style="position: relative;display: table;width: 98%;margin: 1%;font-size: 14px;">
			<div style="display: table-row;width: 100%;height: 20px;">
			<div class="Cell Head" style="width: 5%;">
			      ID
			</div>
			<div class="Cell Head" style="width: 20%;">
			       Nombre
			</div>
			<div class="Cell Head" style="width: 10%;">
			                        		Descripci&oacute;n
			</div>
			<div class="Cell Head" style="width: 10%;">
			                        		Cantidad
			</div>
			<div class="Cell Head" style="width: 10%;">
						                Imagen
			</div>
			<div class="Cell Head" style="width: 50%;">
			</div>
                       </div>
                  	
 			<c:forEach items="${requestScope.productos}" var="dato">
 			<div class="RowOpc"style="display: table-row;width: 100%;height: 20px;">
                             <div class="Cell" style="width: 5%;">
                                     <c:out value="${dato.id}"/>
                             </div>
                             <div class="Cell" style="width: 20%;">
                                     <c:out value="${dato.nombre}"/>
                             </div>
                             <div class="Cell" style="width: 20%;">
			             <c:out value="${dato.descripcion}"/>
                             </div>
                             <div class="Cell" style="width: 20%;">
                                     <c:out value="${dato.cantidad}"/>
                             </div>
                             <div class="Cell" style="width: 20%;">
			             <img src = "<c:out value="${dato.imagen}"/>"/ style="height: 50px;">
                             </div>
                             <div class="Cell" style="width: 5%;">
                                 <form method="get" action="./noProd">
                                     <input type="image" src="./IMG/Minus.png" style="height: 25px;"/>
                                     <input type="hidden" name="idProd" value="${dato.id}">
                                     <input type="hidden" name="idCat" value="${requestScope.catalogo.id}">
                                 </form>
                             </div>
                         </div>
			</c:forEach>
			</div>
                  	
                  	<div id="TituloG">
                  		Todos los productos
                  	</div>
                  	
                  	<div style="position: relative;display: table;width: 98%;margin: 1%;font-size: 14px;">
			<div style="display: table-row;width: 100%;height: 20px;">
			<div class="Cell Head" style="width: 5%;">
			      ID
			</div>
			<div class="Cell Head" style="width: 20%;">
			       Nombre
			</div>
			<div class="Cell Head" style="width: 10%;">
			                        		Descripci&oacute;n
			</div>
			<div class="Cell Head" style="width: 10%;">
			                        		Cantidad
			</div>
			<div class="Cell Head" style="width: 10%;">
						                Imagen
			</div>
			<div class="Cell Head" style="width: 50%;">
			</div>
                       </div>
                  	
 			<c:forEach items="${requestScope.productos2}" var="dato">
 			<div class="RowOpc"style="display: table-row;width: 100%;height: 20px;">
                             <div class="Cell" style="width: 5%;">
                                     <c:out value="${dato.id}"/>
                             </div>
                             <div class="Cell" style="width: 20%;">
                                     <c:out value="${dato.nombre}"/>
                             </div>
                             <div class="Cell" style="width: 20%;">
			             <c:out value="${dato.descripcion}"/>
                             </div>
                             <div class="Cell" style="width: 20%;">
                                     <c:out value="${dato.cantidad}"/>
                             </div>
                             <div class="Cell" style="width: 20%;">
			             <img src = "<c:out value="${dato.imagen}"/>"/ style="height: 50px;">
                             </div>
                             <div class="Cell" style="width: 5%;">
                                 <form method="get" action="./addProd">
                                     <input type="image" src="./IMG/Plus.png" style="height: 25px;"/>
                                     <input type="hidden" name="idProd" value="${dato.id}">
                                     <input type="hidden" name="idCat" value="${requestScope.catalogo.id}">
                                 </form>
                             </div>
                         </div>
			</c:forEach>
			</div>
		
                  	<form method="post" action="./camCat">
                  	
                  	<input type = "text" name = "nombre" class="input" placeholder="Nombre" value="${requestScope.catalogo.nombre}"/>
                  	</br>
                  	
                  	<input type = "text" name = "descripcion" class="input" placeholder="Descripcion" value="${requestScope.catalogo.descripcion}"/>
                  	</br>
                  	</br>
                  	<input type = "hidden" name = "idLol" value = "${requestScope.catalogo.id}"/>
                  	<input type = "hidden" name = "oldnom" value = "${requestScope.catalogo.nombre}"/>		
                  	
                  	<input type = "submit" value = "Modificar"/>
                  	</form>
                  	
                  	</c:when>
                  	</c:choose>
                  
                  	<c:choose>
                  	<c:when test = "${requestScope.cliente!=null}">
                  	
			<div id="TituloG">
                  		Cat&aacute;logos del cliente
                  	</div>
                  	
                  	<div style="position: relative;display: table;width: 98%;margin: 1%;font-size: 14px;">
			<div style="display: table-row;width: 100%;height: 20px;">
			<div class="Cell Head" style="width: 5%;">
			      ID
			</div>
			<div class="Cell Head" style="width: 20%;">
			       Nombre
			</div>
			<div class="Cell Head" style="width: 10%;">
			                        		Descripci&oacute;n
			</div>
			<div class="Cell Head" style="width: 50%;">
			</div>
                       </div>
                  	
 			<c:forEach items="${requestScope.cats}" var="dato">
 			<div class="RowOpc"style="display: table-row;width: 100%;height: 20px;">
                             <div class="Cell" style="width: 5%;">
                                     <c:out value="${dato.id}"/>
                             </div>
                             <div class="Cell" style="width: 20%;">
                                     <c:out value="${dato.nombre}"/>
                             </div>
                             <div class="Cell" style="width: 20%;">
			             <c:out value="${dato.descripcion}"/>
                             </div>
                             <div class="Cell" style="width: 5%;">
                                 <form method="get" action="./noCata">
                                     <input type="image" src="./IMG/Minus.png" style="height: 25px;"/>
                                     <input type="hidden" name="idCat" value="${dato.id}">
                                     <input type="hidden" name="idCli" value="${requestScope.cliente.id}">
                                 </form>
                             </div>
                         </div>
			</c:forEach>
			</div>
                  	
                  	<div id="TituloG">
                  		Todos los cat&aacute;logos
                  	</div>
                  	
                  	<div style="position: relative;display: table;width: 98%;margin: 1%;font-size: 14px;">
			<div style="display: table-row;width: 100%;height: 20px;">
			<div class="Cell Head" style="width: 5%;">
			      ID
			</div>
			<div class="Cell Head" style="width: 20%;">
			       Nombre
			</div>
			<div class="Cell Head" style="width: 10%;">
			                        		Descripci&oacute;n
			</div>
			<div class="Cell Head" style="width: 50%;">
			</div>
                       </div>
                  	
 			<c:forEach items="${requestScope.cats2}" var="dato">
 			<div class="RowOpc"style="display: table-row;width: 100%;height: 20px;">
                             <div class="Cell" style="width: 5%;">
                                     <c:out value="${dato.id}"/>
                             </div>
                             <div class="Cell" style="width: 20%;">
                                     <c:out value="${dato.nombre}"/>
                             </div>
                             <div class="Cell" style="width: 20%;">
			             <c:out value="${dato.descripcion}"/>
                             </div>
                             <div class="Cell" style="width: 5%;">
                                 <form method="get" action="./addCata">
                                     <input type="image" src="./IMG/Plus.png" style="height: 25px;"/>
                                     <input type="hidden" name="idCat" value="${dato.id}">
                                     <input type="hidden" name="idCli" value="${requestScope.cliente.id}">
                                 </form>
                             </div>
                         </div>
			</c:forEach>
			</div>                  	
                  	</c:when>
                  	</c:choose>
                  
                </div>
            </div>
        </div>
    </body>
</html>
