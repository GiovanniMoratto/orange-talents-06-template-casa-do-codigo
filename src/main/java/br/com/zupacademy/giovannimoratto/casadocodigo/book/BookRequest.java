package br.com.zupacademy.giovannimoratto.casadocodigo.book;

import br.com.zupacademy.giovannimoratto.casadocodigo.author.AuthorModel;
import br.com.zupacademy.giovannimoratto.casadocodigo.author.AuthorRepository;
import br.com.zupacademy.giovannimoratto.casadocodigo.category.CategoryModel;
import br.com.zupacademy.giovannimoratto.casadocodigo.category.CategoryRepository;
import br.com.zupacademy.giovannimoratto.casadocodigo.validation.annotations.ExistsId;
import br.com.zupacademy.giovannimoratto.casadocodigo.validation.annotations.UniqueValue;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

/**
 * @Author giovanni.moratto
 */

public class BookRequest {

    /* Attributes */
    @NotBlank
    @UniqueValue(attributeName = "title", entityName = BookModel.class)
    private String title;
    @NotBlank
    @Size(max = 500)
    private String overview;
    private String summary;
    @NotNull
    @Min(20)
    private BigDecimal price;
    @NotNull
    @Min(100)
    private Integer numberOfPages;
    @NotBlank
    @UniqueValue(attributeName = "isbn", entityName = BookModel.class)
    private String isbn;
    @Future
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate publicationDate;
    @NotNull
    @ExistsId(className = CategoryModel.class)
    private Long idCategory;
    @NotNull
    @ExistsId(className = AuthorModel.class)
    private Long idAuthor;

    /* Methods */
    // Convert BookRequest.class in BookModel.class
    public BookModel toModel(AuthorRepository authorRepository, CategoryRepository categoryRepository) throws ResponseStatusException {
        Optional <CategoryModel> categoryOptional = categoryRepository.findById(idCategory);
        Optional <AuthorModel> authorOptional = authorRepository.findById(idAuthor);
        CategoryModel category;
        AuthorModel author;
        if (categoryOptional.isPresent() & authorOptional.isPresent()) {
            category = categoryOptional.get();
            author = authorOptional.get();
        }
        else {
            category = categoryOptional.orElse(null);
            author = authorOptional.orElse(null);
        }
        return new BookModel(
                title,
                overview,
                summary,
                price,
                numberOfPages,
                isbn,
                publicationDate,
                category,
                author
        );

    }

    /* Getters and Setters */
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(Integer numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public Long getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Long idCategory) {
        this.idCategory = idCategory;
    }

    public Long getIdAuthor() {
        return idAuthor;
    }

    public void setIdAuthor(Long idAuthor) {
        this.idAuthor = idAuthor;
    }

}