package com.br.senior.challange.resource.pedido;

import com.br.senior.challange.model.pedido.Pedido;
import com.br.senior.challange.service.pedido.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value="/pedido")
public class PedidoResource {

    @Autowired
    private PedidoService pedidoService;


    @GetMapping(value = "/{id}", produces="application/json")
    public ResponseEntity<Pedido> findById(@PathVariable("id") UUID id) {
        Optional<Pedido> pedido = pedidoService.findById(id);
        if (pedido.isPresent()) {
            return new ResponseEntity<Pedido>(pedidoService.findById(id).get(), HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(value="/{pageNo}/{pageSize}",produces="application/json")
    public ResponseEntity<List<Pedido>> findAll(@PathVariable int pageNo,
                                                @PathVariable int pageSize) {
        List<Pedido> pedidos = pedidoService.findAll(pageNo, pageSize);
        if (!pedidos.isEmpty()) {
            return new ResponseEntity<>(pedidos, HttpStatus.OK);
        };
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value="/", produces="application/json")
    public ResponseEntity<Pedido> register(@RequestBody Pedido pedido) {
        return new ResponseEntity<Pedido>(pedidoService.save(pedido), HttpStatus.CREATED);
    }

    @DeleteMapping(value="/{id}", produces="application/json")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
        pedidoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value="/", produces="application/json")
    public ResponseEntity<Pedido> change(@RequestBody Pedido pedido) {
        return new ResponseEntity<Pedido>(pedidoService.save(pedido), HttpStatus.OK);
    }
}
