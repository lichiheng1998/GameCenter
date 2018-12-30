package fall2018.csc207project.Models;

import java.util.List;

public class User {
    private String userName;
    private String password;
    private String nickName;
    private List<String> gameList;

    public User(){

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public List<String> getGameList() {
        return gameList;
    }

    public void setGameList(List<String> gameList) {
        this.gameList = gameList;
    }

    public boolean removeGame(String game){
        return gameList.remove(game);
    }

    public boolean addGame(String game){
        return gameList.contains(game) && gameList.add(game);
    }
}
