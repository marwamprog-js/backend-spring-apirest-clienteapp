package io.github.marwamprogjs.model.repository;

import io.github.marwamprogjs.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
