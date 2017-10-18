package pl.gda.wsb.markiet.db;

import org.springframework.data.repository.CrudRepository;

/**
 * This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
 * CRUD refers Create, Read, Update, Delete
 *
 * @author Piotr Czapiewski
 */
public interface OfferRepository extends CrudRepository<Offer, Long> {
    
}
