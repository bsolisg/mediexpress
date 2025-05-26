package com.mediexpress.proveedores.model;

import jakarta.persistence.*;

@Entity
@Table(name = "proveedores")
public class Proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String contacto;
    private String correo;
    private String rubro;
    public Object getNombre() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getNombre'");
    }
    public void setNombre(Object nombre2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setNombre'");
    }
    public Object getCorreo() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCorreo'");
    }
    public void setCorreo(Object correo2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setCorreo'");
    }
    public Object getRubro() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getRubro'");
    }
    public void setRubro(Object rubro2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setRubro'");
    }

    // Getters y setters
}
