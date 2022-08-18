package br.com.company.magazine.controller;

import br.com.company.magazine.model.Catalogo;
import br.com.company.magazine.repositories.CatalogoRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@AllArgsConstructor
public class CatalogoController {

    private CatalogoRepository repository;

    @GetMapping("/status")
    public String status() {
        return "Online";
    }

    @Transactional
    @PostMapping("/magazine")
    public ResponseEntity<Catalogo> saveMagazine(@RequestBody @Validated Catalogo catalogo) {
        return new ResponseEntity<Catalogo>(repository.save(catalogo), HttpStatus.OK);
    }

    @DeleteMapping("/magazine/{id}")
    public void deleteMagazine(@PathVariable Long id) {
        repository.deleteById(id);
    }

    @Transactional
    @GetMapping("/magazine")
    public ResponseEntity<List<Catalogo>> getAllRevista() {
        List<Catalogo> lista = repository.findAll();
        if (lista.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            for (Catalogo catalogo : lista) {
                long id = catalogo.getId();
                catalogo.add(linkTo(methodOn(CatalogoController.class).getOneRevista(id)).withSelfRel());
            }
            return new ResponseEntity<List<Catalogo>>(lista, HttpStatus.OK);
        }

    }

    @GetMapping("/magazine/{id}")
    public ResponseEntity<Catalogo> getOneRevista(@PathVariable(value = "id") long id) {
        Optional<Catalogo> revistas = repository.findById(id);
        if (!revistas.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            revistas.get().add(linkTo(methodOn(CatalogoController.class).getAllRevista()).withRel("Lista de revistas"));
            return new ResponseEntity<Catalogo>(revistas.get(), HttpStatus.OK);
        }


    }
}


