package com.wdalivrariacrudapp.controller;


import com.wdalivrariacrudapp.domain.Usuario;
import com.wdalivrariacrudapp.repository.UsuarioRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@Api(value="API REST Usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/usuario")
    @ApiOperation(value="Retorna uma lista de usuários")
    public List<Usuario> listarU() {
        return usuarioRepository.findAll();
    }

    @PostMapping("/usuario")
    @ApiOperation(value="Salva um novo usuário")
    public Usuario salvarU(@RequestBody @Valid Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @DeleteMapping("/usuario")
    @ApiOperation(value="Deleta um usuário")
    public void excluirU(@RequestBody Usuario usuario){
        usuarioRepository.delete(usuario);
    }

    @PutMapping("/usuario")
    @ApiOperation(value="Atualiza um usuário")
    public Usuario atualizarU(@RequestBody @Valid Usuario usuario){
        return usuarioRepository.save(usuario);
    }
}
