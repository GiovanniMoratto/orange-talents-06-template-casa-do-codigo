package br.com.zupacademy.giovannimoratto.casadocodigo.state;

import br.com.zupacademy.giovannimoratto.casadocodigo.country.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;

/**
 * @Author giovanni.moratto
 */

@RestController
@RequestMapping("/estado") // Endpoint
public class StateController {

    /* Dependencies Injection */
    @Autowired
    private StateRepository repository;
    @Autowired
    private CountryRepository countryRepository;

    /* Methods */
    // POST Request - Register a state
    @PostMapping
    @Transactional
    public void addState(@RequestBody @Valid StateRequest request) {
        StateModel state = request.toModel(countryRepository);
        repository.save(state);
    }

}