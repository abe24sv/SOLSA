<!DOCTYPE html>

<html>
    <head>
        <title>E-SOLSA</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        
        <link href="./STL/SOLSA.css" rel='stylesheet' type='text/css'/>
        <link href="css/metro/blue/jtable.css" rel="stylesheet" type="text/css" />
        <link href="css/jquery-ui-1.10.3.custom.css" rel="stylesheet"
                type="text/css" />
        <!-- Include jTable script file. -->
        <script src="js/jquery-1.8.2.js" type="text/javascript"></script>
        <script src="js/jquery-ui-1.10.3.custom.js" type="text/javascript"></script>
        <script src="js/jquery.jtable.js" type="text/javascript"></script>
        <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'/>
        <link href='http://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'/>
    
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
                document.getElementById("Content").setAttribute("style","width:"+(ancho-220)+"px;");    };
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
        <script type="text/javascript">
	$(document).ready(function() {
		$('#ProductTableContainer').jtable({
			title : 'Lista de Productos',
			actions : {
				listAction : 'Controller?action=list',
				createAction : 'Controller?action=create',
				updateAction : 'Controller?action=update',
				deleteAction : 'Controller?action=delete'
			},
			fields : {
				productId : {
					title : 'Id del producto',
					width : '30%',
					key : true,
					list : true,
					edit : false,
					create : true
				},
				name : {
					title : 'nombre',
					width : '30%',
					edit : true
				},
				description : {
					title : 'Descripción',
                                        type : 'textarea',
					width : '30%',
					edit : true
				},
                                quantity : {
					title : 'Cantidad',
					width : '30%',
					edit : true
				},
                                category : {
					title : 'Categoría',
                                        options: '',
                                        list: false
				},
                                subcategory : {
					title : 'Sub-Categoría',
                                        options: '',
                                        list: false
				},
                                image : {
					title : 'Imagen',
                                         input : function (data){
                                            return "<input type='file' name='img'>";
                                        }
				},
				brand : {
					title : 'Marca',
					width : '20%',
					edit : true
				}
			}
		});
		$('#ProductTableContainer').jtable('load');
	});
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
            <div id="HSearch" class="Header Container">
                <div id="Search">
                    <div id="SearchCon">
                        <form>
                            <input id="SBar" class="Search Bar" type="text" placeholder="Buscar Productos"/>
                            <input type="image" src="./IMG/Search.png" style="height: 25px;position: relative;top: 3px;"/>
                        </form>
                    </div>
                    <a href="./buspref.jsp" style="font-size: 12px;">B&uacute;squeda Avanzada</a>
                </div>
            </div>
        </div>
        <div id="Middle">
            <div id="NavBar">
                <a href="./sesion.jsp">
                <div class="Opcion" style="height: 40px;display: table;">
                    <div style="display: table-cell;width: 50px;vertical-align: middle;">
                        <img src="./IMG/SOLSA.png" style="width: 50px;"/>
                    </div>
                    <div style="display: table-cell;position: relative;width: 150px;vertical-align: middle;text-align: center;color: black;">
                        Usuario SOLSA
                    </div>
                </div>
                </a>
                <div class="Opcion Multiple" onclick="ampliarOpcion('Usuarios')">
                    <div style="width: 20px;float: left;">
                        <img id="opcUsuarios" src="./IMG/Mostrar.png" style="width: 10px;"/>
                    </div>
                    <div style="width: 165px;float: left;margin-left: 5px;">
                        Usuarios
                    </div>
                </div>
                <div id="Usuarios" style="display: none;">
                    <div id="BuscOpc" class="Opcion">
                        <form>
                            <input id="BuscOpcBar" type="text" style="" placeholder="B&uacute;squeda"/>
                            <input type="image" src="./IMG/Search.png" style="height: 20px;position: relative;top: 0px;"/>
                        </form>
                    </div>
                    <div style="text-align: center;">
                     <a href="./busprefusr.jsp" style="font-size: 12px;">B&uacute;squeda Avanzada</a>
                    </div>
                    <div class="Opcion Sub">
                        <span>Administrar</span>
                    </div>
                    <div class="Opcion Sub">
                        <span>Registrar</span>
                    </div>
                </div>
                <div class="Opcion Multiple" onclick="ampliarOpcion('Productos')">
                    <div style="width: 20px;float: left;">
                        <img id="opcProductos" src="./IMG/Mostrar.png" style="width: 10px;"/>
                    </div>
                    <div style="width: 165px;float: left;margin-left: 5px;">
                        Productos
                    </div>
                </div>
                <div id="Productos" style="display: none;">
                    <div id="BuscOpc" class="Opcion">
                        <form>
                            <input id="BuscOpcBar" type="text" style="" placeholder="B&uacute;squeda"/>
                            <input type="image" src="./IMG/Search.png" style="height: 20px;position: relative;top: 0px;"/>
                        </form>
                    </div>
                    <div style="text-align: center;">
                     <a href="./busprefprod.jsp" style="font-size: 12px;">B&uacute;squeda Avanzada</a>
                    </div>
                    <div class="Opcion Sub">
                        <span>Administrar</span>
                    </div>
                    <div class="Opcion Sub">
                        <span>Registrar</span>
                    </div>
                </div>
                <div class="Opcion Multiple" onclick="ampliarOpcion('Contenidos')">
                    <div style="width: 20px;float: left;">
                        <img id="opcContenidos" src="./IMG/Mostrar.png" style="width: 10px;"/>
                    </div>
                    <div style="width: 165px;float: left;margin-left: 5px;">
                        Tipos de Contenido
                    </div>
                </div>
                <div id="Contenidos" style="display: none;">
                    <div class="Opcion Sub">
                        <span>Ejemplos</span>
                    </div>
                    <div class="Opcion Sub">
                        <a href="./index.jsp"><span>Vista de Cliente</span></a>
                    </div>
                    <div class="Opcion Sub">
                        <a href="./tabla.jsp"><span>Vista de Administraci&oacute;n</span></a>
                    </div>
                    <div class="Opcion Sub">
                        <a href="./formulario.jsp"><span>Formulario</span></a>
                    </div>
                </div>
            </div>
            <br>
            <div id="Content">
                <div style="width: 80%; margin-right: 10%; margin-left: 10%; text-align: center;">
                    <div id="ProductTableContainer"></div>                  
                </div>
        </div>
    </body>
</html>