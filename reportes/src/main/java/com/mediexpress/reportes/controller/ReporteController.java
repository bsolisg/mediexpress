package com.mediexpress.reportes.controller;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/reportes")
public class ReporteController {

    @GetMapping("/resumen")
    public Map<String, Object> resumen() {
        return Map.of(
            "pedidos_totales", 125,
            "facturacion_total", 485000,
            "clientes_activos", 42,
            "productos_vendidos", 1930
        );
    }

    @GetMapping("/ingresos")
    public Map<String, Object> ingresos(
            @RequestParam String desde,
            @RequestParam String hasta) {

        return Map.of(
            "desde", desde,
            "hasta", hasta,
            "monto_total", 183500
        );
    }

    @GetMapping("/productos/top")
    public Map<String, Object> topProductos() {
        return Map.of(
            "1", "Guantes nitrilo",
            "2", "Mascarilla KN95",
            "3", "Termómetro digital"
        );
    }
}
