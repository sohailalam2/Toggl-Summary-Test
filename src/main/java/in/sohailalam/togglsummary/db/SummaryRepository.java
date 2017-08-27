package in.sohailalam.togglsummary.db;

import in.sohailalam.togglsummary.db.schema.SummarySchema;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The Summary repository for MongoDB
 */
@Service
public interface SummaryRepository extends MongoRepository<SummarySchema, Long> {
    @Override
    List<SummarySchema> findAll();

    @Override
    <S extends SummarySchema> List<S> save(Iterable<S> summaryList);
}

