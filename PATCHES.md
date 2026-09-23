# Registre des patchs Zone A

Un patch = une modification d'un fichier amont. Chaque patch doit pouvoir répondre à la
question : **« comment le supprimer un jour ? »**

Revue trimestrielle obligatoire. Indicateur de santé : le nombre de patchs supprimés par
trimestre doit être ≥ au nombre de patchs ajoutés.

| ID | Motif | Fichiers amont touchés | Remplaçable par un point d'extension ? | Proposable en PR amont ? | Ajouté le |
|----|-------|------------------------|----------------------------------------|--------------------------|-----------|
| P-001 | Déclarer le flavor `gps1fo` qui porte notre identité de paquet. Tout le contenu de marque vit dans `src/gps1fo/`, en fichiers ajoutés. | `android/app/build.gradle.kts` | Non : un product flavor ne se déclare nulle part ailleurs que dans le script Gradle du module. | Non : propre à notre marque. | 2026-09-23 |
| P-003 | `permission_handler_android` déclare `compileSdk = 37`, qu'AGP traduit par la plateforme `android-37` — jamais publiée par Google, qui ne propose que `android-37.0/.1/.2`. Redirection vers la plateforme réellement installée. Le rabaissement à 36 a été essayé et échoue : le greffon utilise de vrais symboles de l'API 37. | `android/build.gradle.kts` | Non : la correspondance doit être faite avant l'évaluation des sous-projets. | Sans objet : le défaut est chez `baseflow` et chez AGP, pas chez Traccar. Le manager **amont intact** échoue à l'identique (mesuré le 2026-09-23). | 2026-09-23 |
| P-002 | Trois chaînes en dur : le schéma de rappel OAuth (2 occurrences, lu aussi par le manifeste) et le serveur par défaut, qui portait `demo.traccar.org` et enverrait nos clients chez un tiers. | `lib/main_screen.dart` | Partiellement : le serveur par défaut oui, si l'amont accepte un `String.fromEnvironment`. Le schéma, non : il doit s'accorder avec le manifeste. | Oui pour le serveur par défaut. Le schéma est propre à notre marque. | 2026-09-23 |
