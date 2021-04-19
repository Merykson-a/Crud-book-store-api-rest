package com.wdalivrariacrudapp.repository;

import com.wdalivrariacrudapp.domain.Aluguel;
import com.wdalivrariacrudapp.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AluguelRepository extends JpaRepository<Aluguel, Long> {

    @Query("select count(m) from Aluguel m where m.status = 'EM_ANDAMENTO'")
    public long noPrazo();

    @Query("select count(m) from Aluguel m where m.status = 'ENTREGUE_PRAZO'")
    public long entregueP();

    @Query("select count(m) from Aluguel m where m.status = 'ENTREGUE_ATRASADO'")
    public long entregueA();

    @Query(value="select * from Aluguel ORDER BY data_aluguel DESC limit 4", nativeQuery=true)
    List<Aluguel> ultimosAlugueis();
}
