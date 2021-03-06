package by.it.tsyhanova.at21;

import by.it.tsyhanova.at21.beans.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class UserDao implements InterfaceDao<User> {
    //чтение
    @Override
    public User read(long id) throws SQLException {
        User user=null;
        String sql="SELECT * FROM tsyhanova.user WHERE ID="+id;
        ConnectionCreator connectionCreator=new ConnectionCreatorMySql();
        try (
                Connection connection=connectionCreator.get();
                Statement statement = connection.createStatement()
        ) {
            //read from db
            //resultSet ничего не возвращает, а при наличии данных шагает на шаг вперед
            ResultSet resultSet = statement.executeQuery(sql);
            //возвращаем юзера, если в resultSet что-нибудь есть
            if (resultSet.next()) {
                user = new User(
                        resultSet.getLong("id"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("email"),
                        resultSet.getDate("create_time")
                );
            }
        }
        return user;
    }
    //создание
    @Override
    public boolean create(User user) throws SQLException {
        String sql=String.format(
                "insert INTO user (username,email,password,create_time)"+
                " values ('%s','%s','%s','%s');",
                user.getUsername(),user.getEmail(),user.getPassword(),user.getDate()
                );
        ConnectionCreator connectionCreator=new ConnectionCreatorMySql();
        try (
                Connection connection=connectionCreator.get();
                Statement statement = connection.createStatement()
        ){
            //проверка отработки запроса sql с возвращением в user id, где user это bean по которому работаем с базой
            //для возвращения id используем Statement.RETURN_GENERATED_KEYS
            if(1==statement.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS)){
                //Statement врзвращает последние ключи
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if(generatedKeys.next()){
                    long id=generatedKeys.getLong(1);
                    user.setId(id);
                    return true;
                }
            }
        }


        return false;
    }

    @Override
    public boolean update(User user) throws SQLException {
        String sql=String.format(
                "UPDATE `user` SET `username`='%s',`email`='%s',`password`='%s',`create_time`='%s' WHERE `user`.`ID`=%d;",
                user.getUsername(),user.getEmail(),user.getPassword(),user.getDate(),user.getId()
        );
        ConnectionCreator connectionCreator=new ConnectionCreatorMySql();
        try (
                Connection connection=connectionCreator.get();
                Statement statement = connection.createStatement()
        ) {
            if(1==statement.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS)){
                //Statement врзвращает последние ключи
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if(generatedKeys.next()){
                    long id=generatedKeys.getLong(1);
                    user.setId(id);
                    return true;
                }
            }
        }
            return false;
    }

    @Override
    public boolean delete(User user) throws SQLException {
        String sql=String.format(
                "DELETE FROM `user` WHERE `user`.`ID`=%d;",
                user.getId()
        );
        ConnectionCreator connectionCreator=new ConnectionCreatorMySql();
        try (
                Connection connection=connectionCreator.get();
                Statement statement = connection.createStatement()
        ) {
            if(1==statement.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS)){
                //Statement врзвращает последние ключи
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if(generatedKeys.next()){
                    long id=generatedKeys.getLong(1);
                    user.setId(id);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public List<User> getAll() throws SQLException {
        return null;
    }
}
