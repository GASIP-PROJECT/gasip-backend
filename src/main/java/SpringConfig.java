import com.example.gasip.entity.ProfessorEntity;
import com.example.gasip.repository.ProfessorRepository;
import com.example.gasip.service.ProfessorService;
import jakarta.persistence.EntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private final ProfessorRepository professorRepository;

    public SpringConfig(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    @Bean
    public ProfessorService professorService() {
        return new ProfessorService(professorRepository);
    }


//    private final DataSource dataSource;
//    private final EntityMana
//    public SpringConfig(DataSource dataSource, EntityManager em) {
//        this.dataSource = dataSource;
//        this.em = em;
//    }
//
//    @Bean
//    public ProfessorService professorService() {
//        return new ProfessorService(ProfessorRepository());
//    }
//    @Bean
//    public ProfessorRepository professorRepository() {
//        return new JpaRepository<em>();
//    }
//
//
//    @Bean
//    public ProfessorRepository professorRepository(){
//        return new JpaProfessorRepository(em);
//    }

}
