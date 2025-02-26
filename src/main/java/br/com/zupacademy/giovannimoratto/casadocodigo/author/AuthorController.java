package br.com.zupacademy.giovannimoratto.casadocodigo.author;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

/**
 * @Author giovanni.moratto
 */

@RestController
@RequestMapping("/autor") // Endpoint
public class AuthorController {

    @PersistenceContext
    private EntityManager em;

    /* Methods */
    // POST Request - Register an author
    @PostMapping
    @Transactional
    public void addAuthor(@RequestBody @Valid AuthorRequest request) {
        AuthorModel author = request.toModel();
        em.persist(author);
    }

}