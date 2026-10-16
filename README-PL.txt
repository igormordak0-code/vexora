VEXORA Client GUI — Minecraft Java 1.21.4 / Fabric

Co zawiera:
- GUI w czarno-fioletowym stylu
- otwieranie/zamykanie klawiszem Right Shift
- kategorie i przyciski demonstracyjne
- brak Kill Aury, cheatów i zmian mechaniki gry

Jak zbudować JAR:
1. Zainstaluj Java JDK 21.
2. Otwórz folder projektu w Eksploratorze Windows.
3. Kliknij pasek adresu, wpisz cmd i naciśnij Enter.
4. Uruchom: gradlew.bat build
5. Po udanej kompilacji JAR będzie w build\libs\
6. Wrzuć JAR do %appdata%\.minecraft\mods i uruchom profil Fabric 1.21.4.

Uwaga: projekt nie jest gotowym skompilowanym JAR-em. Kompilacja wymaga internetu,
żeby Gradle pobrał Gradle distribution i biblioteki Minecraft/Fabric.
