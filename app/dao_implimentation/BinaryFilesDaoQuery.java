package dao_implimentation;

import com.google.common.base.Optional;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
import models.education.config.Image;
import org.jongo.MongoCursor;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ibrahim Olanrewaju Abdulsemiu on 27/07/2017.
 */
public class BinaryFilesDaoQuery {

    private DbConnection dbConnection;
    private GridFS gridFS;
    private Image image;
    private FileInputStream fileInputStream;
    private FileOutputStream fileOutputStream;
    private GridFSInputFile gridFSInputFile;
    private BasicDBObject meta;
    private GridFSDBFile gridFSDBFile;
    private  boolean checkResult;

    private BinaryFilesDaoQuery(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
        this.image = new Image();
        this.gridFS = dbConnection.getGridBinary();
    }

    public boolean insert(File file, String fileName, List tags, String descr){
        try {
            fileInputStream = new FileInputStream(file);
            gridFSInputFile  = gridFS.createFile(fileInputStream,fileName);
            meta = new BasicDBObject("description",descr);
            meta.append("tags",tags);
            gridFSInputFile.setContentType(gridFSInputFile.getContentType());
            gridFSInputFile.setMetaData(meta);
            gridFSInputFile.save();
            checkResult = true;
        }catch (Exception e){
            e.getMessage();
            checkResult  = false;
        }
        return  checkResult;
    }


    public String findById(String id, String newFileName){
        try {
            gridFSDBFile = gridFS.findOne(new BasicDBObject("filename",id));
            fileOutputStream = new FileOutputStream(newFileName);
            gridFSDBFile.writeTo(fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
        }catch (Exception e){
            e.getMessage();
        }
        return gridFSDBFile.getFilename();
    }

    public List<GridFSDBFile> findAllById(String id){

        List<GridFSDBFile> gridFSDBFiles = new ArrayList<>();
        try{
            synchronized(gridFS) {
                gridFSDBFiles =  gridFS.find(new BasicDBObject("filename", id));
            }
        }catch (Exception e){
            e.getMessage();
        }
        return gridFSDBFiles;
    }

    public boolean remove(String id){
        try {
            gridFS.remove(id);
            checkResult = true;
        }catch (Exception e){
            e.getMessage();
            checkResult = false;
        }
        return checkResult;
    }
}
