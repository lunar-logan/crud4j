package com.crud4j;

import net.sf.jsqlparser.statement.create.table.ColumnDefinition;
import net.sf.jsqlparser.statement.create.table.CreateTable;
import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.jboss.forge.roaster.model.source.MethodSource;
import org.jboss.forge.roaster.model.source.ParameterSource;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * Created by Dell on 22-04-2017.
 */
public class GenerateController {
    private EntityContext entityContext;
    private String packageName;

    public GenerateController(EntityContext entityContext, String packageName) {
        this.entityContext = entityContext;
        this.packageName = packageName;
    }

    private String getName() {
        String name = entityContext.getEntityClass().getSimpleName();
        return name + "Controller";
    }

    public String generate() {
        JavaClassSource javaClass = Roaster.create(JavaClassSource.class);
        javaClass.setPackage(packageName).setName(getName());

        javaClass.addAnnotation(RestController.class);
        javaClass.addField()
                .setName("serialVersionUID")
                .setType("long")
                .setLiteralInitializer("1L")
                .setPrivate()
                .setStatic(true)
                .setFinal(true);

        decorateMethod(javaClass.addMethod());

        return javaClass.toString();
    }

    private void decorateMethod(MethodSource<JavaClassSource> methodSource) {
        Objects.requireNonNull(methodSource);

        methodSource
                .setName("create")
                .setPublic()
                .setReturnType(String.class)
                .addAnnotation(RequestMapping.class);

        methodSource
                .addParameter(Model.class, "model");

        CreateTable tableDef = entityContext.getTableDef();
        if (tableDef != null) {
            for (ColumnDefinition columnDefinition : tableDef.getColumnDefinitions()) {
                decorateParameter(methodSource, columnDefinition);
            }
        }

        methodSource.setBody("return \"bla\";");

        methodSource.getAnnotation(RequestMapping.class)
                .setStringValue("name", "/create")
                .setEnumValue("method", RequestMethod.POST);
    }

    private void decorateParameter(MethodSource<JavaClassSource> method, ColumnDefinition colDef) {
        Objects.requireNonNull(colDef);
        Objects.requireNonNull(method);

        String columnName = colDef.getColumnName();
        String dataType = colDef.getColDataType().getDataType();
        Class javaType = SQLTypeToJavaTypeMapping.getInputType(dataType);

        ParameterSource<JavaClassSource> param = method.addParameter(javaType, columnName);
        param.addAnnotation(RequestParam.class).setStringValue("name", columnName);
    }

    public static void main(String[] args) {

    }
}
