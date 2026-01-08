package br.com.java_spring.backend.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private double preco;
    private String descricao;

    @ManyToOne
    @JoinColumn(name="categoria_id")
    private Categoria categoria;
}
