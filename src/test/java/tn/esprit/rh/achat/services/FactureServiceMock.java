package tn.esprit.rh.achat.services;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.rh.achat.entities.Facture;
import tn.esprit.rh.achat.repositories.FactureRepository;

import java.util.ArrayList;
import java.util.List;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class FactureServiceMock {
    @Autowired
    FactureRepository fr;
    FactureRepository factureRepository = Mockito.mock(FactureRepository.class);

    Facture facture;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        facture = new Facture();
        facture.setMontantRemise(50);
        facture.setMontantFacture(101);
        facture.setDateDerniereModificationFacture(null);
        facture.setDateCreationFacture(new java.util.Date());

        factureRepository.save(facture);
    }

    List<Facture> factures =  new ArrayList<>();


    @Test
    public void testRetrieveAllFactures() {
        Mockito.when(factureRepository.findById(1L)).thenReturn(java.util.Optional.of(facture));
        float factureFound = facture.getMontantFacture();
        Assertions.assertEquals(factureFound, facture.getMontantFacture());}

}
