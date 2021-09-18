package me.lordmefloun.pctrails;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.sql.*;
import java.util.UUID;

public class MySql {


    PcTrails plugin;

    private Connection connection;
    public String host, database, username, password, table;
    public int port;


    public MySql(PcTrails plugin){

        this.plugin = plugin;

    }


    public void mysqlSetup() {
        host = plugin.getConfig().getString("database.host");
        port = plugin.getConfig().getInt("database.port");
        database = plugin.getConfig().getString("database.database");
        username = plugin.getConfig().getString("database.user");
        password = plugin.getConfig().getString("database.password");
        table = plugin.getConfig().getString("database.table");


        try{
            synchronized (this){
                if (getConnection() != null && !getConnection().isClosed() ) {
                    return;
                }

                Class.forName("com.mysql.jdbc.Driver");
                setConnection(DriverManager.getConnection("jdbc:mysql://" + this.host + ":" +
                        this.port + "/" + this.database, this.username, this.password));
                Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD + "MySQL connection successfull");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        try {
            PreparedStatement statement = this.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS `"+ database +"`.`" + table + "` ( `id` INT(225) NOT NULL AUTO_INCREMENT , `player` VARCHAR(225) NOT NULL , `trail` INT(255) NOT NULL , PRIMARY KEY (`id`)) ENGINE = InnoDB;");
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public int getPlayerTrail(UUID uuid) {
        try (PreparedStatement statement = getConnection().prepareStatement("SELECT * FROM " + table + " WHERE player=?")){
            statement.setString(1, uuid.toString());


            ResultSet result = statement.executeQuery();

            if (result.next()){
                return result.getInt("trail");

            }
            else{
                return 0;
            }

        }catch(SQLException e){
            e.printStackTrace();
        }

        return 0;
    }

    public void updatePlayerTrail(UUID uuid, int state){


        try (PreparedStatement statement = getConnection().prepareStatement("UPDATE " + table + " SET trail=? WHERE player=?")){

            if(playerRecorded(uuid)) {


                statement.setInt(1, state);
                statement.setString(2, uuid.toString());


                statement.executeUpdate();
            }
            else{
                createPlayerRecord(uuid, state);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void createPlayerRecord(UUID player, int state) {

        if (!playerRecorded(player)) {

            try (PreparedStatement statement = this.getConnection().prepareStatement("INSERT INTO " + table + " (player, trail) VALUES (?,?)")) {

                statement.setString(1, player.toString());
                statement.setInt(2, state);


                statement.executeUpdate();
            } catch (SQLException e) {

                e.printStackTrace();

            }
        }

    }


    public boolean playerRecorded(UUID uuid){
        try (PreparedStatement statement = getConnection().prepareStatement("SELECT * FROM " + table + " WHERE player=?")){
            statement.setString(1, uuid.toString());
            ResultSet result = statement.executeQuery();
            if (result.next()){
                return true;
            }
            else{
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }




    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }






}