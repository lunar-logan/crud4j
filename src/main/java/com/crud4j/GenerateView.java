package com.crud4j;

import j2html.tags.ContainerTag;
import j2html.tags.EmptyTag;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.create.table.ColumnDefinition;
import net.sf.jsqlparser.statement.create.table.CreateTable;

import java.io.Reader;
import java.io.StringReader;
import java.util.List;

import static j2html.TagCreator.*;

/**
 * Created by Dell on 22-04-2017.
 */
public class GenerateView {
    public String getView(Reader input) throws JSQLParserException {
        Statement statement = CCJSqlParserUtil.parse(input);
        CreateTable createTable = (CreateTable) statement;
        Table table = createTable.getTable();
        List<ColumnDefinition> columnDefinitions = createTable.getColumnDefinitions();

        ContainerTag form = form().attr("action", "/").attr("method", "POST");
        ContainerTag htmlTable = table();
        columnDefinitions.forEach(cdef -> {
            htmlTable.with(
                    tr().with(
                            td().withText(cdef.getColumnName()),
                            td().with(getInput(cdef))
                    )
            );
        });
        form.with(htmlTable);
        return div().with(form).render();
    }

    private static EmptyTag getInput(ColumnDefinition columnDefinition) {
        String dataType = columnDefinition.getColDataType().getDataType();
        String columnName = columnDefinition.getColumnName();
        return input()
                .attr("type", TypeMapping.getInputType(dataType))
                .attr("name", columnName);
    }

    public static void main(String[] args) throws JSQLParserException {
        String s = "create table t(id int not null auto_increment primary key, name varchar(100) not null);";
        String view = new GenerateView().getView(new StringReader(s));
        System.out.println(view);
    }
}
