<<<<<<< HEAD
TP2 - Activité 2-2 : Échange d'objets entre Client/Serveur via les Sockets TCP

Description
Cette activité étend l'application pour permettre l'échange d'objets sérialisables (au lieu de chaînes de caractères).
Le client crée un objet `Operation` (implémentant `Serializable`), l'envoie au serveur. Le serveur reçoit l'objet, extrait les données, effectue le calcul et renvoie le résultat.

Structure ajoutée
- `src/communPackage/Operation.java` : Classe partagée implémentant `Serializable`.
- `src/serverPackage/ServerObjet.java` : Serveur utilisant `ObjectInputStream` et `ObjectOutputStream`.
- `src/clientPackage/ClientObjet.java` : Client créant et envoyant l'objet `Operation`.

Comment exécuter
1. Lancer `ServerObjet.java` en premier.
2. Lancer `ClientObjet.java`.

Captures d'écran

console du server 

![img.png](img.png)

console du client 

![img_1.png](img_1.png)
=======
 TP2 - Activité 2-1 : Échange de CC entre Client/Serveur via les Sockets TCP

Description
Cette activité étend l'application Client/Serveur du TP1 pour permettre l'échange de Chaînes de Caractères (CC) complètes au lieu d'un seul octet. Le client envoie une opération mathématique entière (ex: `55 * 25`), le serveur la calcule et renvoie le résultat.

Structure du projet
- `src/serverPackage/Server.java` : Application serveur qui écoute, lit l'opération, calcule et renvoie le résultat.
- `src/clientPackage/Client.java` : Application client qui demande une opération, valide la syntaxe, l'envoie et affiche le résultat.

Comment exécuter
1. Lancer `Server.java` en premier.
2. Lancer `Client.java`.
3. Saisir une opération dans la console du client (ex: `55 * 25`).

Captures d'écran

server 

<img width="483" height="118" alt="image" src="https://github.com/user-attachments/assets/c6db2ea4-4928-4ed8-bab4-c5393dc49bd9" />

client 

<img width="474" height="103" alt="image" src="https://github.com/user-attachments/assets/0480c58b-0ede-4144-b562-f777100b82dd" />

