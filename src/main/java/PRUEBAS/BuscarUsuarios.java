package PRUEBAS;

import java.util.List;
import java.util.Random;
import java.util.zip.DeflaterOutputStream;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.Indexes;
import com.mongodb.client.MongoClients;
import org.bson.Document;
import org.json.simple.JSONArray;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;


import LOGICA.*;
import DATOS.*;

public class BuscarUsuarios {
    final String[] fragmentos1 = {"Alejandro", "Sofía", "Juan", "Valentina", "Carlos", "Isabella", "Miguel", "Camila", "José", "Mariana",
        "Luis", "Lucía", "Andrés", "Emma", "Diego", "Valeria", "Pedro", "Martina", "Javier", "Renata",
        "Manuel", "Ana", "Fernando", "Sara", "Pablo", "Elena", "Ricardo", "Julia", "Gabriel", "Paula",
        "Sebastián", "Victoria", "Daniel", "Carolina", "Fabián", "Laura", "Simón", "Daniela", "Tomás", "Catalina",
        "Adrián", "Gabriela", "Andrea", "María", "Emilio", "Abril", "Hugo", "Adriana", "Ángel", "Florencia",
        "Emmanuel", "Ángela", "Raúl", "Alexa", "Felipe", "Antonella", "Israel", "Jazmín", "Héctor", "Rosa",
        "Gonzalo", "Clara", "Raúl", "Alejandra", "Santiago", "Carmen", "Antonio", "Celeste", "Samuel", "Patricia",
        "Víctor", "Natalia", "Óscar", "Olivia", "Rafael", "Inés", "Roberto", "Lorena", "Mauricio", "Jimena",
        "Esteban", "Regina", "Nicolás", "Gabriela", "Alberto", "Agustina", "Enrique", "Alicia", "Arturo", "Melanie",
        "Rogelio", "Michelle", "Jorge", "Miranda", "Francisco", "Ana Sofía", "Ignacio", "Bianca", "Octavio", "Renata",
        "César", "Stephanie", "Julio", "Ximena", "Lorenzo", "Abril", "Ulises", "Carla", "Armando", "Fátima",
        "Salvador", "Natasha", "Eduardo", "Montserrat", "Erick", "Daniela", "Maximiliano", "Perla", "Luis", "Gabriela",
        "Rodrigo", "Pamela", "Mario", "Evelyn", "Jaime", "Angélica", "Leonardo", "Geraldine", "Vicente", "Ivanna",
        "Marco", "Liliana", "Benjamín", "Vanesa", "Rubén", "Ana Paula", "Abraham", "Eliana", "Raul", "Zoe",
        "Álvaro", "Julieta", "Alejo", "Rafaela", "Gregorio", "Carolina", "Rómulo", "Clarissa", "Óliver", "Alexandra",
        "Federico", "Jennifer", "Feliciano", "Ariana", "Lázaro", "Luciana", "Samir", "Valery", "Óscar", "Giselle",
        "Zacarías", "Mía", "Óliver", "Michelle", "Xavier", "Alondra", "Josué", "Karen", "Joaquín", "Renata",
        "Elías", "Zara", "Baltasar", "Yaretzi", "Gilberto", "Evelyn", "Damián", "Frida", "Julián", "Esmeralda",
        "Isidoro", "Paulina", "Rubén", "Brenda", "Gerardo", "Lía", "Raúl", "Emily", "Enzo", "Violeta",
        "Simón", "Alma", "Arturo", "Regina", "Maximiliano", "Isabel", "Dante", "Romina", "Pedro", "Samanta",
        "Rafael", "Clara", "Francisco", "Raquel", "José Luis", "María José", "Agustín", "Carolina", "Nicolás", "Nora",
        "Óscar", "Miriam", "Braulio", "Ruth", "Gustavo", "Dolores", "Xavier", "Rocío", "Miguel Ángel", "Esperanza",
        "Andrés", "Gloria", "Ariel", "Aurora", "Teodoro", "Elisa", "Jacinto", "Berta", "Fidel", "Marina",
        "Alfredo", "Estela", "Ernesto", "Verónica", "Sergio", "Cecilia", "Arnoldo", "Irma", "Isidro", "Juana",
        "Óscar", "Ofelia", "Iván", "Julieta", "Ismael", "Gabriela", "Guillermo", "Maritza", "Edmundo", "Sandra",
        "Sergio", "Liliana", "Juan Carlos", "Beatriz", "Mauricio", "Alicia", "Roberto", "Socorro", "Adolfo", "Consuelo",
        "Eugenio", "Catalina", "Rodolfo", "Leticia", "Federico", "Rosario", "Genaro", "Enriqueta", "Jorge", "Magdalena",
        "Heriberto", "Susana", "Leonardo", "Elena", "Abel", "Rita", "Jerónimo", "Gabriela", "Ezequiel", "Samantha",
        "Eduardo", "Elvira", "Jacobo", "Mercedes", "Esteban", "Nidia", "Félix", "Elsa", "Antonio", "Esmeralda",
        "Humberto", "Adela", "Mauricio", "Guadalupe", "Omar", "Nadia", "René", "Fabiola", "Roberto", "Diana",
        "Luis", "Cristina", "Marcos", "Anabel", "Gonzalo", "Yolanda", "Octavio", "Aída", "Patricio", "Adriana",
        "Alonso", "Graciela", "Marco", "Ivette", "Ricardo", "Raquel", "Ramón", "Beatriz", "Germán", "Francisca",
        "Abraham", "Araceli", "Tadeo", "Ángeles", "Lázaro", "Esther", "Tomás", "Aurora", "Eduardo", "Consuelo",
        "Óscar", "Rosario", "Ángel", "Valeria", "Daniel", "Liliana", "José", "Lorena", "Javier", "Miriam",
        "Rodrigo", "Sandra", "Raúl", "Adela", "Benito", "Cecilia", "Jorge", "Olga", "Mateo", "Estela",
        "Felipe", "María", "Gabriel", "Victoria", "Álvaro", "Raquel", "Simón", "Marisol", "Valentín", "Maribel",
        "Ismael", "Alicia", "Roberto", "Eugenia", "Hugo", "Ana María", "Óscar", "Laura", "Fernando", "Silvia",
        "Héctor", "Patricia", "Enrique", "Beatriz", "Francisco", "Isabel", "Alberto", "Rosa", "Santiago", "Lucía",
        "Ignacio", "Gabriela", "Armando", "Elena", "Alfredo", "Claudia", "Martín", "Carolina", "Ricardo", "Teresa",
        "César", "Gloria", "León", "Inés", "Eduardo", "María Elena", "Mario", "Daniela", "Pedro", "Karla",
        "Alejandro", "María Fernanda", "Víctor", "Ana", "Rubén", "Fernanda", "Emilio", "María Paula", "Nicolás", "Paulina",
        "Ramiro", "Alejandra", "Federico", "María Alejandra", "Óscar", "María Isabel", "Ramón", "María José", "Evaristo", "María Eugenia",
        "Leonardo", "María Mercedes", "Andrés", "María Virginia", "Vicente", "María del Pilar", "Julio", "María Guadalupe", "Adolfo", "María Teresa",
        "Agustín", "María Dolores", "Isidro", "María de los Ángeles", "Alonso", "María del Carmen", "Fidel", "María de la Luz", "Lorenzo", "María de los Remedios"};
        final String[] fragmentos2 = {"García", "López", "Martínez", "Fernández", "Pérez", "González"};
        final String[] fragmentos3 = {"García", "Rodríguez", "González", "Fernández", "López", "Martínez", "Sánchez", "Pérez", "Gómez", "Martín",
        "Jiménez", "Hernández", "Ruiz", "Díaz", "Moreno", "Álvarez", "Romero", "Alonso", "Torres", "Navarro",
        "Ramírez", "Vargas", "Molina", "Ortega", "Delgado", "Castro", "Ortiz", "Rubio", "Cano", "Núñez",
        "Ramos", "Medina", "Serrano", "Iglesias", "Cortés", "Vega", "Guerrero", "Flores", "Cabrera", "Campos",
        "Mendoza", "Cruz", "Guerrero", "Calderón", "Méndez", "Camacho", "Carrasco", "Reyes", "Giménez", "Pardo",
        "Vidal", "Carmona", "Soto", "Pascual", "Pastor", "Herrero", "Mora", "Arias", "Santos", "Lara",
        "Santiago", "Cervantes", "Solís", "Pacheco", "Fuentes", "Estévez", "Vargas", "Crespo", "Aguilar", "Valdez",
        "Valencia", "Maldonado", "Silva", "Zamora", "Navas", "Chavez", "Padilla", "Vera", "Morales", "Orozco",
        "Nava", "Mejía", "Leyva", "Quintero", "Castañeda", "Aguirre", "Ojeda", "Bautista", "Mesa", "Herrera",
        "Sosa", "Arellano", "Franco", "Duarte", "Gallegos", "Hurtado", "Olvera", "Barrera", "Miranda", "Cuevas",
        "Escobar", "Ibarra", "Montes", "Vargas", "Sepúlveda", "Rojo", "Salcedo", "Becerra", "Miramontes", "Cedillo",
        "Chávez", "Ontiveros", "Zamudio", "Sandoval", "Carbajal", "Guzmán", "Chavarría", "Escobedo", "Tovar", "Morán",
        "Munguía", "Oliva", "Almanza", "Gámez", "Mota", "Yáñez", "Márquez", "Elizondo", "Rocha", "Fajardo",
        "Villarreal", "Corona", "Ayala", "Becerril", "Treviño", "Ríos", "Carmona", "Castaño", "Castellanos", "Esquivel",
        "Bernal", "Del Río", "Navarrete", "Duarte", "Beltrán", "Toscano", "Montaño", "Carrillo", "Herrera", "Castañón",
        "Fernando", "Solano", "Larios", "Barajas", "Moya", "Lugo", "Meneses", "Espinoza", "Montenegro", "Montoya",
        "Quiroz", "Juárez", "Jimeno", "Chacón", "Barrera", "Ángel", "Del Real", "Aragón", "Landa", "Ferrer",
        "Loredo", "Piña", "Sarabia", "Fonseca", "Pacheco", "Aragón", "Soria", "Heredia", "Vela", "Espinosa",
        "Cárdenas", "Cuevas", "Barajas", "Lara", "Orduña", "Aguilar", "Bustos", "Zapata", "Villagrán", "Cadena",
        "Valero", "Rosales", "Palacios", "Ponce", "Mata", "Ávila", "Estrada", "Lugo", "Lozano", "Arreola",
        "Monroy", "Mondragón", "Ballesteros", "Chávez", "Segura", "Pinto", "Peña", "Escalante", "Plascencia", "Echeverría",
        "Echegaray", "Espino", "Alcántar", "Noriega", "Cepeda", "Castañeda", "Valadez", "Villegas", "López", "Estrella",
        "Ferrari", "Arango", "Herrán", "Márquez", "Quiroga", "Palacios", "Rojas", "Mendoza", "Berrio", "Flórez",
        "Serna", "Cruz", "Barón", "Calle", "Díaz", "López", "Peralta", "Álvarez", "Suárez", "Barreto",
        "Almeida", "Gallardo", "Vallejo", "Herrera", "Carranza", "Hidalgo", "Bermúdez", "Jaramillo", "Bonilla", "Villalobos",
        "Nieto", "Monsalve", "Méndez", "Uribe", "Bueno", "Granados", "Fandiño", "Delgado", "Galvis", "Córdoba",
        "Cardozo", "Sierra", "Cardona", "Forero", "Aguilera", "Sáenz", "Fajardo", "Barrera", "Fuentes", "Daza",
        "Castillo", "Ibáñez", "Jaimes", "Moya", "Zambrano", "Bonilla", "Lugo", "Bolaños", "Pérez", "Narváez",
        "Castaño", "Pareja", "Vargas", "Moreno", "Quijano", "Barrera", "Contreras", "Duarte", "Zapata", "Muñoz",
        "Rendón", "Dávila", "Vega", "Mosquera", "Bermúdez", "Bedoya", "Carvajal", "Salcedo", "González", "Lozano",
        "Ortiz", "Murillo", "Pulido", "Cano", "Montoya", "Alvarez", "Chica", "Granada", "Díaz", "Coronado",
        "Naranjo", "Sanabria", "Báez", "Callejas", "Bolaños", "Cardona", "Loaiza", "Pachón", "Araújo", "Fuentes"
        };


    public BuscarUsuarios() {
        MongoClient mongoClient = new MongoClient(
            new MongoClientURI(
                    "mongodb+srv://Admin:passwordAdmin@cluster0.fe0chr9.mongodb.net/"));
    MongoDatabase database = mongoClient.getDatabase("SeaUnalenoDB");
        MongoCollection<Document> collection = database.getCollection("usuarios");
        collection.createIndex(Indexes.ascending("email"), new IndexOptions().unique(true));
        
        // Creación del BsonArray y asignación del arreglo
        JSONArray jsonArray = new JSONArray();
        
        List<Document> users = new ArrayList<>();

        // Cerrar la conexión con la base de datos
        mongoClient.close();
        int x = 1;
        byte[] image=null;
            try {
                    // Obtener la imagen seleccionada por el usuario
                    byte[] imagenBytes = Files.readAllBytes(new File("C:\\Users\\delog\\Downloads\\cris.png").toPath());
                    byte[] imagenComprimida = ComprimirBytes(imagenBytes);
                    
                    image = imagenComprimida;
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
        for (int i = 0; i < x; i++) {
            long numero=generateRandomNumber();
            String name=generarNombreAleatorio();
            String apellido= generarApellidoAleatorio();
            String email=apellido.replace(" ","_");
            System.out.println(i);
            double[] notas=generateRandomArray();
            for (double dato : notas) {
                jsonArray.add(dato);
            }
            String id=generateUniqueID(numero+"");
            
            
            
            Usuario usuario= new Usuario(id,
            "Cristian Camilo",
            "Garcia Palacios",
            "+57"+numero,
            "crgarciapa@unal.edu.co",
            null,
            notas,
            image,
            "cualquiera");
            new conexionBD(usuario, "usuarios");
            
        // Document userDocument = new Document()
        //         .append("id", usuario.getId())
        //         .append("nombres", usuario.getNombres())
        //         .append("apellidos", usuario.getApellidos())
        //         .append("telefono", usuario.getTelefono())
        //         .append("email", usuario.getEmail())
        //         .append("historial", usuario.getHistorial())
        //         .append("notas", jsonArray.toJSONString())
        //         .append("pathImagen", usuario.getImagen())
        //         .append("password", usuario.getPassword());
        //     try {
        //         collection.insertOne(userDocument);
        //     } catch (Exception e) {
        //         // TODO: handle exception
        //         users.add(userDocument);
        //     }
        

            // users.add(new Document()
            //     .append("id", "id"+i)
            //     .append("nombres", ""+name)
            //     .append("apellidos", ""+apellido)
            //     .append("telefono","+57"+(i*6000))
            //     .append("email", name+"@gulugulu.com")
            //     .append("historial", null)
            //     .append("notas", "[1.0]")
            //     .append("pathImagen", image)
            //     .append("password", "cualquiera"));
            // System.out.println(name);
        }
        // collection.insertMany(users);
    }
    private String generateUniqueID(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes(StandardCharsets.UTF_8));

            // Convertir el hash a una representación hexadecimal
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            // Truncar el resultado a 8 caracteres
            return hexString.toString().substring(0, 8);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public long generateRandomNumber() {
        Random random = new Random();
        long randomNumber = random.nextLong();
        randomNumber = Math.abs(randomNumber); // Asegura que el número es positivo

        // Ajusta el número a 10 dígitos
        if (String.valueOf(randomNumber).length() > 10) {
            randomNumber = randomNumber % (long) Math.pow(10, 10);
        } else {
            while (String.valueOf(randomNumber).length() < 10) {
                randomNumber *= 10;
                randomNumber += random.nextInt(10);
            }
        }
        return randomNumber;
    }

    public static void main(String[] args) {
        new BuscarUsuarios();
    }

    private static byte[] ComprimirBytes(byte[] bytes) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DeflaterOutputStream dos = new DeflaterOutputStream(baos);
        dos.write(bytes);
        dos.close();
        return baos.toByteArray();
    }
    public String generarNombreAleatorio() {
        
        Random random = new Random();
        return fragmentos1[random.nextInt(fragmentos1.length)]+ " " + fragmentos2[random.nextInt(fragmentos2.length)] ;
    }
    public String generarApellidoAleatorio() {
        
        Random random = new Random();
        return fragmentos3[random.nextInt(fragmentos3.length)]+ " "+ fragmentos3[random.nextInt(fragmentos3.length)] ;
    }
    public double[] generateRandomArray() {
        Random random = new Random();
        int size = random.nextInt(1000) + 1; // Genera un tamaño aleatorio entre 1 y 10
        double[] array = new double[size];

        for (int i = 0; i < size; i++) {
            double randomValue = random.nextDouble() * 1500 - 200; // Genera un número aleatorio entre -200 y 1300
            array[i] = randomValue;
        }

        return array;
    }

}
