🔍 Moteur de Recherche : Algorithmes TF-IDF et BM25
Ce projet Java implémente un cœur de moteur de recherche capable d'analyser un corpus de documents et de classer ces derniers en fonction de leur pertinence par rapport à une requête utilisateur.

🚀 Concept du Projet
L'objectif est de transformer du texte brut en données statistiques exploitables pour déterminer l'importance d'un mot dans un document par rapport à l'ensemble du corpus. Le projet compare deux approches classiques :

TF-IDF (Term Frequency-Inverse Document Frequency) : Évalue l'importance d'un mot selon sa fréquence locale et sa rareté globale.

Okapi BM25 : Une fonction de classement plus sophistiquée qui améliore TF-IDF en gérant mieux la saturation de la fréquence des termes et la longueur des documents.

🛠️ Architecture du Code
Le projet est structuré de manière modulaire :

Gestion des données : * Document.java & Corpus.java : Modélisation des unités de texte et de la collection complète.

Vocabulary.java : Extraction et stockage de l'ensemble des termes uniques.

Analyse Statistique :

Mot.java, TailleMot.java, TailleDocument.java : Objets utilitaires pour stocker les fréquences et les métadonnées.

Algorithmes de Ranking :

TfIdf.java : Calcul du score basé sur le produit de la fréquence du terme et l'inverse de la fréquence en document.

Bm25.java : Implémentation de la formule probabiliste BM25.

C'est un projet très solide pour une Licence Informatique ! On touche ici aux fondements du Traitement du Langage Naturel (NLP) et des Systèmes de Recherche d'Information (Information Retrieval).Ton code implémente les deux algorithmes les plus célèbres pour classer des documents par pertinence : TF-IDF et son évolution plus robuste, Okapi BM25.🔍 Moteur de Recherche : Algorithmes TF-IDF et BM25Ce projet Java implémente un cœur de moteur de recherche capable d'analyser un corpus de documents et de classer ces derniers en fonction de leur pertinence par rapport à une requête utilisateur.🚀 Concept du ProjetL'objectif est de transformer du texte brut en données statistiques exploitables pour déterminer l'importance d'un mot dans un document par rapport à l'ensemble du corpus. Le projet compare deux approches classiques :TF-IDF (Term Frequency-Inverse Document Frequency) : Évalue l'importance d'un mot selon sa fréquence locale et sa rareté globale.Okapi BM25 : Une fonction de classement plus sophistiquée qui améliore TF-IDF en gérant mieux la saturation de la fréquence des termes et la longueur des documents.🛠️ Architecture du CodeLe projet est structuré de manière modulaire :Gestion des données : * Document.java & Corpus.java : Modélisation des unités de texte et de la collection complète.Vocabulary.java : Extraction et stockage de l'ensemble des termes uniques.Analyse Statistique :Mot.java, TailleMot.java, TailleDocument.java : Objets utilitaires pour stocker les fréquences et les métadonnées.Algorithmes de Ranking :TfIdf.java : Calcul du score basé sur le produit de la fréquence du terme et l'inverse de la fréquence en document.Bm25.java : Implémentation de la formule probabiliste BM25.📐 Modèle MathématiqueL'implémentation du score BM25 repose sur la formule suivante :$$score(D, Q) = \sum_{q_i \in Q} IDF(q_i) \cdot \frac{f(q_i, D) \cdot (k_1 + 1)}{f(q_i, D) + k_1 \cdot (1 - b + b \cdot \frac{|D|}{avgdl})}$$Où $|D|$ est la longueur du document et $avgdl$ la longueur moyenne des documents dans le corpus.

📊 Fonctionnalités
Indexation : Lecture et analyse de jeux de données (via DataSets.java).

Prétraitement : Nettoyage et extraction du vocabulaire.

Recherche : Calcul de pertinence pour une requête donnée.

Évaluation : Comparaison des résultats entre les modèles via Test.java.

💻 Installation et Test
Compiler les fichiers sources :

javac *.java
Lancer les tests de performance :

java Test
