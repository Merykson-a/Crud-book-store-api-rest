package com.wdalivrariacrudapp.controller;

import com.wdalivrariacrudapp.domain.Aluguel;
import com.wdalivrariacrudapp.domain.EstatisticaAluguel;
import com.wdalivrariacrudapp.domain.Quantidade;
import com.wdalivrariacrudapp.repository.AluguelRepository;
import com.wdalivrariacrudapp.repository.EditoraRepository;
import com.wdalivrariacrudapp.repository.LivroRepository;
import com.wdalivrariacrudapp.repository.UsuarioRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@Api(value = "API REST Estatísticas")
@CrossOrigin(origins = "*")
public class EstatisticaController {

    @Autowired
    private AluguelRepository aluguelRepository;

    @Autowired
    private EditoraRepository editoraRepository;

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;


    @GetMapping("/estatistica/total")
    @ApiOperation(value = "Retorna a quantidade total de cada tabela")
    public Quantidade total(Quantidade quantidade){
        quantidade.setQtdE(editoraRepository.count());
        quantidade.setQtdA(aluguelRepository.count());
        quantidade.setQtdL(livroRepository.count());
        quantidade.setQtdU(usuarioRepository.count());
        return quantidade;
    }

    @GetMapping("/estatistica/aluguel")
    @ApiOperation(value = "Retorna a dados estatísticos sobre aluguéis")
    public EstatisticaAluguel statistic(EstatisticaAluguel estatisticaAluguel){
        estatisticaAluguel.setNoPrazo(aluguelRepository.noPrazo());
        estatisticaAluguel.setEntregueA(aluguelRepository.entregueA());
        estatisticaAluguel.setEntregueP(aluguelRepository.entregueP());
        return estatisticaAluguel;
    }
    @GetMapping("/estatistica/ultimosDados")
    @ApiOperation(value = "Retorna os ultimos 5 aluguéis realizados")
    public List<Aluguel> ultimosAlugueis(){
        return aluguelRepository.ultimosAlugueis();
    }
}
