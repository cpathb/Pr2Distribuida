/**
 *
 * @author Carlos
 */

import java.io.Serializable;

public class Alerta implements Serializable{
    private PublicaSuscribeClienteInterface cliente;
    private String tipo;
    private String nombreEmpresa;
    private float valorObjetivo;

    public Alerta(PublicaSuscribeClienteInterface cliente, String tipo, String nombreEmpresa, float valorObjetivo) {
        this.cliente = cliente;
        this.tipo = tipo;
        this.nombreEmpresa = nombreEmpresa;
        this.valorObjetivo = valorObjetivo;
    }

    public PublicaSuscribeClienteInterface getCliente() {
        return cliente;
    }

    public void setCliente(PublicaSuscribeClienteInterface cliente) {
        this.cliente = cliente;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public float getValorObjetivo() {
        return valorObjetivo;
    }

    public void setValorObjetivo(float valorObjetivo) {
        this.valorObjetivo = valorObjetivo;
    }
}
