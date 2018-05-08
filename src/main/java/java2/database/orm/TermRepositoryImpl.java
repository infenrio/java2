package java2.database.orm;

import java2.database.TermRepository;
import java2.domain.Term;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Optional;

@Component
public class TermRepositoryImpl extends ORMRepository implements TermRepository {

    @Override
    public Optional<Term> findById(int id) {
        CriteriaQuery<Term> criteriaQuery = criteriaBuilder().createQuery(Term.class);
        Root<Term> root = criteriaQuery.from(Term.class);
        criteriaQuery.select(root).where(criteriaBuilder().equal(root.get("id"), id));
        Query<Term> q = session().createQuery(criteriaQuery);
        Term term = q.getSingleResult();
        return Optional.ofNullable(term);
    }
}
