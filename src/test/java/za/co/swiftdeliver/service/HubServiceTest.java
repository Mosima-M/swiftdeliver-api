package za.co.swiftdeliver.service;

import org.junit.jupiter.api.Test;
import za.co.swiftdeliver.exception.DuplicateHubException;
import za.co.swiftdeliver.exception.HubNotFoundException;
import za.co.swiftdeliver.model.Hub;
import za.co.swiftdeliver.repository.HubRepository;
import za.co.swiftdeliver.repository.InMemoryHubRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class HubServiceTest {
  @Test
    void shouldCreateHubWhenIdDoesNotExist() {
        HubRepository repo = new InMemoryHubRepository();
        HubService service = new HubService(repo);
        Hub hub = new Hub("H-001", "Gauteng", "Pretoria", true);
        service.createHub(hub);
        Optional<Hub> result = repo.getHub("H-001");
        assertTrue(result.isPresent());
        assertEquals("H-001", result.get().getHubId());
    }

    @Test
    void shouldRejectDuplicateHubId() {
        HubRepository repo = new InMemoryHubRepository();
        HubService service = new HubService(repo);
        Hub originalHub = new Hub("H-001", "Gauteng", "Pretoria", true);
        Hub duplicateHub = new Hub("H-001", "Gauteng", "Joburg", true);
        service.createHub(originalHub);

        assertThrows(DuplicateHubException.class, () -> service.createHub(duplicateHub));

        Optional<Hub> result = repo.getHub("H-001");

        assertTrue(result.isPresent());
        assertEquals("Pretoria", result.get().getSortingCenter());
    }

    @Test
    void shouldReturnEmptyListWhenNoHubsExist() {
        HubRepository repo = new InMemoryHubRepository();
        HubService service = new HubService(repo);

        List<Hub> result = service.getAllHubs();

        assertTrue(result.isEmpty());
    }

    @Test
    void shouldReturnAllHubsWhenRecordsExist() {
        HubRepository repo = new InMemoryHubRepository();
        HubService service = new HubService(repo);
        Hub hub1 = new Hub("H-001", "Gauteng", "Pretoria", true);
        Hub hub2 = new Hub("H-002", "Gauteng", "Joburg", true);

        service.createHub(hub1);
        service.createHub(hub2);
       int number = service.getAllHubs().size();

        assertEquals(2,number);
    }

    @Test
    void shouldReturnHubWhenIdExists() {

        HubRepository repo = new InMemoryHubRepository();
        HubService service = new HubService(repo);
        Hub hub1 = new Hub("H-001", "Gauteng", "Pretoria", true);
        service.createHub(hub1);
        Hub result = service.getHubById("H-001");

        assertEquals("H-001",result.getHubId());
    }

    @Test
    void shouldThrowExceptionWhenHubDoesNotExist() {
        HubRepository repo = new InMemoryHubRepository();
        HubService service = new HubService(repo);

        assertThrows(HubNotFoundException.class, () ->service.getHubById("H-999"));
    }
}
