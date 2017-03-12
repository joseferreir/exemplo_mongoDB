/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.rdu.ifpb.mongo.repositorio;

import br.rdu.ifpb.mongo.entity.Pessoa;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import java.net.UnknownHostException;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author José Ferreira
 */
public class PessoaRepositoriMongo implements Repositorio {

    String host;
    private String BD;
    private int port;
    private Mongo mongo;
    private  int result;

    public PessoaRepositoriMongo(String host, String BD, int port) {
        this.host = host;
        this.BD = BD;
        this.port = port;
        this.mongo = mongo;
    }

    private DB getConnection() {

        try {
            mongo = new MongoClient(host, port);

        } catch (UnknownHostException ex) {
            Logger.getLogger(PessoaRepositoriMongo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mongo.getDB(BD);
    }

    @Override
    public int add(Pessoa pessoa) {
        getConnection();
        DB db = getConnection();
        DBCollection table = db.getCollection("pessoa");
        //
        BasicDBObject document = new BasicDBObject();
        document.put("nome", pessoa.getNome());
        document.put("cpf", pessoa.getCpf());
        document.put("idade", pessoa.getIdade());

        result = table.insert(document).getN();
       closeConnection(mongo);
       return result;
    }

    @Override
    public Pessoa find(String key, String value) {
        Pessoa pessoa = new Pessoa();
        DB db = getConnection();
        DBCollection table = db.getCollection("pessoa");
        BasicDBObject query
	    = new BasicDBObject().append(key, value);

	DBCursor result = table.find(query);

	result.hasNext() ;
            
            DBObject DBO = result.next();
             pessoa.setId(DBO.toMap().get("_id").toString());
            pessoa.setNome(DBO.toMap().get("nome").toString());
             pessoa.setCpf(DBO.toMap().get("cpf").toString());
        int idade = Integer.parseInt(DBO.toMap().get("idade").toString());
              pessoa.setIdade(idade);
             
                
	


        return pessoa;
    }

    @Override
    public int remove(String key, String value) {
        
        DB db = getConnection();
        DBCollection table = db.getCollection("pessoa");
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put(key, value);

         result = table.remove(searchQuery).getN();
        closeConnection(mongo);
        return result;
    }
    private void closeConnection(Mongo mongo){
        mongo.close();
    }
    @Override
    public int upDate(Pessoa pessoa){
         DB db = getConnection();
          DBCollection table = db.getCollection("pessoa");
        BasicDBObject query = new BasicDBObject();
	query.put("nome", pessoa.getNome());

	BasicDBObject newDocument = new BasicDBObject();
	newDocument.put("nome", pessoa.getNome());
        newDocument.put("cpf", pessoa.getCpf());
        newDocument.put("idade", pessoa.getIdade());

	BasicDBObject updateObj = new BasicDBObject();
	updateObj.put("$set", newDocument);

	result = table.update(query, updateObj).getN();
        closeConnection(mongo);
        return result;
    }
}
