<%-- Document : view Created on : 07-15-2022, 06:01:53 PM Author : georg --%>


<%@page import="java.util.Iterator" %>
<%@page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet"
              href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.0/font/bootstrap-icons.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
              rel="stylesheet"
              integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
              crossorigin="anonymous">
        <link rel="stylesheet" type="text/css"
              href="https://cdn.datatables.net/v/bs5/jq-3.6.0/dt-1.12.1/datatables.min.css" />
    </head>

    <body>


        <div class="container">
            <div class="row mt-3">
                <div class="col-md-6 offset-md-3">
                    <div class="card shadow-lg">
                        <div class="card-header bg-dark text-light text-center text-uppercase">
                            Crear cliente
                        </div>
                        <form action="cliente" method="POST" id="frmRegCliente" class="p-3">
                            <div class="mb-1">
                                <label for="inRegClNombre">Nombre</label>
                                <input type="text" id="inRegClNombre" name="inRegClNombre" value="12"
                                       class="form-control" required>
                            </div>
                            <div class="mb-1">
                                <label for="inRegClApellidos">Apellidos</label>
                                <input type="text" value="aldi" id="inRegClApellidos"
                                       name="inRegClApellidos" class="form-control" required>
                            </div>
                            <div class="mb-1">
                                <label for="inRegClDUI">DUI</label>
                                <input type="text" value="1" id="inRegClDUI" name="inRegClDUI"
                                       class="form-control" required>
                            </div>
                            <div class="text-center">
                                <!--<button type="submit" name="opcion" value="registrar" class="btn btn-dark">Registrar</button>-->
                                <button type="submit" class="btn btn-success" name="opcion"
                                        value="registrar"> Agregar</button>
                            </div>
                        </form>
                        <c:if test="${mensaje !=null}">
                            <div class="alert alert-primary alert-dismissible fade show" role="alert">
                                <c:out value="${mensaje}"></c:out>
                                    <button type="button" class="btn-close" data-bs-dismiss="alert"
                                            aria-label="Close"></button>
                                </div>
                        </c:if>
                        <c:if test="${error !=null}">
                            <div class="alert alert-danger alert-dismissible fade show" role="alert">
                                <c:out value="${error}"></c:out>
                                    <button type="button" class="btn-close" data-bs-dismiss="alert"
                                            aria-label="Close"></button>
                                </div>
                        </c:if>

                    </div>
                </div>

            </div>

            <div class="table-responsive mt-4">
                <table class="table table-hover " id="customersTable">
                    <thead class="bg-dark text-light text-center">
                        <tr>
                            <th scope="col">ID</th>
                            <th scope="col">DUI</th>
                            <th scope="col">Nombre</th>
                            <th scope="col">Apellidos</th>
                            <th scope="col">Operación</th>
                        </tr>
                    </thead>
                    <tbody id="tbbListClientes">
                        <c:forEach items="${listclientes}" var="producto">
                            <tr>

                                <td>
                                    <c:out value="${producto.getCodCliente()}"></c:out>
                                    </td>
                                    <td>
                                    <c:out value="${producto.getDui()}"></c:out>
                                    </td>
                                    <td>
                                    <c:out value="${producto.getNombre()}"></c:out>
                                    </td>
                                    <td>
                                    <c:out value="${producto.getApellido()}"></c:out>
                                    </td>

                                    <td class="btnOpciones">
                                        <button class="btn btn-dark crearTransaccion" data-bs-toggle="modal"
                                                data-bs-target="#mdlOperarTransaccion"
                                                id="operarTrans">Operar</button>

                                        <button class="btn btn-secondary crearCuenta" data-bs-toggle="modal"
                                                data-bs-target="#mdlCrearCuenta">Crear cuenta</button>
                                    </td>
                                </tr>
                        </c:forEach>

                    </tbody>
                </table>
            </div>
        </div>









        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"
                integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB"
        crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"
                integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13"
        crossorigin="anonymous"></script>

        <script type="text/javascript"
        src="https://cdn.datatables.net/v/bs5/jq-3.6.0/dt-1.12.1/datatables.min.js"></script>

        <%@include file="modals/modal.jsp" %>
        <script src="assets/js/cuenta.js" async></script>
        <script>
            $(document).ready(function () {
                $('#customersTable').DataTable();
            });


            document.querySelector("body").addEventListener("click", (evt) => {
                if (evt.target.matches(".crearTransaccion")) {
                    getCuentasByUsuario(evt.target.parentElement.parentElement.children[0].textContent.trim());
                    document.getElementById("idClienteRegTrans").value = evt.target.parentElement.parentElement.children[0].textContent.trim();

                }

            });




            function getCuentasByUsuario(idCliente) {
                peticionAjax(idCliente.trim(), pintarCuentas);
            }

            const peticionAjax = async (idCliente, func) => {
                var petidon = await fetch('http://localhost:8080/proyectogodinez/cuenta?opcion=getcuentasByUsuario&idCliente=' + idCliente);
                const listCuentas = await petidon.json();
                // console.log(typeof (listCuentas));
                func(listCuentas.cuenta);
            };


            function pintarCuentas(array = []) {
                let OptDefault;
                // <option value="" selected disabled>Seleccione una</option>
                OptDefault = document.createElement("option");
                OptDefault.value = "";
                OptDefault.selected = true;
                OptDefault.disabled = true;
                OptDefault.textContent = "Seleccione uno";


                let selectCuentas = document.createDocumentFragment();
                let option;
                selectCuentas.appendChild(OptDefault);


                array.forEach((e) => {
                    option = document.createElement("option");
                    option.value = e.idCuentas;
                    option.textContent = e.nombreCuenta;
                    selectCuentas.appendChild(option);
                });
                document.getElementById("inTransCuentaDispon").innerHTML = "";
                document.getElementById("inTransCuentaDispon").appendChild(selectCuentas);
            }
        </script>


    </body>

</html>