package ClientPackage;

import communPackage.Operation;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientObjet {
    public static void main(String[] args) {
        try {
            // 1. Connexion au serveur
            Socket socket = new Socket("localhost", 5000);
            System.out.println("Client (Objets) connecté au serveur.");

            // 2. Création des flux d'objets
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            // 3. Création de l'objet Operation (ex: 55 * 25)
            Operation op = new Operation(55, "*", 25);
            System.out.println("Envoi de l'objet : " + op.getOperand1() + " " + op.getOperator() + " " + op.getOperand2());

            // 4. Envoi de l'objet au serveur
            out.writeObject(op);
            out.flush();

            // 5. Lecture du résultat (qui peut être un Double ou une String)
            Object response = in.readObject();
            System.out.println("Résultat reçu du serveur : " + response);

            // 6. Fermeture
            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}