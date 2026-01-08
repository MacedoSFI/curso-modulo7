package br.com.java_spring.backend.service;

import br.com.java_spring.backend.model.Categoria;
import br.com.java_spring.backend.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
     private CategoriaRepository categoriaRepository;


    // CREATE / UPDATE
    public Categoria salvarCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public Categoria atualizarCategoria(Long id, Categoria categoriaAtualizada) {
        Categoria categoriaExistente = buscarCategoriaPorId(id);
        categoriaExistente.setNome(categoriaAtualizada.getNome());
        return categoriaRepository.save(categoriaExistente);
    }

    // READ by ID
    public Categoria buscarCategoriaPorId(Long id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada com o ID: " + id));
    }

    // DELETE
    public void deletarCategoria(Long id) {
        categoriaRepository.deleteById(id);
    }
    // READ All
    public List<Categoria> listarTodasCategorias() {
        return categoriaRepository.findAll();
    }

}
