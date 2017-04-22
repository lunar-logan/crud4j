package com.crud4j;

import com.crud4j.entity.User;
import com.crud4j.repository.UserRepository;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.create.table.CreateTable;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Dell on 22-04-2017.
 */
public class GenerateControllerTest {

    private EntityContext context;

    @Before
    public void setup() throws JSQLParserException {
        context = new EntityContext();
        context.setEntityClass(User.class);
        context.setRepositoryClass(UserRepository.class);

        String sql = "CREATE TABLE user (\n" +
                "  id       INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,\n" +
                "  name     VARCHAR(100) NOT NULL,\n" +
                "  email    VARCHAR(100) NOT NULL,\n" +
                "  password VARCHAR(200) NOT NULL\n" +
                ");";
        context.setTableDef((CreateTable) CCJSqlParserUtil.parse(sql));

    }

    @Test
    public void generate() throws Exception {
        GenerateController controller = new GenerateController(context, "com.crud4j.controller");
        System.out.println(controller.generate());
    }

    @After
    public void tearDown() {

    }

}