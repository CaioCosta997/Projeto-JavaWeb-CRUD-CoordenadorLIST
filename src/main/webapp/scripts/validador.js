/**
 * @author Leonardo Britto
 */

 function validar(){
	 var nome = frmCoord.nome.value;
	 var curso = document.getElementById("curso").value;
	 var periodo= document.getElementById("periodo").value;
	 if(nome == ""){
		 alert('Prencha o campo nome');
		 console.log('Prencha o campo nome');
		 frmCoord.nome.focus();
		 return false;
	 }else {
		 alert('Sucesso');
		 console.log('Sucesso');
	 }
}

