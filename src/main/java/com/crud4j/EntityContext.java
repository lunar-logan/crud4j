package com.crud4j;

import net.sf.jsqlparser.statement.create.table.CreateTable;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Dell on 22-04-2017.
 */
public class EntityContext {
    private Class entityClass;
    private CreateTable tableDef;
    private Class<? extends CrudRepository> repositoryClass;

    public Class getEntityClass() {
        return entityClass;
    }

    public void setEntityClass(Class entityClass) {
        this.entityClass = entityClass;
    }

    public CreateTable getTableDef() {
        return tableDef;
    }

    public void setTableDef(CreateTable tableDef) {
        this.tableDef = tableDef;
    }

    public Class<? extends CrudRepository> getRepositoryClass() {
        return repositoryClass;
    }

    public void setRepositoryClass(Class<? extends CrudRepository> repositoryClass) {
        this.repositoryClass = repositoryClass;
    }

    public static void main(String[] args) {
    }
}
