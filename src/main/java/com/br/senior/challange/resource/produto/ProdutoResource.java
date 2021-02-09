package com.br.senior.challange.resource.produto;

import com.br.senior.challange.model.pedido.Pedido;
import com.br.senior.challange.model.produto.Produto;
import com.br.senior.challange.service.produto.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value="/produto")
public class ProdutoResource {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping(value = "/{id}", produces="application/json")
    public ResponseEntity<Produto> get(@PathVariable("id") UUID id) {
        Optional<Produto> produto = produtoService.findById(id);
        if (produto.isPresent()) {
            return new ResponseEntity<Produto>(produtoService.findById(id).get(), HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();

    }

    @GetMapping(value="/{pageNo}/{pageSize}",produces="application/json")
    public ResponseEntity<List<Produto>> findAll(@PathVariable int pageNo,
                                                 @PathVariable int pageSize) {
        List<Produto> produtos = produtoService.findAll(pageNo, pageSize);
        if (!produtos.isEmpty()) {
            return new ResponseEntity<>(produtos, HttpStatus.OK);
        };
        return ResponseEntity.noContent().build();

    }

    @PostMapping(value="/", produces="application/json")
    public ResponseEntity<Produto> register(@RequestBody Produto produto) {
        return new ResponseEntity<Produto>(produtoService.save(produto), HttpStatus.CREATED);
    }

    @DeleteMapping(value="/", produces="application/json")
    public ResponseEntity<Void> deleteClients(@RequestBody Produto produto) {
        produtoService.delete(produto);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value="/", produces="application/json")
    public ResponseEntity<Produto> change(@RequestBody Produto produto) {
        return new ResponseEntity<Produto>(produtoService.save(produto), HttpStatus.OK);
    }
}
