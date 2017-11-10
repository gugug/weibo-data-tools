package gdufs.iiip.weibo.data.dao;

import java.util.ArrayList;

import java.util.List;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;
import gdufs.iiip.weibo.data.config.DaoConfig;

public class MongoHelper {

    private String DBName;
    private String ServerAddress;
    private int PORT;

    public MongoHelper(DaoConfig daoConfig) {
        this.DBName = daoConfig.getDBName();
        this.ServerAddress = daoConfig.getServerAddress();
        this.PORT = daoConfig.getPORT();
    }

    public MongoClient getMongoClient() {
        MongoClient mongoClient = null;
        try {
            // 连接到 mongodb 服务
            mongoClient = new MongoClient(ServerAddress, PORT);
            System.out.println("Connect to mongodb successfully");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return mongoClient;
    }

    public MongoDatabase getMongoDataBase(MongoClient mongoClient) {
        MongoDatabase mongoDataBase = null;
        try {
            if (mongoClient != null) {
                mongoDataBase = mongoClient.getDatabase(DBName);
                System.out.println("Connect to DataBase successfully");
            } else {
                throw new RuntimeException("MongoClient不能够为空");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mongoDataBase;
    }

    public void closeMongoClient(MongoDatabase mongoDataBase, MongoClient mongoClient) {
        if (mongoDataBase != null) {
            mongoDataBase = null;
        }
        if (mongoClient != null) {
            mongoClient.close();
        }
        System.out.println("CloseMongoClient successfully");

    }
}
