package com.wdalivrariacrudapp.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Pattern(regexp="^[a-zA-ZÀ-Úà-ú-:,.:$%¨*()@!'+#&=´^~`}}{{/;|0-9]+( [a-zA-ZÀ-Úà-ú-:,.:$%¨*()@!'+#&=´^~`}}{{/;|0-9]+)*$", message = "Verifique o nome inserido, não pode haver espaços no início e fim.")
    @Size(min = 3, max = 60, message = "No mínimo 3 caracteres e no máximo 60!")
    @Column(nullable = false)
    private String nome;

    @Pattern(regexp="^[a-zA-ZÀ-Úà-ú-´`'.]+( [a-zA-ZÀ-Úà-ú-´`'.]+)*$", message = "Verifique o autor inserido(Sem caracteres especiais, números e/ou espaços no início e fim).")
    @Size(min = 3, max = 60, message = "No mínimo 3 caracteres e no máximo 60!")
    @Column(nullable = false)
    private String autor;

    @Range(min = 1, message = "Deve possuir pelo menos 1 livro")
    @Column(nullable = false)
    private int quantidade;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotNull(message = "A data não pode ser vazia.")
    @Column(nullable = false)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate lancamento;

    private int alugados;
    private int totalAlugado;

    @ManyToOne
    @JsonIgnoreProperties("livros")
    @JoinColumn(name = "editora")
    @NotNull(message = "Esse campo não pode ser vazio.")
    private Editora editora;
}
