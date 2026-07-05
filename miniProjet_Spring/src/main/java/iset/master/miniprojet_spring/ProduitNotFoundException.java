package iset.master.miniprojet_spring;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProduitNotFoundException extends RuntimeException {

    public ProduitNotFoundException(Long id) {
        super("Produit avec id " + id + " introuvable");
    }
}
