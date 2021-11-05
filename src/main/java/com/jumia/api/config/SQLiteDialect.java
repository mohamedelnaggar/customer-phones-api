package com.jumia.api.config;

import org.hibernate.dialect.Dialect;

import java.sql.SQLException;
import java.sql.Types;

public class SQLiteDialect extends Dialect {

    public SQLiteDialect() throws SQLException {
        super();
        registerColumnType(Types.BIT, "integer");
        registerColumnType(Types.TINYINT, "tinyint");
        registerColumnType(Types.SMALLINT, "smallint");
        registerColumnType(Types.INTEGER, "integer");
        registerColumnType(Types.CHAR, "char");
        registerColumnType(Types.VARCHAR, "varchar");
    }
}
