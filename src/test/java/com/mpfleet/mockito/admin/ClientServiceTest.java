package com.mpfleet.mockito.admin;

import com.mpfleet.admin.models.Client;
import com.mpfleet.admin.models.Country;
import com.mpfleet.admin.models.State;
import com.mpfleet.admin.repositories.ClientRepository;
import com.mpfleet.admin.services.ClientService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientService clientService;

    private final Country country1 = new Country("USA", "United States of America", "North America", "American", "NA", null);
    private final Country country2 = new Country("CAN", "Canada", "North America", "Canadian", "NA", null);
    private final State state1 = new State("Berlin", "0030", country1 ,"Capital of Germany");
    private final State state2 = new State("Rome", "0006", country2,"Capital of Italy");

    @Test
    public void testFindAll() {
        List<Client> expectedClients = Arrays.asList(
                new Client("Acme Corporation","123 Main St","555-123-4567","555-987-6543","www.acmecorp.com","info@acmecorp.com",country1,state1,"Acme Corporation is a global leader in industrial supplies and manufacturing."),
                new Client("Stellar Designs","456 Park Ave, Suite 100","555-123-4567","555-987-6543","www.stellardesigns.com","contact@stellardesigns.com",country2,state2,"Stellar Designs is a high-end fashion and accessory retailer with a focus on sustainable materials and ethical production.")
        );

        Mockito.when(clientRepository.findAll()).thenReturn(expectedClients);

        List<Client> actualClients = clientService.findAll();

        Assert.assertEquals(expectedClients, actualClients);
        Mockito.verify(clientRepository).findAll();
    }

    @Test
    public void testFindById() {
        Client expectedClient = new Client("Acme Corporation","123 Main St","555-123-4567","555-987-6543","www.acmecorp.com","info@acmecorp.com",country1,state1,"Acme Corporation is a global leader in industrial supplies and manufacturing.");
        Mockito.when(clientRepository.findById(expectedClient.getId())).thenReturn(Optional.of(expectedClient));

        Client actualClient = clientService.findById(expectedClient.getId());

        Assert.assertEquals(expectedClient, actualClient);
        Mockito.verify(clientRepository).findById(expectedClient.getId());
    }

    @Test
    public void testSave() {
        Client clientToSave = new Client(null,"123 Main St","555-123-4567","555-987-6543","www.acmecorp.com","info@acmecorp.com",country1,state1,"Acme Corporation is a global leader in industrial supplies and manufacturing.");
        Client savedClient = new Client("Acme Corporation","123 Main St","555-123-4567","555-987-6543","www.acmecorp.com","info@acmecorp.com",country1,state1,"Acme Corporation is a global leader in industrial supplies and manufacturing.");
        Mockito.when(clientRepository.save(clientToSave)).thenReturn(savedClient);

        clientService.save(clientToSave);

        Mockito.verify(clientRepository).save(clientToSave);
    }

    @Test
    public void testDeleteById() {
        Long idToDelete = 1L;
        clientService.deleteById(idToDelete);
        Mockito.verify(clientRepository).deleteById(idToDelete);
    }

    @Test
    public void testFindByKeyword() {
        List<Client> expectedClients = Arrays.asList(
                new Client("Acme Corporation","123 Main St","555-123-4567","555-987-6543","www.acmecorp.com","info@acmecorp.com",country1,state1,"Acme Corporation is a global leader in industrial supplies and manufacturing."),
                new Client("Stellar Designs","456 Park Ave, Suite 100","555-123-4567","555-987-6543","www.stellardesigns.com","contact@stellardesigns.com",country2,state2,"Stellar Designs is a high-end fashion and accessory retailer with a focus on sustainable materials and ethical production.")
        );

        Mockito.when(clientRepository.findByKeyword("Acme Corporation")).thenReturn(expectedClients);

        List<Client> actualClients = clientService.findByKeyword("Acme Corporation");

        Assert.assertEquals(expectedClients, actualClients);
        Mockito.verify(clientRepository).findByKeyword("Acme Corporation");
    }
}
