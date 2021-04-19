package com.wdalivrariacrudapp.repository;

import com.wdalivrariacrudapp.domain.Editora;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EditoraRepository extends JpaRepository<Editora, Long> {

    Editora findById(long id);
}
