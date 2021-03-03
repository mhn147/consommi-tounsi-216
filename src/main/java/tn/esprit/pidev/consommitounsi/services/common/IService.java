package tn.esprit.pidev.consommitounsi.services.common;

import tn.esprit.pidev.consommitounsi.entities.payment.Item;

import java.util.List;

public interface IService<T > {
    List<T> getAll();
    T getById(Long id);
    T update(T item, Long id);
    void Remove(Long id);
}
