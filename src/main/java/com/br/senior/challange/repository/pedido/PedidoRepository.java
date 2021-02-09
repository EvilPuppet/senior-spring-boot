package com.br.senior.challange.repository.pedido;


import com.br.senior.challange.model.pedido.Pedido;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.UUID;

@Repository
@Transactional
public interface PedidoRepository extends CrudRepository<Pedido, UUID>, PagingAndSortingRepository<Pedido, UUID> {

}