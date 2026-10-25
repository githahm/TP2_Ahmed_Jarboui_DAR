package ServerPackage;

import communPackage.Operation;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerObjet {
    public static void main(String[] args) {
        try {
            // 1. Création du ServerSocket
            ServerSocket serverSocket = new ServerSocket(5000);
            System.out.println("Serveur (Objets) en attente de connexion...");

            // 2. Attente de la connexion
            Socket socket = serverSocket.accept();
            System.out.println("Client connecté !");

            // 3. Création des flux d'objets (Object Streams)
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            // 4. Réception de l'objet Operation
            Operation op = (Operation) in.readObject();
            System.out.println("Objet reçu du client :");
            System.out.println("  Opérande 1 : " + op.getOperand1());
            System.out.println("  Opérateur  : " + op.getOperator());
            System.out.println("  Opérande 2 : " + op.getOperand2());

            // 5. Traitement de l'opération et envoi du résultat
            try {
                double resultat = op.calculate();
                out.writeObject(resultat); // Envoi d'un Double (qui est aussi Serializable)
                out.flush();
                System.out.println("Résultat calculé et envoyé : " + resultat);
            } catch (ArithmeticException | IllegalArgumentException e) {
                // Envoi d'un message d'erreur (String) au client
                out.writeObject("Erreur : " + e.getMessage());
                out.flush();
                System.out.println("Erreur renvoyée au client : " + e.getMessage());
            }

            // 6. Fermeture des sockets
            socket.close();
            serverSocket.close();
            System.out.println("Serveur arrêté.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}