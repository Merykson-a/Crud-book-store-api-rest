package com.wdalivrariacrudapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class Editora {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

//    @Size(min = 3, max = 60, message = "No mínimo 3 caracteres e no máximo 60!")
//    @NotBlank(message = "O campo nome não pode ser deixado em branco")
    @Pattern(regexp="^[a-zA-ZÀ-Úà-ú]+( [a-zA-ZÀ-Úà-ú]+)*$", message = "Verifique o nome inserido(Sem caracteres especiais e/ou espaços no início e fim).")
    @Size(min = 3, max = 60, message = "No mínimo 3 caracteres e no máximo 60!")
    @Column(nullable = false)
    private String nome;

//    @Size(min = 3, max = 60, message = "No mínimo 3 caracteres e no máximo 60!")
//  @NotBlank(message = "O campo cidade não pode ser vazio")
    @Pattern(regexp="^[a-zA-ZÀ-Úà-ú]+( [a-zA-ZÀ-Úà-ú]+)*$", message = "Verifique o nome inserido(Sem caracteres especiais e/ou espaços no início e fim).")
    @Size(min = 3, max = 60, message = "No mínimo 3 caracteres e no máximo 60!")
    @Column(nullable = false)
    private String cidade;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "editora")
    @JsonIgnoreProperties("editora")
    private List<Livro> livros;
}
