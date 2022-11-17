/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


// var customerForm = document.getElementById("frmRegCliente");
// var formcliente = document.forms['frmRegCliente'];
// var cliente = {};
// customerForm.addEventListener("submit", evt => {
// //                                                    evt.preventDCefault();
//     cliente.nombre = formcliente['inRegClNombre'].value;
//     cliente.apellidos = formcliente['inRegClApellidos'].value;
//     cliente.dui = formcliente['inRegClDUI'].value;

//     console.log("daots: " + cliente.dui);
//     //                agregarCliente();
// });


var txtDUIcliente = document.getElementById("inRegClDUI");
txtDUIcliente.addEventListener("keyup", (evt) => {
    
});


function agregarCliente() {
    fetch("cliente?opcion=registrar", {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            method: "POST",
            body: JSON.stringify(cliente)
        })
        .then(function (res) {
            console.log(res);
        })
        .catch(function (res) {
            console.log(res);
        });
}