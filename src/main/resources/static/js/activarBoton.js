function activarBoton(){

var lista = document.getElementById("id");
var boton = document.getElementById("enviar");
if(lista.selectedIndex !=0 )
  boton.disabled = false;
else{
  boton.disabled = true;
}

}