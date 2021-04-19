package com.wdalivrariacrudapp.controller;


import com.wdalivrariacrudapp.domain.Editora;
import com.wdalivrariacrudapp.domain.Usuario;
import com.wdalivrariacrudapp.repository.EditoraRepository;
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
@Api(value="API REST Editoras")
@CrossOrigin(origins = "*")
public class EditoraController {

    @Autowired
    private EditoraRepository editoraRepository;

    @GetMapping("/editora")
    @ApiOperation(value="Retorna uma lista de editoras")
    public List<Editora> listarE(){
        return editoraRepository.findAll();
    }

    @GetMapping("/editora/{id}")
    @ApiOperation(value="Retorna uma editora única")
    public Editora listarPorIdE(@PathVariable(value = "id") long id) {
        return editoraRepository.findById(id);
    }

    @PostMapping("/editora")
    @ApiOperation(value="Salva uma nova editora")
    public ResponseEntity<Editora> salvarE(@RequestBody @Valid Editora editora){
            return new ResponseEntity<>(editoraRepository.save(editora), HttpStatus.OK);
    }

    @DeleteMapping("/editora")
    @ApiOperation(value="Deleta uma editora")
    public void excluirE(@Valid @RequestBody Editora editora){
        editoraRepository.delete(editora);
    }

    @PutMapping("/editora")
    @ApiOperation(value="Atualiza uma editora")
    public ResponseEntity<Editora> atualizarE(@Valid @RequestBody Editora editora){
        return new ResponseEntity<>(editoraRepository.save(editora), HttpStatus.OK);
    }
}
