package crudjava.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import crudjava.models.User;

public interface UserRepository extends JpaRepository<User, Long> {}
