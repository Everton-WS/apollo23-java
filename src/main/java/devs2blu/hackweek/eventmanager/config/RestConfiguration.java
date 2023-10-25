package devs2blu.hackweek.eventmanager.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.metamodel.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

/**
 * Configuration class for exposing identifiers (IDs) of persisted data entities in a Spring Data REST application.
 *
 * This class implements the RepositoryRestConfigurer interface to configure the behavior of Spring Data REST.
 * It specifically exposes IDs for entities to enable their identification in API responses.
 *
 * The main purpose of this configuration is to ensure that the unique identifiers (IDs) of data entities are
 * included in the responses of Spring Data REST endpoints, allowing clients to easily reference and work with
 * specific data records.
 *
 * This configuration class relies on an EntityManager to access entity metamodel information and determine which
 * entities' IDs should be exposed.
 *
 * @see RepositoryRestConfigurer
 */
@Configuration
public class RestConfiguration implements RepositoryRestConfigurer {

    @Autowired
    private EntityManager entityManager;

    @Override
    public void configureRepositoryRestConfiguration(
            RepositoryRestConfiguration config, CorsRegistry cors) {
        Class[] classes = entityManager.getMetamodel()
                .getEntities().stream().map(Type::getJavaType).toArray(Class[]::new);
        config.exposeIdsFor(classes);
    }
}
