package org.github.jamiebuckley.quinqueapi.repositories;

import org.github.jamiebuckley.quinqueapi.models.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GameRepository extends JpaRepository<Game, UUID> {

}
