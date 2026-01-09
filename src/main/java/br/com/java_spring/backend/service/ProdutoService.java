package br.com.java_spring.backend.service;

import br.com.java_spring.backend.model.Produto;
import br.com.java_spring.backend.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;
    // CREATE
    public Produto salvarProduto(Produto produto) {
        return produtoRepository.save(produto);
    }
    // READ All
    public List<Produto> listarTodosProdutos() {
        return produtoRepository.findAll();
    }
    // READ by ID
    public Produto buscarProdutoPorId(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(
                        () -> new RuntimeException("Produto não encontrado!")
                );
    }
    // UPDATE
    public Produto atualizar(Long id, Produto produtoAtualizado) {
        Produto produtoExistente = buscarProdutoPorId(id);
        produtoExistente.setNome(produtoAtualizado.getNome());
        produtoExistente.setPreco(produtoAtualizado.getPreco());
        produtoExistente.setCategoria(produtoAtualizado.getCategoria());
        produtoExistente.setDescricao(produtoAtualizado.getDescricao());
        return produtoRepository.save(produtoExistente);
    }
    // DELETE
    public void deletarProduto(Long id) {
        produtoRepository.deleteById(id);
    }
    // EXTRA
    public List<Produto> buscarPorNome(String nome) {
        return produtoRepository.findByNomeContainingIgnoreCase(nome);
    }
    // JPQL
    public List<Produto> buscarProdutosComplexos(Double min, Double max, String termo) {
        return produtoRepository.buscarProdutosComplexos(min, max, termo);
    }

    public List<Produto> buscarTresMaisCaros(Double valor) {
        return produtoRepository.buscarTresProdutosMaisCaros(valor);
    }

}
