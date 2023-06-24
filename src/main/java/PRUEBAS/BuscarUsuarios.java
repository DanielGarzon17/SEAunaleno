package PRUEBAS;

import java.util.Random;
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
        int x = 1000000;
        BST usuarios = new BST();
        for (int i = 0; i < x; i++) {
            String name=generarNombreAleatorio();
            usuarios.insert(new Usuario("id1",""+name,null,"+5768983464","email@guggw",null,null,null));
            // System.out.println(name);
        }
        usuarios.insert(new Usuario("id1","DGGG",null,"+5768983464","email@guggw",null,null,null));
        long tiempoInicial = System.nanoTime();
        usuarios.get("Alejandro García García");
        System.out.println("s"+x + "=" + (System.nanoTime() - tiempoInicial));
    }

    public static void main(String[] args) {
        BuscarUsuarios a =new BuscarUsuarios();
    }
    public String generarNombreAleatorio() {
        
        Random random = new Random();
        return fragmentos1[random.nextInt(fragmentos1.length)]+ " " + fragmentos2[random.nextInt(fragmentos2.length)] + " " + fragmentos3[random.nextInt(fragmentos3.length)];
    }
}
