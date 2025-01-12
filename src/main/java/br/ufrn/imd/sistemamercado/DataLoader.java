package br.ufrn.imd.sistemamercado;


import br.ufrn.imd.sistemamercado.enums.UserRole;
import br.ufrn.imd.sistemamercado.model.UserEntity;
import br.ufrn.imd.sistemamercado.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (repository.count() == 0) {
            UserEntity admin = new UserEntity("admin", passwordEncoder.encode("admin"), UserRole.ADMIN);
            repository.save(admin);
        }
    }
}
