package com.wdalivrariacrudapp.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Pattern(regexp="^[a-zA-ZÀ-Úà-ú]+( [a-zA-ZÀ-Úà-ú]+)*$", message = "Verifique o nome inserido(Sem caracteres especiais e/ou espaços no início e fim).")
    @Size(min = 3, max = 60, message = "No mínimo 3 caracteres e no máximo 60!")
    @Column(nullable = false)
    private String nome;

    @Size(min = 5, max = 60, message = "No mínimo 5 caracteres e no máximo 60!")
    @Pattern(regexp="^[a-zA-ZÀ-Úà-ú0-9-,.;:]+( [a-zA-ZÀ-Úà-ú0-9-,.;:]+)*$", message = "Verifique o endereço inserido(Sem caracteres especiais e/ou espaços no início e fim).")
    @Column(nullable = false)
    private String endereco;

    @Size(min = 3, max = 60, message = "No mínimo 3 caracteres e no máximo 60!")
    @Pattern(regexp="^[a-zA-ZÀ-Úà-ú0-9]+( [a-zA-ZÀ-Úà-ú0-9]+)*$", message = "Verifique a cidade inserida(Sem caracteres especiais e/ou espaços no início e fim).")
    @Column(nullable = false)
    private String cidade;

    @Pattern(regexp="[A-Za-z0-9._%-+]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}", message = "Insira um email válido, no formato: Email@email.com!")
    @Column(nullable = false)
    private String email;

}
