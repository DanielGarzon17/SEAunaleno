package LOGICA;

import org.bson.BsonArray;
import org.bson.BsonDouble;
import org.bson.Document;
import org.bson.codecs.BsonArrayCodec;
import org.bson.codecs.BsonDoubleCodec;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.conversions.Bson;
import org.bson.types.Binary;
import org.json.simple.JSONArray;

import static com.mongodb.client.model.Filters.*;

import java.math.BigDecimal;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.Indexes;
import com.mongodb.client.model.Updates;

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

    public conexionBD(Usuario usuario, String collectionName) {
        this.collectionName = collectionName;
        this.collection = database.getCollection(collectionName);
        collection.createIndex(Indexes.ascending("email"), new IndexOptions().unique(true));
        
        // Creación del BsonArray y asignación del arreglo
        JSONArray jsonArray = new JSONArray();
        for (double dato : usuario.getNotas()) {
            jsonArray.add(dato);
        }
        Document userDocument = new Document()
                .append("id", usuario.getId())
                .append("nombres", usuario.getNombres())
                .append("apellidos", usuario.getApellidos())
                .append("telefono", usuario.getTelefono())
                .append("email", usuario.getEmail())
                .append("historial", usuario.getHistorial())
                .append("notas", jsonArray.toJSONString())
                .append("pathImagen", usuario.getImagen())
                .append("password", usuario.getPassword());

        collection.insertOne(userDocument);
    }

    public conexionBD(Usuario usuario, Queue<Double> notas) {
        // usuario.setNotas(notas);
        Document filtro = new Document("id", usuario.getId());
        collection.updateOne(filtro, Updates.push("notas", notas.pop().doubleValue()));

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
            String notasString= document.getString("notas");
            byte[] imagenBytes = null;

            Binary binData = document.get("pathImagen", Binary.class);
            if (binData != null) {
                imagenBytes = binData.getData();
            }

            double[] notas = convertirStringADoubleArray(notasString);
            usuarios.pushBack(new Usuario(id, nombres, apellidos, telefono, email, null, notas, imagenBytes, password));
        }
        return usuarios;
    }

    public static double[] convertirStringADoubleArray(String input) {
        String[] elementos = input.replaceAll("[\\[\\]]", "").split(",");
        double[] valores = new double[elementos.length];
        for (int i = 0; i < elementos.length; i++) {
            valores[i] = Double.parseDouble(elementos[i]);
        }
        return valores;
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
