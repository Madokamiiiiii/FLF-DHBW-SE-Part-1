## Transkript / Erklärung zum Sequenzdiagramm der Komplexaufgabe 1

### Lösungsansatz

Es wurde lediglich ein Sequenzdiagramm erstellt; dieses bildet allerdings beide Use-cases aus den Anforderungen ab.

Dies wurde über alt-Blöcke realisiert, das gesamte Sequenzdiagramm ist somit ein großer alt-Block und bildet ab:

1. Use-Case 1: Fahrer und Operator betreten das Fahrzeug (oberer Teilblock)
2. Use-Case 2: Fahrer und Operator verlassen das Fahrzeug (unterer Teilblock)

### Weitere alt-Blöcke bzw. Verzweigungen

- Use-Case 1 stellt lediglich das Einsteigen dar; die zusätzliche Verzweigung bildet die Fallunterscheidung "ID-String
  ist gültig" / "ID-String ist nicht gültig" ab.
    - Ist der String ungültig, vermerkt die Central Unit intern ein false und gemäß Spezifikationen geschieht sonst
      nichts.
- Use-Case 2 beherbergt die gleiche Fallunterscheidung, nur eben für das Sperren der Türen
    - Ist der String gültig, gibt es eine weitere Fallunterscheidung für die die Türen betreffende Aktion: sind diese
      noch offen, werden sie geschlossen, bevor sie gesperrt werden

Das sind die 3 Verschachtelungstiefen, die das möglicherweise etwas verwirrende Sequenzdiagramm besitzt; ich hoffe,
dieser Text kann bestehende Unklarheiten beseitigen.