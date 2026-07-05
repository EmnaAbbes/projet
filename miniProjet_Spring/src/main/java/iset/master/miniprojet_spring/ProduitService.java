package iset.master.miniprojet_spring;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProduitService {

    private final ProduitRepository produitRepository;

    public ProduitService(ProduitRepository produitRepository) {
        this.produitRepository = produitRepository;
    }

    public List<Produit> findAll() {
        return produitRepository.findAll();
    }

    public Produit findById(Long id) {
        return produitRepository.findById(id)
                .orElseThrow(() -> new ProduitNotFoundException(id));
    }

    public Produit create(Produit produit) {
        produit.setId(null);
        return produitRepository.save(produit);
    }

    public Produit update(Long id, Produit produit) {
        Produit existingProduit = findById(id);
        existingProduit.setNom(produit.getNom());
        existingProduit.setPrix(produit.getPrix());
        return produitRepository.save(existingProduit);
    }

    public void delete(Long id) {
        Produit existingProduit = findById(id);
        produitRepository.delete(existingProduit);
    }
}
