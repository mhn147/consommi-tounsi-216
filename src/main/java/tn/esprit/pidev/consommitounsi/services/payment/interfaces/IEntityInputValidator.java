package tn.esprit.pidev.consommitounsi.services.payment.interfaces;

import tn.esprit.pidev.consommitounsi.models.payment.ValidationResult;

public interface IEntityInputValidator<T> {
    ValidationResult validate(T entity);
}
