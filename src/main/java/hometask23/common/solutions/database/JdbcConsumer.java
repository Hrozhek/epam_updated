package hometask23.common.solutions.database;

public interface JdbcConsumer<T> {
    void accept(T t) throws Exception;
}
