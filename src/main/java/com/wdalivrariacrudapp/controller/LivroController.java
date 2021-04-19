package com.wdalivrariacrudapp.controller;


import com.wdalivrariacrudapp.domain.Editora;
import com.wdalivrariacrudapp.domain.Livro;
import com.wdalivrariacrudapp.repository.LivroRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
@Api(value = "API REST Livros")
@CrossOrigin(origins = "*")
public class LivroController {

    @Autowired
    private LivroRepository livroRepository;

    @GetMapping("/livro")
    @ApiOperation(value = "Retorna uma lista de livros")
    public List<Livro> listarL() {
        return livroRepository.findAll();
    }

    @GetMapping("/livro/disponivel")
    @ApiOperation(value = "Retorna uma lista de livros em que possuem quantidade disponível no estoque")
    public List<Livro> listarDisp() {
        return livroRepository.findDisponivel();
    }

    @PostMapping("/livro")
    @ApiOperation(value = "Salva um novo livro")
    public Livro salvarL(@RequestBody @Valid Livro livro) {
        return livroRepository.save(livro);
    }

    @DeleteMapping("/livro")
    @ApiOperation(value = "Deleta um livro")
    public void excluirL(@RequestBody Livro livro) {
        livroRepository.delete(livro);
    }

    @PutMapping("/livro")
    @ApiOperation(value = "Atualiza um livro")
    public ResponseEntity<Livro> atualizarL(@RequestBody @Valid Livro livro) {
        if(livro.getAlugados() > livro.getQuantidade()){
            return new ResponseEntity("A quantidade de livro não pode ser menor do que a de alugados!", HttpStatus.BAD_REQUEST);
        }
        else {
            return new ResponseEntity<>(livroRepository.save(livro), HttpStatus.OK);
        }
    }
}
