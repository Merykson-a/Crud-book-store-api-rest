package com.wdalivrariacrudapp.controller;

import com.wdalivrariacrudapp.domain.Aluguel;
import com.wdalivrariacrudapp.domain.Livro;
import com.wdalivrariacrudapp.domain.Status;
import com.wdalivrariacrudapp.repository.AluguelRepository;
import com.wdalivrariacrudapp.repository.LivroRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@Api(value = "API REST Aluguéis")
@CrossOrigin(origins = "*")
public class AluguelController {

    @Autowired
    private AluguelRepository aluguelRepository;

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private LivroController livroController;

    @GetMapping("/aluguel")
    @ApiOperation(value = "Retorna uma lista de aluguèis")
    public List<Aluguel> listarA() {
        return aluguelRepository.findAll();
    }

    @PostMapping("/aluguel")
    @ApiOperation(value = "Salva um novo aluguel")
    public Aluguel salvarA(@Valid @RequestBody Aluguel aluguel){
        Livro livro = livroRepository.findById(aluguel.getLivro().getId());
        livro.setAlugados(livro.getAlugados() + 1);
        livroController.atualizarL(livro);
        aluguel.setStatus(Status.EM_ANDAMENTO);
        return aluguelRepository.save(aluguel);
    }

    @DeleteMapping("/aluguel")
    @ApiOperation(value = "Deleta um aluguel")
    public void excluirA(@Valid @RequestBody Aluguel aluguel) {
        aluguelRepository.delete(aluguel);
    }

    @PutMapping("/aluguel")
    @ApiOperation(value = "Atualiza um aluguel")
    public Aluguel atualizarA(@Valid @RequestBody Aluguel aluguel) {
        return aluguelRepository.save(aluguel);
    }

    @PutMapping("/aluguel/devolver")
    @ApiOperation(value = "Devolve um livro")
    public ResponseEntity<Aluguel> devolverA(@Valid @RequestBody Aluguel aluguel){

        if(!aluguel.getDataDevolucao().isBefore(aluguel.getDataAluguel())){
            if(aluguel.getDataDevolucao().isAfter(aluguel.getPrevDataDevolucao())){
                aluguel.setStatus(Status.ENTREGUE_ATRASADO);
            }
            else {
                aluguel.setStatus(Status.ENTREGUE_PRAZO);
            }
            Livro livro = livroRepository.findById(aluguel.getLivro().getId());
            livro.setAlugados(livro.getAlugados() - 1);
            livroController.atualizarL(livro);
            return new ResponseEntity<>(aluguelRepository.save(aluguel), HttpStatus.OK);
        }
        else {
            return new ResponseEntity("A data de devolução não pode ser anterior a data do aluguel!", HttpStatus.BAD_REQUEST); }
        }
}
