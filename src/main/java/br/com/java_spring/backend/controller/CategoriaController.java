package br.com.java_spring.backend.controller;

import br.com.java_spring.backend.model.Categoria;
import br.com.java_spring.backend.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    // Rota: POST /api/categorias (CREATE)
    @PostMapping
    public Categoria criarCategoria(@RequestBody Categoria categoria) {
        return categoriaService.salvarCategoria(categoria);
    }

    // Rota: GET /api/categorias/{id} (READ by ID)
    @GetMapping("/{id}")
    public ResponseEntity<Categoria> buscarCategoriaPorId(@PathVariable Long id) {
        Categoria categoria = categoriaService.buscarCategoriaPorId(id);
        return ResponseEntity.ok(categoria);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> atualizarCategoria(@PathVariable Long id, @RequestBody Categoria categoria) {
        Categoria atualizada = categoriaService.atualizarCategoria(id, categoria);
        return ResponseEntity.ok(atualizada);
    }

    // Rota: DELETE /api/categorias/{id} (DELETE)
    @DeleteMapping("/{id}")
    public void deletarCategoria(@PathVariable Long id) {
            categoriaService.deletarCategoria(id);
    }

    // Rota: GET /api/categorias (READ All)
    @GetMapping
    public List<Categoria> listarCategorias() {
        return categoriaService.listarTodasCategorias();
    }

}
