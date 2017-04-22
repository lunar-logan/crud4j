package com.crud4j;

import java.util.List;

/**
 * Created by Dell on 21-04-2017.
 */
public interface Node {
    List<Node> getChildren();

    int count();

    Schema getSchema();

    Iterable<Row> getData();
}
