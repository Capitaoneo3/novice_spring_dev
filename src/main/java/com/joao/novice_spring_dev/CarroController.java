package com.joao.novice_spring_dev;

import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class CarroController {

    CarroRepository repository;

    @GetMapping("/carro")
    public List<Carro> getAllCarros(){
        return repository.findAll();
    }
    @GetMapping("/carro/{id}")
    public Carro getCarroById(@PathVariable Long id){
        return repository.findById(id).orElse(null);
    }
    // @PostMapping("/carro")
    // public Carro saveCarro(@RequestBody Carro carro){
    //     return repository.save(carro);
    // }
    @PostMapping("/carro")
    public ResponseEntity<Carro> saveCarro(@RequestBody Carro carro){
        Carro newCarro = repository.save(carro);
        return new ResponseEntity<Carro>(newCarro, HttpStatus.CREATED);
    }

    @DeleteMapping("/carro/{id}")
    public ResponseEntity<String> deleteCarro(@PathVariable Long id){
        StatusBody sBody = new StatusBody();

        if (repository.findById(id).orElse(null) != null) {
            repository.deleteById(id);
            sBody.status = "Deletado com sucesso";
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "text/plain");
            headers.add("X-Custom-Header", "exemplo");
            ResponseEntity  myResponse =  new ResponseEntity<>(sBody.status, headers, HttpStatus.ACCEPTED);
            return myResponse;

        }else{
            sBody.status = "Não foi possível deletar: Carro não encontrado.";
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "text/plain");
            headers.add("X-Custom-Header", "exemplo");
            ResponseEntity  myResponse =  new ResponseEntity<>(sBody.status, headers, HttpStatus.OK);

            return myResponse;
        }
        // HttpStatus negativos podem não retornar o corpo e causar exceção.
    }
}