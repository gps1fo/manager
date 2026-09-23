# État de la synchronisation amont

- **Dépôt amont** : `https://github.com/traccar/traccar-manager`
- **Tag amont courant** : `v6.1.1`
- **Date du dernier sync** : `AAAA-MM-JJ`
- **Stratégie d'intégration** : `merge`  ← décision stable, ne pas changer au cas par cas
- **Écart toléré** : 2 versions mineures maximum

## Décisions locales

**`pubspec.lock` est versionné ici, alors que l'amont l'ignore** (`.gitignore`, ligne 35). Sans
lui, chaque construction résout plus d'une centaine de paquets à neuf : le build n'est pas
reproductible et peut casser sans qu'une ligne change. Mesuré le 23/09/2026 — le manager amont,
intact, ne construisait plus sur Android à cause d'une plateforme SDK réclamée par un greffon
transitif. Le fichier est suivi par `git add -f`, ce qui **ne touche pas** au `.gitignore` amont
et n'entre pas au budget de divergence : c'est un fichier ajouté.

Régénérer après toute modification de `pubspec.yaml`, avec la version de Flutter épinglée dans
`compose.dev.yaml` (ancre `x-flutter-image`), et jamais avec une autre.

## Conflits récurrents et résolution habituelle

| Fichier | Nature du conflit | Résolution |
|---------|-------------------|------------|

## Historique des synchronisations

| Date | Tag amont | Durée | Patchs en conflit | Patchs supprimés | Notes |
|------|-----------|-------|-------------------|------------------|-------|
