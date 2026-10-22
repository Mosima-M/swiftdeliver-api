package za.co.swiftdeliver.service;

import org.junit.jupiter.api.Test;
import za.co.swiftdeliver.model.Hub;
import za.co.swiftdeliver.repository.HubRepository;
import za.co.swiftdeliver.repository.InMemoryHubRepository;

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

        assertThrows(IllegalArgumentException.class, () -> service.createHub(duplicateHub));

        Optional<Hub> result = repo.getHub("H-001");

        assertTrue(result.isPresent());
        assertEquals("Pretoria", result.get().getSortingCenter());
    }
}
