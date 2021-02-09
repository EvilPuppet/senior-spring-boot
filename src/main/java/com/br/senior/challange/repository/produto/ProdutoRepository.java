package com.br.senior.challange.repository.produto;

import com.br.senior.challange.model.pedido.Pedido;
import com.br.senior.challange.model.produto.Produto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.UUID;

@Repository
@Transactional
public interface ProdutoRepository extends CrudRepository<Produto, UUID>, PagingAndSortingRepository<Produto, UUID> {

}