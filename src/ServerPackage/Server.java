package serverPackage;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            // 1. Création du ServerSocket (écoute sur le port 5000)
            ServerSocket serverSocket = new ServerSocket(5000);
            System.out.println("Je suis un serveur en attente de la connexion d'un client...");

            // 2. Attente de la connexion d'un client
            Socket socket = serverSocket.accept();
            System.out.println("Un client est connecté !");

            // 3. Préparation des flux d'E/S (Entrée/Sortie) pour les Chaînes de Caractères (CC)
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // 4. Lecture de l'opération envoyée par le client
            String operation = in.readLine();
            System.out.println("Opération reçue du client : " + operation);

            // 5. Traitement de l'opération
            String response;
            String[] parts = operation.trim().split("\\s+"); // Sépare par exemple "55 * 25" en ["55", "*", "25"]

            if (parts.length == 3) {
                try {
                    double a = Double.parseDouble(parts[0]);
                    String op = parts[1];
                    double b = Double.parseDouble(parts[2]);
                    double result = 0;

                    switch (op) {
                        case "+":
                            result = a + b;
                            break;
                        case "-":
                            result = a - b;
                            break;
                        case "*":
                            result = a * b;
                            break;
                        case "/":
                            if (b != 0) {
                                result = a / b;
                            } else {
                                out.println("Erreur: Division par zéro impossible");
                                socket.close();
                                serverSocket.close();
                                return;
                            }
                            break;
                        default:
                            out.println("Erreur: Opérateur inconnu (" + op + ")");
                            socket.close();
                            serverSocket.close();
                            return;
                    }

                    response = String.valueOf(result);

                } catch (NumberFormatException e) {
                    response = "Erreur: Les opérandes doivent être des nombres valides.";
                }
            } else {
                response = "Erreur: Syntaxe invalide (Format attendu: nombre opérateur nombre)";
            }

            // 6. Envoi du résultat au client
            System.out.println("Envoi du résultat : " + response);
            out.println(response);

            // 7. Fermeture des sockets (Étape finale)
            socket.close();
            serverSocket.close();
            System.out.println("Serveur arrêté.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}