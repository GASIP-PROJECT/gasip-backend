package com.example.gasip.repository;

import com.example.gasip.entity.ProfessorEntity;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class JpaProfessorRepository implements ProfessorRepository{
    private final EntityManager em;
    public JpaProfessorRepository(EntityManager em) {
        this.em = em;
    }


    public List<ProfessorEntity> findAll() {
        return em.createQuery("select m from ProfessorEntity m", ProfessorEntity.class)
                .getResultList();
    }

    @Override
    public Optional<ProfessorEntity> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public <S extends ProfessorEntity> S save(S entity) {
        return null;
    }

    @Override
    public <S extends ProfessorEntity> List<S> saveAll(Iterable<S> entities) {
        return null;
    }


    @Override
    public List<ProfessorEntity> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(ProfessorEntity entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends ProfessorEntity> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends ProfessorEntity> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends ProfessorEntity> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<ProfessorEntity> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public ProfessorEntity getOne(Long aLong) {
        return null;
    }

    @Override
    public ProfessorEntity getById(Long aLong) {
        return null;
    }

    @Override
    public ProfessorEntity getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends ProfessorEntity> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends ProfessorEntity> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends ProfessorEntity> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends ProfessorEntity> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }


    @Override
    public <S extends ProfessorEntity> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends ProfessorEntity> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends ProfessorEntity, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public List<ProfessorEntity> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<ProfessorEntity> findAll(Pageable pageable) {
        return null;
    }
}
