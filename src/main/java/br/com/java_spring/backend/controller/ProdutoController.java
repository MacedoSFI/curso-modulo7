package br.com.java_spring.backend.controller;

import br.com.java_spring.backend.model.Produto;
import br.com.java_spring.backend.repository.ProdutoRepository;
import br.com.java_spring.backend.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    // Rota: POST /api/produtos (CREATE)
    @PostMapping
    public Produto criarProduto(@RequestBody Produto produto) {
        return produtoService.salvarProduto(produto);
    }

    // Rota: GET /api/produtos (READ All)
    @GetMapping
    public List<Produto> listarProdutos() {
        return produtoService.listarTodosProdutos();
    }

    // Rota: GET /api/produtos/{id} (READ by ID)
    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarProdutoPorId(@PathVariable Long id) {
        Produto produto = produtoService.buscarProdutoPorId(id);
        return ResponseEntity.ok(produto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizar(@PathVariable Long id, @RequestBody Produto produto) {
        Produto atualizado = produtoService.atualizar(id, produto);
        return ResponseEntity.ok(atualizado);
    }

    @GetMapping("/busca")
    public ResponseEntity<List<Produto>> buscarPeloNome(@RequestParam String nome) {
        List<Produto> resultados = produtoService.buscarPorNome(nome);
        return ResponseEntity.ok(resultados);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        produtoService.deletarProduto(id);
    }

    //-aula JPQL

    @GetMapping("/mais_caros")
    public ResponseEntity<List<Produto>> buscarMaisCaros(@RequestParam Double preco) {
        List<Produto> resultados = produtoService.buscarTresMaisCaros(preco);
        return ResponseEntity.ok(resultados);
    }

    @GetMapping("/mais_complexos")
    public ResponseEntity<List<Produto>> buscarMaisComplexos(@RequestParam Double min, Double max, String termo) {
        List<Produto> resultados = produtoService.buscarProdutosComplexos(min, max, termo);
        return ResponseEntity.ok(resultados);
    }

}
