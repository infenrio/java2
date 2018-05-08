package java2.database;

import java2.domain.Term;

import java.util.Optional;

public interface TermRepository {
    Optional<Term> findById(int id);
}
