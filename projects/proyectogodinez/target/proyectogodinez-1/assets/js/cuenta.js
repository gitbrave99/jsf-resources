/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

var tableList = document.querySelectorAll("#tbbListClientes tr");
var btnsOpciones = document.querySelectorAll(".btnOpciones button");

btnsOpciones.forEach(btns => {
    btns.addEventListener("click", evt => {
        if (evt.target.classList.contains('crearCuenta')) {
            console.log("clk");
            let dtCliente = {};
            dtCliente.idCliente = btns.parentElement.parentElement.children[0].textContent.trim();
            dtCliente.dui = btns.parentElement.parentElement.children[1].textContent.trim();
            dtCliente.nombre = btns.parentElement.parentElement.children[2].textContent.trim();
            dtCliente.apellidos = btns.parentElement.parentElement.children[3].textContent.trim();
            cargarModalCrearCuenta(dtCliente);
        }
        if (evt.target.classList.contains('crearTransaccion')) {
            // peticionNumcuentaClitenAJAX(document.getElementById("idClienteRegTrans").value);
            // console.log("valueid: "+document.getElementById("idClienteRegTrans").value);
        }

    })
});



function cargarModalCrearCuenta(dtCliente) {
    let idUsuario = document.getElementById("CreacCidUsuario");
    let clienteCuenta = document.getElementById("clienteCuenta");
    idUsuario.value = dtCliente.idCliente;
    clienteCuenta.value = dtCliente.nombre + " " + dtCliente.apellidos + " - " + dtCliente.dui;
}


document.getElementById("inTransCuentaDispon").addEventListener("change", (evt) => {
    let idCliente = evt.target.parentElement.children[0].value;
    let nmCuenta = evt.target.selectedOptions[0].text.trim();
    peticionSaldocuentaAjax(idCliente, nmCuenta);
    console.log("seleted tipo: "+evt.target.selectedOptions[0].text);
    document.getElementById("numCuentaCliente").value = evt.target.selectedOptions[0].value;
console.log("optio ; "+evt.target.selectedOptions[0].value);
});

var selectedTipoTrans = 0;
document.getElementById("inTransTipoTransaccion").addEventListener("change", (evt) => {
    console.log("nm: " + evt.target.selectedIndex);
    selectedTipoTrans = evt.target.selectedIndex;
    console.log("slete: " + selectedTipoTrans);
    peticionNumcuentaClitenAJAX(document.getElementById("idClienteRegTrans").value);
    
    

    console.log("candgando");

});

// if (selectedTipoTrans == 2) {

// }

document.getElementById("inTransValor").addEventListener("keyup", (evt) => {
    const saldoActual = parseFloat(document.getElementById("outTransSaldo").value);
    if (selectedTipoTrans == 2) {
        if (parseFloat(evt.target.value) > saldoActual) {
            document.getElementById("mensajeRetiro").textContent = "Retiro mayo que saldo";

        } else {
            document.getElementById("mensajeRetiro").textContent = "";
        }
    } else {
        document.getElementById("mensajeRetiro").textContent = "";
    }


});




const peticionSaldocuentaAjax = async (idCliente, nmCuenta) => {
    var petidon = await fetch('http://localhost:8080/proyectogodinez/cuenta?opcion=getSaldoByCuentaUsuario&idCliente=' + idCliente + '&nmCuenta=' + nmCuenta);
    const saldoActual = await petidon.text();
    //MOSTRAR SALDO CUENTA X
    document.getElementById("outTransSaldo").value = saldoActual;
};

const peticionNumcuentaClitenAJAX = async (idCliente) => {
    var petidon = await fetch('http://localhost:8080/proyectogodinez/cuenta?opcion=getNumCuentaCliente&idCliente=' + idCliente);
    const nucuenta = await petidon.text();
    //MOSTRAR SALDO CUENTA X
    // document.getElementById("numCuentaCliente").value = nucuenta;
};