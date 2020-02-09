package hometask23.common.solutions.database;

public interface JdbcFunction<FROM, TO> {
    TO apply(FROM from) throws Exception;
}
