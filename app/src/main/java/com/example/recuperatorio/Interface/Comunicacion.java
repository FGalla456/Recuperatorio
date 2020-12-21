package com.example.recuperatorio.Interface;

import com.example.recuperatorio.Dominio.Usuario;

public interface Comunicacion {
    void showMessage(String msg);
    void lanzarActividad(Class<?> tipoActividad , Usuario U);
}
