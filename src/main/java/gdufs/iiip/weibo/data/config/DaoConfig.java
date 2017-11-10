package gdufs.iiip.weibo.data.config;

/**
 * Created by gu on 2017/11/10.
 */
public class DaoConfig {
    public String DBName = "weibo";
    public String ServerAddress = "112.74.175.37";
    public int PORT = 27017;

    public DaoConfig() {
    }

    public DaoConfig(String DBName, String serverAddress, int PORT) {
        this.DBName = DBName;
        ServerAddress = serverAddress;
        this.PORT = PORT;
    }

    public String getDBName() {
        return DBName;
    }

    public void setDBName(String DBName) {
        this.DBName = DBName;
    }

    public String getServerAddress() {
        return ServerAddress;
    }

    public void setServerAddress(String serverAddress) {
        ServerAddress = serverAddress;
    }

    public int getPORT() {
        return PORT;
    }

    public void setPORT(int PORT) {
        this.PORT = PORT;
    }

    public static void main(String[] args) {
        DaoConfig daoConfig = new DaoConfig("aa","sss",33);
        System.out.println(daoConfig.getDBName());
    }

}
