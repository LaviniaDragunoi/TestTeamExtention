# TestTeamExtention

In activity_main.xml layout am implementat buttonul Get Transactions. La click pe buton este chemata metoda 
loadList() care urmeaza sa preia lista produselor si sa o distribuie in recyclerView-ul pozitionat sub buttonul Get Transactions.
Pentru networking am folosit Retrofit si Gson, librarii ce au fost declarate in build.gradel(Module:app).
In ApiInterface.java sunt create specificatiile endpoint-urilor pentru fiecare informatie pe care dorim sa o preluam din Json.
In ApiCLient.java se construieste url-ul,la care urmeaza sa fie atasat si endpoint-ul si trimis pentru networking
request de catre Retrofit. Daca raspunsul este unul cu succes atunci acest rezultat primit va fi afisat in recyclerView
sub buttonul mai sus mentionat.( Aici ar fi de adaugat new Thread pentru a muta toata actiunea networking-ului de pe MainThread, 
deoarece astfel putem ajunge la ANR).

# Librarii
* [Retrofit2](https://github.com/square/retrofit)
* [Butterknife](https://github.com/JakeWharton/butterknife)

