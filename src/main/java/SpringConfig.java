import com.example.gasip.repository.JpaProfessorRepository;
import com.example.gasip.service.ProfessorService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.example.gasip.repository.ProfessorRepository;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {
    private final DataSource dataSource;
    private final EntityManager em;
    public SpringConfig(DataSource dataSource, EntityManager em) {
        this.dataSource = dataSource;
        this.em = em;
    }

    @Bean
    ProfessorService professorService() {
        return new ProfessorService(professorRepository());
    }

    @Bean
    public ProfessorRepository professorRepository(){
        return new JpaProfessorRepository(em);
    }

}
