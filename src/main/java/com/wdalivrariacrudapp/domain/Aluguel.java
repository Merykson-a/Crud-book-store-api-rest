package com.wdalivrariacrudapp.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Aluguel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotNull(message = "Não pode ser nulo")
    @Column(nullable = false)
    private LocalDate dataAluguel;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataDevolucao;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotNull(message = "Não pode ser nulo")
    @Column(nullable = false)
    private LocalDate prevDataDevolucao;

    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private Livro livro;

    @Enumerated(EnumType.STRING)
    private Status status;

}
