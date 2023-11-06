package dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import helper.DBHelper;
import model.User;

public class UserDAO {

    private User user;
    private DBHelper db;


    public UserDAO(Context ctx, User user){

        this.db = new DBHelper(ctx);
        this.user = user;
    }

    public boolean verificarEmailESenha() {

        SQLiteDatabase dbLite = this.db.getReadableDatabase();
        String sql = "SELECT * FROM user where email = ? AND senha = ?";
        Cursor cursor = dbLite.rawQuery(sql,
                new String[]{this.user.getMail(), this.user.getPassword()});

        if (cursor.getCount() > 0){
            return true;
        }else{
            return false;
        }

    }

    public boolean cadastrar(){
        SQLiteDatabase dbLite = this.db.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nome", this.user.getName());
        cv.put("senha", this.user.getPassword());
        cv.put("email",this.user.getMail());

        long ret = dbLite.insert("user", null,cv);
        if (ret > 0){

            return  true;
        }
        return false;



    }

    public  boolean atualizar(){
        SQLiteDatabase dbLite = this.db.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("nome", this.user.getName());
        cv.put("senha", this.user.getPassword());
        cv.put("email",this.user.getMail());

        long ret = dbLite.update("user", cv,"email= ?", new String[]{this.user.getMail()});
        if (ret > 0){

            return  true;
        }
        return false;


    }
    public boolean delete(){

        SQLiteDatabase dbLite = this.db.getWritableDatabase();
        long ret = dbLite.delete("user","email = ?", new String[]{this.user.getMail()} );

        if (ret > 0){
            return true;
        }
        return false;
    }



}
