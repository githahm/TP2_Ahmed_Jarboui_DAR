package clientPackage;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            System.out.println("Je suis un client pas encore connecté...");

            // 1. Connexion au serveur (localhost, port 5000)
            Socket socket = new Socket("localhost", 5000);
            System.out.println("Je suis un client connecté !");

            // 2. Préparation des flux d'E/S (Entrée/Sortie)
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in);

            // 3. Saisie de l'opération par l'utilisateur
            System.out.print("Entrez l'opération complète (ex: 55 * 25) : ");
            String operation = scanner.nextLine();

            // 4. Validation de la syntaxe avant envoi (Empêche l'envoi de chaînes incorrectes)
            // Regex : nombre espaces opérateur espaces nombre
            String regex = "\\s*[-+]?\\d+(\\.\\d+)?\\s+[+\\-*/]\\s+[-+]?\\d+(\\.\\d+)?\\s*";

            if (!operation.matches(regex)) {
                System.out.println("Erreur de syntaxe. Utilisez le format 'nombre opérateur nombre' (ex: 25 ** 55 est invalide).");
            } else {
                // 5. Envoi de l'opération au serveur
                out.println(operation);

                // 6. Lecture et affichage du résultat reçu
                String resultat = in.readLine();
                System.out.println("Résultat reçu du serveur : " + resultat);
            }

            // 7. Fermeture des ressources
            scanner.close();
            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}