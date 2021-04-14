package tn.esprit.pidev.consommitounsi.services.payment.interfaces;

import tn.esprit.pidev.consommitounsi.models.payment.ValidationResult;

public interface IEntityValidator<T> {
    ValidationResult validateExistence(Long id);
    ValidationResult validateInput(T entity);
}
