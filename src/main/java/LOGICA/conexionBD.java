package LOGICA;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.Binary;

import static com.mongodb.client.model.Filters.*;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import DATOS.Usuario;

public class conexionBD {
    MongoClient mongoClient = new MongoClient(
            new MongoClientURI(
                    "mongodb+srv://Admin:passwordAdmin@cluster0.fe0chr9.mongodb.net/"));
    MongoDatabase database = mongoClient.getDatabase("SeaUnalenoDB");
    String collectionName;
    MongoCollection<Document> collection;
    public LinkedList<Usuario> usuarios;

    public conexionBD(String collectionName) {
        this.collectionName = collectionName;
        this.collection = database.getCollection(collectionName);
        System.out.println("conectado a la base:" + collectionName);
        setUsuarios(traerUsuarios());
    }
    
    public LinkedList<Usuario> traerUsuarios() {

        LinkedList<Usuario> usuarios = new LinkedList<Usuario>();

        Bson filter = ne("id", "");
        FindIterable<Document> result = getCollection().find(filter);

        for (Document document : result) {
            String id = document.getString("id");
            String nombres = document.getString("nombres");
            String apellidos = document.getString("apellidos");
            String telefono = document.getString("telefono");
            String email = document.getString("email");
            String password = document.getString("password");
            byte[] imagenBytes = null;

            Binary binData = document.get("pathImagen", Binary.class);
            if (binData != null) {
                imagenBytes = binData.getData();
            }

            usuarios.pushBack(new Usuario(id, nombres, apellidos, telefono, email, null, null, imagenBytes, password));
        }
        return usuarios;
    }

    public Usuario verificarEmail(String email, String password) {
        LinkedList<Usuario> usuarioslocal = getUsuarios();
        BST arbolUsuarios = new BST();
        for (int i = 0; i < usuarioslocal.size; i++) {
            Usuario n;
            try {
                n = usuarioslocal.get(i);
                System.out.println(n.getEmail());
                arbolUsuarios.insert(n);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        Usuario usuarioEncontrado = arbolUsuarios.findEmail(email);
        if (usuarioEncontrado.getPassword().equals(password)) {
            return usuarioEncontrado;
        }
        return null;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public LinkedList<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(LinkedList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public MongoCollection<Document> getCollection() {
        return collection;
    }

    public void setCollection(MongoCollection<Document> collection) {
        this.collection = collection;
    }
}
