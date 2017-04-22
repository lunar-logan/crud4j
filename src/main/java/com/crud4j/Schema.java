package com.crud4j;

/**
 * Created by Dell on 21-04-2017.
 */
public interface Schema {
    /**
     * Returns the number of columns in the schema
     *
     * @return
     */
    int count();

    Iterable<TypeDefn> getColumns();
}
