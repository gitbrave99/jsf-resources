/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author georg
 */
@Entity
@Table(name = "tipo_transaccion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoTransaccion.findAll", query = "SELECT t FROM TipoTransaccion t"),
    @NamedQuery(name = "TipoTransaccion.findByIdtipotransaccion", query = "SELECT t FROM TipoTransaccion t WHERE t.idtipotransaccion = :idtipotransaccion"),
    @NamedQuery(name = "TipoTransaccion.findByNombreTransaccion", query = "SELECT t FROM TipoTransaccion t WHERE t.nombreTransaccion = :nombreTransaccion")})
public class TipoTransaccion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id_tipo_transaccion")
    private Integer idtipotransaccion;
    @Size(max = 100)
    @Column(name = "nombre_transaccion")
    private String nombreTransaccion;

    public TipoTransaccion() {
    }

    public TipoTransaccion(Integer idtipotransaccion) {
        this.idtipotransaccion = idtipotransaccion;
    }

    public Integer getIdtipotransaccion() {
        return idtipotransaccion;
    }

    public void setIdtipotransaccion(Integer idtipotransaccion) {
        this.idtipotransaccion = idtipotransaccion;
    }

    public String getNombreTransaccion() {
        return nombreTransaccion;
    }

    public void setNombreTransaccion(String nombreTransaccion) {
        this.nombreTransaccion = nombreTransaccion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtipotransaccion != null ? idtipotransaccion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoTransaccion)) {
            return false;
        }
        TipoTransaccion other = (TipoTransaccion) object;
        if ((this.idtipotransaccion == null && other.idtipotransaccion != null) || (this.idtipotransaccion != null && !this.idtipotransaccion.equals(other.idtipotransaccion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.TipoTransaccion[ idtipotransaccion=" + idtipotransaccion + " ]";
    }
    
}
