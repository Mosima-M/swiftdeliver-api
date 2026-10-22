package za.co.swiftdeliver.repository;

import org.junit.jupiter.api.Test;
import za.co.swiftdeliver.model.Hub;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InMemoryHubRepositoryTest {

    @Test
    void shouldReturnEmptyListForNewRepository() {

        InMemoryHubRepository repo = new InMemoryHubRepository();

        List<Hub> result = repo.getAllHubs();
        assertTrue(result.isEmpty());
    }
    @Test
    void shouldRetrieveSavedHub() {
        InMemoryHubRepository repo = new InMemoryHubRepository();
        Hub hub = new Hub("H-001","Gauteng", "Pretoria", true);
        repo.saveHub(hub);
        Optional<Hub> result = repo.getHub("H-001");
        assertTrue(result.isPresent());
        assertEquals("H-001", result.get().getHubId());
    }

    @Test
    void shouldReturnEmptyOptionalWhenHubDoesNotExist() {

        InMemoryHubRepository repo = new InMemoryHubRepository();
        Optional<Hub> result = repo.getHub("H-999");
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldReplaceHubWhenIdAlreadyExists() {
        InMemoryHubRepository repo = new InMemoryHubRepository();
        Hub hub1 = new Hub("H-001", "Gauteng", "Pretoria", true);
        Hub hub2 = new Hub("H-001", "Gauteng", "Joburg", true);

        repo.saveHub(hub1);
        repo.saveHub(hub2);
        Optional<Hub> result = repo.getHub("H-001");

        assertTrue(result.isPresent());
        assertEquals("Joburg", result.get().getSortingCenter());
    }
}
