package pe.com.bcp.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.com.bcp.api.entidad.Moneda;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface MonedaRepository extends JpaRepository<Moneda, String> {

    @Query("SELECT o FROM Moneda o where o.nombre in :nombre")
    List<Moneda> findByNombre(@Param("nombre") List<String> nombre);

    @Modifying
    @Transactional
    @Query("UPDATE Moneda SET tipo = :tipo WHERE nombre = :nombre")
    void update(@Param("nombre") String nombre, @Param("tipo") String tipo);

    List<Moneda> findAll();

}
