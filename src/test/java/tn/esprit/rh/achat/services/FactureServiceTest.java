package tn.esprit.rh.achat.services;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.rh.achat.entities.Facture;

import tn.esprit.rh.achat.services.IFactureService;

import java.text.ParseException;
import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FactureServiceTest {
   @Autowired
    IFactureService fs;
    @Test
    @Order(1)
    public void testRetrieveAllFactures() {
        Assertions.assertNotNull(fs.retrieveAllFactures());
    }

    @Test
    @Order(2)
    public void testAddFacture() throws ParseException {
        Facture f = new Facture();
        f.setMontantRemise(0);
        f.setMontantFacture(10);
        f.setDateDerniereModificationFacture(null);
        f.setDateCreationFacture(new java.util.Date());
        Facture factureAdded = fs.addFacture(f);
        Assertions.assertEquals(f.getMontantFacture(), factureAdded.getMontantFacture());

    }

}
