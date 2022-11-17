/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ajax;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author georg
 */
public class CInfoHelper {

    public List<CSelectDepartamento> getDepartamentoList() {
        List<CSelectDepartamento> deptos = new ArrayList();
        deptos.add(new CSelectDepartamento(1, "Santa Ana"));
        deptos.add(new CSelectDepartamento(2, "Sonsonate"));
        deptos.add(new CSelectDepartamento(3, "San Salvador"));
        return deptos;
    }

    public List<CSelectCiudad> getCiudades() {
        List<CSelectCiudad> cities = new ArrayList();
        cities.add(new CSelectCiudad(11, "Santa Ana", 1));
        cities.add(new CSelectCiudad(12, "Metapán", 1));
        cities.add(new CSelectCiudad(13, "chalchuapa", 1));
        cities.add(new CSelectCiudad(14, "El Congo", 1));

        cities.add(new CSelectCiudad(21, "Acajutla", 2));
        cities.add(new CSelectCiudad(22, "Caluco", 2));
        cities.add(new CSelectCiudad(23, "Izalco", 2));
        cities.add(new CSelectCiudad(24, "Juayúa", 2));

        cities.add(new CSelectCiudad(31, "Nahuizalco", 3));
        cities.add(new CSelectCiudad(32, "Izalco", 3));
        cities.add(new CSelectCiudad(33, "cuscatlán", 3));
        cities.add(new CSelectCiudad(34, "Ataco", 3));

        return cities;
    }

    public CSelectDepartamento getDepartamentosPorCodigo(int codigo) {
        CSelectDepartamento dep = null;
        for (CSelectDepartamento d : this.getDepartamentoList()) {
            if (d.getCodigo() == codigo) {
                dep = d;
                break;
            }
        }
        return dep;
    }

    public CSelectCiudad getCiudadesPorCodigo(int codigo) {
        CSelectCiudad city = null;
        for (CSelectCiudad ci : this.getCiudades()) {
            if (ci.getCodigo() == codigo) {
                city = ci;
                break;
            }
        }
        return city;
    }
}
