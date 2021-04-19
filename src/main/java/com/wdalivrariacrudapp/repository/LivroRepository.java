package com.wdalivrariacrudapp.repository;

import com.wdalivrariacrudapp.domain.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LivroRepository extends JpaRepository<Livro, Long> {

    @Query("select a from Livro a where a.quantidade > a.alugados")
    List<Livro> findDisponivel();

    Livro findById(long id);
}
