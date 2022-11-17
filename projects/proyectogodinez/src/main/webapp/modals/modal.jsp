<!-- MODAL OPERAR  -->
<!-- Button trigger modal -->

<!-- Modal -->
<div class="modal fade" id="mdlOperarTransaccion" tabindex="-1" aria-labelledby="mdlOperarTransaccionLabel"
    aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="mdlOperarTransaccionLabel">Transacciones</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form class="needs-validation" method="post" action="cuenta" id="formTransacc" novalidate>
                    <input type="hidden" id="numCuentaCliente" name="numCuentaCliente">
                    <div class="mb-1">
                        <input type="hidden" id="idClienteRegTrans" name="idClienteRegTrans">
                        <label for="inTransCuentaDispon">Cuenta</label>
                        <select class="form-select btn-outline-dark" id="inTransCuentaDispon"
                            aria-label="Select cuenta disponible" required>
                            <option value="" selected disabled>Seleccione una</option>
                        </select>
                        <div class="invalid-feedback">
                            Selecciona una cuenta
                        </div>
                    </div>
                    <div class="mb-1">
                        <label for="inTransTipoTransaccion">Tipo Transaccion</label>
                        <select class="form-select btn-outline-dark" id="inTransTipoTransaccion"
                            name="inTransTipoTransaccion" aria-label="selec tipo Transacción" required>
                            <option value="" selected disabled>Seleccione uno</option>
                            <option value="1">Deposito</option>
                            <option value="2">Retiro</option>
                        </select>
                        <div class="invalid-feedback">
                            Seleccione Transaacion
                        </div>
                    </div>
                    <div class="mb-1">
                        <label for="outTransSaldo">Saldo</label>
                        <div class="input-group ">
                            <div class="input-group-text bg-dark text-light">$</div>
                            <input type="text" class="form-control btn-outline-seondary" id="outTransSaldo"
                                name="outTransSaldo" placeholder="0.0" value="0.0" readonly>
                        </div>
                    </div>

                    <div class="mb-1">
                        <label for="outSaldo">Valor Transaccion</label>
                        <label class="visually-hidden" for="inTransValor">Username</label>
                        <div class="input-group ">
                            <div class="input-group-text bg-dark text-light">$</div>
                            <input type="number" min="1" step="0.01" class="form-control" id="inTransValor"
                                name="inTransValor" required>
                            <div class="invalid-feedback">
                                Agregar una cantidad minima $1
                            </div>
                        </div>
                        <span class="text-danger" id="mensajeRetiro"></span>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">CANCELAR</button>
                        <button type="submit" class="btn btn-primary" name="opcion"
                            value="agregarTransaccion">GUARDAR</button>
                    </div>
                    <script>
                        // Example starter JavaScript for disabling form submissions if there are invalid fields
                        (function () {
                            'use strict'

                            // Fetch all the forms we want to apply custom Bootstrap validation styles to
                            var forms = document.querySelectorAll('.needs-validation')

                            // Loop over them and prevent submission
                            Array.prototype.slice.call(forms)
                                .forEach(function (form) {
                                    form.addEventListener('submit', function (event) {
                                        if (!form.checkValidity()) {
                                            event.preventDefault()
                                            event.stopPropagation()
                                        }

                                        form.classList.add('was-validated')
                                    }, false)
                                })
                        })()
                    </script>
                </form>
            </div>
        </div>
    </div>
</div>

<!-- MODAL CREAR CUENTA  -->
<div class="modal fade" id="mdlCrearCuenta" tabindex="-1" aria-labelledby="mdlCrearCuenta" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="mdlCrearCuenta">Crear cuenta</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form action="cuenta" method="POST" id="frmCrearCuenta" class="needs-validation" novalidate>
                    <input type="hidden" id="CreacCidUsuario" name="CreacCidUsuario">
                    <div class="mb-1">
                        <label>Usuario</label>
                        <input type="text" class="form-control" value="" id="clienteCuenta" name="clienteCuenta"
                            readonly>
                    </div>
                    <div class="mb-1">
                        <label for="inCreCNombreCuenta" class="form-label">Nombre Cuenta</label>
                        <input type="text" class="form-control" id="inCreCNombreCuenta" name="inCreCNombreCuenta"
                            required>
                        <div class="valid-tooltip">
                            Agrega Nombre Cuenta
                        </div>
                    </div>
                    <div class="mb-1">
                        <label for="inCreCMontoApertura">Monto Apertura</label>
                        <div class="input-group ">
                            <div class="input-group-text bg-dark text-light">$</div>
                            <input type="number" min="1" step="0.01" class="form-control" id="inCreCMontoApertura"
                                name="inCreCMontoApertura" required>
                            <div class="invalid-feedback">
                                Agregar una cantidad
                            </div>
                        </div>
                    </div>
                    <div class="text-center">
                        <button type="submit" name="opcion" value="crearcuenta" class="btn btn-secondary">Crear
                            cuenta</button>
                    </div>

                    <script>
                            // Example starter JavaScript for disabling form submissions if there are invalid fields
                            (function () {
                                'use strict'

                                // Fetch all the forms we want to apply custom Bootstrap validation styles to
                                var forms = document.querySelectorAll('.needs-validation')

                                // Loop over them and prevent submission
                                Array.prototype.slice.call(forms)
                                    .forEach(function (form) {
                                        form.addEventListener('submit', function (event) {
                                            if (!form.checkValidity()) {
                                                event.preventDefault()
                                                event.stopPropagation()
                                            }

                                            form.classList.add('was-validated')
                                        }, false)
                                    })
                            })()
                    </script>
                </form>
            </div>
        </div>
    </div>
</div>