package br.com.java_spring.backend.repository;

import br.com.java_spring.backend.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    List<Produto> findByNomeContainingIgnoreCase(String nome);
    // 1. Usando JPQL (Recomendado)
    @Query("SELECT p FROM Produto p WHERE p.preco >= :min AND p.preco <= :max AND p.nome LIKE %:termo%")
    List<Produto> buscarProdutosComplexos(
            @Param("min") Double min,
            @Param("max") Double max,
            @Param("termo") String termo
    );

    // 2. Usando SQL Nativo (Caso precise de algo muito específico do MySQL)
    @Query(value = "SELECT * FROM produto WHERE preco > :valor LIMIT 3", nativeQuery = true)
    List<Produto> buscarTresProdutosMaisCaros(@Param("valor") Double valor);

}
